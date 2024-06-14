package br.com.itau.pix.validation.update;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.UpdatePixKeyValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Order(1)
public class UpdateBodyValidation implements UpdatePixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportUpdatePixKeyDTO supportUpdatePixKeyDTO) throws ValidationException {
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = supportUpdatePixKeyDTO.getPixKeyRequestBodyDTO();
        PixKeyDTO pixKeyDTO = supportUpdatePixKeyDTO.getPixKeyDTO();

        validateFields(pixKeyRequestBodyDTO, pixKeyDTO);

        return null;
    }

    private void validateFields(PixKeyRequestBodyDTO pixKeyRequestBodyDTO, PixKeyDTO pixKeyDTO) {
        List<FieldErrorDTO> errors = new ArrayList<>();

        if (!pixKeyRequestBodyDTO.getKeyType().equalsIgnoreCase(pixKeyDTO.getKeyType())) {
            errors.add(new FieldErrorDTO("keyType", "The provided key type does not match the registered key type."));
        }
        if (!pixKeyRequestBodyDTO.getAccountType().toUpperCase().equalsIgnoreCase(pixKeyDTO.getAccountType().getName())) {
            errors.add(new FieldErrorDTO("accountType", "The provided account type does not match the registered account type."));
        }
        if (!(Objects.equals(pixKeyRequestBodyDTO.getAgencyNumber(), pixKeyDTO.getAgencyNumber()))) {
            errors.add(new FieldErrorDTO("agencyNumber", "The provided agency number does not match the registered agency number."));
        }
        if (!(Objects.equals(pixKeyRequestBodyDTO.getAccountNumber(), pixKeyDTO.getAccountNumber()))) {
            errors.add(new FieldErrorDTO("accountNumber", "The provided account number does not match the registered account number."));
        }
        if (!pixKeyRequestBodyDTO.getAccountHolderFirstName().equalsIgnoreCase(pixKeyDTO.getAccountHolderFirstName())) {
            errors.add(new FieldErrorDTO("accountHolderFirstName", "The provided account holder's first name does not match the registered account holder's first name."));
        }
        if (!pixKeyRequestBodyDTO.getAccountHolderLastName().equalsIgnoreCase(pixKeyDTO.getAccountHolderLastName())) {
            errors.add(new FieldErrorDTO("accountHolderLastName", "The provided account holder's last name does not match the registered account holder's last name."));
        }

        if(!errors.isEmpty()){
            throw new ValidationException("Validation Error",errors);
        }
    }

}
