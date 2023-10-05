package com.backend.dto.mapper;

import com.backend.dto.ItemDTO;
import com.backend.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO paraDTO(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemDTO(item.getId(), item.getNumserie(), item.getDtaquisicao(), item.getTipoItem(),
                item.getTitulo());
    }

    public Item paraEntidade(ItemDTO itemDto) {
        if (itemDto == null) {
            return null;
        }

        Item item = new Item();
        if (itemDto.id() != null) {
            item.setId(itemDto.id());
        }

        item.setNumserie(itemDto.numserie());
        item.setDtaquisicao(itemDto.dtaquisicao());
        item.setTipoItem(itemDto.tipoItem());
        item.setTitulo(itemDto.titulo());

        return item;

    }
}
