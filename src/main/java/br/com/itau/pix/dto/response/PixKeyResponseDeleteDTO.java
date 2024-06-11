package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyValueDTO;
import lombok.Data;

@Data
public class PixKeyResponseDeleteDTO {

    private String id;

    private String keyType;

    private String keyValue;

    private String accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String timestampInclusion;

    private String timestampExclusion;



    public PixKeyResponseDeleteDTO(PixKeyDTO pixKeyDTO, PixKeyValueDTO pixKeyValueDTO) {
        this.id = pixKeyValueDTO.getId();
        this.keyType = pixKeyValueDTO.getKeyType();
        this.keyValue = pixKeyValueDTO.getKeyValue();
        this.accountType = pixKeyDTO.getAccountType();
        this.agencyNumber = pixKeyDTO.getAgencyNumber();
        this.accountNumber = pixKeyDTO.getAccountNumber();
        this.accountHolderFirstName = pixKeyDTO.getAccountHolderFirstName();
        this.accountHolderLastName = pixKeyDTO.getAccountHolderLastName();
        this.timestampInclusion = pixKeyValueDTO.getTimestampInclusion();
        this.timestampExclusion = pixKeyValueDTO.getTimestampExclusion();
    }

}
