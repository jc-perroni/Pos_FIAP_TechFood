package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteDataSource;
import com.posfiap.techfood.core.application.interfaces.restaurante.IRestauranteGateway;
import com.posfiap.techfood.core.domain.entities.Restaurante;

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
        return restauranteDTOOptional.map(RestauranteGatewayImp::toEntity);
    }

    @Override
    public List<Restaurante> findAll(int size, int offset) {
        List<RestauranteDTO> restauranteDTOList = this.dataSource.findAll(size, offset);
        return restauranteDTOList.stream().map(RestauranteGatewayImp::toEntity).toList();
    }

    @Override
    public Restaurante update(Restaurante restaurante, long id) {
        RestauranteDTO restauranteDTO = toDto(restaurante);
        return toEntity(this.dataSource.update(restauranteDTO, id));
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        RestauranteDTO restauranteDTO = toDto(restaurante);
        return toEntity(this.dataSource.save(restauranteDTO));
    }

    @Override
    public Integer delete(long id) {
        return this.dataSource.delete(id);
    }

    private static Restaurante toEntity(RestauranteDTO restauranteDTO) {
        return Restaurante.create(
                restauranteDTO.id(),
                restauranteDTO.idProprietario(),
                restauranteDTO.nome(),
                restauranteDTO.telefone(),
                restauranteDTO.endereco()
        );
    }

    private static RestauranteDTO toDto(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getIdProprietario(),
                restaurante.getNome(),
                restaurante.getTelefone(),
                restaurante.getEndereco()
        );
    }
}
