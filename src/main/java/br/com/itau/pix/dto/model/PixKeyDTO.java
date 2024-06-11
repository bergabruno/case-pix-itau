package br.com.itau.pix.dto.model;

import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "PixKey")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Validated
public class PixKeyDTO {

    @Id
    private String id;

    private String accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String accountCombination;

    private List<String> pixKeyValueIds;

    public PixKeyDTO(PixKeyRequestPostDTO pixKeyRequestPostDTO, PixKeyValueDTO pixKeyValueDTO){
        this.id = UUID.randomUUID().toString();
        this.accountType = pixKeyRequestPostDTO.getAccountType();
        this.agencyNumber = pixKeyRequestPostDTO.getAgencyNumber();
        this.accountNumber = pixKeyRequestPostDTO.getAccountNumber();
        this.accountHolderFirstName = pixKeyRequestPostDTO.getAccountHolderFirstName();
        this.accountHolderLastName = pixKeyRequestPostDTO.getAccountHolderLastName();
        this.accountCombination = pixKeyValueDTO.getAccountCombinationInclusion();
    }

    public void addPixKeyValueId(String pixKeyValueId) {
        if (this.pixKeyValueIds == null) {
            this.pixKeyValueIds = new ArrayList<>();
        }
        this.pixKeyValueIds.add(pixKeyValueId);
    }

    public String getLastPixKeyValueId() {
        if (pixKeyValueIds == null || pixKeyValueIds.isEmpty()) {
            return null;
        }
        return pixKeyValueIds.get(pixKeyValueIds.size() - 1);
    }
}
