package br.com.itau.pix.validation.get;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ParamEmptyValidationTest {

    private ParamEmptyValidation paramEmptyValidation;

    @BeforeEach
    public void setUp() {
        paramEmptyValidation = new ParamEmptyValidation();
    }

    @Test
    public void testValidateParamEmpty_NullParams() {
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(null);

        FieldErrorDTO result = paramEmptyValidation.validate(supportGetPixKeyDTO);
        assertNotNull(result);
        assertEquals("Query Empty", result.getField());
        assertEquals("A query esta vazia, verifique sua requisicao", result.getErrorMessage());
    }

    @Test
    public void testValidateParamEmpty_EmptyParams() {
        HashMap<String, Object> params = new HashMap<>();
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = paramEmptyValidation.validate(supportGetPixKeyDTO);
        assertNotNull(result);
        assertEquals("Query Empty", result.getField());
        assertEquals("A query esta vazia, verifique sua requisicao", result.getErrorMessage());
    }

    @Test
    public void testValidateParamEmpty_WithParams() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("keyType", "EMAIL");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = paramEmptyValidation.validate(supportGetPixKeyDTO);
        assertNull(result);
    }
}
