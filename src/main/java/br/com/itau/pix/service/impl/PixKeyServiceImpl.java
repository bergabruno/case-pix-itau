package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.dto.request.PixKeyRequestDTO;
import br.com.itau.pix.exception.InvalidKeyException;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.service.PixKeyValueService;
import br.com.itau.pix.validator.PixKeyValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PixKeyServiceImpl implements PixKeyService {

    private final PixKeyValidator pixKeyValidator;

    private final PixKeyRepository pixKeyRepository;

    private final PixKeyValueService pixKeyValueService;

    private final ObjectMapper objectMapper;

    @Override
    public PixKeyDTO savePixKey(PixKeyRequestDTO requestDTO) {
        validateKey(requestDTO);

        PixKeyValueDTO pixKeyValueDTO = new PixKeyValueDTO(requestDTO);
        pixKeyValueDTO = pixKeyValueService.save(pixKeyValueDTO);

        PixKeyDTO pixKeyDTO = objectMapper.convertValue(requestDTO, PixKeyDTO.class);
        pixKeyDTO.addPixKeyValueId(pixKeyValueDTO.getId());
        pixKeyDTO.setId(UUID.randomUUID().toString());

        return pixKeyRepository.save(pixKeyDTO);
    }

    private void validateKey(PixKeyRequestDTO requestDTO) {
        boolean isValidKey = pixKeyValidator.isValidKey(requestDTO.getKeyType(), requestDTO.getKeyValue());
        if (!isValidKey) {
            throw new InvalidKeyException("A chave está errada");
        }
    }
}
