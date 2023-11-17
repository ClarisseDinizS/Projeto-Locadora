package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.DependenteDTO;
import com.backend.model.Dependente;

@Component
public class DependenteMapper {

    public DependenteDTO paraDTO(Dependente dependente) {
        if (dependente == null) {
            return null;
        }
        return new DependenteDTO(dependente.getId(), dependente.getNumeroInscricao(), dependente.getNome(),
                dependente.getDataNascimento(), dependente.getSexo(), dependente.getEstahAtivo(),
                dependente.getSocio());
    }

    public Dependente paraEntidade(DependenteDTO dependenteDTO) {
        if (dependenteDTO == null) {
            return null;
        }

        Dependente dependente = new Dependente();
        if (dependenteDTO.id() != null) {
            dependente.setId(dependenteDTO.id());
        }

        dependente.setNumeroInscricao(dependenteDTO.numeroInscricao());
        dependente.setNome(dependenteDTO.nome());
        dependente.setDataNascimento(dependenteDTO.dataNascimento());
        dependente.setSexo(dependenteDTO.sexo());
        dependente.setEstahAtivo(dependenteDTO.estahAtivo());
        dependente.setSocio(dependenteDTO.socio());
        
        return dependente;

    }
}
