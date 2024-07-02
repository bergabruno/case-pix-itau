package br.com.itau.pix.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    ACTIVE ("Active"), INACTIVE ("Inactive");

    private final String name;
}
