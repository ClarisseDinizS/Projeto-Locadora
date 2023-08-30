package projeto.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import projeto.model.application.DiretorApplication;
import projeto.model.domain.Diretor;

@WebServlet(name = "EditarDiretorController", urlPatterns = { "/editarDiretor" })
public class EditarDiretorController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Diretor diretor = DiretorApplication.buscarDiretorPorId(id);

        request.setAttribute("id", diretor.getId());
        request.setAttribute("nome", diretor.getNome());

        request.getRequestDispatcher("/editarDiretor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");

        DiretorApplication.atualizarDiretor(id, nome);

        response.sendRedirect("listarDiretor");
    }
    
}
