package br.com.itau.pix.dto.support;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportUpdatePixKeyDTO {

    private PixKeyDTO pixKeyDTO;

    private PixKeyRequestBodyDTO pixKeyRequestBodyDTO;

    private List<PixKeyDTO> pixKeyDTOs;

    private PixKeyDTO pixKeyDTOFromValue;

}
