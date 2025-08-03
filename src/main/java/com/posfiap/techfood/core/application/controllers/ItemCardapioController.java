package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.ItemCardapioDTO;
import com.posfiap.techfood.core.application.gateways.ItemCardapioGatewayImp;
import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioDataSource;
import com.posfiap.techfood.core.application.presenters.ItemCardapioPresenter;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;
import com.posfiap.techfood.core.domain.mapper.ItemCardapioMapper;
import com.posfiap.techfood.core.domain.usecases.itemcardapio.*;

import java.util.List;

public class ItemCardapioController {

    private final IItemCardapioDataSource dataSource;

    public ItemCardapioController(IItemCardapioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ItemCardapioController create(IItemCardapioDataSource dataSource) {
        return new ItemCardapioController(dataSource);
    }

    public List<ItemCardapioDTO> findAllItemCardapio(int size, int offset) {
        var itemCardapioGateway = ItemCardapioGatewayImp.create(dataSource);
        var findAllItemCardapioUsecase = FindAllItemCardapioUsecase.create(itemCardapioGateway);

        try {
            List<ItemCardapio> itemCardapioList = findAllItemCardapioUsecase.run(size, offset);
            return itemCardapioList.stream().map(ItemCardapioPresenter::toDto).toList();
        } catch (Exception e) {
            return null;
        }
    }

    public ItemCardapioDTO findItemCardapioById(Long id) {
        var itemCardapioGateway = ItemCardapioGatewayImp.create(dataSource);
        var findItemCardapioByIdUsecase = FindItemCardapioByIdUsecase.create(itemCardapioGateway);

        try {
            ItemCardapio itemCardapio = findItemCardapioByIdUsecase.run(id);
            return ItemCardapioPresenter.toDto(itemCardapio);
        } catch (Exception e) {
            return null;
        }
    }

    public ItemCardapioDTO inserirItemCardapio(ItemCardapioDTO itemCardapioDTO) {
        var itemCardapioGateway = ItemCardapioGatewayImp.create(dataSource);
        var insertItemCardapioUsecase = InsertItemCardapioUsecase.create(itemCardapioGateway);

        try {
            ItemCardapio itemCardapio = insertItemCardapioUsecase.run(ItemCardapioMapper.toEntity(itemCardapioDTO));
            return ItemCardapioPresenter.toDto(itemCardapio);
        } catch (Exception e) {
            return  null;
        }
    }

    public ItemCardapioDTO atualizarItemCardapio(ItemCardapioDTO itemCardapioDTO) {
        var itemCardapioGateway = ItemCardapioGatewayImp.create(dataSource);
        var updateItemCardapioUsecase = UpdateItemCardapioUsecase.create(itemCardapioGateway);

        try {
            ItemCardapio itemCardapio = updateItemCardapioUsecase.run(ItemCardapioMapper.toEntity(itemCardapioDTO));
            return ItemCardapioPresenter.toDto(itemCardapio);
        } catch (Exception e) {
            return  null;
        }
    }

    public Integer excluirItemCardapio(Long id) {
        var itemCardapioGateway = ItemCardapioGatewayImp.create(dataSource);
        var deleteItemCardapioUsecase = DeleteItemCardapioUsecase.create(itemCardapioGateway);

        try {
            return deleteItemCardapioUsecase.run(id);
        } catch (Exception e) {
            return null;
        }
    }
}
