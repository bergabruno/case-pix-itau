package br.com.itau.pix.dto.error;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorDTO {

    private Integer status;
    private String message;
    private LocalDateTime timestamp;
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
