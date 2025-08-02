package com.posfiap.techfood.core.application.interfaces.itemcardapio;

import com.posfiap.techfood.core.application.dto.ItemCardapioDTO;

import java.util.List;
import java.util.Optional;

public interface IItemCardapioDataSource {
    Optional<ItemCardapioDTO> findById(Long id);
    List<ItemCardapioDTO> findAll(int size, int offset);
    ItemCardapioDTO update(ItemCardapioDTO itemCardapioDTO, long id);
    ItemCardapioDTO save(ItemCardapioDTO itemCardapioDTO);
    Integer delete(long id);
}
