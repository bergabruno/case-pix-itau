package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PixKeyResponsePostDTOTest {

    @Test
    public void testNoArgsConstructor() {
        PixKeyResponsePostDTO responsePostDTO = new PixKeyResponsePostDTO();

        assertNull(responsePostDTO.getId());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        String id = "1234";
        PixKeyResponsePostDTO responsePostDTO = new PixKeyResponsePostDTO(id);

        assertEquals(id, responsePostDTO.getId());
    }

    @Test
    public void testSetters() {
        PixKeyResponsePostDTO responsePostDTO = new PixKeyResponsePostDTO();
        String id = "5678";

        responsePostDTO.setId(id);

        assertEquals(id, responsePostDTO.getId());
    }
}
