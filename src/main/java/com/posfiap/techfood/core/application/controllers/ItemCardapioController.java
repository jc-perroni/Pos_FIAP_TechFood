package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.ItemCardapioDTO;
import com.posfiap.techfood.core.application.gateways.RestauranteGatewayImp;
import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioDataSource;
import com.posfiap.techfood.core.application.presenters.RestaurantePresenter;
import com.posfiap.techfood.core.domain.entities.Restaurante;
import com.posfiap.techfood.core.domain.usecases.restaurante.FindAllRestauranteUsecase;

import java.util.List;

public class ItemCardapioController {

    private final IItemCardapioDataSource dataSource;

    public ItemCardapioController(IItemCardapioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ItemCardapioController create(IItemCardapioDataSource dataSource) {
        return new ItemCardapioController(dataSource);
    }
    /*
    public List<ItemCardapioDTO> findAllItemCardapio(int size, int offset) {
        var restauranteGateway = RestauranteGatewayImp.create(dataSource);
        var findAllRestauranteUsecase = FindAllRestauranteUsecase.create(restauranteGateway);

        try {
            List<Restaurante> restauranteList = findAllRestauranteUsecase.run(size, offset);
            return restauranteList.stream().map(RestaurantePresenter::toDto).toList();
        } catch (Exception e) {
            return null;
        }
    }*/
}
