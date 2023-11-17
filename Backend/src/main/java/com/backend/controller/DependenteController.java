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

import com.backend.dto.DependenteDTO;
import com.backend.service.DependenteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/dependente")
@Tag(name="DependenteController", description = "Fornece serviços web REST para acesso e manipulação de dados de dependentes")
public class DependenteController {

    private final DependenteService dependenteService;

    public DependenteController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }

    @GetMapping
    @Operation( summary = "Listar dependentes",
            description = "Retorna a lista de dependentes do sistema",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o dependente seja incluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso não tenha sido possível cadastrar o dependente")
            })
    public List<DependenteDTO> listar() {
        return dependenteService.listar();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Buscar dependente por ID",
            description = "Retorna as informações do dependente pelo ID fornecido",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o dependente seja encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o dependente não for encontrado pelo id")
            })
    public DependenteDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return dependenteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation( summary = "Criar dependente",
            description = "Cria um dependente com todos os atributos dele",
            responses ={
                    @ApiResponse(responseCode = "201", description = "Caso o dependente seja criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Caso não seja possivel criar o dependente")
            })
    public DependenteDTO criar(@RequestBody @Valid DependenteDTO dependenteDTO) {
        return dependenteService.criar(dependenteDTO);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualizar dependente",
            description = "Atualiza informações dentro de um dependente já criado",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o dependente seja atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o dependente não for encontrado pelo id")
            })
    public DependenteDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid DependenteDTO dependenteDTO) {
        return dependenteService.atualizar(id, dependenteDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation( summary = "Deletar dependente",
            description = "Deleta um dependente do banco de dados",
            responses ={
                    @ApiResponse(responseCode = "204", description = "Caso o dependente seja excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o id a ser excluido não seja encontrado")
            })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        dependenteService.excluir(id);
    }
}
