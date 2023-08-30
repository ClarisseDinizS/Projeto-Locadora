package projeto.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import projeto.model.application.DiretorApplication;

@WebServlet(name = "ExcluirDiretorController", urlPatterns = { "/excluirDiretor" })
public class ExcluirDiretorController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        int resultado = DiretorApplication.excluirDiretor(id);

        if (resultado == 0) {
            // Exclusão bem-sucedida
            response.sendRedirect("listarDiretor");
        } else if (resultado == 1) {
            response.sendRedirect("ErroDiretorNaoEncontrado.jsp");
        } else {
            response.sendRedirect("ErroDesconhecido.jsp");
        }
    }
    
}
