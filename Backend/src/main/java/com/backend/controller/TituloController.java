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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/titulo")
public class TituloController {

    private final TituloService tituloService;

    public TituloController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @GetMapping
    public List<TituloDTO> listar() {
        return tituloService.listar();
    }

    @GetMapping("/{id}")
    public TituloDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return tituloService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TituloDTO criar(@RequestBody @Valid TituloDTO tituloDto) {
        return tituloService.criar(tituloDto);
    }

    @PutMapping("/{id}")
    public TituloDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid TituloDTO tituloDto) {
        return tituloService.atualizar(id, tituloDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        tituloService.excluir(id);
    }
}
