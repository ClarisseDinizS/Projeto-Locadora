package projeto.model.application;

import java.util.List;

import projeto.dao.DiretorDAO;
import projeto.model.domain.Diretor;

public class DiretorApplication {
    
    public static List<Diretor> listarDiretor() {
        DiretorDAO diretorDAO = new DiretorDAO();
        return diretorDAO.listarDiretor();
    }

    public static int incluirDiretor(String nome) {

        if (nome == null || nome.equals(""))
            return 1;

        Diretor diretor = new Diretor();
        diretor.setNome(nome);

        DiretorDAO diretorDAO = new DiretorDAO();

        try {
            diretorDAO.inserirDiretor(diretor);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static Diretor buscarDiretorPorId(int id) {
        DiretorDAO diretorDAO = new DiretorDAO();
        return diretorDAO.buscarDiretorPorId(id);
    }

    public static int atualizarDiretor(int id, String novoNome) {
        DiretorDAO diretorDAO = new DiretorDAO();
        Diretor diretor = diretorDAO.buscarDiretorPorId(id);

        if (diretor == null) {
            return 1; 
        }

        diretor.setNome(novoNome);
        try {
            diretorDAO.atualizarDiretor(diretor);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static int excluirDiretor(int id) {
        DiretorDAO diretorDAO = new DiretorDAO();
        Diretor diretor = diretorDAO.buscarDiretorPorId(id);

        if (diretor == null) {
            return 1;
        }

        try {
            diretorDAO.excluirDiretor(id);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }
}
