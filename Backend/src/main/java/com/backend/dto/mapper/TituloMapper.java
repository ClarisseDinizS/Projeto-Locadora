package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.TituloDTO;
import com.backend.model.Titulo;

@Component
public class TituloMapper {

    public TituloDTO paraDTO(Titulo titulo) {
        if (titulo == null) {
            return null;
        }
        return new TituloDTO(titulo.getId(), titulo.getNome(), titulo.getAno(), titulo.getSinopse(),
                titulo.getCategoria(), titulo.getDiretor(), titulo.getClasse(), titulo.getAtores());
    }

    public Titulo paraEntidade(TituloDTO tituloDto) {
        if (tituloDto == null) {
            return null;
        }

        Titulo titulo = new Titulo();
        if (tituloDto.id() != null) {
            titulo.setId(tituloDto.id());
        }

        titulo.setNome(tituloDto.nome());
        titulo.setAno(tituloDto.ano());
        titulo.setSinopse(tituloDto.sinopse());
        titulo.setCategoria(tituloDto.categoria());
        titulo.setDiretor(tituloDto.diretor());
        titulo.setClasse(tituloDto.classe());
        titulo.setAtores(tituloDto.atores());

        return titulo;

    }

}
