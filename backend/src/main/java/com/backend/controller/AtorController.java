package com.backend.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

import com.backend.dto.AtorDTO;
import com.backend.service.AtorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/ator")
@Tag(name="AtorController", description = "Fornece serviços web REST para acesso e manipulação de dados de atores")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    @Operation( summary = "Listar atores",
            description = "Retorna a lista de atores do sistema",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o ator seja incluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso não tenha sido possível cadastrar o ator")
            })
    public List<AtorDTO> listar() {
        return atorService.listar();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Buscar ator por ID",
            description = "Retorna as informações do ator pelo ID fornecido",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o ator seja encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o ator não for encontrado pelo id")
            })
    public AtorDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation( summary = "Criar ator",
            description = "Cria um ator com todos os atributos dele",
            responses ={
                    @ApiResponse(responseCode = "201", description = "Caso o ator seja criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Caso não seja possivel criar o ator")
            })
    public AtorDTO criar(@RequestBody @Valid AtorDTO atorDto) {
        return atorService.criar(atorDto);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualizar ator",
            description = "Atualiza informações dentro de um ator já criado",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o ator seja atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o ator não for encontrado pelo id")
            })
    public AtorDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid AtorDTO atorDto) {
        return atorService.atualizar(id, atorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation( summary = "Deletar ator",
            description = "Deleta um ator do banco de dados",
            responses ={
                    @ApiResponse(responseCode = "204", description = "Caso o ator seja excluído com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Não é possível excluir este Ator por estar relacionado a algum titulo Títulos"),
                    @ApiResponse(responseCode = "404", description = "Caso o id a ser excluido não seja encontrado")
            })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        atorService.excluir(id);
    }
}