package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Restaurante;
import com.posfiap.techfood.repositories.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public List<Restaurante> findAllRestaurantes(int page, int size){
        int offset = (page - 1) * size;
        return restauranteRepository.findAll(size, offset);
    }

    public Optional<Restaurante> findRestauranteById(Long id){
        return Optional.ofNullable(restauranteRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public void updateRestaurante(Restaurante restaurante, Long id){
        var update = restauranteRepository.update(restaurante, id);
        if(update ==0) {
            throw new RuntimeException("O restaurante de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertRestaurante(Restaurante restaurante){
        var insert = restauranteRepository.save(restaurante);
        Assert.state(insert ==1, "Erro ao tentar gravar o restaurante.");
    }

    public void deleteRestaurante(Long id) {
        var delete = restauranteRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Restaurante não encontrado com a ID: " + id);
        }
    }

}
