package br.com.itau.pix.service;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PixKeyService {

    PixKeyDTO savePixKey(PixKeyRequestDTO requestDTO);
}
