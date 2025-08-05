package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteDataSource;
import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteGateway;
import com.posfiap.techfood.core.domain.entities.Restaurante;
import com.posfiap.techfood.core.domain.mapper.RestauranteMapper;

import java.util.List;
import java.util.Optional;

public class RestauranteGatewayImp implements IRestauranteGateway {
    private final IRestauranteDataSource dataSource;

    private RestauranteGatewayImp(IRestauranteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RestauranteGatewayImp create(IRestauranteDataSource dataSource) {
        return new RestauranteGatewayImp(dataSource);
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        Optional<RestauranteDTO> restauranteDTOOptional = this.dataSource.findById(id);
        return restauranteDTOOptional.map(RestauranteMapper::toEntity);
    }

    @Override
    public List<Restaurante> findAll(int size, int offset) {
        List<RestauranteDTO> restauranteDTOList = this.dataSource.findAll(size, offset);
        return restauranteDTOList.stream().map(RestauranteMapper::toEntity).toList();
    }

    @Override
    public Restaurante update(Restaurante restaurante, long id) {
        RestauranteDTO restauranteDTO = RestauranteMapper.toDto(restaurante);
        return RestauranteMapper.toEntity(this.dataSource.update(restauranteDTO, id));
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        RestauranteDTO restauranteDTO = RestauranteMapper.toDto(restaurante);
        return RestauranteMapper.toEntity(this.dataSource.save(restauranteDTO));
    }

    @Override
    public Integer delete(long id) {
        return this.dataSource.delete(id);
    }


}
