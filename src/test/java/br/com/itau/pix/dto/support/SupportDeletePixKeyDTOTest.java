package br.com.itau.pix.dto.support;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SupportDeletePixKeyDTOTest {

    @Test
    public void testAllArgsConstructorAndGetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");

        SupportDeletePixKeyDTO supportDeletePixKeyDTO = new SupportDeletePixKeyDTO(pixKeyDTO);

        assertEquals(pixKeyDTO, supportDeletePixKeyDTO.getPixKeyDTO());
        assertEquals("1234", supportDeletePixKeyDTO.getPixKeyDTO().getId());
        assertEquals("EMAIL", supportDeletePixKeyDTO.getPixKeyDTO().getKeyType());
        assertEquals("john.doe@example.com", supportDeletePixKeyDTO.getPixKeyDTO().getKeyValue());
    }

    @Test
    public void testSetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("5678");
        pixKeyDTO.setKeyType("PHONE");
        pixKeyDTO.setKeyValue("+123456789");

        SupportDeletePixKeyDTO supportDeletePixKeyDTO = new SupportDeletePixKeyDTO();
        supportDeletePixKeyDTO.setPixKeyDTO(pixKeyDTO);

        assertEquals(pixKeyDTO, supportDeletePixKeyDTO.getPixKeyDTO());
        assertEquals("5678", supportDeletePixKeyDTO.getPixKeyDTO().getId());
        assertEquals("PHONE", supportDeletePixKeyDTO.getPixKeyDTO().getKeyType());
        assertEquals("+123456789", supportDeletePixKeyDTO.getPixKeyDTO().getKeyValue());
    }
}
