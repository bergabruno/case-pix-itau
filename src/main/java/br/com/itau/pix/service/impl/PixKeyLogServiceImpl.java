package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyLogDTO;
import br.com.itau.pix.repository.PixKeyLogRepository;
import br.com.itau.pix.service.PixKeyLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class PixKeyLogServiceImpl implements PixKeyLogService {

    private final PixKeyLogRepository repository;

    @Override
    public void saveLogAsync(PixKeyLogDTO pixKeyLogDTO) {
        try{

            PixKeyLogDTO pixKeyLogDTOOptional = repository.findByKeyValueId(pixKeyLogDTO.getKeyValueId()).orElse(null);

            if(pixKeyLogDTOOptional != null) {
                pixKeyLogDTOOptional.addHistory(pixKeyLogDTO.getLastHistory());
                pixKeyLogDTO = pixKeyLogDTOOptional;
            }

            log.info("Envio de log: {}", pixKeyLogDTO.getId());
            repository.save(pixKeyLogDTO);
        }catch (Exception e){
            log.info("Occours an erro to send pixKeyLog ->" + e.getMessage());
        }
    }
}
