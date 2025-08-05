package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.ItemCardapioDTO;
import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioDataSource;
import com.posfiap.techfood.core.application.interfaces.itemcardapio.IItemCardapioGateway;
import com.posfiap.techfood.core.domain.entities.ItemCardapio;
import com.posfiap.techfood.core.domain.mapper.ItemCardapioMapper;

import java.util.List;
import java.util.Optional;

public class ItemCardapioGatewayImp implements IItemCardapioGateway {

    private final IItemCardapioDataSource dataSource;

    private ItemCardapioGatewayImp(IItemCardapioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ItemCardapioGatewayImp create(IItemCardapioDataSource dataSource) {
        return new ItemCardapioGatewayImp(dataSource);
    }

    @Override
    public Optional<ItemCardapio> findById(Long id) {
        Optional<ItemCardapioDTO> itemCardapioDTOOptional = this.dataSource.findById(id);
        return itemCardapioDTOOptional.map(ItemCardapioMapper::toEntity);
    }

    @Override
    public List<ItemCardapio> findAll(int size, int offset) {
        List<ItemCardapioDTO> itemCardapioDTOList = this.dataSource.findAll(size, offset);
        return itemCardapioDTOList.stream().map(ItemCardapioMapper::toEntity).toList();
    }

    @Override
    public ItemCardapio update(ItemCardapio itemCardapio, long id) {
        ItemCardapioDTO itemCardapioDTO = ItemCardapioMapper.toDto(itemCardapio);
        return ItemCardapioMapper.toEntity(this.dataSource.update(itemCardapioDTO, id));
    }

    @Override
    public ItemCardapio save(ItemCardapio itemCardapio) {
        ItemCardapioDTO itemCardapioDTO = ItemCardapioMapper.toDto(itemCardapio);
        return ItemCardapioMapper.toEntity(this.dataSource.save(itemCardapioDTO));
    }

    @Override
    public Integer delete(long id) {
        return this.dataSource.delete(id);
    }
}
