package br.com.itau.pix.dto.model;

import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.enumerators.StatusEnum;

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

    private String agencyAccountCombinationInclusion;

    private String accountCombinationInclusion;

    private StatusEnum status;

    public PixKeyValueDTO(PixKeyRequestPostDTO pixKeyRequestPostDTO) {
        this.keyType = pixKeyRequestPostDTO.getKeyType();
        this.keyValue = pixKeyRequestPostDTO.getKeyValue();
        this.agencyAccountCombinationInclusion = pixKeyRequestPostDTO.getAgencyNumber().toString() + pixKeyRequestPostDTO.getAccountNumber().toString();
        this.accountCombinationInclusion = pixKeyRequestPostDTO.getAccountType().toLowerCase() + "|" + this.agencyAccountCombinationInclusion;
    }
}
