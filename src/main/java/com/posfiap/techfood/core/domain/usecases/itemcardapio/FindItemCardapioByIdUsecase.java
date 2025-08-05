package com.posfiap.techfood.core.domain.usecases.itemcardapio;

import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioGateway;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;
import com.posfiap.techfood.core.domain.exceptions.ItemCardapioNaoEncontradoException;

import java.util.Optional;

public class FindItemCardapioByIdUsecase {

    private final IItemCardapioGateway iItemCardapioGateway;

    private FindItemCardapioByIdUsecase(IItemCardapioGateway iItemCardapioGateway) {
        this.iItemCardapioGateway = iItemCardapioGateway;
    }

    public static FindItemCardapioByIdUsecase create(IItemCardapioGateway iItemCardapioGateway) {
        return new FindItemCardapioByIdUsecase(iItemCardapioGateway);
    }

    public ItemCardapio run(Long id) {
        Optional<ItemCardapio> iItemCardapio = this.iItemCardapioGateway.findById(id);
        if (iItemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com id " + id + " não encontrado na base dados");
        }

        return iItemCardapio.get();
    }
}
