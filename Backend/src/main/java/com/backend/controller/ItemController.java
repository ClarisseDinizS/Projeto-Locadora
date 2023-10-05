package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ItemDTO;
import com.backend.service.ItemService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDTO> listar() {
        return itemService.listar();
    }

    @GetMapping("/{id}")
    public ItemDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return itemService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemDTO criar(@RequestBody @Valid ItemDTO itemDto) {
        return itemService.criar(itemDto);
    }

    @PutMapping("/{id}")
    public ItemDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ItemDTO itemDto) {
        return itemService.atualizar(id, itemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        itemService.excluir(id);
    }
}
