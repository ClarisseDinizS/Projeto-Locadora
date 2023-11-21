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

import com.backend.dto.LocacaoDTO;
import com.backend.service.LocacaoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/locacao")
public class LocacaoController {
    
    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @GetMapping
    public List<LocacaoDTO> listar() {
        return locacaoService.listar();
    }
    
    @GetMapping("/{id}")
    public LocacaoDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return locacaoService.buscarPorId(id);
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocacaoDTO criar(@RequestBody @Valid LocacaoDTO locacaoDto) {
        return locacaoService.criar(locacaoDto);
    }
    
    @PutMapping("/{id}")
    public LocacaoDTO atualizar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid LocacaoDTO locacaoDto) {
        return locacaoService.atualizar(id, locacaoDto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        locacaoService.excluir(id);
    }

}
