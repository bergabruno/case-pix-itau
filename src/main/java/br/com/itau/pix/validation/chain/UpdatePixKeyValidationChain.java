package br.com.itau.pix.validation.chain;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.UpdatePixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UpdatePixKeyValidationChain {

    private final List<UpdatePixKeyValidator> validators;

    public UpdatePixKeyValidationChain(List<UpdatePixKeyValidator> validators) {
        this.validators = validators;
    }

    public void validate(SupportUpdatePixKeyDTO supportUpdatePixKeyDTO) {
        List<FieldErrorDTO> errors = new ArrayList<>();

        for (UpdatePixKeyValidator validator : validators) {
            FieldErrorDTO fieldErrorDTO;
            fieldErrorDTO = validator.validate(supportUpdatePixKeyDTO);
            if (fieldErrorDTO != null) {
                errors.add(fieldErrorDTO);
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Validation Failed", errors);
        }
    }
}
