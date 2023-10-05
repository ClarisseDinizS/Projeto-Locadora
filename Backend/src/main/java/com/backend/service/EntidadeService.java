package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.exception.RelationFoundException;
import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Diretor;
import com.backend.model.Titulo;
import com.backend.repository.TituloRepository;

@Service
public class EntidadeService {

    private final TituloRepository tituloRepository;

    public EntidadeService(TituloRepository tituloRepository) {
        this.tituloRepository = tituloRepository;
    }

    public void verificarRelacoesComTitulos(Object object, String mensagemErro) {
        List<Titulo> titulos = null;

        switch (object.getClass().getSimpleName()) {
            case "Classe":
                titulos = tituloRepository.findByClasse((Classe) object);
                break;
            case "Diretor":
                titulos = tituloRepository.findByDiretor((Diretor) object);
                break;

            case "Ator":
                titulos = tituloRepository.findByAtores((Ator) object);
                break;
            default:
                break;
        }

        if (!titulos.isEmpty()) {

            StringBuilder mensagem = new StringBuilder(mensagemErro + "\n");

            for (Titulo titulo : titulos) {
                mensagem.append(titulo.getNome());
                if (titulos.indexOf(titulo) < titulos.size() - 1) {
                    mensagem.append(", ");
                }
                mensagem.append("\n");
            }

            throw new RelationFoundException(mensagem.toString());
        }
    }
}
