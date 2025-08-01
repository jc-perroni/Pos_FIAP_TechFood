package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteDataSource;

public class RestauranteController {

    private final IRestauranteDataSource dataSource;

    public RestauranteController(IRestauranteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RestauranteController create(IRestauranteDataSource dataSource) {
        return new RestauranteController(dataSource);
    }

}
