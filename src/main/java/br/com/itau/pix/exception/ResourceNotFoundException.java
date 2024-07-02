package br.com.itau.pix.exception;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final FieldErrorDTO fieldErrorDTO;

    public ResourceNotFoundException(final String message, FieldErrorDTO fieldErrorDTO) {
        super(message);
        this.fieldErrorDTO = fieldErrorDTO;
    }
}
