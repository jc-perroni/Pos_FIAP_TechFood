package com.posfiap.techfood.core.domain.usecases.itemcardapio;

import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioGateway;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;

import java.util.List;

public class FindAllItemCardapioUsecase {

    private final IItemCardapioGateway iItemCardapioGateway;

    private FindAllItemCardapioUsecase(IItemCardapioGateway iItemCardapioGateway) {
        this.iItemCardapioGateway = iItemCardapioGateway;
    }

    public static FindAllItemCardapioUsecase create(IItemCardapioGateway iItemCardapioGateway) {
        return new FindAllItemCardapioUsecase(iItemCardapioGateway);
    }

    public List<ItemCardapio> run(int size, int offset) {
        return this.iItemCardapioGateway.findAll(size, offset);
    }
}
