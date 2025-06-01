package com.posfiap.techfood.controllers;


import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.models.dto.EnderecoDTO;
import com.posfiap.techfood.services.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAllEnderecos(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        var enderecos = enderecoService.findAllEnderecos(page, size);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findEnderecoById(@PathVariable("id") Long id){
        var endereco = enderecoService.findEnderecoById(id);
        return ResponseEntity.ok(endereco);
    }
    @PostMapping
    public ResponseEntity<Void> inserirEndereco(
        @Valid @RequestBody EnderecoDTO endereco
        ){
        enderecoService.insertEndereco(endereco);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEndereco(
            @PathVariable("id") Long id, @RequestBody Endereco endereco
    ){
        enderecoService.updateEndereco(endereco, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(
            @PathVariable("id") Long id){
        enderecoService.deleteEndereco(id);
        return ResponseEntity.ok().build();
    }


}

