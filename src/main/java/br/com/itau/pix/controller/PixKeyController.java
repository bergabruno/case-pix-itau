package br.com.itau.pix.controller;

import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import br.com.itau.pix.service.PixKeyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keys")
@AllArgsConstructor
public class PixKeyController {

    private PixKeyService pixKeyService;

    @PostMapping
    public ResponseEntity<PixKeyResponsePostDTO> save(@RequestBody @Validated PixKeyRequestPostDTO requestDTO) {
        String id = pixKeyService.savePixKey(requestDTO).getLastPixKeyValueId();
        PixKeyResponsePostDTO pixKeyResponsePostDTO = new PixKeyResponsePostDTO(id);

        return new ResponseEntity<>(pixKeyResponsePostDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PixKeyResponseDeleteDTO> delete(@PathVariable String id) {
        return new ResponseEntity<>(pixKeyService.deletePixKey(id), HttpStatus.OK);
    }
}
