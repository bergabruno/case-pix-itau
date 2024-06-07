package br.com.itau.pix.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PixKeyRegistrationDTO {

    private String id; // Gerar um codigo de registro Unico - UUID

    @NotNull
    private String keyType; // se cadastrar um CPF - colocar max 5, se cadastrar um CNPj - max 20 ?
    // Não deve permitir o registro de chaves duplicadas. O valor informado no campo VALOR

    @NotNull
    @Size(max = 77)
    private String keyValue;

    @NotNull
    private String accountType;

    @Digits(integer = 4, fraction = 0)
    private int agencyNumber;

    @Digits(integer = 8, fraction = 0)
    private int accountNumber;

    @NotNull
    @Size(max = 30)
    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String timestamp;
}
