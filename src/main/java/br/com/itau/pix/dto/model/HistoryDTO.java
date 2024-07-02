package br.com.itau.pix.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HistoryDTO {

    private String message;
    private String step;
    private String timestamp;
}
