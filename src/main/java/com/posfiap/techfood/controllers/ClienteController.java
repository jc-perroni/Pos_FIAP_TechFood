package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes(@RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        var clientes = clienteService.findAllClientes(page, size);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> findClienteById(@PathVariable("id") Long id){
        var cliente = clienteService.findClienteById(id);
        return ResponseEntity.ok(cliente);
    }
    @PostMapping
    public ResponseEntity<Void> inserirCliente(
        @RequestBody Cliente cliente
        ){
        clienteService.insertCliente(cliente);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCliente(
            @PathVariable("id") Long id, @RequestBody Cliente cliente
    ){
        clienteService.updateCliente(cliente, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(
            @PathVariable("id") Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }


}

