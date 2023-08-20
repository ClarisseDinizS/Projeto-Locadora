package com.projeto.model.application;

import java.util.List;

import com.projeto.dao.AtorDAO;
import com.projeto.model.domain.Ator;

public class AtorApplication {

    public static List<Ator> listarAtor() {
        AtorDAO atorDAO = new AtorDAO();
        return atorDAO.listarAtor();
    }

    public static int incluirAtor(String nome) {

        if (nome == null || nome.equals(""))
            return 1;

        Ator ator = new Ator();
        ator.setNome(nome);

        AtorDAO atorDAO = new AtorDAO();

        try {
            atorDAO.inserirAtor(ator);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static Ator buscarAtorPorId(int id) {
        AtorDAO atorDAO = new AtorDAO();
        return atorDAO.buscarAtorPorId(id);
    }

    public static int atualizarAtor(int id, String novoNome) {
        AtorDAO atorDAO = new AtorDAO();
        Ator ator = atorDAO.buscarAtorPorId(id);

        if (ator == null) {
            return 1; // Ator não encontrado
        }

        ator.setNome(novoNome);
        try {
            atorDAO.atualizarAtor(ator);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static int excluirAtor(int id) {
        AtorDAO atorDAO = new AtorDAO();
        Ator ator = atorDAO.buscarAtorPorId(id);

        if (ator == null) {
            return 1; // Ator não encontrado
        }

        try {
            atorDAO.excluirAtor(id);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }
}
