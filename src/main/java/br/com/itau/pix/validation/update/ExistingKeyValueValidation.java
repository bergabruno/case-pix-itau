package br.com.itau.pix.validation.update;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.validation.validators.base.UpdatePixKeyValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ExistingKeyValueValidation implements UpdatePixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportUpdatePixKeyDTO supportUpdatePixKeyDTO) {
        return validateExistingKeyValue(supportUpdatePixKeyDTO.getPixKeyDTOFromValue());
    }

    private FieldErrorDTO validateExistingKeyValue(PixKeyDTO pixKeyDTO) {
        if (pixKeyDTO != null) {
            return new FieldErrorDTO("KeyValue", "There is already a PIX key with this key value in another account.");
        }
        return null;
    }
}
