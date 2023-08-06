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

@RestController
@RequestMapping("/api/ator")
public class AtorController {

    private final AtorRepository atorRepository;

    public AtorController(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    @GetMapping
    public @ResponseBody List<Ator> listar() {
        return atorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPorId(@PathVariable Long id) {
        return atorRepository.findById(id)
                .map(registro -> ResponseEntity.ok().body(registro))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ator criar(@RequestBody Ator ator) {
        return atorRepository.save(ator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizar(@PathVariable Long id, @RequestBody Ator ator) {
        return atorRepository.findById(id)
                .map(registro -> {
                    registro.setNome(ator.getNome());

                    Ator atualizado = atorRepository.save(registro);

                    return ResponseEntity.ok().body(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return atorRepository.findById(id)
                .map(registro -> {
                    atorRepository.deleteById(id);

                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}