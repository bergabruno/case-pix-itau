package br.com.itau.pix.dto.request;

import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PixKeyRequestBodyDTOTest {

    private final Validator validator;

    public PixKeyRequestBodyDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testPixKeyRequestBodyDTOSettersAndGetters() {
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = new PixKeyRequestBodyDTO();

        pixKeyRequestBodyDTO.setId("1");
        pixKeyRequestBodyDTO.setKeyValue("john.doe@example.com");
        pixKeyRequestBodyDTO.setKeyType("EMAIL");
        pixKeyRequestBodyDTO.setAccountType("corrente");
        pixKeyRequestBodyDTO.setAgencyNumber(1234);
        pixKeyRequestBodyDTO.setAccountNumber(56789012);
        pixKeyRequestBodyDTO.setAccountHolderFirstName("John");
        pixKeyRequestBodyDTO.setAccountHolderLastName("Doe");

        assertEquals("1", pixKeyRequestBodyDTO.getId());
        assertEquals("john.doe@example.com", pixKeyRequestBodyDTO.getKeyValue());
        assertEquals("EMAIL", pixKeyRequestBodyDTO.getKeyType());
        assertEquals("corrente", pixKeyRequestBodyDTO.getAccountType());
        assertEquals(1234, pixKeyRequestBodyDTO.getAgencyNumber());
        assertEquals(56789012, pixKeyRequestBodyDTO.getAccountNumber());
        assertEquals("John", pixKeyRequestBodyDTO.getAccountHolderFirstName());
        assertEquals("Doe", pixKeyRequestBodyDTO.getAccountHolderLastName());
    }

    @Test
    public void testPixKeyRequestBodyDTOValidation() {
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = new PixKeyRequestBodyDTO();

        // Invalid data
        pixKeyRequestBodyDTO.setKeyValue(null);
        pixKeyRequestBodyDTO.setKeyType("INVALID");
        pixKeyRequestBodyDTO.setAccountType("INVALID");
        pixKeyRequestBodyDTO.setAgencyNumber(12345);  // Invalid length
        pixKeyRequestBodyDTO.setAccountNumber(123456789);  // Invalid length
        pixKeyRequestBodyDTO.setAccountHolderFirstName(null);
        pixKeyRequestBodyDTO.setAccountHolderLastName("More than 77 char: 12344565467575687687686795678987698769786986798679755453454235dfvj239o4876239test");

        Set<ConstraintViolation<PixKeyRequestBodyDTO>> violations = validator.validate(pixKeyRequestBodyDTO);

        assertEquals(7, violations.size());
    }

    @Test
    public void testPixKeyRequestBodyDTOValidData() {
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = new PixKeyRequestBodyDTO();

        pixKeyRequestBodyDTO.setKeyValue("john.doe@example.com");
        pixKeyRequestBodyDTO.setKeyType("EMAIL");
        pixKeyRequestBodyDTO.setAccountType("corrente");
        pixKeyRequestBodyDTO.setAgencyNumber(1234);
        pixKeyRequestBodyDTO.setAccountNumber(56789012);
        pixKeyRequestBodyDTO.setAccountHolderFirstName("John");
        pixKeyRequestBodyDTO.setAccountHolderLastName("Doe");

        Set<ConstraintViolation<PixKeyRequestBodyDTO>> violations = validator.validate(pixKeyRequestBodyDTO);

        assertTrue(violations.isEmpty());
    }
}
