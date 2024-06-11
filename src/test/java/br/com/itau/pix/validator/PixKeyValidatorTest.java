package br.com.itau.pix.validator;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
public class PixKeyValidatorTest {

    private PixKeyValidator pixKeyValidator;

    @BeforeEach
    public void setUp() {
        pixKeyValidator = new PixKeyValidator();
    }

    @Test
    public void testValidPhoneNumber() {
        assertDoesNotThrow(() -> pixKeyValidator.validate("CELULAR", "+55119444332211"));
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.validate("CELULAR", "119444332211"));
    }

    @Test
    public void testValidEmail() {
        assertDoesNotThrow(() -> pixKeyValidator.validate("EMAIL", "test@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.validate("EMAIL", "invalid-email"));
    }

    @Test
    public void testValidCPF() {
        assertDoesNotThrow(() -> pixKeyValidator.validate("CPF", "12345678909"));
    }

    @Test
    public void testInvalidCPF() {
        assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.validate("CPF", "12345678900"));
    }

    @Test
    public void testValidCNPJ() {
        assertDoesNotThrow(() -> pixKeyValidator.validate("CNPJ", "12345678000195"));
    }

    @Test
    public void testInvalidCNPJ() {
        assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.validate("CNPJ", "12345678000100"));
    }

    @Test
    public void testValidRandomKey() {
        assertDoesNotThrow(() -> pixKeyValidator.validate("ALEATORIO", "abcdefghijklmnopqrstuvwxyz01234523fd"));
    }

    @Test
    public void testInvalidRandomKey() {
        assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.validate("ALEATORIO", "invalid-random-key"));
    }

    @Test
    public void testUnknownKeyType() {
        assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.validate("UNKNOWN", "some-value"));
    }
}