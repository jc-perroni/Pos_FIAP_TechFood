package com.posfiap.techfood.core.domain.mapper;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.domain.entities.Restaurante;

public class RestauranteMapper {

    public static Restaurante toEntity(RestauranteDTO restauranteDTO) {
        return Restaurante.create(
                restauranteDTO.id(),
                restauranteDTO.idProprietario(),
                restauranteDTO.nome(),
                restauranteDTO.telefone(),
                restauranteDTO.endereco(),
                restauranteDTO.tipoCozinha(),
                restauranteDTO.horaAbertura(),
                restauranteDTO.horaFechamento()
        );
    }

    public static RestauranteDTO toDto(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getIdProprietario(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getTelefone(),
                restaurante.getEndereco(),
                restaurante.getHoraAbertura(),
                restaurante.getHoraFechamento()
        );
    }
}
