package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.ClienteDTO;
import com.backend.enums.SimNao;
import com.backend.model.Cliente;

@Component
public class ClienteMapper {

    public ClienteDTO paraDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(cliente.getId(), cliente.getNome(),
                cliente.getDataNascimento(), cliente.getSexo(), cliente.getEstahAtivo().getValor());
    }

    public Cliente paraEntidade(ClienteDTO clienteDto) {
        if (clienteDto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        if (clienteDto.id() != null) {
            cliente.setId(clienteDto.id());
        }

        cliente.setNome(clienteDto.nome());
        cliente.setDataNascimento(clienteDto.dataNascimento());
        cliente.setSexo(clienteDto.sexo());
        cliente.setEstahAtivo(converterValorEstahAtivo(clienteDto.estahAtivo()));
        return cliente;

    }

    public SimNao converterValorEstahAtivo(String valor) {
        if (valor == null) {
            return null;
        }

        return switch (valor) {
            case "Sim" -> SimNao.SIM;
            case "Não" -> SimNao.NAO;
            default -> throw new IllegalArgumentException("Status Inválido: " + valor);
        };
    }
}
