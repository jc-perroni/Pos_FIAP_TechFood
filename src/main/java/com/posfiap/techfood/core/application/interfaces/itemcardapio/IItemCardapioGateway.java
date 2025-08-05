package com.posfiap.techfood.core.application.interfaces.itemcardapio;

import com.posfiap.techfood.core.domain.entities.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface IItemCardapioGateway {
    Optional<ItemCardapio> findById(Long id);
    List<ItemCardapio> findAll(int size, int offset);
    ItemCardapio update(ItemCardapio itemCardapio, long id);
    ItemCardapio save(ItemCardapio itemCardapio);
    Integer delete(long id);
}
