package br.com.itau.pix.dto.model;

import br.com.itau.pix.dto.request.PixKeyRequestDTO;
import br.com.itau.pix.enumerators.StatusEnum;

import jakarta.validation.constraints.Digits;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


@Document(collection = "PixKeyValueDTO")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Validated
@NoArgsConstructor
public class PixKeyValueDTO {

    @Id
    private String id;

    private String keyType;

    private String keyValue;

    private String timestampInclusion;

    private String timestampExclusion;

    private String accountHolderInclusion;

    private StatusEnum status;

    public PixKeyValueDTO(PixKeyRequestDTO pixKeyRequestDTO) {
        this.keyType = pixKeyRequestDTO.getKeyType();
        this.keyValue = pixKeyRequestDTO.getKeyValue();
        this.accountHolderInclusion = pixKeyRequestDTO.getAgencyNumber().toString() + pixKeyRequestDTO.getAccountNumber().toString();
    }
}
