package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.ClasseDTO;
import com.backend.model.Classe;

@Component
public class ClasseMapper {
    
    public ClasseDTO paraDTO(Classe classe){
        if(classe == null){
            return null;
        }
         return new ClasseDTO(classe.getId(), classe.getNome(), classe.getValor(), classe.getData());
    }

    public Classe paraEntidade(ClasseDTO classeDTO){
       if(classeDTO == null){
            return null;
        } 

        Classe classe = new Classe();
        if(classeDTO.id() != null){
            classe.setId(classeDTO.id());
        }

        classe.setNome(classeDTO.nome());
        classe.setValor(classeDTO.valor());
        classe.setData(classeDTO.data());

        return classe;

    }
}
