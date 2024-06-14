package br.com.itau.pix.dto.support;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SupportSavePixKeyDTOTest {

    @Test
    public void testAllArgsConstructorAndGetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");

        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = new PixKeyRequestBodyDTO();
        pixKeyRequestBodyDTO.setKeyType("EMAIL");
        pixKeyRequestBodyDTO.setKeyValue("john.doe@example.com");

        List<PixKeyDTO> pixKeyDTOs = new ArrayList<>();
        pixKeyDTOs.add(pixKeyDTO);

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(pixKeyDTO, pixKeyRequestBodyDTO, pixKeyDTOs);

        assertEquals(pixKeyDTO, supportSavePixKeyDTO.getPixKeyDTO());
        assertEquals(pixKeyRequestBodyDTO, supportSavePixKeyDTO.getPixKeyRequestBodyDTO());
        assertEquals(pixKeyDTOs, supportSavePixKeyDTO.getPixKeyDTOs());
    }

    @Test
    public void testSetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("5678");
        pixKeyDTO.setKeyType("PHONE");
        pixKeyDTO.setKeyValue("+123456789");

        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = new PixKeyRequestBodyDTO();
        pixKeyRequestBodyDTO.setKeyType("PHONE");
        pixKeyRequestBodyDTO.setKeyValue("+123456789");

        List<PixKeyDTO> pixKeyDTOs = new ArrayList<>();
        pixKeyDTOs.add(pixKeyDTO);

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO();
        supportSavePixKeyDTO.setPixKeyDTO(pixKeyDTO);
        supportSavePixKeyDTO.setPixKeyRequestBodyDTO(pixKeyRequestBodyDTO);
        supportSavePixKeyDTO.setPixKeyDTOs(pixKeyDTOs);

        assertEquals(pixKeyDTO, supportSavePixKeyDTO.getPixKeyDTO());
        assertEquals(pixKeyRequestBodyDTO, supportSavePixKeyDTO.getPixKeyRequestBodyDTO());
        assertEquals(pixKeyDTOs, supportSavePixKeyDTO.getPixKeyDTOs());
    }
}
