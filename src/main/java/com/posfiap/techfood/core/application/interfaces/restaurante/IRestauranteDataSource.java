package com.posfiap.techfood.core.application.interfaces.restaurante;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;

import java.util.List;
import java.util.Optional;

public interface IRestauranteDataSource {
    Optional<RestauranteDTO> findById(Long id);
    List<RestauranteDTO> findAll(int size, int offset);
    RestauranteDTO update(RestauranteDTO restaurante, long id);
    RestauranteDTO save(RestauranteDTO restaurante);
    Integer delete(long id);
}
