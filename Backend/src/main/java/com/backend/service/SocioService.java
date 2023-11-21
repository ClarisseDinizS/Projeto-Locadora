package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.SocioDTO;
import com.backend.dto.mapper.SocioMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.model.Socio;
import com.backend.repository.SocioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SocioService {

    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;

    public SocioService(SocioRepository socioRepository, SocioMapper socioMapper) {
        this.socioRepository = socioRepository;
        this.socioMapper = socioMapper;
    }

    public List<SocioDTO> listar() {
        return socioRepository.findAll().stream().map(socioMapper::paraDTO).collect(Collectors.toList());
    }

    public List<SocioDTO> listarAtivos() {
        return socioRepository.findAllByEstahAtivo().stream().map(socioMapper::paraDTO).collect(Collectors.toList());
    }

    public List<SocioDTO> listarInativos() {
        return socioRepository.findAllByEstahInativo().stream().map(socioMapper::paraDTO).collect(Collectors.toList());
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
                    Socio socio = socioMapper.paraEntidade(socioDTO);
                    registro.setNumeroInscricao(socioDTO.numeroInscricao());
                    registro.setNome(socioDTO.nome());
                    registro.setDataNascimento(socioDTO.dataNascimento());
                    registro.setSexo(socioDTO.sexo());
                    registro.setEstahAtivo(this.socioMapper.converterValorEstahAtivo(socioDTO.estahAtivo()));
                    registro.setCpf(socioDTO.cpf());
                    registro.setEndereco(socioDTO.endereco());
                    registro.setTelefone(socioDTO.telefone());

                    registro.getDependentes().clear();

                    socio.getDependentes().forEach(registro.getDependentes()::add);
                    socio.getDependentes().forEach(dependente -> {
                        dependente.setEstahAtivo(this.socioMapper.converterValorEstahAtivo(socioDTO.estahAtivo()));
                    });
                    return socioMapper.paraDTO(socioRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        socioRepository.delete(socioRepository.findById(id)
        .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
