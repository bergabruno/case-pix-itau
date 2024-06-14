package br.com.itau.pix.validation.get;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import br.com.itau.pix.validation.validators.base.GetPixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OnlyIdParamValidation implements GetPixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportGetPixKeyDTO supportGetPixKeyDTO) {
        return validateOnlyId(supportGetPixKeyDTO.getParamsForSearch());
    }

    private FieldErrorDTO validateOnlyId(HashMap<String, Object> params){
        if(params.containsKey("id") && params.size() > 1){
            return new FieldErrorDTO("Only ID Accept","When ID is provided, the Query is made by only ID, take off other params.");
        }

        return null;
    }
}
