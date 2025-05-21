package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.ContaBancaria;
import com.posfiap.techfood.services.ContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/contaBancarias")
public class ContaBancariaController {

    private final ContaBancariaService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> findAllContaBancarias(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        var contaBancarias = contaBancariaService.findAllContaBancarias(page, size);
        return ResponseEntity.ok(contaBancarias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContaBancaria>> findContaBancariaById(@PathVariable("id") Long id){
        var contaBancaria = contaBancariaService.findContaBancariaById(id);
        return ResponseEntity.ok(contaBancaria);
    }
    @PostMapping
    public ResponseEntity<Void> inserirContaBancaria(
        @RequestBody ContaBancaria contaBancaria
        ){
        contaBancariaService.insertContaBancaria(contaBancaria);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarContaBancaria(
            @PathVariable("id") Long id, @RequestBody ContaBancaria contaBancaria
    ){
        contaBancariaService.updateContaBancaria(contaBancaria, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirContaBancaria(
            @PathVariable("id") Long id){
        contaBancariaService.deleteContaBancaria(id);
        return ResponseEntity.ok().build();
    }


}

