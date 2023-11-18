package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.ItemDTO;
import com.backend.dto.mapper.ItemMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.ItemRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDTO> listar() {
        return itemRepository.findAll().stream().map(itemMapper::paraDTO).collect(Collectors.toList());
    }

    public ItemDTO buscarPorId(@NotNull @Positive Long id) {
        return itemRepository.findById(id).map(itemMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public ItemDTO criar(@Valid @NotNull ItemDTO itemDto) {
        return itemMapper.paraDTO(itemRepository.save(itemMapper.paraEntidade(itemDto)));
    }

    public ItemDTO atualizar(@NotNull @Positive Long id, @Valid ItemDTO itemDto) {
        return itemRepository.findById(id)
                .map(registro -> {
                    registro.setNumSerie(itemDto.numSerie());
                    registro.setDtaAquisicao(itemDto.dtaAquisicao());
                    registro.setTipoItem(this.itemMapper.converterTipoItem(itemDto.tipoItem()));
                    registro.setTitulo(itemDto.titulo());

                    return itemMapper.paraDTO(itemRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        itemRepository.delete(itemRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
