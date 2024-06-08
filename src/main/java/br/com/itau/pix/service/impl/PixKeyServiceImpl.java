package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.validator.PixKeyValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PixKeyServiceImpl implements PixKeyService {

    private PixKeyValidator pixKeyValidator;

    @Override
    public UUID savePixKey(PixKeyDTO pixKeyDTO) {

//        String keyType = pixKeyValidator.getKeyType(pixKeyDTO.getKeyValue());
//        pixKeyDTO.setKeyType(keyType);

        UUID id = UUID.randomUUID();

        // save

        return null;
    }
}
