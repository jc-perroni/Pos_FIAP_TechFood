package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteDTO;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteUpdateDTO;
import com.posfiap.techfood.infrastructure.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/clientes")
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Listar todos os clientes paginados")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAllClientes(
        @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
        @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size
    ) {
        var clientes = clienteService.findAllClientes(page, size);
        return ResponseEntity.ok(clientes.getContent());
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findClienteById(
        @Parameter(description = "ID do cliente") @PathVariable("id") Long id
    ){
        var cliente = clienteService.findClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Inserir novo cliente")
    @PostMapping
    public ResponseEntity<Void> inserirCliente(
        @Valid @RequestBody ClienteDTO cliente
    ){
        clienteService.insertCliente(cliente);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCliente(
        @Parameter(description = "ID do cliente") @PathVariable("id") Long id,
        @Valid @RequestBody ClienteUpdateDTO cliente
    ){
        clienteService.updateCliente(cliente, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(
        @Parameter(description = "ID do cliente") @PathVariable("id") Long id
    ){
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }
}