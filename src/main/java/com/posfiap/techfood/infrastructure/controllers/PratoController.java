package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.infrastructure.models.Prato;
import com.posfiap.techfood.infrastructure.models.dto.prato.PratoDTO;
import com.posfiap.techfood.infrastructure.models.dto.prato.PratoUpdateDTO;
import com.posfiap.techfood.infrastructure.services.PratoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/prato")
@Tag(name = "Prato", description = "Operações para gerenciamento de pratos")
public class PratoController {

    private final PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @GetMapping
    public ResponseEntity<List<Prato>> findAllPratos(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var pratos = pratoService.findAllPratos(page, size);
        return ResponseEntity.ok(pratos.getContent());
    }

    @Operation(summary = "Buscar prato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Prato> findPratoById(
            @Parameter(description = "ID do prato") @PathVariable("id") Long id
    ) {
        var prato = pratoService.findPratoById(id);
        return ResponseEntity.ok(prato);
    }

    @Operation(summary = "Inserir novo prato")
    @PostMapping
    public ResponseEntity<Void> inserirPrato(
            @Valid @RequestBody PratoDTO pratoDTO,
            @RequestParam("cardapioId") Long cardapioId
    ) {
        pratoService.insertPrato(pratoDTO, cardapioId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Atualizar prato existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPrato(
            @Parameter(description = "ID do prato") @PathVariable("id") Long id,
            @Valid @RequestBody PratoUpdateDTO pratoDTO
    ) {
        pratoService.updatePrato(pratoDTO, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir prato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPrato(
            @Parameter(description = "ID do prato") @PathVariable("id") Long id
    ) {
        pratoService.deletePrato(id);
        return ResponseEntity.ok().build();
    }
}