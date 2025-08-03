package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.Proprietario;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioDTO;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioUpdateDTO;
import com.posfiap.techfood.services.ProprietarioService;
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

    private final ProprietarioService proprietarioService;

    @Operation(summary = "Listar todos os proprietários paginados")
    @GetMapping
    public ResponseEntity<List<Proprietario>> findAllProprietarios(
        @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
        @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size
    ) {
        var proprietarios = proprietarioService.findAllProprietarios(page, size);
        return ResponseEntity.ok(proprietarios.getContent());
    }

    @Operation(summary = "Buscar proprietário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> findProprietarioById(
        @Parameter(description = "ID do proprietário") @PathVariable("id") Long id
    ){
        var proprietario = proprietarioService.findProprietarioById(id);
        return ResponseEntity.ok(proprietario);
    }

    @Operation(summary = "Inserir novo proprietário")
    @PostMapping
    public ResponseEntity<Void> inserirProprietario(
        @RequestBody ProprietarioDTO proprietario
    ){
        proprietarioService.insertProprietario(proprietario);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar proprietário existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProprietario(
        @Parameter(description = "ID do proprietário") @PathVariable("id") Long id,
        @RequestBody ProprietarioUpdateDTO proprietario
    ){
        proprietarioService.updateProprietario(proprietario, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir proprietário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProprietario(
        @Parameter(description = "ID do proprietário") @PathVariable("id") Long id
    ){
        proprietarioService.deleteProprietario(id);
        return ResponseEntity.ok().build();
    }
}
