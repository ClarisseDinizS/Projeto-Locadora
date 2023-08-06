package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Ator;
import com.backend.repository.AtorRepository;
import com.backend.service.AtorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/ator")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public @ResponseBody List<Ator> listar() {
        return atorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorService.buscarPorId(id)
                .map(registro -> ResponseEntity.ok().body(registro))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ator criar(@RequestBody @Valid Ator ator) {
        return atorService.criar(ator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Ator ator) {
        return atorService.atualizar(id, ator)
                .map(registro -> ResponseEntity.ok().body(registro))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull @Positive Long id) {
        if (atorService.excluir(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().<Void>build();
    }
}