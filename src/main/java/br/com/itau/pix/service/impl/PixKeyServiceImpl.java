package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.service.PixKeyValueService;
import br.com.itau.pix.validator.PixKeyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PixKeyServiceImpl implements PixKeyService {

    private final PixKeyValidator pixKeyValidator;

    private final PixKeyRepository pixKeyRepository;

    private final PixKeyValueService pixKeyValueService;

    @Override
    public PixKeyDTO savePixKey(PixKeyRequestPostDTO requestDTO) {
        log.info("Save pix key: {}", requestDTO);
        // Validação da chave PIX
        pixKeyValidator.validate(requestDTO.getKeyType(), requestDTO.getKeyValue());

        // Salvando o valor da chave PIX
        PixKeyValueDTO pixKeyValueDTO = pixKeyValueService.save(new PixKeyValueDTO(requestDTO));

        // Criando o DTO da chave PIX
        PixKeyDTO pixKeyDTO = new PixKeyDTO(requestDTO, pixKeyValueDTO);

        // Verificando se a chave PIX já existe no banco de dados
        pixKeyDTO = findExistingPixKey(pixKeyDTO);

        // Adicionando o ID do valor da chave PIX
        pixKeyDTO.addPixKeyValueId(pixKeyValueDTO.getId());

        // Salvando a chave PIX no repositório
        return pixKeyRepository.save(pixKeyDTO);
    }

    @Override
    public PixKeyResponseDeleteDTO deletePixKey(String pixKeyId) {
        PixKeyValueDTO pixKeyValueDTO = pixKeyValueService.exclusionPixKeyValue(pixKeyId);

        PixKeyDTO pixKeyDTO = pixKeyRepository.findByAccountCombination(pixKeyValueDTO.getAccountCombinationInclusion()).orElseThrow(() -> new ResourceNotFoundException("A chave foi desativada, porem nao foi encontrado uma conta ativa"));
        
        return new PixKeyResponseDeleteDTO(pixKeyDTO, pixKeyValueDTO);
    }

    private PixKeyDTO findExistingPixKey(PixKeyDTO pixKeyDTO) {
        Optional<PixKeyDTO> pixKeyDTOOptional = pixKeyRepository.findByAccountCombination(pixKeyDTO.getAccountCombination());
        return pixKeyDTOOptional.orElse(pixKeyDTO);
    }
}
