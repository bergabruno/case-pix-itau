package br.com.itau.pix.dto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryDTOTest {

    @Test
    public void testHistoryDTOConstructorAndGetters() {
        String message = "Test message";
        String step = "Step 1";
        String timestamp = "2024-06-14T10:00:00";

        HistoryDTO historyDTO = new HistoryDTO(message, step, timestamp);

        assertEquals(message, historyDTO.getMessage());
        assertEquals(step, historyDTO.getStep());
        assertEquals(timestamp, historyDTO.getTimestamp());
    }

    @Test
    public void testHistoryDTOSetters() {
        String message = "Test message";
        String step = "Step 1";
        String timestamp = "2024-06-14T10:00:00";
        HistoryDTO historyDTO = new HistoryDTO(message, step, timestamp);

        historyDTO.setMessage(message);
        historyDTO.setStep(step);
        historyDTO.setTimestamp(timestamp);

        assertEquals(message, historyDTO.getMessage());
        assertEquals(step, historyDTO.getStep());
        assertEquals(timestamp, historyDTO.getTimestamp());
    }
}
