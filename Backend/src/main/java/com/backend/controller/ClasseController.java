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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/classe")
public class ClasseController {
    
    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public List<ClasseDTO> listar() {
        return classeService.listar();
    }

    @GetMapping("/{id}")
    public ClasseDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return classeService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClasseDTO criar(@RequestBody @Valid ClasseDTO classeDTO) {
        return classeService.criar(classeDTO);
    }

    @PutMapping("/{id}")
    public ClasseDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ClasseDTO classeDTO) {
        return classeService.atualizar(id, classeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        classeService.excluir(id);
    }
}
