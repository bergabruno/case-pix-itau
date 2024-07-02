package br.com.itau.pix.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeyTypesEnum {

    PHONE ("Phone"), EMAIL ("Email"), CPF("Cpf"), CNPJ("Cnpj"), RANDOM("Random");

    private final String name;
}
