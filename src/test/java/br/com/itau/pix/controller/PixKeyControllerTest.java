package br.com.itau.pix.controller;

import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.*;
import br.com.itau.pix.service.PixKeyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PixKeyControllerTest {

    @InjectMocks
    private PixKeyController pixKeyController;

    @Mock
    private PixKeyService pixKeyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        PixKeyRequestBodyDTO requestBodyDTO = new PixKeyRequestBodyDTO();
        PixKeyResponsePostDTO responsePostDTO = new PixKeyResponsePostDTO();

        when(pixKeyService.savePixKey(any(PixKeyRequestBodyDTO.class))).thenReturn(responsePostDTO);

        ResponseEntity<PixKeyResponsePostDTO> response = pixKeyController.save(requestBodyDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responsePostDTO, response.getBody());
    }

    @Test
    public void testGetByParam() {
        Page<PixKeyResponseGetDTO> page = new PageImpl<>(List.of(new PixKeyResponseGetDTO()));
        HashMap<String, Object> headers = new HashMap<>();

        when(pixKeyService.getByParams(any(HashMap.class), any(int.class), any(int.class)))
                .thenReturn(page);

        ResponseEntity<Page<PixKeyResponseGetDTO>> response = pixKeyController.getByParam(headers, 0, 20);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }

    @Test
    public void testUpdate() {
        PixKeyRequestBodyDTO requestBodyDTO = new PixKeyRequestBodyDTO();
        PixKeyResponsePatchDTO responsePatchDTO = new PixKeyResponsePatchDTO();

        when(pixKeyService.update(any(PixKeyRequestBodyDTO.class))).thenReturn(responsePatchDTO);

        ResponseEntity<PixKeyResponsePatchDTO> response = pixKeyController.update("123", requestBodyDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responsePatchDTO, response.getBody());
    }

    @Test
    public void testDelete() {
        PixKeyResponseDeleteDTO responseDeleteDTO = new PixKeyResponseDeleteDTO();

        when(pixKeyService.deletePixKey(any(String.class))).thenReturn(responseDeleteDTO);

        ResponseEntity<PixKeyResponseDeleteDTO> response = pixKeyController.delete("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDeleteDTO, response.getBody());
    }
}
