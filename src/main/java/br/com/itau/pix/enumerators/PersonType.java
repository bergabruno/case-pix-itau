package br.com.itau.pix.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonType {
    FISICA("Fisica"),
    JURIDICA("Juridica"),
    UNDEFINED("Undefined");

    private final String name;
}