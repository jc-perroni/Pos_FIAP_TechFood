package com.posfiap.techfood.core.domain.usecases.itemcardapio;

import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioGateway;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;
import com.posfiap.techfood.core.domain.exceptions.ItemCardapioNaoEncontradoException;

import java.util.Optional;

public class DeleteItemCardapioUsecase {

    private final IItemCardapioGateway iItemCardapioGateway;

    private DeleteItemCardapioUsecase(IItemCardapioGateway iItemCardapioGateway) {
        this.iItemCardapioGateway = iItemCardapioGateway;
    }

    public static DeleteItemCardapioUsecase create(IItemCardapioGateway iItemCardapioGateway) {
        return new DeleteItemCardapioUsecase(iItemCardapioGateway);
    }

    public Integer run(Long id) {
        Optional<ItemCardapio> iItemCardapio = this.iItemCardapioGateway.findById(id);
        if (iItemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com id " + id + " não encontrado na base dados");
        }

        return this.iItemCardapioGateway.delete(id);
    }
}
