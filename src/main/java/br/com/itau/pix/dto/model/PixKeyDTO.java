package br.com.itau.pix.dto.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "PixKey")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Validated
public class PixKeyDTO {

    @Id
    private String id;

    private String accountType;

    private int agencyNumber;

    private int accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private List<String> pixKeyValueIds;


    public void addPixKeyValueId(String pixKeyValueId) {
        if (this.pixKeyValueIds == null) {
            this.pixKeyValueIds = new ArrayList<>();
        }
        this.pixKeyValueIds.add(pixKeyValueId);
    }
}
