package br.com.itau.pix.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
