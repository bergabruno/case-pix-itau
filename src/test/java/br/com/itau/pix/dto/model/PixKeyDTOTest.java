package br.com.itau.pix.dto.model;

import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.util.DateFormatUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PixKeyDTOTest {

    @Test
    public void testPixKeyDTOConstructorAndGetters() {
        PixKeyRequestBodyDTO requestBodyDTO = new PixKeyRequestBodyDTO();
        requestBodyDTO.setAccountType("CORRENTE");
        requestBodyDTO.setAgencyNumber(1234);
        requestBodyDTO.setAccountNumber(56789);
        requestBodyDTO.setAccountHolderFirstName("John");
        requestBodyDTO.setAccountHolderLastName("Doe");
        requestBodyDTO.setKeyType("EMAIL");
        requestBodyDTO.setKeyValue("john.doe@example.com");

        PixKeyDTO pixKeyDTO = new PixKeyDTO(requestBodyDTO);

        assertNotNull(pixKeyDTO.getId());
        assertEquals(AccountTypeEnum.CORRENTE, pixKeyDTO.getAccountType());
        assertEquals(1234, pixKeyDTO.getAgencyNumber());
        assertEquals(56789, pixKeyDTO.getAccountNumber());
        assertEquals("John", pixKeyDTO.getAccountHolderFirstName());
        assertEquals("Doe", pixKeyDTO.getAccountHolderLastName());
        assertEquals("EMAIL", pixKeyDTO.getKeyType());
        assertEquals("john.doe@example.com", pixKeyDTO.getKeyValue());
        assertNotNull(pixKeyDTO.getTimestampInclusion());
        assertEquals(StatusEnum.ACTIVE, pixKeyDTO.getStatus());
    }

    @Test
    public void testPixKeyDTOSetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();

        pixKeyDTO.setId("id");
        pixKeyDTO.setAccountType(AccountTypeEnum.POUPANCA);
        pixKeyDTO.setAgencyNumber(4321);
        pixKeyDTO.setAccountNumber(98765);
        pixKeyDTO.setAccountHolderFirstName("Jane");
        pixKeyDTO.setAccountHolderLastName("Smith");
        pixKeyDTO.setKeyType("PHONE");
        pixKeyDTO.setKeyValue("+1234567890");
        pixKeyDTO.setTimestampInclusion(DateFormatUtil.formatToString(LocalDateTime.now()));
        pixKeyDTO.setTimestampExclusion(DateFormatUtil.formatToString(LocalDateTime.now()));
        pixKeyDTO.setStatus(StatusEnum.INACTIVE);

        assertEquals("id", pixKeyDTO.getId());
        assertEquals(AccountTypeEnum.POUPANCA, pixKeyDTO.getAccountType());
        assertEquals(4321, pixKeyDTO.getAgencyNumber());
        assertEquals(98765, pixKeyDTO.getAccountNumber());
        assertEquals("Jane", pixKeyDTO.getAccountHolderFirstName());
        assertEquals("Smith", pixKeyDTO.getAccountHolderLastName());
        assertEquals("PHONE", pixKeyDTO.getKeyType());
        assertEquals("+1234567890", pixKeyDTO.getKeyValue());
        assertNotNull(pixKeyDTO.getTimestampInclusion());
        assertNotNull(pixKeyDTO.getTimestampExclusion());
        assertEquals(StatusEnum.INACTIVE, pixKeyDTO.getStatus());
    }
}
