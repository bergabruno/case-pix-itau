package br.com.itau.pix.dto.model;

import br.com.itau.pix.dto.request.AccountKeyRequestBodyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.util.DateFormatUtil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "PixKey")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PixKeyDTO {

    @Id
    private String id;

    private AccountTypeEnum accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    @Indexed
    private String keyType;

    @Indexed(unique = true)
    private String keyValue;

    private String timestampInclusion;

    private String timestampExclusion;

    private StatusEnum status;

    public PixKeyDTO(AccountKeyRequestBodyDTO requestPostDTO){
        this.id = UUID.randomUUID().toString();
        this.accountType = AccountTypeEnum.getAccountType(requestPostDTO.getAccountType());
        this.agencyNumber = requestPostDTO.getAgencyNumber();
        this.accountNumber = requestPostDTO.getAccountNumber();
        this.accountHolderFirstName = requestPostDTO.getAccountHolderFirstName();
        this.accountHolderLastName = requestPostDTO.getAccountHolderLastName();
        this.keyType = requestPostDTO.getKeyType();
        this.keyValue = requestPostDTO.getKeyValue();
        this.timestampInclusion = DateFormatUtil.formatToString(LocalDateTime.now());
        this.status = StatusEnum.ACTIVE;
    }


}
