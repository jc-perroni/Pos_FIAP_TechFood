package com.posfiap.techfood.core.domain.usecases.restaurante;

import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteGateway;
import com.posfiap.techfood.core.domain.entities.Restaurante;

import java.util.List;

public class FindAllRestauranteUsecase {

    private IRestauranteGateway restauranteGateway;

    private FindAllRestauranteUsecase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static FindAllRestauranteUsecase create(IRestauranteGateway restauranteGateway) {
        return new FindAllRestauranteUsecase(restauranteGateway);
    }

    public List<Restaurante> run(int size, int offset) {
        return this.restauranteGateway.findAll(size, offset);
    }
}
