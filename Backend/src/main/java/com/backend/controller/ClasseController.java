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

import com.backend.dto.ClasseDTO;
import com.backend.service.ClasseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/classe")
@Tag(name = "ClasseController", description = "Controlador para operações relacionadas a classes")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    @Operation(summary = "Listar classes", description = "Retorna uma lista de classes cadastradas no sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de classes retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public List<ClasseDTO> listar() {
        return classeService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar classe por ID", description = "Retorna informações detalhadas de uma classe com base no seu ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Informações da classe retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public ClasseDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return classeService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar classe", description = "Cria uma nova classe com base nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Criado classe com sucesso."),
            @ApiResponse(responseCode = "400", description = "Caso falte algum parâmetro obrigatório ou o formato dos dados esteja incorreto.")
    })
    public ClasseDTO criar(@RequestBody @Valid ClasseDTO classeDTO) {
        return classeService.criar(classeDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar classe", description = "Atualiza as informações de uma classe existente com base no seu ID e nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Criado classe com sucesso."),
            @ApiResponse(responseCode = "400", description = "Caso falte algum parâmetro obrigatório ou o formato dos dados esteja incorreto."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public ClasseDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ClasseDTO classeDTO) {
        return classeService.atualizar(id, classeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir classe", description = "Remove uma classe do sistema com base no seu ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Excluída classe com sucesso."),
            @ApiResponse(responseCode = "400", description = "Não é possível excluir esta Classe por estar relacionado a esses Títulos: {títulos}"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o id: {id}")
    })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        classeService.excluir(id);
    }
}
