package br.com.itau.pix.validation.update;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.UpdatePixKeyValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class IdenticalKeyValueValidation implements UpdatePixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportUpdatePixKeyDTO supportUpdatePixKeyDTO) throws ValidationException {
        PixKeyRequestBodyDTO pixKeyRequestBodyDTO = supportUpdatePixKeyDTO.getPixKeyRequestBodyDTO();
        List<PixKeyDTO> pixKeyDTOs = supportUpdatePixKeyDTO.getPixKeyDTOs();

        validateIdenticalKeyValue(pixKeyRequestBodyDTO, pixKeyDTOs);
        return null;
    }

    private void validateIdenticalKeyValue(PixKeyRequestBodyDTO pixKeyRequestBodyDTO, List<PixKeyDTO> pixKeyDTOS) {

        boolean identicalMatch = pixKeyDTOS.stream().anyMatch(key -> key.getKeyValue().equalsIgnoreCase(pixKeyRequestBodyDTO.getKeyValue()));

        if (identicalMatch) {
            FieldErrorDTO fieldErrorDTO = new FieldErrorDTO("keyValue", "O valor da chave informada ja existe em sua conta");
            throw new ValidationException("Validation Error", List.of(fieldErrorDTO));
        }

    }

}
