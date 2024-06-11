package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPatchDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.service.PixKeyValueService;
import br.com.itau.pix.validator.PixKeyValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
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

        pixKeyValidator.validate(requestDTO.getKeyType(), requestDTO.getKeyValue());

        PixKeyValueDTO pixKeyValueDTO = pixKeyValueService.save(new PixKeyValueDTO(requestDTO));

        PixKeyDTO pixKeyDTO = new PixKeyDTO(requestDTO, pixKeyValueDTO);

        pixKeyDTO = findExistingPixKey(pixKeyDTO);

        pixKeyDTO.addPixKeyValueId(pixKeyValueDTO.getId());

        return pixKeyRepository.save(pixKeyDTO);
    }

    @Override
    public PixKeyResponseGetDTO getByParam(Map<String, Object> params) {
        log.info("Get pix key by params: {}", params);
        return null;
    }

    @Override
    public PixKeyResponsePatchDTO update(PixKeyRequestPatchDTO pixKeyRequestPatchDTO) {
        pixKeyValidator.validate(pixKeyRequestPatchDTO.getKeyType(), pixKeyRequestPatchDTO.getKeyValue());

        PixKeyValueDTO pixKeyValueDTO = new PixKeyValueDTO(pixKeyRequestPatchDTO);

        pixKeyValueDTO = pixKeyValueService.updateKeyValue(pixKeyRequestPatchDTO.getId(),pixKeyValueDTO);

        return new PixKeyResponsePatchDTO(pixKeyValueDTO, pixKeyRequestPatchDTO);
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
