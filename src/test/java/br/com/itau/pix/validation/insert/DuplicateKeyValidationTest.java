package br.com.itau.pix.validation.insert;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.enumerators.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateKeyValidationTest {

    private DuplicateKeyValidation duplicateKeyValidation;

    @BeforeEach
    public void setUp() {
        duplicateKeyValidation = new DuplicateKeyValidation();
    }

    @Test
    public void testValidate_NoDuplicate() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(123);
        request.setAccountNumber(123);

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(null, request, null);

        FieldErrorDTO result = duplicateKeyValidation.validate(supportSavePixKeyDTO);
        assertNull(result);
    }

    @Test
    public void testValidate_DuplicateInSameAccount_Active() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.ACTIVE);
        pixKeyDTO.setAccountType(AccountTypeEnum.getAccountType("CORRENTE"));
        pixKeyDTO.setAgencyNumber(123);
        pixKeyDTO.setAccountNumber(123);

        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(123);
        request.setAccountNumber(123);

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(pixKeyDTO, request, null);

        FieldErrorDTO result = duplicateKeyValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidate_DuplicateInSameAccount_Inactive() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.INACTIVE);
        pixKeyDTO.setAccountType(AccountTypeEnum.getAccountType("CORRENTE"));
        pixKeyDTO.setAgencyNumber(123);
        pixKeyDTO.setAccountNumber(123);

        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(123);
        request.setAccountNumber(123);

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(pixKeyDTO, request, null);

        FieldErrorDTO result = duplicateKeyValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }

    @Test
    public void testValidate_DuplicateInDifferentAccount() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setStatus(StatusEnum.ACTIVE);
        pixKeyDTO.setAccountType(AccountTypeEnum.getAccountType("POUPANCA"));
        pixKeyDTO.setAgencyNumber(123);
        pixKeyDTO.setAccountNumber(123);

        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(123);
        request.setAccountNumber(1234);

        SupportSavePixKeyDTO supportSavePixKeyDTO = new SupportSavePixKeyDTO(pixKeyDTO, request, null);

        FieldErrorDTO result = duplicateKeyValidation.validate(supportSavePixKeyDTO);
        assertNotNull(result);
        assertEquals("keyValue", result.getField());
    }
}
