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
import java.util.Map;

@RestController
@RequestMapping("/account-keys")
@AllArgsConstructor
public class PixKeyController {

    private PixKeyService pixKeyService;

    @PostMapping
    public ResponseEntity<PixKeyResponsePostDTO> save(@RequestBody @Validated PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        return new ResponseEntity<>(pixKeyService.savePixKey(pixKeyRequestBodyDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PixKeyResponseGetDTO>> getByParam(
            @RequestParam HashMap<String, Object> headers,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<PixKeyResponseGetDTO> pixKeyServiceByParams = pixKeyService.getByParams(headers, page, size);

        return new ResponseEntity<>(pixKeyServiceByParams, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PixKeyResponsePatchDTO> update(@PathVariable String id, @RequestBody @Validated PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        pixKeyRequestBodyDTO.setId(id);
        PixKeyResponsePatchDTO pixKeyResponsePatchDTO = pixKeyService.update(pixKeyRequestBodyDTO);

        return new ResponseEntity<>(pixKeyResponsePatchDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PixKeyResponseDeleteDTO> delete(@PathVariable String id) {
        return new ResponseEntity<>(pixKeyService.deletePixKey(id), HttpStatus.OK);
    }
}
