package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PixKeyResponsePatchDTO {

    private String id;

    private String keyType;

    private String keyValue;

    private AccountTypeEnum accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String timestampInclusion;

    public PixKeyResponsePatchDTO(PixKeyDTO pixKeyDTO){
        this.id = pixKeyDTO.getId();
        this.keyType = pixKeyDTO.getKeyType();
        this.keyValue = pixKeyDTO.getKeyValue();
        this.accountType = pixKeyDTO.getAccountType();
        this.agencyNumber = pixKeyDTO.getAgencyNumber();
        this.accountNumber = pixKeyDTO.getAccountNumber();
        this.accountHolderFirstName = pixKeyDTO.getAccountHolderFirstName();
        this.accountHolderLastName = pixKeyDTO.getAccountHolderLastName();
        this.timestampInclusion = pixKeyDTO.getTimestampInclusion();
    }
}
