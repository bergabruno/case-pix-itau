package br.com.itau.pix.dto.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldErrorDTOTest {

    @Test
    public void testFieldErrorDTOConstructorAndGetters() {
        String field = "fieldName";
        String errorMessage = "must not be null";

        FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(field, errorMessage);

        assertEquals(field, fieldErrorDTO.getField());
        assertEquals(errorMessage, fieldErrorDTO.getErrorMessage());
    }

    @Test
    public void testFieldErrorDTOSetters() {
        FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(null, null);

        String field = "fieldName";
        String errorMessage = "must not be null";

        fieldErrorDTO.setField(field);
        fieldErrorDTO.setErrorMessage(errorMessage);

        assertEquals(field, fieldErrorDTO.getField());
        assertEquals(errorMessage, fieldErrorDTO.getErrorMessage());
    }
}
