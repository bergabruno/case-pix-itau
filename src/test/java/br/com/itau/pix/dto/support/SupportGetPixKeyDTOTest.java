package br.com.itau.pix.dto.support;

import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SupportGetPixKeyDTOTest {

    @Test
    public void testAllArgsConstructorAndGetters() {
        HashMap<String, Object> paramsForSearch = new HashMap<>();
        paramsForSearch.put("keyType", "EMAIL");
        paramsForSearch.put("keyValue", "john.doe@example.com");

        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(paramsForSearch);

        assertEquals(paramsForSearch, supportGetPixKeyDTO.getParamsForSearch());
        assertEquals("EMAIL", supportGetPixKeyDTO.getParamsForSearch().get("keyType"));
        assertEquals("john.doe@example.com", supportGetPixKeyDTO.getParamsForSearch().get("keyValue"));
    }

    @Test
    public void testSetters() {
        HashMap<String, Object> paramsForSearch = new HashMap<>();
        paramsForSearch.put("keyType", "PHONE");
        paramsForSearch.put("keyValue", "+123456789");

        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO();
        supportGetPixKeyDTO.setParamsForSearch(paramsForSearch);

        assertEquals(paramsForSearch, supportGetPixKeyDTO.getParamsForSearch());
        assertEquals("PHONE", supportGetPixKeyDTO.getParamsForSearch().get("keyType"));
        assertEquals("+123456789", supportGetPixKeyDTO.getParamsForSearch().get("keyValue"));
    }
}
