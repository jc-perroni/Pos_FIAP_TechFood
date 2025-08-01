package com.posfiap.techfood.core.domain.usecases.restaurante;

import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteGateway;
import com.posfiap.techfood.core.domain.entities.Restaurante;

public class InsertRestauranteUsecase {

    private IRestauranteGateway restauranteGateway;

    private InsertRestauranteUsecase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static InsertRestauranteUsecase create(IRestauranteGateway restauranteGateway) {
        return new InsertRestauranteUsecase(restauranteGateway);
    }

    public Restaurante run(Restaurante restaurante) {
        return this.restauranteGateway.save(restaurante);
    }
}
