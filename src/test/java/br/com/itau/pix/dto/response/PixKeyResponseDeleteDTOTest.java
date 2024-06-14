package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.util.DateFormatUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixKeyResponseDeleteDTOTest {

    @Test
    public void testPixKeyResponseDeleteDTOConstructorAndGetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");
        pixKeyDTO.setAccountType(AccountTypeEnum.CORRENTE);
        pixKeyDTO.setAgencyNumber(1234);
        pixKeyDTO.setAccountNumber(567890);
        pixKeyDTO.setAccountHolderFirstName("John");
        pixKeyDTO.setAccountHolderLastName("Doe");
        pixKeyDTO.setTimestampInclusion("2023-01-01T12:00:00");
        pixKeyDTO.setTimestampExclusion("2023-01-02T12:00:00");

        PixKeyResponseDeleteDTO pixKeyResponseDeleteDTO = new PixKeyResponseDeleteDTO(pixKeyDTO);

        assertEquals("1234", pixKeyResponseDeleteDTO.getId());
        assertEquals("EMAIL", pixKeyResponseDeleteDTO.getKeyType());
        assertEquals("john.doe@example.com", pixKeyResponseDeleteDTO.getKeyValue());
        assertEquals(AccountTypeEnum.CORRENTE, pixKeyResponseDeleteDTO.getAccountType());
        assertEquals(1234, pixKeyResponseDeleteDTO.getAgencyNumber());
        assertEquals(567890, pixKeyResponseDeleteDTO.getAccountNumber());
        assertEquals("John", pixKeyResponseDeleteDTO.getAccountHolderFirstName());
        assertEquals("Doe", pixKeyResponseDeleteDTO.getAccountHolderLastName());
    }

    @Test
    public void testPixKeyResponseDeleteDTOSetters() {
        PixKeyResponseDeleteDTO pixKeyResponseDeleteDTO = new PixKeyResponseDeleteDTO();

        pixKeyResponseDeleteDTO.setId("5678");
        pixKeyResponseDeleteDTO.setKeyType("PHONE");
        pixKeyResponseDeleteDTO.setKeyValue("+123456789");
        pixKeyResponseDeleteDTO.setAccountType(AccountTypeEnum.POUPANCA);
        pixKeyResponseDeleteDTO.setAgencyNumber(4321);
        pixKeyResponseDeleteDTO.setAccountNumber(987654);
        pixKeyResponseDeleteDTO.setAccountHolderFirstName("Jane");
        pixKeyResponseDeleteDTO.setAccountHolderLastName("Smith");
        pixKeyResponseDeleteDTO.setTimestampInclusion("2023-02-01T12:00:00");
        pixKeyResponseDeleteDTO.setTimestampExclusion("2023-02-02T12:00:00");

        assertEquals("5678", pixKeyResponseDeleteDTO.getId());
        assertEquals("PHONE", pixKeyResponseDeleteDTO.getKeyType());
        assertEquals("+123456789", pixKeyResponseDeleteDTO.getKeyValue());
        assertEquals(AccountTypeEnum.POUPANCA, pixKeyResponseDeleteDTO.getAccountType());
        assertEquals(4321, pixKeyResponseDeleteDTO.getAgencyNumber());
        assertEquals(987654, pixKeyResponseDeleteDTO.getAccountNumber());
        assertEquals("Jane", pixKeyResponseDeleteDTO.getAccountHolderFirstName());
        assertEquals("Smith", pixKeyResponseDeleteDTO.getAccountHolderLastName());
        assertEquals("2023-02-01T12:00:00", pixKeyResponseDeleteDTO.getTimestampInclusion());
        assertEquals("2023-02-02T12:00:00", pixKeyResponseDeleteDTO.getTimestampExclusion());
    }
}
