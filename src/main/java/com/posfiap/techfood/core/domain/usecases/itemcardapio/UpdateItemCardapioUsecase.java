package com.posfiap.techfood.core.domain.usecases.itemcardapio;

import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioGateway;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;
import com.posfiap.techfood.core.domain.exceptions.ItemCardapioNaoEncontradoException;

import java.util.Optional;

public class UpdateItemCardapioUsecase {

    private final IItemCardapioGateway iItemCardapioGateway;

    private UpdateItemCardapioUsecase(IItemCardapioGateway iItemCardapioGateway) {
        this.iItemCardapioGateway = iItemCardapioGateway;
    }

    public static UpdateItemCardapioUsecase create(IItemCardapioGateway iItemCardapioGateway) {
        return new UpdateItemCardapioUsecase(iItemCardapioGateway);
    }

    public ItemCardapio run(ItemCardapio itemCardapio) {
        Optional<ItemCardapio> iItemCardapio = this.iItemCardapioGateway.findById(itemCardapio.getId());
        if (iItemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException(
                    "Item do cardápio com id " + itemCardapio.getId() + " não encontrado na base dados");
        }
        return this.iItemCardapioGateway.update(itemCardapio, itemCardapio.getId());
    }
}
