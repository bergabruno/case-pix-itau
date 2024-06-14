package br.com.itau.pix.exception;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException{

    private final List<FieldErrorDTO> fieldErrors;

    public ValidationException(final String message, final List<FieldErrorDTO> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }
}
