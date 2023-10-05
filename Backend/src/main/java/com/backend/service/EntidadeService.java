package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.exception.RelationFoundException;
import com.backend.model.Titulo;

@Service
public class EntidadeService {

    public void verificarRelacoesComTitulos(List<Titulo> titulos, String mensagemErro) {

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
