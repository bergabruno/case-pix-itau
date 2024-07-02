package br.com.itau.pix.dto.model;

import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.util.DateFormatUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PixKeyLogDTOTest {

    @Test
    public void testPixKeyLogDTOConstructorAndGetters() {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1234");
        pixKeyDTO.setAccountType(AccountTypeEnum.POUPANCA);
        pixKeyDTO.setAgencyNumber(1234);
        pixKeyDTO.setAccountNumber(56789);
        pixKeyDTO.setAccountHolderFirstName("John");
        pixKeyDTO.setAccountHolderLastName("Doe");
        pixKeyDTO.setKeyType("EMAIL");
        pixKeyDTO.setKeyValue("john.doe@example.com");
        pixKeyDTO.setTimestampInclusion(DateFormatUtil.formatToString(LocalDateTime.now()));
        pixKeyDTO.setStatus(StatusEnum.ACTIVE);

        String message = "Test message";
        String step = "Step 1";

        PixKeyLogDTO pixKeyLogDTO = new PixKeyLogDTO(pixKeyDTO, message, step);

        assertNotNull(pixKeyLogDTO.getId());
        assertEquals("1234", pixKeyLogDTO.getKeyValueId());
        assertNotNull(pixKeyLogDTO.getHistoryDTOs());
        assertEquals(1, pixKeyLogDTO.getHistoryDTOs().size());

        HistoryDTO historyDTO = pixKeyLogDTO.getHistoryDTOs().get(0);
        assertEquals(message, historyDTO.getMessage());
        assertEquals(step, historyDTO.getStep());
        assertEquals(pixKeyDTO.getTimestampInclusion(), historyDTO.getTimestamp());
    }

    @Test
    public void testAddHistory() {
        PixKeyLogDTO pixKeyLogDTO = new PixKeyLogDTO();
        HistoryDTO historyDTO = new HistoryDTO("Test message", "Step 1", DateFormatUtil.formatToString(LocalDateTime.now()));

        pixKeyLogDTO.addHistory(historyDTO);

        assertNotNull(pixKeyLogDTO.getHistoryDTOs());
        assertEquals(1, pixKeyLogDTO.getHistoryDTOs().size());
        assertEquals(historyDTO, pixKeyLogDTO.getHistoryDTOs().get(0));
    }

    @Test
    public void testGetLastHistory() {
        PixKeyLogDTO pixKeyLogDTO = new PixKeyLogDTO();
        HistoryDTO historyDTO1 = new HistoryDTO("Test message 1", "Step 1", DateFormatUtil.formatToString(LocalDateTime.now()));
        HistoryDTO historyDTO2 = new HistoryDTO("Test message 2", "Step 2", DateFormatUtil.formatToString(LocalDateTime.now()));

        pixKeyLogDTO.addHistory(historyDTO1);
        pixKeyLogDTO.addHistory(historyDTO2);

        HistoryDTO lastHistoryDTO = pixKeyLogDTO.getLastHistory();

        assertEquals(historyDTO2, lastHistoryDTO);
    }
}
