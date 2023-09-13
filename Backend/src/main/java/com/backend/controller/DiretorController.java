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

import com.backend.dto.DiretorDTO;
import com.backend.service.DiretorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/diretor")
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @GetMapping
    public List<DiretorDTO> listar() {
        return diretorService.listar();
    }

    @GetMapping("/{id}")
    public DiretorDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return diretorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DiretorDTO criar(@RequestBody @Valid DiretorDTO diretorDto) {
        return diretorService.criar(diretorDto);
    }

    @PutMapping("/{id}")
    public DiretorDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid DiretorDTO diretorDto) {
        return diretorService.atualizar(id, diretorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        diretorService.excluir(id);
    }
    
}
