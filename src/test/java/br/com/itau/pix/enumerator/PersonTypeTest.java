package br.com.itau.pix.enumerator;

import br.com.itau.pix.enumerators.PersonType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTypeTest {

    @Test
    public void testFisicaType() {
        assertEquals("Fisica", PersonType.FISICA.getName());
    }

    @Test
    public void testJuridicaType() {
        assertEquals("Juridica", PersonType.JURIDICA.getName());
    }

    @Test
    public void testUndefinedType() {
        assertEquals("Undefined", PersonType.UNDEFINED.getName());
    }
}
