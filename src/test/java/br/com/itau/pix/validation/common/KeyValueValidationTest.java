package br.com.itau.pix.validation.common;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.validation.commom.KeyValueValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyValueValidationTest {

    private KeyValueValidation keyValueValidation;

    @BeforeEach
    public void setUp() {
        keyValueValidation = new KeyValueValidation();
    }

    @Test
    public void testValidatePhone_Success() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("CELULAR");
        dto.setKeyValue("+5511999999999");
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidatePhone_Failure() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("CELULAR");
        dto.setKeyValue("11999999999");
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidateEmail_Success() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("EMAIL");
        dto.setKeyValue("test@example.com");
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateEmail_Failure() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("EMAIL");
        dto.setKeyValue("test@.com");
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidateCpf_Success() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("CPF");
        dto.setKeyValue("12345678909"); // Valid CPF
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateCpf_Failure() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("CPF");
        dto.setKeyValue("12345678900"); // Invalid CPF
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidateCnpj_Success() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("CNPJ");
        dto.setKeyValue("12345678000195"); // Valid CNPJ
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateCnpj_Failure() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("CNPJ");
        dto.setKeyValue("12345678000190"); // Invalid CNPJ
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidateRandom_Success() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("ALEATORIO");
        dto.setKeyValue("abc123xyz789def456ghi123jkl789mno456");
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidateRandom_Failure() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("ALEATORIO");
        dto.setKeyValue("abc123");
        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, dto, null);

        FieldErrorDTO result = keyValueValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidate_Update_Success() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("EMAIL");
        dto.setKeyValue("test@example.com");
        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(null, dto, null, null);

        FieldErrorDTO result = keyValueValidation.validate(supportUpdatePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidate_Update_Failure() {
        PixKeyRequestBodyDTO dto = new PixKeyRequestBodyDTO();
        dto.setKeyType("EMAIL");
        dto.setKeyValue("test@.com");
        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(null, dto, null, null);

        FieldErrorDTO result = keyValueValidation.validate(supportUpdatePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }
}
