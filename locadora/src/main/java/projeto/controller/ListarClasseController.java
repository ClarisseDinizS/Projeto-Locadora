package projeto.controller;

import java.io.IOException;
import java.util.List;

import projeto.model.application.ClasseApplication;
import projeto.model.domain.Classe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarClasseController", urlPatterns = { "/listarClasse" })
public class ListarClasseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Classe> listarClasse = ClasseApplication.listarClasse();

        request.setAttribute("listarClasse", listarClasse);
        request.getRequestDispatcher("listarClasse.jsp").forward(request, response);
    }
}
