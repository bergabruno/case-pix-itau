package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.enumerators.PersonType;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.AlreadyInactiveException;
import br.com.itau.pix.exception.DuplicateKeyException;
import br.com.itau.pix.exception.InvalidKeyException;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.repository.PixKeyValueRepository;
import br.com.itau.pix.service.PixKeyValueService;
import br.com.itau.pix.util.DateFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PixKeyValueServiceImpl implements PixKeyValueService {

    private PixKeyValueRepository pixKeyValueRepository;

    @Override
    public PixKeyValueDTO save(PixKeyValueDTO pixKeyValueDTO) {
        validateDuplicateKey(pixKeyValueDTO);

        // Contar chaves existentes para a conta
        List<PixKeyValueDTO> existingKeys = pixKeyValueRepository.findByAccountCombinationInclusion(pixKeyValueDTO.getAccountCombinationInclusion());
        List<PixKeyValueDTO> activeKeys = existingKeys.stream()
                .filter(key -> key.getStatus() == StatusEnum.ACTIVE)
                .toList();

        validateKeyCount(pixKeyValueDTO, activeKeys);

        pixKeyValueDTO.setId(UUID.randomUUID().toString());
        pixKeyValueDTO.setTimestampInclusion(DateFormatUtil.formatToString(LocalDateTime.now()));
        pixKeyValueDTO.setStatus(StatusEnum.ACTIVE);

        return pixKeyValueRepository.save(pixKeyValueDTO);
    }

    @Override
    public PixKeyValueDTO findById(String id) {
        return pixKeyValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O id informado nao existe no banco de dados."));
    }

    @Override
    public PixKeyValueDTO update(String id, PixKeyValueDTO pixKeyValueDTO) {
        //TODO fazer a alteracao
        return null;
    }

    @Override
    public PixKeyValueDTO exclusionPixKeyValue(String id) {
        PixKeyValueDTO pixKeyValueDTO = this.findById(id);

        if(StatusEnum.INACTIVE == pixKeyValueDTO.getStatus()){
            throw new AlreadyInactiveException("A chave ja se encontra desativada!");
        }

        pixKeyValueDTO.setStatus(StatusEnum.INACTIVE);
        pixKeyValueDTO.setTimestampExclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        return pixKeyValueRepository.save(pixKeyValueDTO);
    }


    private void validateDuplicateKey(PixKeyValueDTO pixKeyValueDTO) {
        Optional<PixKeyValueDTO> existingKeyValue = pixKeyValueRepository.findByKeyValue(pixKeyValueDTO.getKeyValue());

        if (existingKeyValue.isPresent()) {
            PixKeyValueDTO keyValueDTO = existingKeyValue.get();
            if (keyValueDTO.getAccountCombinationInclusion().equalsIgnoreCase(pixKeyValueDTO.getAccountCombinationInclusion())) {
                throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em sua conta");
            } else {
                throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em outra conta.");
            }
        }
    }

    private void validateKeyCount(PixKeyValueDTO pixKeyValueDTO, List<PixKeyValueDTO> activeKeys) {
        PersonType personType = getPersonType(activeKeys);

        if (personType == PersonType.UNDEFINED && activeKeys.size() == 4) {
            if (!pixKeyValueDTO.getKeyType().equalsIgnoreCase("CPF") && !pixKeyValueDTO.getKeyType().equalsIgnoreCase("CNPJ")) {
                throw new InvalidKeyException("A próxima chave cadastrada deve ser um CPF ou CNPJ.");
            }
        }

        if (personType == PersonType.FISICA && activeKeys.size() >= 5) {
            throw new InvalidKeyException("Você não pode cadastrar mais de 5 chaves para pessoa física.");
        }

        if (personType == PersonType.JURIDICA && activeKeys.size() >= 20) {
            throw new InvalidKeyException("Você não pode cadastrar mais de 20 chaves para pessoa jurídica.");
        }
    }

    private PersonType getPersonType(List<PixKeyValueDTO> activeKeys) {
        boolean isPessoaFisica = activeKeys.stream().anyMatch(key -> key.getKeyType().equalsIgnoreCase("CPF"));
        boolean isPessoaJuridica = activeKeys.stream().anyMatch(key -> key.getKeyType().equalsIgnoreCase("CNPJ"));

        if (isPessoaFisica) {
            return PersonType.FISICA;
        } else if (isPessoaJuridica) {
            return PersonType.JURIDICA;
        } else {
            return PersonType.UNDEFINED;
        }
    }
}
