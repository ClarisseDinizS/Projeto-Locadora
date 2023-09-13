package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.DiretorDTO;
import com.backend.model.Diretor;

@Component
public class DiretorMapper {

    public DiretorDTO paraDTO(Diretor diretor){
        if(diretor == null){
            return null;
        }
         return new DiretorDTO(diretor.getId(), diretor.getNome());
    }

    public Diretor paraEntidade(DiretorDTO diretorDto){
       if(diretorDto == null){
            return null;
        } 

        Diretor diretor = new Diretor();
        if(diretorDto.id() != null){
            diretor.setId(diretorDto.id());
        }

        diretor.setNome(diretorDto.nome());

        return diretor;

    }
    
}
