package br.com.itau.pix.validation.get;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import br.com.itau.pix.validation.validators.base.GetPixKeyValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TimestampDuplicateParamValidation implements GetPixKeyValidator {

    @Override
    public FieldErrorDTO validate(SupportGetPixKeyDTO supportGetPixKeyDTO) {
        return validateTimestampDuplicate(supportGetPixKeyDTO.getParamsForSearch());
    }


    private FieldErrorDTO validateTimestampDuplicate(HashMap<String, Object> params){
        if(params.containsKey("timestampInclusion") && params.containsKey("timestampExclusion")){
            return new FieldErrorDTO("Timestamp Duplicated","That's not allowed to make a Query with timestampInclusion and timestampExclusion, choose only one.");
        }

        return null;
    }
}
