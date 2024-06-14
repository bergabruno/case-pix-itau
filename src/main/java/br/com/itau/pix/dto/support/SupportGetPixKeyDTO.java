package br.com.itau.pix.dto.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportGetPixKeyDTO {

    private HashMap<String, Object> paramsForSearch;
}
