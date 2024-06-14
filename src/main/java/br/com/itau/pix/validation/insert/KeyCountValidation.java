package br.com.itau.pix.validation.insert;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.enumerators.PersonType;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.InsertPixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KeyCountValidation implements InsertPixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportSavePixKeyDTO supportSavePixKeyDTO) throws ValidationException {
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = supportSavePixKeyDTO.getPixKeyRequestBodyDTO();
        List<PixKeyDTO> pixKeyDTOList = supportSavePixKeyDTO.getPixKeyDTOs();

        return validateKeyCount(pixKeyDTOList, pixKeyRequestBodyDTO);
    }


    private FieldErrorDTO validateKeyCount(List<PixKeyDTO> pixKeyDTOS, PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        if(pixKeyDTOS.isEmpty()){
            return null;
        }

        PersonType personType = getPersonType(pixKeyDTOS);

        if (personType == PersonType.UNDEFINED && pixKeyDTOS.size() >= 4) {
            if (!pixKeyRequestBodyDTO.getKeyType().equalsIgnoreCase("CPF") && !pixKeyRequestBodyDTO.getKeyType().equalsIgnoreCase("CNPJ")) {
                return new FieldErrorDTO("keyValue", "The next key must be a CPF or a CNPJ.");
            }
        }

        if (personType == PersonType.FISICA && pixKeyDTOS.size() >= 5) {
            return new FieldErrorDTO("keyValue", "You cannot register more than 5 keys for an individual Person.");
        }

        if (personType == PersonType.JURIDICA && pixKeyDTOS.size() >= 20) {
            return new FieldErrorDTO("keyValue", "You cannot register more than 20 keys for a legal entity.");
        }

        return null;
    }

    private PersonType getPersonType(List<PixKeyDTO> activeKeys) {
        boolean isPessoaFisica = activeKeys.stream().anyMatch(key -> key.getKeyType().equalsIgnoreCase("CPF"));
        boolean isPessoaJuridica = activeKeys.stream().anyMatch(key -> key.getKeyType().equalsIgnoreCase("CNPJ"));

        if (isPessoaFisica) {
            return PersonType.FISICA;
        } else if (isPessoaJuridica) {
            return PersonType.JURIDICA;
        } else {
            return PersonType.UNDEFINED;
        }
    }
}
