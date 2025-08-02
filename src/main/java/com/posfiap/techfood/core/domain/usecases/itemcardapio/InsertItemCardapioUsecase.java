package com.posfiap.techfood.core.domain.usecases.itemcardapio;

import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioGateway;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;

public class InsertItemCardapioUsecase {

    private final IItemCardapioGateway iItemCardapioGateway;

    private InsertItemCardapioUsecase(IItemCardapioGateway iItemCardapioGateway) {
        this.iItemCardapioGateway = iItemCardapioGateway;
    }

    public static InsertItemCardapioUsecase create(IItemCardapioGateway iItemCardapioGateway) {
        return new InsertItemCardapioUsecase(iItemCardapioGateway);
    }

    public ItemCardapio run(ItemCardapio itemCardapio) {
        return this.iItemCardapioGateway.save(itemCardapio);
    }
}
