package com.posfiap.techfood.core.domain.mapper;

import com.posfiap.techfood.core.application.dto.ItemCardapioDTO;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;

public class ItemCardapioMapper {

    public static ItemCardapio toEntity(ItemCardapioDTO itemCardapioDTO) {
        return ItemCardapio.create(itemCardapioDTO.id(),
                itemCardapioDTO.idRestaurante(),
                itemCardapioDTO.nome(),
                itemCardapioDTO.descricao(),
                itemCardapioDTO.valor(),
                itemCardapioDTO.apenasPedidosPresenciais(),
                itemCardapioDTO.foto());
    }

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
