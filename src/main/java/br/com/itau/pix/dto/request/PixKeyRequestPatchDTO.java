package br.com.itau.pix.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Validated
public class PixKeyRequestPatchDTO {

    private String id;

    @NotNull
    @Size(max = 77)
    private String keyValue;

    @NotNull
    @Pattern(regexp = "^(?i)(EMAIL|CELULAR|CPF|CNPJ|ALEATORIO)$", message = "Invalid key type")
    private String keyType;

    @NotNull
    @Pattern(regexp = "^(?i)(poupanca|corrente)$", message = "Invalid account type")
    private String accountType;

    @Digits(integer = 4, fraction = 0)
    private Integer agencyNumber;

    @Digits(integer = 8, fraction = 0)
    private Integer accountNumber;

    @NotNull
    @Size(max = 30)
    private String accountHolderFirstName;

    @Size(max = 45)
    private String accountHolderLastName;
}
