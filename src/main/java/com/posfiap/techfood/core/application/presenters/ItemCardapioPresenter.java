package com.posfiap.techfood.core.application.presenters;

import com.posfiap.techfood.core.application.dto.ItemCardapioDTO;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;

public class ItemCardapioPresenter {

    public static ItemCardapioDTO toDto(ItemCardapio itemCardapio) {
        return new ItemCardapioDTO(
                itemCardapio.getId(),
                itemCardapio.getIdRestaurante(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getValor(),
                itemCardapio.getApenasPedidosPresenciais(),
                itemCardapio.getFoto());
    }
}
