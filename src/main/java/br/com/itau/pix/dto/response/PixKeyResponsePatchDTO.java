package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPatchDTO;
import lombok.Data;

@Data
public class PixKeyResponsePatchDTO {

    private String id;

    private String keyType;

    private String keyValue;

    private String accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String timestampInclusion;

    public PixKeyResponsePatchDTO(PixKeyValueDTO pixKeyValueDTO, PixKeyRequestPatchDTO pixKeyRequestPatchDTO){
        this.id = pixKeyValueDTO.getId();;
        this.keyType = pixKeyValueDTO.getKeyType();
        this.keyValue = pixKeyValueDTO.getKeyType();
        this.accountType = pixKeyRequestPatchDTO.getAccountType();
        this.agencyNumber = pixKeyRequestPatchDTO.getAgencyNumber();
        this.accountNumber = pixKeyRequestPatchDTO.getAccountNumber();
        this.accountHolderFirstName = pixKeyRequestPatchDTO.getAccountHolderFirstName();
        this.accountHolderLastName = pixKeyRequestPatchDTO.getAccountHolderLastName();
        this.timestampInclusion = pixKeyValueDTO.getTimestampUpdate();
    }
}
