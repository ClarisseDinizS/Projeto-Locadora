package com.backend.service;

import com.backend.dto.TituloDTO;
import com.backend.dto.mapper.TituloMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.TituloRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class TituloService {

    private final TituloRepository tituloRepository;
    private final TituloMapper tituloMapper;
    private final EntidadeService entidadeService;

    public TituloService(TituloRepository tituloRepository, TituloMapper tituloMapper,
            EntidadeService entidadeService) {
        this.tituloRepository = tituloRepository;
        this.tituloMapper = tituloMapper;
        this.entidadeService = entidadeService;
    }

    public List<TituloDTO> listar() {
        return tituloRepository.findAll().stream().map(tituloMapper::paraDTO).collect(Collectors.toList());
    }

    public TituloDTO buscarPorId(@NotNull @Positive Long id) {
        return tituloRepository.findById(id).map(tituloMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public TituloDTO criar(@Valid @NotNull TituloDTO tituloDto) {
        return tituloMapper.paraDTO(tituloRepository.save(tituloMapper.paraEntidade(tituloDto)));
    }

    public TituloDTO atualizar(@NotNull @Positive Long id, @Valid TituloDTO tituloDto) {
        return tituloRepository.findById(id)
                .map(registro -> {
                    registro.setNome(tituloDto.nome());
                    registro.setAno(tituloDto.ano());
                    registro.setSinopse(tituloDto.sinopse());
                    registro.setCategoria(tituloDto.categoria());
                    registro.setDiretor(tituloDto.diretor());
                    registro.setClasse(tituloDto.classe());
                    registro.setAtores(tituloDto.atores());

                    return tituloMapper.paraDTO(tituloRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        entidadeService.verificarRelacoes(tituloRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));

        tituloRepository.delete(tituloRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}
