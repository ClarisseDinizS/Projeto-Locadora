package com.backend.controller;

import com.backend.dto.ClienteDTO;
import com.backend.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClienteDTO criar(@RequestBody @Valid ClienteDTO clienteDto) {
        return clienteService.criar(clienteDto);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ClienteDTO clienteDto) {
        return clienteService.atualizar(id, clienteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        clienteService.excluir(id);
    }
}