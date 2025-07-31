package com.posfiap.techfood.infra.controllers;

import com.posfiap.techfood.infra.models.Restaurante;
import com.posfiap.techfood.infra.services.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/restaurantes")
@Tag(name = "Restaurantes", description = "Operações relacionadas aos restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @Operation(summary = "Listar todos os restaurantes com paginação")
    @GetMapping
    public ResponseEntity<List<Restaurante>> findAllRestaurantes(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        var restaurantes = restauranteService.findAllRestaurantes(page, size);
        return ResponseEntity.ok(restaurantes);
    }

    @Operation(summary = "Buscar restaurante por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Restaurante>> findRestauranteById(@PathVariable("id") Long id){
        var restaurante = restauranteService.findRestauranteById(id);
        return ResponseEntity.ok(restaurante);
    }

    @Operation(summary = "Cadastrar novo restaurante")
    @PostMapping
    public ResponseEntity<Void> inserirRestaurante(@RequestBody Restaurante restaurante){
        restauranteService.insertRestaurante(restaurante);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar restaurante existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarRestaurante(@PathVariable("id") Long id,
                                                     @RequestBody Restaurante restaurante){
        restauranteService.updateRestaurante(restaurante, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir restaurante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRestaurante(@PathVariable("id") Long id){
        restauranteService.deleteRestaurante(id);
        return ResponseEntity.ok().build();
    }
}
