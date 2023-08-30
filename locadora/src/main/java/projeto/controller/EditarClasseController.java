package projeto.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projeto.model.application.ClasseApplication;
import projeto.model.domain.Classe;

@WebServlet(name = "EditarClasseController", urlPatterns = { "/editarClasse" })
public class EditarClasseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Classe classe = ClasseApplication.buscarClassePorId(id);

        request.setAttribute("id", classe.getId());
        request.setAttribute("nome", classe.getNome());
        request.setAttribute("valor", classe.getValor());
        request.setAttribute("prazoDevolucao", classe.getPrazoDevolucao());

        request.getRequestDispatcher("/editarClasse.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        double valor = Double.parseDouble(request.getParameter("valor"));
        String prazoDevolucaoStr = request.getParameter("prazoDevolucao");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date prazoDevolucao = null;

        try {
            prazoDevolucao = dateFormat.parse(prazoDevolucaoStr);
        } catch (ParseException e) {
            // Tratar erro de conversão da data
            e.printStackTrace();
            response.sendRedirect("ErroConversaoData.jsp");
            return;
        }

        ClasseApplication.atualizarClasse(id, nome, valor, prazoDevolucao);

        response.sendRedirect("listarClasse");
    }
}
