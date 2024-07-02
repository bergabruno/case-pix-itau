package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixKeyResponseGetDTOTest {

    @Test
    public void testFromPixKeyDTO() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");
        pixKeyDTO.setAccountType(AccountTypeEnum.POUPANCA);
        pixKeyDTO.setAgencyNumber(1234);
        pixKeyDTO.setAccountNumber(567890);
        pixKeyDTO.setAccountHolderFirstName("John");
        pixKeyDTO.setAccountHolderLastName("Doe");
        pixKeyDTO.setTimestampInclusion("2023-01-01T12:00:00");
        pixKeyDTO.setTimestampExclusion("2023-01-02T12:00:00");

        PixKeyResponseGetDTO responseGetDTO = PixKeyResponseGetDTO.fromPixKeyDTO(pixKeyDTO);

        assertEquals("1234", responseGetDTO.getId());
        assertEquals("EMAIL", responseGetDTO.getKeyType());
        assertEquals("john.doe@example.com", responseGetDTO.getKeyValue());
        assertEquals(AccountTypeEnum.POUPANCA, responseGetDTO.getAccountType());
        assertEquals(1234, responseGetDTO.getAgencyNumber());
        assertEquals(567890, responseGetDTO.getAccountNumber());
        assertEquals("John", responseGetDTO.getAccountHolderFirstName());
        assertEquals("Doe", responseGetDTO.getAccountHolderLastName());
        assertEquals("2023-01-01T12:00:00", responseGetDTO.getTimestampInclusion());
        assertEquals("2023-01-02T12:00:00", responseGetDTO.getTimestampExclusion());
    }

    @Test
    public void testFromPixKeyDTONullValues() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");
        pixKeyDTO.setAccountType(AccountTypeEnum.CORRENTE);
        pixKeyDTO.setAgencyNumber(1234);
        pixKeyDTO.setAccountNumber(567890);
        pixKeyDTO.setAccountHolderFirstName("John");
        pixKeyDTO.setTimestampInclusion("2023-01-01T12:00:00");

        PixKeyResponseGetDTO responseGetDTO = PixKeyResponseGetDTO.fromPixKeyDTO(pixKeyDTO);

        assertEquals("1234", responseGetDTO.getId());
        assertEquals("EMAIL", responseGetDTO.getKeyType());
        assertEquals("john.doe@example.com", responseGetDTO.getKeyValue());
        assertEquals(AccountTypeEnum.CORRENTE, responseGetDTO.getAccountType());
        assertEquals(1234, responseGetDTO.getAgencyNumber());
        assertEquals(567890, responseGetDTO.getAccountNumber());
        assertEquals("John", responseGetDTO.getAccountHolderFirstName());
        assertEquals("", responseGetDTO.getAccountHolderLastName());  // Default value for null
        assertEquals("2023-01-01T12:00:00", responseGetDTO.getTimestampInclusion());
        assertEquals("", responseGetDTO.getTimestampExclusion());  // Default value for null
    }

    @Test
    public void testPixKeyResponseGetDTOSettersAndGetters() {
        PixKeyResponseGetDTO responseGetDTO = new PixKeyResponseGetDTO();

        responseGetDTO.setId("5678");
        responseGetDTO.setKeyType("PHONE");
        responseGetDTO.setKeyValue("+123456789");
        responseGetDTO.setAccountType(AccountTypeEnum.CORRENTE);
        responseGetDTO.setAgencyNumber(4321);
        responseGetDTO.setAccountNumber(987654);
        responseGetDTO.setAccountHolderFirstName("Jane");
        responseGetDTO.setAccountHolderLastName("Smith");
        responseGetDTO.setTimestampInclusion("2023-02-01T12:00:00");
        responseGetDTO.setTimestampExclusion("2023-02-02T12:00:00");

        assertEquals("5678", responseGetDTO.getId());
        assertEquals("PHONE", responseGetDTO.getKeyType());
        assertEquals("+123456789", responseGetDTO.getKeyValue());
        assertEquals(AccountTypeEnum.CORRENTE, responseGetDTO.getAccountType());
        assertEquals(4321, responseGetDTO.getAgencyNumber());
        assertEquals(987654, responseGetDTO.getAccountNumber());
        assertEquals("Jane", responseGetDTO.getAccountHolderFirstName());
        assertEquals("Smith", responseGetDTO.getAccountHolderLastName());
        assertEquals("2023-02-01T12:00:00", responseGetDTO.getTimestampInclusion());
        assertEquals("2023-02-02T12:00:00", responseGetDTO.getTimestampExclusion());
    }
}
