package br.com.itau.pix.enumerator;

import br.com.itau.pix.enumerators.KeyTypesEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyTypesEnumTest {

    @Test
    public void testPhoneType() {
        assertEquals("Phone", KeyTypesEnum.PHONE.getName());
    }

    @Test
    public void testEmailType() {
        assertEquals("Email", KeyTypesEnum.EMAIL.getName());
    }

    @Test
    public void testCpfType() {
        assertEquals("Cpf", KeyTypesEnum.CPF.getName());
    }

    @Test
    public void testCnpjType() {
        assertEquals("Cnpj", KeyTypesEnum.CNPJ.getName());
    }

    @Test
    public void testRandomType() {
        assertEquals("Random", KeyTypesEnum.RANDOM.getName());
    }
}
