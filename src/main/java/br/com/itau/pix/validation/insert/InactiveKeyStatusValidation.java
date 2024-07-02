package br.com.itau.pix.validation.insert;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.DeletePixKeyValidator;
import br.com.itau.pix.validation.validators.base.UpdatePixKeyValidator;
import org.springframework.stereotype.Component;

@Component
public class InactiveKeyStatusValidation implements UpdatePixKeyValidator, DeletePixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportUpdatePixKeyDTO supportUpdatePixKeyDTO) throws ValidationException {
        PixKeyDTO pixKeyDTO = supportUpdatePixKeyDTO.getPixKeyDTO();

        return inactiveKeyStatus(pixKeyDTO);
    }

    @Override
    public FieldErrorDTO validate(SupportDeletePixKeyDTO supportDeletePixKeyDTO) {
        PixKeyDTO pixKeyDTO = supportDeletePixKeyDTO.getPixKeyDTO();

        return inactiveKeyStatus(pixKeyDTO);
    }

    private FieldErrorDTO inactiveKeyStatus(PixKeyDTO pixKeyDTO) {
        if (pixKeyDTO.getStatus() == StatusEnum.INACTIVE) {
            return new FieldErrorDTO("keyStatus","A chave se encontra desativada");
        }

        return null;
    }

}
