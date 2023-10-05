package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.AtorDTO;
import com.backend.model.Ator;

@Component
public class AtorMapper {

    public AtorDTO paraDTO(Ator ator) {
        if (ator == null) {
            return null;
        }
        return new AtorDTO(ator.getId(), ator.getNome());
    }

    public Ator paraEntidade(AtorDTO atorDto) {
        if (atorDto == null) {
            return null;
        }

        Ator ator = new Ator();
        if (atorDto.id() != null) {
            ator.setId(atorDto.id());
        }

        ator.setNome(atorDto.nome());

        return ator;

    }

}
