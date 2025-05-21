package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.Restaurante;
import com.posfiap.techfood.services.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> findAllRestaurantes(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        var restaurantes = restauranteService.findAllRestaurantes(page, size);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Restaurante>> findRestauranteById(@PathVariable("id") Long id){
        var restaurante = restauranteService.findRestauranteById(id);
        return ResponseEntity.ok(restaurante);
    }
    @PostMapping
    public ResponseEntity<Void> inserirRestaurante(
        @RequestBody Restaurante restaurante
        ){
        restauranteService.insertRestaurante(restaurante);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarRestaurante(
            @PathVariable("id") Long id, @RequestBody Restaurante restaurante
    ){
        restauranteService.updateRestaurante(restaurante, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRestaurante(
            @PathVariable("id") Long id){
        restauranteService.deleteRestaurante(id);
        return ResponseEntity.ok().build();
    }


}

