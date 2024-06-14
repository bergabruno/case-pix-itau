package br.com.itau.pix.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PixKeyLogDTO {

    @Id
    private String id;
    private String keyValueId;
    private List<HistoryDTO> historyDTOs;

    public PixKeyLogDTO(PixKeyDTO pixKeyDTO, String message, String step) {
        this.id = UUID.randomUUID().toString();
        this.keyValueId = pixKeyDTO.getId();
        this.addHistory(new HistoryDTO(message, step, pixKeyDTO.getTimestampExclusion() != null ? pixKeyDTO.getTimestampExclusion() : pixKeyDTO.getTimestampInclusion()));
    }

    public void addHistory(HistoryDTO historyDTO) {
        if(historyDTOs == null) {
            historyDTOs = new ArrayList<>();
        }
        historyDTOs.add(historyDTO);
    }

    public HistoryDTO getLastHistory() {
        return historyDTOs.get(historyDTOs.size() - 1);
    }
}
