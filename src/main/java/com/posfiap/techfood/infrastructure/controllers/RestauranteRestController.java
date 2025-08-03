package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.core.application.controllers.RestauranteController;
import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.application.dto.RestauranteSalvamentoDTO;
import com.posfiap.techfood.infrastructure.models.Restaurante;
import com.posfiap.techfood.infrastructure.services.RestauranteService;
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
public class RestauranteRestController {

    //private final RestauranteService restauranteService;
    
    private final RestauranteController restauranteController;

    @Operation(summary = "Listar todos os restaurantes com paginação")
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> findAllRestaurantes(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok(
                restauranteController.findAllRestaurante(page, size));
    }

    @Operation(summary = "Buscar restaurante por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> findRestauranteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                restauranteController.findRestauranteById(id));
    }

    @Operation(summary = "Cadastrar novo restaurante")
    @PostMapping
    public ResponseEntity<RestauranteDTO> inserirRestaurante(@RequestBody RestauranteSalvamentoDTO restaurante){
        return ResponseEntity.ok(restauranteController.inserirRestaurante(restaurante));
    }

    @Operation(summary = "Atualizar restaurante existente")
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> atualizarRestaurante(@PathVariable("id") Long id,
                                                     @RequestBody RestauranteSalvamentoDTO restaurante){
        return ResponseEntity.ok(restauranteController.atualizarRestaurante(restaurante, id));
    }

    @Operation(summary = "Excluir restaurante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRestaurante(@PathVariable("id") Long id){
        restauranteController.excluirRestaurante(id);
        return ResponseEntity.ok().build();
    }
}
