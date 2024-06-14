package br.com.itau.pix.validation.chain;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.DeletePixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DeletePixKeyValidationChain {

    private final List<DeletePixKeyValidator> validators;

    public DeletePixKeyValidationChain(List<DeletePixKeyValidator> validators) {
        this.validators = validators;
    }

    public void validate(SupportDeletePixKeyDTO supportDeletePixKeyDTO) {
        List<FieldErrorDTO> errors = new ArrayList<>();

        for (DeletePixKeyValidator validator : validators) {
            FieldErrorDTO fieldErrorDTO;
            fieldErrorDTO = validator.validate(supportDeletePixKeyDTO);
            if (fieldErrorDTO != null) {
                errors.add(fieldErrorDTO);
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Validation Failed", errors);
        }
    }
}
