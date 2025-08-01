package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.application.gateways.RestauranteGatewayImp;
import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteDataSource;
import com.posfiap.techfood.core.application.presenters.RestaurantePresenter;
import com.posfiap.techfood.core.domain.entities.Restaurante;
import com.posfiap.techfood.core.domain.mapper.RestauranteMapper;
import com.posfiap.techfood.core.domain.usecases.restaurante.*;

import java.util.List;

public class RestauranteController {

    private final IRestauranteDataSource dataSource;

    public RestauranteController(IRestauranteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RestauranteController create(IRestauranteDataSource dataSource) {
        return new RestauranteController(dataSource);
    }

    public List<RestauranteDTO> findAllRestaurante(int size, int offset) {
        var restauranteGateway = RestauranteGatewayImp.create(dataSource);
        var findAllRestauranteUsecase = FindAllRestauranteUsecase.create(restauranteGateway);

        try {
            List<Restaurante> restauranteList = findAllRestauranteUsecase.run(size, offset);
            return restauranteList.stream().map(RestaurantePresenter::toDto).toList();
        } catch (Exception e) {
            return null;
        }
    }

    public RestauranteDTO findRestauranteById(Long id) {
        var restauranteGateway = RestauranteGatewayImp.create(dataSource);
        var findRestauranteByIdUsecase = FindRestauranteByIdUsecase.create(restauranteGateway);

        try {
            Restaurante restaurante = findRestauranteByIdUsecase.run(id);
            return RestaurantePresenter.toDto(restaurante);
        } catch (Exception e) {
            return null;
        }
    }

    public RestauranteDTO inserirRestaurante(RestauranteDTO restauranteDTO) {
        var restauranteGateway = RestauranteGatewayImp.create(dataSource);
        var insertRestauranteUsecase = InsertRestauranteUsecase.create(restauranteGateway);

        try {
            Restaurante restaurante = insertRestauranteUsecase.run(RestauranteMapper.toEntity(restauranteDTO));
            return RestaurantePresenter.toDto(restaurante);
        } catch (Exception e) {
            return  null;
        }
    }

    public RestauranteDTO atualizarRestaurante(RestauranteDTO restauranteDTO) {
        var restauranteGateway = RestauranteGatewayImp.create(dataSource);
        var updateRestauranteUsecase = UpdateRestauranteUsecase.create(restauranteGateway);

        try {
            Restaurante restaurante = updateRestauranteUsecase.run(RestauranteMapper.toEntity(restauranteDTO));
            return RestaurantePresenter.toDto(restaurante);
        } catch (Exception e) {
            return  null;
        }
    }

    public Integer excluirRestaurante(Long id) {
        var restauranteGateway = RestauranteGatewayImp.create(dataSource);
        var deleteRestauranteUsecase = DeleteRestauranteUsecase.create(restauranteGateway);

        try {
            return deleteRestauranteUsecase.run(id);
        } catch (Exception e) {
            return null;
        }
    }

}
