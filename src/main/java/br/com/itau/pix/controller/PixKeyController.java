package br.com.itau.pix.controller;

import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import br.com.itau.pix.service.PixKeyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/pix-keys")
@AllArgsConstructor
public class PixKeyController {

    private PixKeyService pixKeyService;


    @PostMapping
    public ResponseEntity<PixKeyResponsePostDTO> save(@RequestBody @Validated PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        PixKeyResponsePostDTO response = pixKeyService.savePixKey(pixKeyRequestBodyDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PixKeyResponseGetDTO>> getByParam(
            @RequestParam HashMap<String, Object> headers,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<PixKeyResponseGetDTO> response = pixKeyService.getByParams(headers, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PixKeyResponsePatchDTO> update(@PathVariable String id, @RequestBody @Validated PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        pixKeyRequestBodyDTO.setId(id);
        PixKeyResponsePatchDTO response = pixKeyService.update(pixKeyRequestBodyDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PixKeyResponseDeleteDTO> delete(@PathVariable String id) {
        PixKeyResponseDeleteDTO response = pixKeyService.deletePixKey(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
