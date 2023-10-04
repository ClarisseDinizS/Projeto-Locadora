package com.backend.controller;

import com.backend.dto.ItemDTO;
import com.backend.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
