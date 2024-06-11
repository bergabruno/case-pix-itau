package br.com.itau.pix.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDTO {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String trace;
    private String message;
    private String path;
    private List<FieldErrorDTO> fieldErrors;


    public ErrorDTO(int status, String message, LocalDateTime timestamp, String path, List<FieldErrorDTO> fieldErrors) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
        this.fieldErrors = fieldErrors != null ? fieldErrors : new ArrayList<>();
    }
}
