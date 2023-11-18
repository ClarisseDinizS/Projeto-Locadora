package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.SocioDTO;
import com.backend.enums.SimNao;
import com.backend.model.Socio;

@Component
public class SocioMapper {

    public SocioDTO paraDTO(Socio socio) {
        if (socio == null) {
            return null;
        }
        return new SocioDTO(socio.getId(), socio.getNumeroInscricao(), socio.getNome(), socio.getDataNascimento(),
                socio.getSexo(), socio.getEstahAtivo().getValor(), socio.getCpf(), socio.getEndereco(),
                socio.getTelefone(), socio.getDependentes());
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
        socio.setDependentes(socioDTO.dependentes());

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
}
