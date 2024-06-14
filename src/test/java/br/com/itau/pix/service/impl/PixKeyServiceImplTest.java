package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyLogDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.service.PixKeyLogService;
import br.com.itau.pix.validation.chain.DeletePixKeyValidationChain;
import br.com.itau.pix.validation.chain.GetPixKeyValidationChain;
import br.com.itau.pix.validation.chain.SavePixKeyValidationChain;
import br.com.itau.pix.validation.chain.UpdatePixKeyValidationChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PixKeyServiceImplTest {

    @Mock
    private SavePixKeyValidationChain saveValidationChain;

    @Mock
    private UpdatePixKeyValidationChain updateValidationChain;

    @Mock
    private DeletePixKeyValidationChain deleteValidationChain;

    @Mock
    private GetPixKeyValidationChain getValidationChain;

    @Mock
    private PixKeyRepository repository;

    @Mock
    private PixKeyLogService logService;

    @InjectMocks
    private PixKeyServiceImpl pixKeyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePixKey() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyValue("some-key-value");
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(123);
        request.setAccountNumber(342343);

        PixKeyDTO pixKeyDTO = new PixKeyDTO();

        when(repository.save(any(PixKeyDTO.class))).thenReturn(pixKeyDTO);

        PixKeyResponsePostDTO response = pixKeyService.savePixKey(request);

        verify(saveValidationChain, times(1)).validate(any(SupportSavePixKeyDTO.class));
        verify(logService, times(1)).saveLogAsync(any(PixKeyLogDTO.class));
        verify(repository, times(1)).save(any(PixKeyDTO.class));

        assertNotNull(response);
    }

    @Test
    public void testGetByParams() {
        HashMap<String, Object> params = new HashMap<>();
        int page = 0;
        int size = 10;
        List<PixKeyDTO> pixKeyList = Collections.singletonList(new PixKeyDTO());
        Page<PixKeyDTO> pixKeyPage = new PageImpl<>(pixKeyList);
        
        when(repository.findByCustomCriteria(any(Map.class), any(Pageable.class))).thenReturn(pixKeyPage);

        params.put("agencyNumber", "123");
        params.put("accountNumber", "123");
        Page<PixKeyResponseGetDTO> response = pixKeyService.getByParams(params, page, size);
        
        verify(getValidationChain, times(1)).validate(any(SupportGetPixKeyDTO.class));
        verify(repository, times(1)).findByCustomCriteria(any(Map.class), any(Pageable.class));
        
        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    public void testUpdatePixKey() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setId("some-id");
        request.setKeyValue("updated-key-value");
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(123);
        request.setAccountNumber(342343);

        PixKeyDTO pixKeyDTO = new PixKeyDTO();

        when(repository.findById(any(String.class))).thenReturn(Optional.of(pixKeyDTO));
        when(repository.save(any(PixKeyDTO.class))).thenReturn(pixKeyDTO);

        PixKeyResponsePatchDTO response = pixKeyService.update(request);

        verify(updateValidationChain, times(1)).validate(any(SupportUpdatePixKeyDTO.class));
        verify(logService, times(1)).saveLogAsync(any(PixKeyLogDTO.class));
        verify(repository, times(1)).save(any(PixKeyDTO.class));

        assertNotNull(response);
    }

    @Test
    public void testDeletePixKey() {
        String pixKeyId = "test-id";
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setTimestampExclusion("2023-01-01T00:00:00");

        when(repository.findById(any(String.class))).thenReturn(Optional.of(pixKeyDTO));
        when(repository.save(any(PixKeyDTO.class))).thenReturn(pixKeyDTO);

        PixKeyResponseDeleteDTO response = pixKeyService.deletePixKey(pixKeyId);

        verify(deleteValidationChain, times(1)).validate(any(SupportDeletePixKeyDTO.class));
        verify(logService, times(1)).saveLogAsync(any(PixKeyLogDTO.class));
        verify(repository, times(1)).save(any(PixKeyDTO.class));

        assertNotNull(response);
    }

    @Test
    public void testFindById_NotFound() {
        String pixKeyId = "invalid-id";
        
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            pixKeyService.deletePixKey(pixKeyId);
        });
        
        assertEquals("Id Invalido", exception.getMessage());
    }
}
