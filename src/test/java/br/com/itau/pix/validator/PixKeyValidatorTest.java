package br.com.itau.pix.validator;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.itau.pix.enumerators.KeyTypesEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
public class PixKeyValidatorTest {

    private PixKeyValidator pixKeyValidator;

    @BeforeEach
    void setUp() {
        pixKeyValidator = new PixKeyValidator();
    }

    @Test
    void testValidPhoneNumber() {
        String key = "+5511999999999";
        assertEquals(KeyTypesEnum.PHONE.getName(), pixKeyValidator.getKeyType(key));
    }

    @Test
    void testValidEmail() {
        String key = "test@example.com.br";
        assertEquals(KeyTypesEnum.EMAIL.getName(), pixKeyValidator.getKeyType(key));
    }

    @Test
    void testValidCPF() {
        String key = "12345678909"; // Um CPF válido fictício
        assertEquals(KeyTypesEnum.CPF.getName(), pixKeyValidator.getKeyType(key));
    }

    @Test
    void testInvalidCPF() {
        String key = "12345678900"; // Um CPF inválido
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.getKeyType(key));
        assertEquals("A chave CPF não é válida", exception.getMessage());
    }

    @Test
    void testValidCNPJ() {
        String key = "12345678000195"; // Um CNPJ válido fictício
        assertEquals(KeyTypesEnum.CNPJ.getName(), pixKeyValidator.getKeyType(key));
    }

    @Test
    void testInvalidCNPJ() {
        String key = "12345678000190"; // Um CNPJ inválido
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.getKeyType(key));
        assertEquals("A chave CNPJ não é válida", exception.getMessage());
    }

    @Test
    void testValidRandomKey() {
        String key = "abcdefghijklmnopqrstuvwxyz0123456789";
        assertEquals(KeyTypesEnum.RANDOM.getName(), pixKeyValidator.getKeyType(key));
    }

    @Test
    void testInvalidKey() {
        String key = "invalidKey";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pixKeyValidator.getKeyType(key));
        assertEquals("Valor de chave nao corresponde a nenhum tipo conhecido.", exception.getMessage());
    }
}
