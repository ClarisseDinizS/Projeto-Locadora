package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.DependenteDTO;
import com.backend.dto.mapper.ClienteMapper;
import com.backend.dto.mapper.DependenteMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.DependenteRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final DependenteMapper dependenteMapper;
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public DependenteService(DependenteRepository dependenteRepository, DependenteMapper dependenteMapper,
    ClienteService clienteService, ClienteMapper clienteMapper) {
        this.dependenteRepository = dependenteRepository;
        this.dependenteMapper = dependenteMapper;
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    public List<DependenteDTO> listar() {
        return dependenteRepository.findAll().stream().map(dependenteMapper::paraDTO).collect(Collectors.toList());
    }

    public DependenteDTO buscarPorId(@NotNull @Positive Long id) {
        return dependenteRepository.findById(id).map(dependenteMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public DependenteDTO criar(@Valid @NotNull DependenteDTO dependenteDTO) {
        // Convertendo o DTO para a entidade Cliente
        clienteService.criar(clienteMapper.paraDTO(dependenteMapper.paraEntidade(dependenteDTO)));
        return dependenteMapper.paraDTO(dependenteRepository.save(dependenteMapper.paraEntidade(dependenteDTO)));
    }

    public DependenteDTO atualizar(@NotNull @Positive Long id, @Valid DependenteDTO dependenteDTO) {
        return dependenteRepository.findById(id)
                .map(registro -> {
                    registro.setNumeroInscricao(dependenteDTO.numeroInscricao());
                    registro.setNome(dependenteDTO.nome());
                    registro.setDataNascimento(dependenteDTO.dataNascimento());
                    registro.setSexo(dependenteDTO.sexo());
                    registro.setEstahAtivo(dependenteDTO.estahAtivo());
                    registro.setSocio(dependenteDTO.socio());

                    return dependenteMapper.paraDTO(dependenteRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        dependenteRepository.delete(dependenteRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
