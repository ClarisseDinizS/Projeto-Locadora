package com.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Ator> listar(){
        return atorRepository.findAll();
    }
}
