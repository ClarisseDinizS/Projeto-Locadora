package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.ClasseDTO;
import com.backend.dto.mapper.ClasseMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.ClasseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ClasseService {
    
    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;

    public ClasseService(ClasseRepository classeRepository, ClasseMapper classeMapper) {
        this.classeRepository = classeRepository;
        this.classeMapper = classeMapper;
    }

    public List<ClasseDTO> listar() {
        return classeRepository.findAll().stream().map(classeMapper::paraDTO).collect(Collectors.toList());
    }

    public ClasseDTO buscarPorId (@NotNull @Positive Long id) {
        return classeRepository.findById(id).map(classeMapper::paraDTO).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public ClasseDTO criar(@Valid @NotNull ClasseDTO classeDto) {
        return classeMapper.paraDTO(classeRepository.save(classeMapper.paraEntidade(classeDto)));
    }

    public ClasseDTO atualizar(@NotNull @Positive Long id, @Valid ClasseDTO classeDto) {
        return classeRepository.findById(id)
                .map(registro -> {
                    registro.setNome(classeDto.nome());
                    registro.setValor(classeDto.valor());
                    registro.setData(classeDto.data());

                    return classeMapper.paraDTO(classeRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        classeRepository.delete(classeRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
