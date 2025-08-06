package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.core.application.controllers.UsuarioController;
import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioUpdateDto;
import com.posfiap.techfood.infrastructure.datasource.ClienteDataSource;
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

    private final ClienteDataSource clienteDataSource;

    @Operation(summary = "Listar todos os clientes paginados")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAllClientes(
            @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size) {
        UsuarioController usuarioController = UsuarioController.create(clienteDataSource);
        return ResponseEntity.ok(
                usuarioController.findAllUsuarios(page, size));
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findClienteById(
            @Parameter(description = "ID do cliente") @PathVariable("id") Long id) {
        UsuarioController usuarioController = UsuarioController.create(clienteDataSource);
        return ResponseEntity.ok(
                usuarioController.findUsuarioById(id));
    }

    @Operation(summary = "Inserir novo cliente")
    @PostMapping
    public ResponseEntity<Void> inserirCliente(
            @Valid @RequestBody NovoUsuarioDTO novoUsuarioDTO) {
        UsuarioController usuarioController = UsuarioController.create(clienteDataSource);

        usuarioController.inserirUsuario(novoUsuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCliente(
            @Parameter(description = "ID do cliente") @PathVariable("id") Long id,
            @Valid @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
        UsuarioController usuarioController = UsuarioController.create(clienteDataSource);
        usuarioController.atualizarUsuario(usuarioUpdateDto, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(
        @Parameter(description = "ID do cliente") @PathVariable("id") Long id
    ){
        UsuarioController usuarioController = UsuarioController.create(clienteDataSource);
        usuarioController.excluirUsuario(id);
        return ResponseEntity.ok().build();
    }
}