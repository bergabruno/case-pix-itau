package br.com.itau.pix.service;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import org.springframework.stereotype.Service;


@Service
public interface PixKeyService {

    PixKeyDTO savePixKey(PixKeyRequestPostDTO requestDTO);

    PixKeyResponseDeleteDTO deletePixKey(String pixKeyId);
}
