package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.AtorDTO;
import com.backend.dto.mapper.AtorMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.AtorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AtorService {

    private final AtorRepository atorRepository;
    private final AtorMapper atorMapper;
    private final EntidadeService entidadeService;

    public AtorService(AtorRepository atorRepository, AtorMapper atorMapper,
            EntidadeService entidadeService) {
        this.atorRepository = atorRepository;
        this.atorMapper = atorMapper;
        this.entidadeService = entidadeService;
    }

    public List<AtorDTO> listar() {
        return atorRepository.findAll().stream().map(atorMapper::paraDTO).collect(Collectors.toList());
    }

    public AtorDTO buscarPorId(@NotNull @Positive Long id) {
        return atorRepository.findById(id).map(atorMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public AtorDTO criar(@Valid @NotNull AtorDTO atorDto) {
        return atorMapper.paraDTO(atorRepository.save(atorMapper.paraEntidade(atorDto)));
    }

    public AtorDTO atualizar(@NotNull @Positive Long id, @Valid AtorDTO atorDto) {
        return atorRepository.findById(id)
                .map(registro -> {
                    registro.setNome(atorDto.nome());

                    return atorMapper.paraDTO(atorRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        entidadeService.verificarRelacoes(atorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));

        atorRepository.delete(atorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}