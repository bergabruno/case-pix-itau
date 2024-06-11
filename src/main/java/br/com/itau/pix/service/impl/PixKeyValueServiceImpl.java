package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.enumerators.PersonType;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.*;
import br.com.itau.pix.repository.PixKeyValueRepository;
import br.com.itau.pix.service.PixKeyValueService;
import br.com.itau.pix.util.DateFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PixKeyValueServiceImpl implements PixKeyValueService {

    private PixKeyValueRepository pixKeyValueRepository;

    @Override
    public PixKeyValueDTO save(PixKeyValueDTO pixKeyValueDTO) {
        List<PixKeyValueDTO> activeKeys = getPixKeysActive(pixKeyValueDTO);

        validateDuplicateKey(pixKeyValueDTO);

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
    public PixKeyValueDTO updateKeyValue(String id, PixKeyValueDTO pixKeyValueDTO) {
        PixKeyValueDTO pixKeyValueFromDB = this.findById(id);

        if (pixKeyValueFromDB.getStatus() == StatusEnum.INACTIVE) {
            throw new AlreadyInactiveException("A chave se encontra desativada, nao eh possivel alterar uma chave desativada.");
        }

        if(!pixKeyValueFromDB.getAccountCombinationInclusion().equalsIgnoreCase(pixKeyValueDTO.getAccountCombinationInclusion())){
            throw new DifferentAccountException("A conta informada nao coincide com a conta cadastrada");
        }

        if (!pixKeyValueFromDB.getKeyType().equalsIgnoreCase(pixKeyValueDTO.getKeyType())) {
            throw new InvalidKeyTypeException("O tipo da chave informada nao coincide com o tipo de chave cadastrada.");
        }

        if(pixKeyValueFromDB.getKeyValue().equalsIgnoreCase(pixKeyValueDTO.getKeyValue())){
            throw new DuplicateKeyException("O valor da chave informada eh o mesmo cadastrado.");
        }

        pixKeyValueFromDB.setKeyValue(pixKeyValueDTO.getKeyValue());
        pixKeyValueFromDB.setTimestampUpdate(DateFormatUtil.formatToString(LocalDateTime.now()));

        return pixKeyValueRepository.save(pixKeyValueFromDB);
    }

    @Override
    public PixKeyValueDTO exclusionPixKeyValue(String id) {
        PixKeyValueDTO pixKeyValueDTO = this.findById(id);

        if (StatusEnum.INACTIVE == pixKeyValueDTO.getStatus()) {
            throw new AlreadyInactiveException("A chave ja se encontra desativada!");
        }

        pixKeyValueDTO.setStatus(StatusEnum.INACTIVE);
        pixKeyValueDTO.setTimestampExclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        return pixKeyValueRepository.save(pixKeyValueDTO);
    }

    private List<PixKeyValueDTO> getPixKeysActive(PixKeyValueDTO pixKeyValueDTO) {
        List<PixKeyValueDTO> existingKeys = pixKeyValueRepository.findByAccountCombinationInclusion(pixKeyValueDTO.getAccountCombinationInclusion());
        return existingKeys.stream()
                .filter(key -> key.getStatus() == StatusEnum.ACTIVE)
                .toList();
    }


    private void validateDuplicateKey(PixKeyValueDTO pixKeyValueDTO) {
        PixKeyValueDTO existingKeyValue = pixKeyValueRepository.findByKeyValue(pixKeyValueDTO.getKeyValue()).orElse(null);

        if(existingKeyValue != null){
            String isInactive = existingKeyValue.getStatus() == StatusEnum.INACTIVE ? "INATIVA" : "ATIVA";
            if (existingKeyValue.getAccountCombinationInclusion().equalsIgnoreCase(pixKeyValueDTO.getAccountCombinationInclusion())) {
                throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em sua conta. O status da chave eh: " + isInactive);
            } else {
                throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em outra conta.");
            }
        }

    }

    private void validateKeyCount(PixKeyValueDTO pixKeyValueDTO, List<PixKeyValueDTO> activeKeys) {
        PersonType personType = getPersonType(activeKeys);

        if (personType == PersonType.UNDEFINED && activeKeys.size() >= 4) {
            if (!pixKeyValueDTO.getKeyType().equalsIgnoreCase("CPF") && !pixKeyValueDTO.getKeyType().equalsIgnoreCase("CNPJ")) {
                throw new InvalidKeyValueException("A próxima chave cadastrada deve ser um CPF ou CNPJ.");
            }
        }

        if (personType == PersonType.FISICA && activeKeys.size() >= 5) {
            throw new InvalidKeyValueException("Você não pode cadastrar mais de 5 chaves para pessoa física.");
        }

        if (personType == PersonType.JURIDICA && activeKeys.size() >= 20) {
            throw new InvalidKeyValueException("Você não pode cadastrar mais de 20 chaves para pessoa jurídica.");
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
