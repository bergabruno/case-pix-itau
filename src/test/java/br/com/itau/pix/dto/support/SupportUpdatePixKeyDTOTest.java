package br.com.itau.pix.dto.support;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportUpdatePixKeyDTOTest {

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

        PixKeyDTO pixKeyDTOFromValue = new PixKeyDTO();
        pixKeyDTOFromValue.setId("5678");
        pixKeyDTOFromValue.setKeyType("PHONE");
        pixKeyDTOFromValue.setKeyValue("+123456789");

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(pixKeyDTO, pixKeyRequestBodyDTO, pixKeyDTOs, pixKeyDTOFromValue);

        assertEquals(pixKeyDTO, supportUpdatePixKeyDTO.getPixKeyDTO());
        assertEquals(pixKeyRequestBodyDTO, supportUpdatePixKeyDTO.getPixKeyRequestBodyDTO());
        assertEquals(pixKeyDTOs, supportUpdatePixKeyDTO.getPixKeyDTOs());
        assertEquals(pixKeyDTOFromValue, supportUpdatePixKeyDTO.getPixKeyDTOFromValue());
    }

    @Test
    public void testSetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");

        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = new PixKeyRequestBodyDTO();
        pixKeyRequestBodyDTO.setKeyType("EMAIL");
        pixKeyRequestBodyDTO.setKeyValue("john.doe@example.com");

        List<PixKeyDTO> pixKeyDTOs = new ArrayList<>();
        pixKeyDTOs.add(pixKeyDTO);

        PixKeyDTO pixKeyDTOFromValue = new PixKeyDTO();
        pixKeyDTOFromValue.setId("5678");
        pixKeyDTOFromValue.setKeyType("PHONE");
        pixKeyDTOFromValue.setKeyValue("+123456789");

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO();
        supportUpdatePixKeyDTO.setPixKeyDTO(pixKeyDTO);
        supportUpdatePixKeyDTO.setPixKeyRequestBodyDTO(pixKeyRequestBodyDTO);
        supportUpdatePixKeyDTO.setPixKeyDTOs(pixKeyDTOs);
        supportUpdatePixKeyDTO.setPixKeyDTOFromValue(pixKeyDTOFromValue);

        assertEquals(pixKeyDTO, supportUpdatePixKeyDTO.getPixKeyDTO());
        assertEquals(pixKeyRequestBodyDTO, supportUpdatePixKeyDTO.getPixKeyRequestBodyDTO());
        assertEquals(pixKeyDTOs, supportUpdatePixKeyDTO.getPixKeyDTOs());
        assertEquals(pixKeyDTOFromValue, supportUpdatePixKeyDTO.getPixKeyDTOFromValue());
    }
}
