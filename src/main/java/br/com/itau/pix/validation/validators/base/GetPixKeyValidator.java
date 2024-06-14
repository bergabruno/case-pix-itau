package br.com.itau.pix.validation.validators.base;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;

public interface GetPixKeyValidator {

    FieldErrorDTO validate(SupportGetPixKeyDTO supportGetPixKeyDTO);
}
