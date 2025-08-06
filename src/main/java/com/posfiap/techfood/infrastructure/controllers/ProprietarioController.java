package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.core.application.controllers.UsuarioController;
import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioUpdateDto;
import com.posfiap.techfood.infrastructure.datasource.ProprietarioDataSource;
import com.posfiap.techfood.infrastructure.services.ProprietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/proprietarios")
@Tag(name = "Proprietários", description = "Operações relacionadas a proprietários de restaurantes")
public class ProprietarioController {

    private final ProprietarioDataSource proprietarioDataSource;

    @Operation(summary = "Listar todos os proprietários paginados")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAllProprietarios(
        @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
        @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size
    ) {
        UsuarioController usuarioController = UsuarioController.create(proprietarioDataSource);
        return ResponseEntity.ok(
                usuarioController.findAllUsuarios(page, size));
    }

    @Operation(summary = "Buscar proprietário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findProprietarioById(
        @Parameter(description = "ID do proprietário") @PathVariable("id") Long id
    ){
        UsuarioController usuarioController = UsuarioController.create(proprietarioDataSource);
        return ResponseEntity.ok(
                usuarioController.findUsuarioById(id));
    }

    @Operation(summary = "Inserir novo proprietário")
    @PostMapping
    public ResponseEntity<Void> inserirProprietario(
        @RequestBody NovoUsuarioDTO novoUsuarioDTO
    ){
        UsuarioController usuarioController = UsuarioController.create(proprietarioDataSource);
        usuarioController.inserirUsuario(novoUsuarioDTO);

        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar proprietário existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProprietario(
        @Parameter(description = "ID do proprietário") @PathVariable("id") Long id,
        @RequestBody UsuarioUpdateDto usuarioUpdateDto
    ){
        UsuarioController usuarioController = UsuarioController.create(proprietarioDataSource);
        usuarioController.atualizarUsuario(usuarioUpdateDto, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir proprietário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProprietario(
        @Parameter(description = "ID do proprietário") @PathVariable("id") Long id
    ){
        UsuarioController usuarioController = UsuarioController.create(proprietarioDataSource);

        usuarioController.excluirUsuario(id);
        return ResponseEntity.ok().build();
    }
}
