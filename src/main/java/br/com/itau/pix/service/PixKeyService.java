package br.com.itau.pix.service;

import br.com.itau.pix.dto.model.PixKeyDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PixKeyService {

    UUID savePixKey(PixKeyDTO pixKeyDTO);
}
