package projeto.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import projeto.model.application.ClasseApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarClasseController", urlPatterns = { "/salvarClasse" })
public class CadastrarClasseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        double valor = Double.parseDouble(request.getParameter("valor"));
        String prazoDevolucaoStr = request.getParameter("prazoDevolucao");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date prazoDevolucao = null;

        try {
            prazoDevolucao = sdf.parse(prazoDevolucaoStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Tratar erro de conversão de data, se necessário
            response.sendRedirect("ErroDataInvalida.jsp");
            return;
        }

        int resultado = ClasseApplication.incluirClasse(nome, valor, prazoDevolucao);

        if (resultado == 0) {
            // Inclusão bem-sucedida
            response.sendRedirect("listarClasse");
        } else if (resultado == 1) {
            // Tratar o erro de dados inválidos
            response.sendRedirect("ErroDadosInvalidos.jsp");
        } else if (resultado == 2) {
            // Tratar o erro de inserção
            response.sendRedirect("ErroInsercao.jsp");
        } else {
            // Erro desconhecido
            response.sendRedirect("ErroDesconhecido.jsp");
        }
    }
}
