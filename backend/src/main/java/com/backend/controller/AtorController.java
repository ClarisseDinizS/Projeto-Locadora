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

import com.backend.dto.AtorDTO;
import com.backend.service.AtorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/ator")
@Tag(name="AtorController", description = "")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    @Operation(summary = "Listar atores")
    public List<AtorDTO> listar() {
        return atorService.listar();
    }

    @GetMapping("/{id}")
    public AtorDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AtorDTO criar(@RequestBody @Valid AtorDTO atorDto) {
        return atorService.criar(atorDto);
    }

    @PutMapping("/{id}")
    public AtorDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid AtorDTO atorDto) {
        return atorService.atualizar(id, atorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        atorService.excluir(id);
    }
}