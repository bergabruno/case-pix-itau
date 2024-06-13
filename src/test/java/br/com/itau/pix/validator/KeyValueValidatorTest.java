package br.com.itau.pix.validator;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
public class KeyValueValidatorTest {

    private KeyValueValidator keyValueValidator;

    @BeforeEach
    public void setUp() {
        keyValueValidator = new KeyValueValidator();
    }

    @Test
    public void testValidPhoneNumber() {
        assertDoesNotThrow(() -> keyValueValidator.validate("CELULAR", "+55119444332211"));
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> keyValueValidator.validate("CELULAR", "119444332211"));
    }

    @Test
    public void testValidEmail() {
        assertDoesNotThrow(() -> keyValueValidator.validate("EMAIL", "test@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> keyValueValidator.validate("EMAIL", "invalid-email"));
    }

    @Test
    public void testValidCPF() {
        assertDoesNotThrow(() -> keyValueValidator.validate("CPF", "12345678909"));
    }

    @Test
    public void testInvalidCPF() {
        assertThrows(IllegalArgumentException.class, () -> keyValueValidator.validate("CPF", "12345678900"));
    }

    @Test
    public void testValidCNPJ() {
        assertDoesNotThrow(() -> keyValueValidator.validate("CNPJ", "12345678000195"));
    }

    @Test
    public void testInvalidCNPJ() {
        assertThrows(IllegalArgumentException.class, () -> keyValueValidator.validate("CNPJ", "12345678000100"));
    }

    @Test
    public void testValidRandomKey() {
        assertDoesNotThrow(() -> keyValueValidator.validate("ALEATORIO", "abcdefghijklmnopqrstuvwxyz01234523fd"));
    }

    @Test
    public void testInvalidRandomKey() {
        assertThrows(IllegalArgumentException.class, () -> keyValueValidator.validate("ALEATORIO", "invalid-random-key"));
    }

    @Test
    public void testUnknownKeyType() {
        assertThrows(IllegalArgumentException.class, () -> keyValueValidator.validate("UNKNOWN", "some-value"));
    }
}