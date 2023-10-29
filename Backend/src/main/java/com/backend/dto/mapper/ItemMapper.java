package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.ItemDTO;
import com.backend.enums.Tipo;
import com.backend.model.Item;

@Component
public class ItemMapper {

    public ItemDTO paraDTO(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemDTO(item.getId(), item.getNumSerie(), item.getDtaAquisicao(),
                item.getTipoItem().getValor(), item.getTitulo());
    }

    public Item paraEntidade(ItemDTO itemDto) {
        if (itemDto == null) {
            return null;
        }

        Item item = new Item();
        if (itemDto.id() != null) {
            item.setId(itemDto.id());
        }

        item.setNumSerie(itemDto.numSerie());
        item.setDtaAquisicao(itemDto.dtaAquisicao());
        item.setTipoItem(converterValorItem(itemDto.tipoItem()));
        item.setTitulo(itemDto.titulo());

        return item;

    }

    public Tipo converterValorItem(String valor) {
        if (valor == null) {
            return null;
        }
        return switch(valor){
            case "Fita" -> Tipo.FITA;
            case "DVD" -> Tipo.DVD;
            case "Blu-Ray" -> Tipo.BLURAY;
            default -> throw new IllegalArgumentException("Tipo Inválido: " + valor);
        };
    }
}
