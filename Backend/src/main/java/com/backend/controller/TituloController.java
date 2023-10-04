package com.backend.controller;

import com.backend.dto.TituloDTO;
import com.backend.service.TituloService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
