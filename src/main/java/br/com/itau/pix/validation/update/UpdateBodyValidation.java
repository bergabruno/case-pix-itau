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
            errors.add(new FieldErrorDTO("keyType", "O tipo da chave informada nao coincide com o tipo de chave cadastrada."));
        }
        if (!pixKeyRequestBodyDTO.getAccountType().toUpperCase().equalsIgnoreCase(pixKeyDTO.getAccountType().getName())) {
            errors.add(new FieldErrorDTO("accountType", "O tipo da conta informado nao coincide com o tipo de conta cadastrada."));
        }
        if (!(Objects.equals(pixKeyRequestBodyDTO.getAgencyNumber(), pixKeyDTO.getAgencyNumber()))) {
            errors.add(new FieldErrorDTO("agencyNumber", "O numero da agência informado nao coincide com o numero da agência cadastrada."));
        }
        if (!(Objects.equals(pixKeyRequestBodyDTO.getAccountNumber(), pixKeyDTO.getAccountNumber()))) {
            errors.add(new FieldErrorDTO("accountNumber", "O numero da conta informado nao coincide com o numero da conta cadastrada."));
        }
        if (!pixKeyRequestBodyDTO.getAccountHolderFirstName().equalsIgnoreCase(pixKeyDTO.getAccountHolderFirstName())) {
            errors.add(new FieldErrorDTO("accountHolderFirstName", "O primeiro nome do titular da conta informado nao coincide com o primeiro nome do titular da conta cadastrada."));
        }
        if (!pixKeyRequestBodyDTO.getAccountHolderLastName().equalsIgnoreCase(pixKeyDTO.getAccountHolderLastName())) {
            errors.add(new FieldErrorDTO("accountHolderLastName", "O sobrenome do titular da conta informado nao coincide com o sobrenome do titular da conta cadastrada."));
        }

        if(!errors.isEmpty()){
            throw new ValidationException("Validation Error",errors);
        }
    }

}
