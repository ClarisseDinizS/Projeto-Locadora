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

import com.backend.dto.TituloDTO;
import com.backend.service.TituloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/titulo")
@Tag(name = "TituloController", description = "Controlador para operações relacionadas a títulos")
public class TituloController {

    private final TituloService tituloService;

    public TituloController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @GetMapping
    @Operation(summary = "Listar títulos", description = "Retorna uma lista de títulos cadastrados no sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de títulos retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public List<TituloDTO> listar() {
        return tituloService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar título por ID", description = "Retorna informações detalhadas de um título com base no seu ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Informações do título retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public TituloDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return tituloService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar título", description = "Cria um novo título com base nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Criado título com sucesso."),
            @ApiResponse(responseCode = "400", description = "Caso falte algum parâmetro obrigatório ou o formato dos dados esteja incorreto.")
    })
    public TituloDTO criar(@RequestBody @Valid TituloDTO tituloDto) {
        return tituloService.criar(tituloDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar título", description = "Atualiza as informações de um título existente com base no seu ID e nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Criado título com sucesso."),
            @ApiResponse(responseCode = "400", description = "Caso falte algum parâmetro obrigatório ou o formato dos dados esteja incorreto."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public TituloDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid TituloDTO tituloDto) {
        return tituloService.atualizar(id, tituloDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir título", description = "Remove um título do sistema com base no seu ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Excluído título com sucesso."),
            @ApiResponse(responseCode = "400", description = "Não é possível excluir este Título por estar relacionado a esses Itens: {itens}"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        tituloService.excluir(id);
    }
}
