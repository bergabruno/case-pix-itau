package br.com.itau.pix.service;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyValueDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface PixKeyValueService {

    PixKeyValueDTO save(PixKeyValueDTO pixKeyValueDTO);

    PixKeyValueDTO findById(String id);

    PixKeyValueDTO update(String id, PixKeyValueDTO pixKeyValueDTO);

    PixKeyValueDTO exclusionPixKeyValue(String id);
}
