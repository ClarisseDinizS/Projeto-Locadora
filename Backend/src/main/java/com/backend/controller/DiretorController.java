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

import com.backend.dto.DiretorDTO;
import com.backend.service.DiretorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/diretor")
@Tag(name = "DiretorController", description = "Controlador para operações relacionadas a diretores")
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @GetMapping
    @Operation(summary = "Listar diretores", description = "Retorna uma lista de diretores cadastrados no sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de diretores retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public List<DiretorDTO> listar() {
        return diretorService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar diretor por ID", description = "Retorna informações detalhadas de um diretor com base no seu ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Informações do diretor retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public DiretorDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return diretorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar diretor", description = "Cria um novo diretor com base nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Criado diretor com sucesso."),
            @ApiResponse(responseCode = "400", description = "Caso falte algum parâmetro obrigatório ou o formato dos dados esteja incorreto.")
    })
    public DiretorDTO criar(@RequestBody @Valid DiretorDTO diretorDto) {
        return diretorService.criar(diretorDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar diretor", description = "Atualiza as informações de um diretor existente com base no seu ID e nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Criado diretor com sucesso."),
            @ApiResponse(responseCode = "400", description = "Caso falte algum parâmetro obrigatório ou o formato dos dados esteja incorreto."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public DiretorDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid DiretorDTO diretorDto) {
        return diretorService.atualizar(id, diretorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir diretor", description = "Remove um diretor do sistema com base no seu ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Excluído diretor com sucesso."),
            @ApiResponse(responseCode = "400", description = "Não é possível excluir este Diretor por estar relacionado a esses Títulos: {títulos}"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        diretorService.excluir(id);
    }

}
