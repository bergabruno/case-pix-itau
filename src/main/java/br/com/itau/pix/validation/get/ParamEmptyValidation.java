package br.com.itau.pix.validation.get;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import br.com.itau.pix.validation.validators.base.GetPixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ParamEmptyValidation implements GetPixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportGetPixKeyDTO supportGetPixKeyDTO) {
        return validateParamEmpty(supportGetPixKeyDTO.getParamsForSearch());
    }

    private FieldErrorDTO validateParamEmpty(HashMap<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return new FieldErrorDTO("Query Empty", "A query esta vazia, verifique sua requisicao");
        }

        return null;
    }
}
