package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixKeyResponsePatchDTOTest {

    @Test
    public void testPixKeyResponsePatchDTOConstructorAndGetters() {
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

        PixKeyResponsePatchDTO responsePatchDTO = new PixKeyResponsePatchDTO(pixKeyDTO);

        assertEquals("1234", responsePatchDTO.getId());
        assertEquals("EMAIL", responsePatchDTO.getKeyType());
        assertEquals("john.doe@example.com", responsePatchDTO.getKeyValue());
        assertEquals(AccountTypeEnum.CORRENTE, responsePatchDTO.getAccountType());
        assertEquals(1234, responsePatchDTO.getAgencyNumber());
        assertEquals(567890, responsePatchDTO.getAccountNumber());
        assertEquals("John", responsePatchDTO.getAccountHolderFirstName());
        assertEquals("Doe", responsePatchDTO.getAccountHolderLastName());
        assertEquals("2023-01-01T12:00:00", responsePatchDTO.getTimestampInclusion());
    }

    @Test
    public void testPixKeyResponsePatchDTOSetters() {
        PixKeyResponsePatchDTO responsePatchDTO = new PixKeyResponsePatchDTO();

        responsePatchDTO.setId("5678");
        responsePatchDTO.setKeyType("PHONE");
        responsePatchDTO.setKeyValue("+123456789");
        responsePatchDTO.setAccountType(AccountTypeEnum.CORRENTE);
        responsePatchDTO.setAgencyNumber(4321);
        responsePatchDTO.setAccountNumber(987654);
        responsePatchDTO.setAccountHolderFirstName("Jane");
        responsePatchDTO.setAccountHolderLastName("Smith");
        responsePatchDTO.setTimestampInclusion("2023-02-01T12:00:00");

        assertEquals("5678", responsePatchDTO.getId());
        assertEquals("PHONE", responsePatchDTO.getKeyType());
        assertEquals("+123456789", responsePatchDTO.getKeyValue());
        assertEquals(AccountTypeEnum.CORRENTE, responsePatchDTO.getAccountType());
        assertEquals(4321, responsePatchDTO.getAgencyNumber());
        assertEquals(987654, responsePatchDTO.getAccountNumber());
        assertEquals("Jane", responsePatchDTO.getAccountHolderFirstName());
        assertEquals("Smith", responsePatchDTO.getAccountHolderLastName());
        assertEquals("2023-02-01T12:00:00", responsePatchDTO.getTimestampInclusion());
    }
}
