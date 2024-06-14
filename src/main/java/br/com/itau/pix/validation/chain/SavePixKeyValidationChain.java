package br.com.itau.pix.validation.chain;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.InsertPixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SavePixKeyValidationChain {

    private final List<InsertPixKeyValidator> validators;

    public SavePixKeyValidationChain(List<InsertPixKeyValidator> validators) {
        this.validators = validators;
    }

    public void validate(SupportSavePixKeyDTO supportSavePixKeyDTO) {
        List<FieldErrorDTO> errors = new ArrayList<>();

        for (InsertPixKeyValidator validator : validators) {
            FieldErrorDTO fieldErrorDTO;
            fieldErrorDTO = validator.validate(supportSavePixKeyDTO);
            if (fieldErrorDTO != null) {
                errors.add(fieldErrorDTO);
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Validation Failed", errors);
        }
    }
}
