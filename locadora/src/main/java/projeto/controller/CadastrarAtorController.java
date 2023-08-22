package projeto.controller;

import java.io.IOException;

import projeto.model.application.AtorApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarAtorController", urlPatterns = { "/salvarAtor" })
public class CadastrarAtorController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        
        int resultado = AtorApplication.incluirAtor(nome);

        if (resultado == 0) {
            // Inclusão bem-sucedida
            response.sendRedirect("listarAtor");
        } else if (resultado == 1) {
            // Tratar o erro de nome vazio
            response.sendRedirect("ErroNomeVazio.jsp");
        } else if (resultado == 2) {
            // Tratar o erro de inserção
            response.sendRedirect("ErroInsercao.jsp");
        } else {
            // Erro desconhecido
            response.sendRedirect("ErroDesconhecido.jsp");
        }
    }
}
