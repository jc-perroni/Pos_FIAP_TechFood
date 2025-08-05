package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.infrastructure.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infrastructure.models.Cardapio;
import com.posfiap.techfood.infrastructure.services.CardapioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cardapio")
@Tag(name = "Cardapio", description = "Operações para gerenciamento de pratos")
public class CardapioController {

    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping
    public ResponseEntity<List<Cardapio>> findAllCaradapios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    )
    {
        var cardapios = cardapioService.findAllCardapios(page, size);
        return ResponseEntity.ok(cardapios.getContent());
    }


    @Operation(summary = "Buscar cardápio por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> findCardapioById(
            @Parameter(description = "ID do cardápio") @PathVariable("id") Long id
    ){
        var cardapio = cardapioService.findCardapioById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cardápio não localizado para o ID " + id));
        return ResponseEntity.ok(cardapio);
    }

    @Operation(summary = "Inserir novo cardapio")
    @PostMapping
    public ResponseEntity<Void> inserirCardapio(
            @Valid @RequestBody Cardapio cardapio
    ){
        cardapioService.insertCardapio(cardapio);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar cardápio existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCardapio(
            @Parameter(description = "ID do cardapio") @PathVariable("id") Long id,
            @Valid @RequestBody Cardapio cardapio
    ){
        cardapioService.updateCardapio(cardapio, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir cardápio por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCardapio(
            @Parameter(description = "ID do cardápio") @PathVariable("id") Long id
    ){
        cardapioService.deleteCardapio(id);
        return ResponseEntity.ok().build();
    }
}
