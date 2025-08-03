package com.posfiap.techfood.infrastructure.datasource;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteDataSource;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class RestauranteDataSource implements IRestauranteDataSource {
    @Override
    public Optional<RestauranteDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<RestauranteDTO> findAll(int size, int offset) {
        return List.of(new RestauranteDTO(
                1L,
                2L,
                "Restaurante 1",
                "tipo da cozinha",
                "3333-4444",
                "Rua tal",
                LocalTime.of(6, 30),
                LocalTime.of(23, 0)
        ));
    }

    @Override
    public RestauranteDTO update(RestauranteDTO restaurante, long id) {
        return null;
    }

    @Override
    public RestauranteDTO save(RestauranteDTO restaurante) {
        return null;
    }

    @Override
    public Integer delete(long id) {
        return 0;
    }
}
