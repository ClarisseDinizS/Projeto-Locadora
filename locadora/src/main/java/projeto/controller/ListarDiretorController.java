package projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projeto.model.application.DiretorApplication;
import projeto.model.domain.Diretor;

@WebServlet(name = "ListarDiretorController", urlPatterns = { "/listarDiretor" })
public class ListarDiretorController extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        List<Diretor> listarDiretor = DiretorApplication.listarDiretor();

        request.setAttribute("listarDiretor", listarDiretor);
        request.getRequestDispatcher("listarDiretor.jsp").forward(request, response);
    }
}
