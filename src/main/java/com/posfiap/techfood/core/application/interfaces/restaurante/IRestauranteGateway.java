package com.posfiap.techfood.core.application.interfaces.restaurante;

import com.posfiap.techfood.core.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface IRestauranteGateway {
    Optional<Restaurante> findById(Long id);
    List<Restaurante> findAll(int size, int offset);
    Restaurante update(Restaurante restaurante, long id);
    Restaurante save(Restaurante restaurante);
    Integer delete(long id);
}
