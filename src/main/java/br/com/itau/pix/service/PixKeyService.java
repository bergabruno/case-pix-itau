package br.com.itau.pix.service;

import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public interface PixKeyService {

    PixKeyResponsePostDTO savePixKey(PixKeyRequestBodyDTO requestPostDTO);

    Page<PixKeyResponseGetDTO> getByParams(HashMap<String, Object> params, Integer page, Integer size);

    PixKeyResponsePatchDTO update(PixKeyRequestBodyDTO requestPostDTO);

    PixKeyResponseDeleteDTO deletePixKey(String pixKeyId);
}
