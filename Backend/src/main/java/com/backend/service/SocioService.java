package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.SocioDTO;
import com.backend.dto.mapper.ClienteMapper;
import com.backend.dto.mapper.SocioMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.SocioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SocioService {

    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public SocioService(SocioRepository socioRepository, SocioMapper socioMapper,
            ClienteService clienteService, ClienteMapper clienteMapper) {
        this.socioRepository = socioRepository;
        this.socioMapper = socioMapper;
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    public List<SocioDTO> listar() {
        return socioRepository.findAll().stream().map(socioMapper::paraDTO).collect(Collectors.toList());
    }

    public SocioDTO buscarPorId(@NotNull @Positive Long id) {
        return socioRepository.findById(id).map(socioMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public SocioDTO criar(@Valid @NotNull SocioDTO socioDTO) {
        return socioMapper.paraDTO(socioRepository.save(socioMapper.paraEntidade(socioDTO)));
    }

    public SocioDTO atualizar(@NotNull @Positive Long id, @Valid SocioDTO socioDTO) {
        return socioRepository.findById(id)
                .map(registro -> {
                    registro.setNumeroInscricao(socioDTO.numeroInscricao());
                    registro.setNome(socioDTO.nome());
                    registro.setDataNascimento(socioDTO.dataNascimento());
                    registro.setSexo(socioDTO.sexo());
                    registro.setEstahAtivo(this.socioMapper.converterValorEstahAtivo(socioDTO.estahAtivo()));
                    registro.setCpf(socioDTO.cpf());
                    registro.setEndereco(socioDTO.endereco());
                    registro.setTelefone(socioDTO.telefone());
                    registro.setDependentes(socioDTO.dependentes());

                    return socioMapper.paraDTO(socioRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        socioRepository.delete(socioRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
