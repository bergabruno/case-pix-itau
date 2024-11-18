package br.com.itau.pix.validation.insert;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.InsertPixKeyValidator;
import org.springframework.stereotype.Component;

@Component
public class DuplicateKeyValidation implements InsertPixKeyValidator {


    @Override
    public FieldErrorDTO validate(SupportSavePixKeyDTO supportSavePixKeyDTO) throws ValidationException {
        PixKeyDTO pixKeyDTO = supportSavePixKeyDTO.getPixKeyDTO();
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = supportSavePixKeyDTO.getPixKeyRequestBodyDTO();

        return validateDuplicateKey(pixKeyDTO, pixKeyRequestBodyDTO);
    }


    private FieldErrorDTO validateDuplicateKey(PixKeyDTO pixKeyDTO, PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        if(pixKeyDTO == null){
            return null;
        }

        String isInactive = pixKeyDTO.getStatus() == StatusEnum.INACTIVE ? "INATIVA" : "ATIVA";

        String accountCombination = pixKeyDTO.getAccountType() + "|" + pixKeyDTO.getAgencyNumber().toString() + pixKeyDTO.getAccountNumber().toString();
        String accountCombinationRequest = pixKeyRequestBodyDTO.getAccountType() + "|" + pixKeyRequestBodyDTO.getAgencyNumber().toString() + pixKeyRequestBodyDTO.getAccountNumber().toString();

        if (accountCombination.equalsIgnoreCase(accountCombinationRequest)) {
            return new FieldErrorDTO("keyValue", "The key you tried to register is already registered to your account. The status of the key is: " + isInactive);
        } else {
            return new FieldErrorDTO("keyValue", "The key you tried to register is already registered in other account.");
        }
    }
}
