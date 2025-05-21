package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.Proprietario;
import com.posfiap.techfood.services.ProprietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/proprietarios")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    @GetMapping
    public ResponseEntity<List<Proprietario>> findAllProprietarios(@RequestParam("page") int page,
                                                                      @RequestParam("size") int size) {
        var proprietarios = proprietarioService.findAllProprietarios(page, size);
        return ResponseEntity.ok(proprietarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Proprietario>> findProprietarioById(@PathVariable("id") Long id){
        var proprietario = proprietarioService.findProprietarioById(id);
        return ResponseEntity.ok(proprietario);
    }
    @PostMapping
    public ResponseEntity<Void> inserirProprietario(
        @RequestBody Proprietario proprietario
        ){
        proprietarioService.insertProprietario(proprietario);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProprietario(
            @PathVariable("id") Long id, @RequestBody Proprietario proprietario
    ){
        proprietarioService.updateProprietario(proprietario, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProprietario(
            @PathVariable("id") Long id){
        proprietarioService.deleteProprietario(id);
        return ResponseEntity.ok().build();
    }


}

