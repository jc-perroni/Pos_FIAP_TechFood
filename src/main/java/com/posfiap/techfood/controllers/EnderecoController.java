package com.posfiap.techfood.controllers;


import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.services.EnderecoService;
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

    // Funções para o caso de cliente
    @GetMapping("/cliente")
    public ResponseEntity<List<Endereco>> findAllEnderecosForUsers(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        var enderecos = enderecoService.findAllEnderecosForUsers(page, size);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<Optional<Endereco>> findEnderecoByIdForUsers(@PathVariable("id") Long id){
        var endereco = enderecoService.findEnderecoByIdForUsers(id);
        return ResponseEntity.ok(endereco);
    }
    @PostMapping("/cliente")
    public ResponseEntity<Void> inserirEnderecoForUsers(
        @RequestBody Endereco endereco
        ){
        enderecoService.insertEnderecoForUser(endereco);
        return ResponseEntity.status(201).build();
    }


    // Funções para o caso de endereço de restaurante
    @GetMapping("/restaurante")
    public ResponseEntity<List<Endereco>> findAllEnderecosForRestaurants(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        var enderecos = enderecoService.findAllEnderecosForRestaurants(page, size);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<Optional<Endereco>> findEnderecoByIdForRestaurants(@PathVariable("id") Long id){
        var endereco = enderecoService.findEnderecoByIdForRestaurant(id);
        return ResponseEntity.ok(endereco);
    }
    @PostMapping("/cliente")
    public ResponseEntity<Void> inserirEnderecoForRestaurant(
            @RequestBody Endereco endereco
    ){
        enderecoService.insertEnderecoForRestaurant(endereco);
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

