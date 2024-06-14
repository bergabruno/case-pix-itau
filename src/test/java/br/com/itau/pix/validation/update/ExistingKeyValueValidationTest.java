package br.com.itau.pix.validation.update;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExistingKeyValueValidationTest {

    private ExistingKeyValueValidation existingKeyValueValidation;

    @BeforeEach
    public void setUp() {
        existingKeyValueValidation = new ExistingKeyValueValidation();
    }

    @Test
    public void testValidate_ExistingKeyValue() {
        PixKeyDTO existingPixKeyDTO = new PixKeyDTO();
        existingPixKeyDTO.setKeyValue("existing-key");

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(null, null, null, existingPixKeyDTO);

        FieldErrorDTO result = existingKeyValueValidation.validate(supportUpdatePixKeyDTO);
        assertNotNull(result);
        assertEquals("KeyValue", result.getField());
        assertEquals("Ja existe uma chave pix com esse valor de chave em outra conta", result.getErrorMessage());
    }

    @Test
    public void testValidate_NoExistingKeyValue() {
        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(null, null, null, null);

        FieldErrorDTO result = existingKeyValueValidation.validate(supportUpdatePixKeyDTO);
        assertNull(result);
    }
}
