package br.com.itau.pix.validation.update;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateBodyValidationTest {

    private UpdateBodyValidation updateBodyValidation;

    @BeforeEach
    public void setUp() {
        updateBodyValidation = new UpdateBodyValidation();
    }

    @Test
    public void testValidate_FieldsMatch() {
        PixKeyDTO pixKeyDTO = createPixKeyDTO();
        PixKeyRequestBodyDTO request = createPixKeyRequestBodyDTO();

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(pixKeyDTO, request, null, null);

        assertDoesNotThrow(() -> {
            updateBodyValidation.validate(supportUpdatePixKeyDTO);
        });
    }

    @Test
    public void testValidate_FieldsDoNotMatch() {
        PixKeyDTO pixKeyDTO = createPixKeyDTO();
        PixKeyRequestBodyDTO request = createPixKeyRequestBodyDTO();
        request.setAccountNumber(12645); // Simulate mismatch

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = new SupportUpdatePixKeyDTO(pixKeyDTO, request, null, null);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            updateBodyValidation.validate(supportUpdatePixKeyDTO);
        });

        List<FieldErrorDTO> fieldErrors = exception.getFieldErrors();
        assertEquals(1, fieldErrors.size());
        FieldErrorDTO fieldError = fieldErrors.get(0);
        assertEquals("accountNumber", fieldError.getField());
    }

    private PixKeyDTO createPixKeyDTO() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setAccountType(AccountTypeEnum.CORRENTE);
        pixKeyDTO.setAgencyNumber(1234);
        pixKeyDTO.setAccountNumber(1234);
        pixKeyDTO.setAccountHolderFirstName("John");
        pixKeyDTO.setAccountHolderLastName("Doe");
        return pixKeyDTO;
    }

    private PixKeyRequestBodyDTO createPixKeyRequestBodyDTO() {
        PixKeyRequestBodyDTO request = new PixKeyRequestBodyDTO();
        request.setKeyType("EMAIL");
        request.setAccountType("CORRENTE");
        request.setAgencyNumber(1234);
        request.setAccountNumber(1234);
        request.setAccountHolderFirstName("John");
        request.setAccountHolderLastName("Doe");
        return request;
    }
}
