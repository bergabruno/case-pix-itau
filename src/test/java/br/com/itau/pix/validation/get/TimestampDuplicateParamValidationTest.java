package br.com.itau.pix.validation.get;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampDuplicateParamValidationTest {

    private TimestampDuplicateParamValidation timestampDuplicateParamValidation;

    @BeforeEach
    public void setUp() {
        timestampDuplicateParamValidation = new TimestampDuplicateParamValidation();
    }

    @Test
    public void testValidateTimestampDuplicate_WithBothTimestamps() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestampInclusion", "2023-01-01T00:00:00");
        params.put("timestampExclusion", "2023-01-02T00:00:00");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = timestampDuplicateParamValidation.validate(supportGetPixKeyDTO);
        assertNotNull(result);
        assertEquals("Timestamp Duplicated", result.getField());
        assertEquals("nao eh possivel realizar query por meio do timestamp de inclusao e exclusao", result.getErrorMessage());
    }

    @Test
    public void testValidateTimestampDuplicate_WithOnlyTimestampInclusion() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestampInclusion", "2023-01-01T00:00:00");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = timestampDuplicateParamValidation.validate(supportGetPixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateTimestampDuplicate_WithOnlyTimestampExclusion() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestampExclusion", "2023-01-02T00:00:00");
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = timestampDuplicateParamValidation.validate(supportGetPixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateTimestampDuplicate_WithNoTimestamps() {
        HashMap<String, Object> params = new HashMap<>();
        SupportGetPixKeyDTO supportGetPixKeyDTO = new SupportGetPixKeyDTO(params);

        FieldErrorDTO result = timestampDuplicateParamValidation.validate(supportGetPixKeyDTO);
        assertNull(result);
    }
}
