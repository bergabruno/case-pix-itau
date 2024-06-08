package br.com.itau.pix.dto.model;

import br.com.itau.pix.enumerators.StatusEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


@Document(collection = "PixKeyValueDTO")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Validated
public class PixKeyValueDTO {

    @Id
    private String id;

    private String keyType;

    private String keyValue;

    private String timestampInclusion;

    private String timestampExclusion;

    private StatusEnum status;
}
