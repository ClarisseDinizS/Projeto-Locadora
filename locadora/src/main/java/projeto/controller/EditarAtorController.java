package projeto.controller;

import java.io.IOException;

import projeto.model.application.AtorApplication;
import projeto.model.domain.Ator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarAtorController", urlPatterns = { "/editarAtor" })
public class EditarAtorController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Ator ator = AtorApplication.buscarAtorPorId(id);

        request.setAttribute("id", ator.getId());
        request.setAttribute("nome", ator.getNome());

        request.getRequestDispatcher("/editarAtor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");

        AtorApplication.atualizarAtor(id, nome);

        response.sendRedirect("listarAtor");
    }
}
