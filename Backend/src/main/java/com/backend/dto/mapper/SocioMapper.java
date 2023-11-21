package com.backend.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.backend.dto.ClienteDTO;
import com.backend.dto.SocioDTO;
import com.backend.enums.SimNao;
import com.backend.model.Cliente;
import com.backend.model.Socio;

@Component
public class SocioMapper {

    private final ClienteMapper clienteMapper;

    public SocioMapper(ClienteMapper clienteMapper) {
        this.clienteMapper = clienteMapper;
    }

    public SocioDTO paraDTO(Socio socio) {
        if (socio == null) {
            return null;
        }

        List<ClienteDTO> dependentes = socio.getDependentes().stream()
                .map(dependente -> new ClienteDTO(dependente.getId(), dependente.getNumeroInscricao(),
                        dependente.getNome(), dependente.getDataNascimento(), dependente.getSexo(),
                        dependente.getEstahAtivo().getValor()))
                .collect(Collectors.toList());

        return new SocioDTO(socio.getId(), socio.getNumeroInscricao(), socio.getNome(), socio.getDataNascimento(),
                socio.getSexo(), socio.getEstahAtivo().getValor(), socio.getCpf(), socio.getEndereco(),
                socio.getTelefone(), dependentes);
    }

    public Socio paraEntidade(SocioDTO socioDTO) {
        if (socioDTO == null) {
            return null;
        }

        Socio socio = new Socio();
        if (socioDTO.id() != null) {
            socio.setId(socioDTO.id());
        }

        socio.setNumeroInscricao(socioDTO.numeroInscricao());
        socio.setNome(socioDTO.nome());
        socio.setDataNascimento(socioDTO.dataNascimento());
        socio.setSexo(socioDTO.sexo());
        socio.setEstahAtivo(converterValorEstahAtivo(socioDTO.estahAtivo()));
        socio.setCpf(socioDTO.cpf());
        socio.setEndereco(socioDTO.endereco());
        socio.setTelefone(socioDTO.telefone());

        List<Cliente> dependentes = socioDTO.dependentes().stream().map(dependenteDTO -> {
            var dependente = new Cliente();
            dependente.setNumeroInscricao(dependenteDTO.numeroInscricao());
            dependente.setNome(dependenteDTO.nome());
            dependente.setDataNascimento(dependenteDTO.dataNascimento());
            dependente.setSexo(dependenteDTO.sexo());
            dependente.setEstahAtivo(this.clienteMapper.converterValorEstahAtivo(dependenteDTO.estahAtivo()));

            return dependente;
        }).collect(Collectors.toList());

        socio.setDependentes(dependentes);

        return socio;
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

    public ClienteMapper getClienteMapper() {
        return clienteMapper;
    }
}
