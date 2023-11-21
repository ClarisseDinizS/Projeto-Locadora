package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.ClienteDTO;
import com.backend.dto.SocioDTO;
import com.backend.dto.mapper.ClienteMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.ClienteRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream().map(clienteMapper::paraDTO).collect(Collectors.toList());
    }

    public List<ClienteDTO> listarAtivos() {
        return clienteRepository.findAllByEstahAtivo().stream().map(clienteMapper::paraDTO).collect(Collectors.toList());
    }

    public List<ClienteDTO> listarDependentes() {
        return clienteRepository.findAllClientesQueNaoSaoSocios().stream().map(clienteMapper::paraDTO).collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(@NotNull @Positive Long id) {
        return clienteRepository.findById(id).map(clienteMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public ClienteDTO criar(@Valid @NotNull ClienteDTO clienteDto) {
        return clienteMapper.paraDTO(clienteRepository.save(clienteMapper.paraEntidade(clienteDto)));
    }

    public ClienteDTO atualizar(@NotNull @Positive Long id, @Valid ClienteDTO clienteDto) {
        return clienteRepository.findById(id)
                .map(registro -> {
                    registro.setNumeroInscricao(clienteDto.numeroInscricao());
                    registro.setNome(clienteDto.nome());
                    registro.setDataNascimento(clienteDto.dataNascimento());
                    registro.setSexo(clienteDto.sexo());
                    registro.setEstahAtivo(this.clienteMapper.converterValorEstahAtivo(clienteDto.estahAtivo()));

                    return clienteMapper.paraDTO(clienteRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        clienteRepository.delete(clienteRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
