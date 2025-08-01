package com.posfiap.techfood.core.domain.usecases.restaurante;

import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteGateway;
import com.posfiap.techfood.core.domain.entities.Restaurante;
import com.posfiap.techfood.core.domain.exceptions.RestauranteNaoEncontradoException;

import java.util.Optional;

public class FindRestauranteByIdUsecase {

    private IRestauranteGateway restauranteGateway;

    private FindRestauranteByIdUsecase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static FindRestauranteByIdUsecase create(IRestauranteGateway restauranteGateway) {
        return new FindRestauranteByIdUsecase(restauranteGateway);
    }

    public Restaurante run(Long id) {
        Optional<Restaurante> restaurante = this.restauranteGateway.findById(id);
        if (restaurante.isEmpty()) {
            throw new RestauranteNaoEncontradoException("Restaurante do id " + id + " não encontrado na base dados");
        }

        return restaurante.get();
    }
}
