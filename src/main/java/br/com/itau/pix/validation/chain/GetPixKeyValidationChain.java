package br.com.itau.pix.validation.chain;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.GetPixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class GetPixKeyValidationChain {

    private final List<GetPixKeyValidator> validators;

    public GetPixKeyValidationChain(List<GetPixKeyValidator> validators) {
        this.validators = validators;
    }

    public void validate(SupportGetPixKeyDTO supportGetPixKeyDTO) {
        List<FieldErrorDTO> errors = new ArrayList<>();

        for (GetPixKeyValidator validator : validators) {
            FieldErrorDTO fieldErrorDTO;
            fieldErrorDTO = validator.validate(supportGetPixKeyDTO);
            if (fieldErrorDTO != null) {
                errors.add(fieldErrorDTO);
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Validation Failed", errors);
        }
    }
}
