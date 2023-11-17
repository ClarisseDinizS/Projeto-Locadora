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

import com.backend.dto.SocioDTO;
import com.backend.service.SocioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/socio")
@Tag(name="SocioController", description = "Fornece serviços web REST para acesso e manipulação de dados de sócios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    @Operation( summary = "Listar sócios",
            description = "Retorna a lista de sócios do sistema",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o sócio seja incluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso não tenha sido possível cadastrar o sócio")
            })
    public List<SocioDTO> listar() {
        return socioService.listar();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Buscar sócio por ID",
            description = "Retorna as informações do sócio pelo ID fornecido",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o sócio seja encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o sócio não for encontrado pelo id")
            })
    public SocioDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return socioService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation( summary = "Criar sócio",
            description = "Cria um sócio com todos os atributos dele",
            responses ={
                    @ApiResponse(responseCode = "201", description = "Caso o sócio seja criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Caso não seja possivel criar o sócio")
            })
    public SocioDTO criar(@RequestBody @Valid SocioDTO socioDTO) {
        return socioService.criar(socioDTO);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualizar sócio",
            description = "Atualiza informações dentro de um sócio já criado",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o sócio seja atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o sócio não for encontrado pelo id")
            })
    public SocioDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid SocioDTO socioDTO) {
        return socioService.atualizar(id, socioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation( summary = "Deletar sócio",
            description = "Deleta um sócio do banco de dados",
            responses ={
                    @ApiResponse(responseCode = "204", description = "Caso o sócio seja excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o id a ser excluido não seja encontrado")
            })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        socioService.excluir(id);
    }
}
