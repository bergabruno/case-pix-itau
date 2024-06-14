package br.com.itau.pix.validation.insert;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.enumerators.PersonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KeyCountValidationTest {

    private KeyCountValidation keyCountValidation;

    @BeforeEach
    public void setUp() {
        keyCountValidation = new KeyCountValidation();
    }

    @Test
    public void testValidate_NoKeys() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidate_UndefinedPersonType_MaxKeys() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("EMAIL");

        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PixKeyDTO key = new PixKeyDTO();
            key.setKeyType("EMAIL");
            pixKeyDTOList.add(key);
        }

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidate_Fisica_MaxKeys() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("EMAIL");

        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PixKeyDTO key = new PixKeyDTO();
            key.setKeyType(i == 0 ? "CPF" : "EMAIL");
            pixKeyDTOList.add(key);
        }

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidate_Juridica_MaxKeys() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("EMAIL");

        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PixKeyDTO key = new PixKeyDTO();
            key.setKeyType(i == 0 ? "CNPJ" : "EMAIL");
            pixKeyDTOList.add(key);
        }

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidate_UndefinedPersonType_ValidKey() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("CPF");

        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PixKeyDTO key = new PixKeyDTO();
            key.setKeyType("EMAIL");
            pixKeyDTOList.add(key);
        }

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidate_Fisica_ValidKey() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("EMAIL");

        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PixKeyDTO key = new PixKeyDTO();
            key.setKeyType(i == 0 ? "CPF" : "EMAIL");
            pixKeyDTOList.add(key);
        }

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidate_Juridica_ValidKey() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("EMAIL");

        List<PixKeyDTO> pixKeyDTOList = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            PixKeyDTO key = new PixKeyDTO();
            key.setKeyType(i == 0 ? "CNPJ" : "EMAIL");
            pixKeyDTOList.add(key);
        }

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, pixKeyDTOList);

        FieldErrorDTO result = keyCountValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }
}
