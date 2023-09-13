package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.DiretorDTO;
import com.backend.dto.mapper.DiretorMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.DiretorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DiretorService {

    private final DiretorRepository diretorRepository;
    private final DiretorMapper diretorMapper;

    public DiretorService(DiretorRepository diretorRepository, DiretorMapper diretorMapper) {
        this.diretorRepository = diretorRepository;
        this.diretorMapper = diretorMapper;
    }

    public List<DiretorDTO> listar() {
        return diretorRepository.findAll().stream().map(diretorMapper::paraDTO).collect(Collectors.toList());
    }

    public DiretorDTO buscarPorId (@NotNull @Positive Long id) {
        return diretorRepository.findById(id).map(diretorMapper::paraDTO).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public DiretorDTO criar(@Valid @NotNull DiretorDTO diretorDto) {
        return diretorMapper.paraDTO(diretorRepository.save(diretorMapper.paraEntidade(diretorDto)));
    }

    public DiretorDTO atualizar(@NotNull @Positive Long id, @Valid DiretorDTO diretorDto) {
        return diretorRepository.findById(id)
                .map(registro -> {
                    registro.setNome(diretorDto.nome());

                    return diretorMapper.paraDTO(diretorRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        diretorRepository.delete(diretorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
    
}
