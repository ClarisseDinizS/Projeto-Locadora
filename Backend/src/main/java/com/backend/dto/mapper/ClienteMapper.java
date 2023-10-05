package com.backend.dto.mapper;

import com.backend.dto.ClienteDTO;
import com.backend.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO paraDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(cliente.getId(), cliente.getNumeroInscricao(), cliente.getNome(),
                cliente.getDataNascimento(), cliente.getSexo(), cliente.getEstahAtivo());
    }

    public Cliente paraEntidade(ClienteDTO clienteDto) {
        if (clienteDto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        if (clienteDto.id() != null) {
            cliente.setId(clienteDto.id());
        }

        cliente.setNumeroInscricao(clienteDto.numeroInscricao());
        cliente.setNome(clienteDto.nome());
        cliente.setDataNascimento(clienteDto.dataNascimento());
        cliente.setSexo(clienteDto.sexo());
        cliente.setEstahAtivo(clienteDto.estahAtivo());

        return cliente;

    }
}
