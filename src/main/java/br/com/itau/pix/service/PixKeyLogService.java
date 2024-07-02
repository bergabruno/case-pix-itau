package br.com.itau.pix.service;

import br.com.itau.pix.dto.model.PixKeyLogDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface PixKeyLogService {

    @Async
    void saveLogAsync(PixKeyLogDTO pixKeyLogDTO);

}
