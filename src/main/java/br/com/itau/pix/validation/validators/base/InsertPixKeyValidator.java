package br.com.itau.pix.validation.validators.base;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;

public interface InsertPixKeyValidator {

    FieldErrorDTO validate(SupportSavePixKeyDTO supportSavePixKeyDTO);
}
