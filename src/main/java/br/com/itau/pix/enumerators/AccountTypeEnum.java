package br.com.itau.pix.enumerators;

import br.com.itau.pix.exception.InvalidAccountTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTypeEnum  {
    POUPANCA("POUPANCA"),
    CORRENTE("CORRENTE");

    private final String name;

    public static AccountTypeEnum getAccountType(String accountType){
        return switch (accountType.toUpperCase()) {
            case "POUPANCA" -> AccountTypeEnum.POUPANCA;
            case "CORRENTE" -> AccountTypeEnum.CORRENTE;
            default -> throw new InvalidAccountTypeException("O tipo de conta esta invalido");
        };
    }
}