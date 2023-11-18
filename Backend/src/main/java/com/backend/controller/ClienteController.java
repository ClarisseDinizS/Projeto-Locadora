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

import com.backend.dto.ClienteDTO;
import com.backend.service.ClienteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/cliente")
@Tag(name="ClienteController", description = "Fornece serviços web REST para acesso e manipulação de dados de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation( summary = "Listar clientes",
            description = "Retorna a lista de clientes do sistema",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o cliente seja incluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso não tenha sido possível cadastrar o cliente")
            })
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/dependentes")
    @Operation( summary = "Listar clientes que são dependentes",
            description = "Retorna a lista de clientes que são dependentes do sistema",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o cliente seja incluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso não tenha sido possível cadastrar o cliente")
            })
    public List<ClienteDTO> listarDependentes() {
        return clienteService.listarDependentes();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Buscar cliente por ID",
            description = "Retorna as informações do cliete pelo ID fornecido",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o cliente seja encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o cliente não for encontrado pelo id")
            })
    public ClienteDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation( summary = "Criar cliente",
            description = "Cria um cliente com todos os atributos dele",
            responses ={
                    @ApiResponse(responseCode = "201", description = "Caso o cliente seja criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Caso não seja possivel criar o cliente")
            })
    public ClienteDTO criar(@RequestBody @Valid ClienteDTO clienteDto) {
        return clienteService.criar(clienteDto);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualizar cliente",
            description = "Atualiza informações dentro de um cliente já criado",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Caso o cliente seja atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o cliente não for encontrado pelo id")
            })
    public ClienteDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ClienteDTO clienteDto) {
        return clienteService.atualizar(id, clienteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation( summary = "Deletar cliente",
            description = "Deleta um cliente do banco de dados",
            responses ={
                    @ApiResponse(responseCode = "204", description = "Caso o cliente seja excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Caso o id a ser excluido não seja encontrado")
            })
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        clienteService.excluir(id);
    }
}
