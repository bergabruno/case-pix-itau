package br.com.itau.pix.enumerator;

import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.exception.InvalidAccountTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTypeEnumTest {

    @Test
    public void testGetAccountTypePoupanca() {
        AccountTypeEnum accountType = AccountTypeEnum.getAccountType("POUPANCA");
        assertEquals(AccountTypeEnum.POUPANCA, accountType);
    }

    @Test
    public void testGetAccountTypeCorrente() {
        AccountTypeEnum accountType = AccountTypeEnum.getAccountType("CORRENTE");
        assertEquals(AccountTypeEnum.CORRENTE, accountType);
    }

    @Test
    public void testGetAccountTypePoupancaLowercase() {
        AccountTypeEnum accountType = AccountTypeEnum.getAccountType("poupanca");
        assertEquals(AccountTypeEnum.POUPANCA, accountType);
    }

    @Test
    public void testGetAccountTypeCorrenteLowercase() {
        AccountTypeEnum accountType = AccountTypeEnum.getAccountType("corrente");
        assertEquals(AccountTypeEnum.CORRENTE, accountType);
    }

    @Test
    public void testInvalidAccountType() {
        Exception exception = assertThrows(InvalidAccountTypeException.class, () -> {
            AccountTypeEnum.getAccountType("INVALIDO");
        });

        String expectedMessage = "O tipo de conta esta invalido";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
