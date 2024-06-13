package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PixKeyRepositoryCustom {

    Page<PixKeyDTO> findByCustomCriteria(Map<String, Object> criteria, Pageable pageable);
}
