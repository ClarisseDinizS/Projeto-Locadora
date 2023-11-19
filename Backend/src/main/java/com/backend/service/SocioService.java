package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.SocioDTO;
import com.backend.dto.mapper.SocioMapper;
import com.backend.enums.SimNao;
import com.backend.exception.RegistroNotFoundException;
import com.backend.model.Cliente;
import com.backend.model.Socio;
import com.backend.repository.ClienteRepository;
import com.backend.repository.SocioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SocioService {

    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;
    private final ClienteRepository clienteRepository;

    public SocioService(SocioRepository socioRepository, SocioMapper socioMapper,
            ClienteRepository clienteRepository) {
        this.socioRepository = socioRepository;
        this.socioMapper = socioMapper;
        this.clienteRepository = clienteRepository;
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

                    return socioMapper.paraDTO(socioRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        // socioRepository.delete(socioRepository.findById(id)
        // .orElseThrow(() -> new RegistroNotFoundException(id)));

        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));

        socio.setEstahAtivo(SimNao.NAO);
        socioRepository.save(socio);
    }
}
