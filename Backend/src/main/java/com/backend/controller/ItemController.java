package com.backend.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="ItemController", description = "Fornece serviços web REST para acesso e manipulação de dados de itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @Operation( summary = "Listar os itens",
            description = "Retorna a lista de itens do sistema",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o item seja incluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso não tenha sido possível cadastrar o item")
            })
    public List<ItemDTO> listar() {
        return itemService.listar();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Buscar item por ID",
            description = "Retorna as informações do item pelo ID fornecido",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o item seja encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o item não for encontrado pelo id")
            })
    public ItemDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return itemService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation( summary = "Criar item",
            description = "Cria um item com todos os atributos dele",
            responses ={
                    @ApiResponse(responseCode = "201", description = "Caso o item seja criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Caso não seja possivel criar o item")
            })
    public ItemDTO criar(@RequestBody @Valid ItemDTO itemDto) {
        return itemService.criar(itemDto);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualizar item",
            description = "Atualiza informações dentro de um item já criado",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o item seja atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o item não for encontrado pelo id")
            })
    public ItemDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ItemDTO itemDto) {
        return itemService.atualizar(id, itemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation( summary = "Deletar item",
            description = "Deleta um item do banco de dados",
            responses ={
                    @ApiResponse(responseCode = "204", description = "Caso o item seja excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o id a ser excluido não seja encontrado")
            })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        itemService.excluir(id);
    }
}
