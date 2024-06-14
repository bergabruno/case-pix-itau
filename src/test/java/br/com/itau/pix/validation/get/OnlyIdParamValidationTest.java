package br.com.itau.pix.validation.get;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class OnlyIdParamValidationTest {

    private OnlyIdParamValidation onlyIdParamValidation;

    @BeforeEach
    public void setUp() {
        onlyIdParamValidation = new OnlyIdParamValidation();
    }

    @Test
    public void testValidateOnlyId_Success() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", "some-id");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = onlyIdParamValidation.validate(supportGetPixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateOnlyId_WithExtraParams() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", "some-id");
        params.put("keyType", "EMAIL");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = onlyIdParamValidation.validate(supportGetPixKeyDTO);
        assertNotNull(result);
        assertEquals("Only ID Accept", result.getField());
    }

    @Test
    public void testValidateWithoutId_Success() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("keyType", "EMAIL");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = onlyIdParamValidation.validate(supportGetPixKeyDTO);
        assertNull(result);
    }
}
