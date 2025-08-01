package com.posfiap.techfood.core.application.presenters;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.domain.entities.Restaurante;

public class RestaurantePresenter {

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
