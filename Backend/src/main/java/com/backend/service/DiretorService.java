package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.DiretorDTO;
import com.backend.dto.mapper.DiretorMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.exception.RelationFoundException;
import com.backend.model.Diretor;
import com.backend.model.Titulo;
import com.backend.repository.DiretorRepository;
import com.backend.repository.TituloRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DiretorService {

    private final DiretorRepository diretorRepository;
    private final DiretorMapper diretorMapper;
    private final TituloRepository tituloRepository;
    private final EntidadeService entidadeService;

    public DiretorService(DiretorRepository diretorRepository, DiretorMapper diretorMapper,
            TituloRepository tituloRepository, EntidadeService entidadeService) {
        this.diretorRepository = diretorRepository;
        this.diretorMapper = diretorMapper;
        this.tituloRepository = tituloRepository;
        this.entidadeService = entidadeService;
    }

    public List<DiretorDTO> listar() {
        return diretorRepository.findAll().stream().map(diretorMapper::paraDTO).collect(Collectors.toList());
    }

    public DiretorDTO buscarPorId(@NotNull @Positive Long id) {
        return diretorRepository.findById(id).map(diretorMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
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
        // diretorRepository.delete(diretorRepository.findById(id)
        // .orElseThrow(() -> new RegistroNotFoundException(id)));
        Diretor diretor = diretorRepository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<Titulo> titulo = tituloRepository.findByDiretor(diretor);

        entidadeService.verificarRelacoesComTitulos(titulo,
                "Não é possível excluir este diretor por estar relacionado a esses títulos:");

        diretorRepository.delete(diretor);
    }
}
