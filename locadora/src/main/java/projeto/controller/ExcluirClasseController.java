package projeto.controller;

import java.io.IOException;

import projeto.model.application.ClasseApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirClasseController", urlPatterns = { "/excluirClasse" })
public class ExcluirClasseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        int resultado = ClasseApplication.excluirClasse(id);

        if (resultado == 0) {
            // Exclusão bem-sucedida
            response.sendRedirect("listarClasse");
        } else if (resultado == 1) {
            // Tratar o erro de classe não encontrado
            response.sendRedirect("ErroAtorNaoEncontrado.jsp");
        } else {
            // Erro desconhecido
            response.sendRedirect("ErroDesconhecido.jsp");
        }
    }
}
