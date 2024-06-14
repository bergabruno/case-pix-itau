package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.HistoryDTO;
import br.com.itau.pix.dto.model.PixKeyLogDTO;
import br.com.itau.pix.repository.PixKeyLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PixKeyLogServiceImplTest {

    @Mock
    private PixKeyLogRepository repository;

    @InjectMocks
    private PixKeyLogServiceImpl pixKeyLogService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveLogAsync_NewLog() {
        PixKeyLogDTO pixKeyLogDTO = new PixKeyLogDTO();
        pixKeyLogDTO.setKeyValueId("keyValueId");
        
        when(repository.findByKeyValueId(any(String.class))).thenReturn(Optional.empty());
        
        pixKeyLogService.saveLogAsync(pixKeyLogDTO);
        
        verify(repository, times(1)).save(pixKeyLogDTO);
    }

    @Test
    public void testSaveLogAsync_UpdateLog() {
        PixKeyLogDTO existingPixKeyLogDTO = new PixKeyLogDTO();
        existingPixKeyLogDTO.setKeyValueId("keyValueId");
        PixKeyLogDTO newPixKeyLogDTO = new PixKeyLogDTO();
        newPixKeyLogDTO.setKeyValueId("keyValueId");
        newPixKeyLogDTO.addHistory(new HistoryDTO("","",""));
        
        when(repository.findByKeyValueId(any(String.class))).thenReturn(Optional.of(existingPixKeyLogDTO));
        
        pixKeyLogService.saveLogAsync(newPixKeyLogDTO);
        
        verify(repository, times(1)).save(existingPixKeyLogDTO);
        assert(!existingPixKeyLogDTO.getHistoryDTOs().isEmpty());
    }

    @Test
    public void testSaveLogAsync_ExceptionHandling() {
        PixKeyLogDTO pixKeyLogDTO = new PixKeyLogDTO();
        pixKeyLogDTO.setKeyValueId("keyValueId");
        
        doThrow(new RuntimeException("Test exception")).when(repository).save(any(PixKeyLogDTO.class));
        
        pixKeyLogService.saveLogAsync(pixKeyLogDTO);
        
        verify(repository, times(1)).save(pixKeyLogDTO);
    }
}
