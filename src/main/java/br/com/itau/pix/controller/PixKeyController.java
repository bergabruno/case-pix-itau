package br.com.itau.pix.controller;

import br.com.itau.pix.dto.request.PixKeyRequestPatchDTO;
import br.com.itau.pix.dto.request.PixKeyRequestPostDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import br.com.itau.pix.service.PixKeyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/keys")
@AllArgsConstructor
public class PixKeyController {

    private PixKeyService pixKeyService;

    @PostMapping
    public ResponseEntity<PixKeyResponsePostDTO> save(@RequestBody @Validated PixKeyRequestPostDTO pixKeyRequestPostDTO) {
        String id = pixKeyService.savePixKey(pixKeyRequestPostDTO).getLastPixKeyValueId();
        PixKeyResponsePostDTO pixKeyResponsePostDTO = new PixKeyResponsePostDTO(id);

        return new ResponseEntity<>(pixKeyResponsePostDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PixKeyResponseGetDTO>> getByParam(@PathVariable String id) {
        return new ResponseEntity<>(new ArrayList<PixKeyResponseGetDTO>(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PixKeyResponsePatchDTO> update(@PathVariable String id, @RequestBody PixKeyRequestPatchDTO pixKeyRequestPatchDTO) {
        pixKeyRequestPatchDTO.setId(id);
        PixKeyResponsePatchDTO pixKeyResponsePatchDTO = pixKeyService.update(pixKeyRequestPatchDTO);

        return new ResponseEntity<>(pixKeyResponsePatchDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PixKeyResponseDeleteDTO> delete(@PathVariable String id) {
        return new ResponseEntity<>(pixKeyService.deletePixKey(id), HttpStatus.OK);
    }
}
