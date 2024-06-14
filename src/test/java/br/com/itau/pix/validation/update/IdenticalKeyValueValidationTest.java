package br.com.itau.pix.validation.update;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IdenticalKeyValueValidationTest {

    private IdenticalKeyValueValidation identicalKeyValueValidation;

    @BeforeEach
    public void setUp() {
        identicalKeyValueValidation = new IdenticalKeyValueValidation();
    }

    @Test
    public void testValidate_IdenticalKeyValue() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyValue("duplicate-key");

        PixKeyDTO existingKey = new PixKeyDTO();
        existingKey.setKeyValue("duplicate-key");

        List<PixKeyDTO> pixKeyDTOs = List.of(existingKey);
        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(null, request, pixKeyDTOs, null);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            identicalKeyValueValidation.validate(supportUpdatePixKeyDTO);
        });

        List<FieldErrorDTO> fieldErrors = exception.getFieldErrors();
        assertEquals(1, fieldErrors.size());
        FieldErrorDTO fieldError = fieldErrors.get(0);
        assertEquals("keyValue", fieldError.getField());
    }

    @Test
    public void testValidate_NoIdenticalKeyValue() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyValue("new-key");

        PixKeyDTO existingKey = new PixKeyDTO();
        existingKey.setKeyValue("existing-key");

        List<PixKeyDTO> pixKeyDTOs = List.of(existingKey);
        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(null, request, pixKeyDTOs, null);

        assertDoesNotThrow(() -> {
            identicalKeyValueValidation.validate(supportUpdatePixKeyDTO);
        });
    }
}
