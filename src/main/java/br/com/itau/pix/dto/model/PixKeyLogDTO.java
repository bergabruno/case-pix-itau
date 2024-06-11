package br.com.itau.pix.dto.model;

import org.springframework.data.annotation.Id;

public class PixKeyLogDTO {

    @Id
    private String id;

    private String keyValueId;

    private String message;

    private String timestamp;
}
