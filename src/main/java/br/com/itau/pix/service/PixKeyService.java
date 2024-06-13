package br.com.itau.pix.service;

import br.com.itau.pix.dto.request.AccountKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface PixKeyService {

    PixKeyResponsePostDTO savePixKey(AccountKeyRequestBodyDTO requestPostDTO);

    Page<PixKeyResponseGetDTO> getByParams(Map<String, Object> params, Integer page, Integer size);

    PixKeyResponsePatchDTO update(AccountKeyRequestBodyDTO requestPostDTO);

    PixKeyResponseDeleteDTO deletePixKey(String pixKeyId);
}
