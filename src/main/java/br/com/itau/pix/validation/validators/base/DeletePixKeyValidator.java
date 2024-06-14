package br.com.itau.pix.validation.validators.base;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;

public interface DeletePixKeyValidator {

    FieldErrorDTO validate(SupportDeletePixKeyDTO supportDeletePixKeyDTO);
}
