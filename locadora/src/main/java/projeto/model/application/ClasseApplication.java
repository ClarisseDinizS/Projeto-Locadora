package projeto.model.application;

import java.util.Date;
import java.util.List;

import projeto.dao.ClasseDAO;
import projeto.model.domain.Classe;

public class ClasseApplication {

    public static List<Classe> listarClasse() {
        ClasseDAO classeDAO = new ClasseDAO();
        return classeDAO.listarClasse();
    }

    public static int incluirClasse(String nome, double valor, Date prazoDevolucao) {

        if (nome == null || nome.isEmpty() || valor <= 0 || prazoDevolucao == null) {
            return 1; // Dados inválidos
        }

        Classe classe = new Classe();
        classe.setNome(nome);
        classe.setValor(valor);
        classe.setPrazoDevolucao(prazoDevolucao);

        ClasseDAO classeDAO = new ClasseDAO();

        try {
            classeDAO.inserirClasse(classe);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static Classe buscarClassePorId(int id) {
        ClasseDAO classeDAO = new ClasseDAO();
        return classeDAO.buscarClassePorId(id);
    }

    public static int atualizarClasse(int id, String novoNome, double novoValor, Date novoPrazoDevolucao) {
        ClasseDAO classeDAO = new ClasseDAO();
        Classe classe = classeDAO.buscarClassePorId(id);

        if (classe == null) {
            return 1; // Classe não encontrado
        }

        classe.setNome(novoNome);
        classe.setValor(novoValor);
        classe.setPrazoDevolucao(novoPrazoDevolucao);

        try {
            classeDAO.atualizarClasse(classe);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static int excluirClasse(int id) {
        ClasseDAO classeDAO = new ClasseDAO();
        Classe classe = classeDAO.buscarClassePorId(id);

        if (classe == null) {
            return 1; // Classe não encontrado
        }

        try {
            classeDAO.excluirClasse(id);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }
}
