package br.com.itau.pix.validation.insert;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.enumerators.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InactiveKeyStatusValidationTest {

    private InactiveKeyStatusValidation inactiveKeyStatusValidation;

    @BeforeEach
    public void setUp() {
        inactiveKeyStatusValidation = new InactiveKeyStatusValidation();
    }

    @Test
    public void testValidate_Update_InactiveKey() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.INACTIVE);

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(pixKeyDTO, null, null, null);

        FieldErrorDTO result = inactiveKeyStatusValidation.validate(supportUpdatePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyStatus", result.getField());
        assertEquals("A chave se encontra desativada", result.getErrorMessage());
    }

    @Test
    public void testValidate_Update_ActiveKey() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.ACTIVE);

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(pixKeyDTO, null, null, null);

        FieldErrorDTO result = inactiveKeyStatusValidation.validate(supportUpdatePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidate_Delete_InactiveKey() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.INACTIVE);

        SupportDeletePixKeyDTO supportDeletePixKeyDTO = new SupportDeletePixKeyDTO(pixKeyDTO);

        FieldErrorDTO result = inactiveKeyStatusValidation.validate(supportDeletePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyStatus", result.getField());
        assertEquals("A chave se encontra desativada", result.getErrorMessage());
    }

    @Test
    public void testValidate_Delete_ActiveKey() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.ACTIVE);

        SupportDeletePixKeyDTO supportDeletePixKeyDTO = new SupportDeletePixKeyDTO(pixKeyDTO);

        FieldErrorDTO result = inactiveKeyStatusValidation.validate(supportDeletePixKeyDTO);
        assertNull(result);
    }
}
