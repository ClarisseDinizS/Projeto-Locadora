package com.backend.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.backend.model.Ator;
import com.backend.repository.AtorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AtorService {

    private final AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public List<Ator> listar() {
        return atorRepository.findAll();
    }

    public Optional<Ator> buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorRepository.findById(id);
    }

    public Ator criar(@Valid Ator ator) {
        return atorRepository.save(ator);
    }

    public Optional<Ator> atualizar(@NotNull @Positive Long id, @Valid Ator ator) {
        return atorRepository.findById(id)
                .map(registro -> {
                    registro.setNome(ator.getNome());

                    return atorRepository.save(registro);
                });
    }

    public boolean excluir(@PathVariable @NotNull @Positive Long id) {
        return atorRepository.findById(id)
                .map(registro -> {
                    atorRepository.deleteById(id);

                    return true;
                })
                .orElse(false);
    }
    
}
