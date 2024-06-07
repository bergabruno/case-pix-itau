package br.com.itau.pix.service.impl;

import br.com.itau.pix.model.PixKeyRegistrationDTO;
import br.com.itau.pix.service.PixService;
import br.com.itau.pix.validator.PixKeyValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PixServceImpl implements PixService {

    private PixKeyValidator pixKeyValidator;

    @Override
    public UUID savePixKey(PixKeyRegistrationDTO pixKeyRegistrationDTO) {

        String keyType = pixKeyValidator.getKeyType(pixKeyRegistrationDTO.getKeyValue());
        pixKeyRegistrationDTO.setKeyType(keyType);

        UUID id = UUID.randomUUID();

        // save

        return null;
    }
}
