package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.dto.request.PixKeyRequestDTO;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.DuplicateKeyException;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.repository.PixKeyValueRepository;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.service.PixKeyValueService;
import br.com.itau.pix.util.DateFormatUtil;
import br.com.itau.pix.validator.PixKeyValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PixKeyValueServiceImpl implements PixKeyValueService {

    private PixKeyValueRepository pixKeyValueRepository;


    @Override
    public PixKeyValueDTO save(PixKeyValueDTO pixKeyValueDTO) {
        //TODO FAZER A VALIDACAO POR QUANTIDADE DE CHAVES CADASTRADAS - SE FOR CPF -> 5, SE FOR CNPJ -> 20

        Optional<PixKeyValueDTO> existingKeyValue = pixKeyValueRepository.findByKeyValue(pixKeyValueDTO.getKeyValue());

        if (existingKeyValue.isPresent()) {
            PixKeyValueDTO keyValueDTO = existingKeyValue.get();
            if (keyValueDTO.getAccountHolderInclusion().equalsIgnoreCase(pixKeyValueDTO.getAccountHolderInclusion())) {
                throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em sua conta");
            } else {
                throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em outra conta.");
            }
        }

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

        pixKeyValueDTO.setStatus(StatusEnum.INACTIVE);
        pixKeyValueDTO.setTimestampExclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        return pixKeyValueRepository.save(pixKeyValueDTO);
    }
}
