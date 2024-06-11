package br.com.itau.pix.service;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPatchDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface PixKeyService {

    PixKeyDTO savePixKey(PixKeyRequestPostDTO pixKeyRequestPostDTO);

    PixKeyResponseGetDTO getByParam(Map<String, Object> params);

    PixKeyResponsePatchDTO update(PixKeyRequestPatchDTO requestDTO);

    PixKeyResponseDeleteDTO deletePixKey(String pixKeyId);
}
