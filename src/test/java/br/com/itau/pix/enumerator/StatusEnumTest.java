package br.com.itau.pix.enumerator;

import br.com.itau.pix.enumerators.StatusEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusEnumTest {

    @Test
    public void testActiveStatus() {
        assertEquals("Active", StatusEnum.ACTIVE.getName());
    }

    @Test
    public void testInactiveStatus() {
        assertEquals("Inactive", StatusEnum.INACTIVE.getName());
    }

    @Test
    public void testEnumValues() {
        StatusEnum[] expectedValues = {StatusEnum.ACTIVE, StatusEnum.INACTIVE};
        StatusEnum[] actualValues = StatusEnum.values();
        assertEquals(expectedValues.length, actualValues.length);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], actualValues[i]);
        }
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(StatusEnum.ACTIVE, StatusEnum.valueOf("ACTIVE"));
        assertEquals(StatusEnum.INACTIVE, StatusEnum.valueOf("INACTIVE"));
    }
}
