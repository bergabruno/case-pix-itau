package br.com.itau.pix.dto.response;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PixKeyResponseGetDTO {

    private String id;

    private String keyType;

    private String keyValue;

    private AccountTypeEnum accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String timestampInclusion;

    private String timestampExclusion;

    public static PixKeyResponseGetDTO fromPixKeyDTO(PixKeyDTO pixKeyDTO) {
        PixKeyResponseGetDTO responseGetDTO = new PixKeyResponseGetDTO();
        responseGetDTO.setId(pixKeyDTO.getId());
        responseGetDTO.setKeyType(pixKeyDTO.getKeyType());
        responseGetDTO.setKeyValue(pixKeyDTO.getKeyValue());
        responseGetDTO.setAccountType(pixKeyDTO.getAccountType());
        responseGetDTO.setAgencyNumber(pixKeyDTO.getAgencyNumber());
        responseGetDTO.setAccountNumber(pixKeyDTO.getAccountNumber());
        responseGetDTO.setAccountHolderFirstName(pixKeyDTO.getAccountHolderFirstName());
        responseGetDTO.setAccountHolderLastName(pixKeyDTO.getAccountHolderLastName() == null ? "" : pixKeyDTO.getAccountHolderLastName());
        responseGetDTO.setTimestampInclusion(pixKeyDTO.getTimestampInclusion());
        responseGetDTO.setTimestampExclusion(pixKeyDTO.getTimestampExclusion() == null ? "" : pixKeyDTO.getTimestampExclusion());
        return responseGetDTO;
    }
}
