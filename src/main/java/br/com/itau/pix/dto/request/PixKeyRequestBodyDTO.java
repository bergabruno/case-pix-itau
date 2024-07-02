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
public class PixKeyRequestBodyDTO {

    private String id;

    @NotNull(message = "Key value cannot be null")
    @Size(max = 77, message = "Key value cannot exceed 77 characters")
    private String keyValue;

    @NotNull(message = "Key type cannot be null")
    @Pattern(regexp = "^(?i)(EMAIL|CELULAR|CPF|CNPJ|ALEATORIO)$", message = "Key type must be one of the following: EMAIL, CELULAR, CPF, CNPJ, ALEATORIO")
    private String keyType;

    @NotNull(message = "Account type cannot be null")
    @Pattern(regexp = "^(?i)(poupanca|corrente)$", message = "Account type must be either 'poupanca' or 'corrente'")
    private String accountType;

    @Digits(integer = 4, fraction = 0, message = "Agency number must be a numeric value with up to 4 digits")
    private Integer agencyNumber;

    @Digits(integer = 8, fraction = 0, message = "Account number must be a numeric value with up to 8 digits")
    private Integer accountNumber;

    @NotNull(message = "Account holder's first name cannot be null")
    @Size(max = 30, message = "Account holder's first name cannot exceed 30 characters")
    private String accountHolderFirstName;

    @Size(max = 45, message = "Account holder's last name cannot exceed 45 characters")
    private String accountHolderLastName;
}
