package br.com.itau.pix.service;

import br.com.itau.pix.model.PixKeyRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PixService {

    UUID savePixKey(PixKeyRegistrationDTO pixKeyRegistrationDTO);
}
