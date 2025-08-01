package com.posfiap.techfood.core.domain.usecases.restaurante;

import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteGateway;
import com.posfiap.techfood.core.domain.entities.Restaurante;
import com.posfiap.techfood.core.domain.exceptions.RestauranteNaoEncontradoException;

import java.util.Optional;

public class UpdateRestauranteUsecase {

    private IRestauranteGateway restauranteGateway;

    private UpdateRestauranteUsecase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static UpdateRestauranteUsecase create(IRestauranteGateway restauranteGateway) {
        return new UpdateRestauranteUsecase(restauranteGateway);
    }

    public Restaurante run(Restaurante restaurante, long id) {
        Optional<Restaurante> restauranteExistente = this.restauranteGateway.findById(id);
        if (restauranteExistente.isEmpty()) {
            throw new RestauranteNaoEncontradoException("Restaurante do id "  + id + " não existe");
        }
        return this.restauranteGateway.update(restaurante, id);
    }
}
