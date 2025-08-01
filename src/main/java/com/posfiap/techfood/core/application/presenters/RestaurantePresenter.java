package com.posfiap.techfood.core.application.presenters;

import com.posfiap.techfood.core.application.dto.RestauranteDTO;
import com.posfiap.techfood.core.domain.entities.Restaurante;

public class RestaurantePresenter {

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
