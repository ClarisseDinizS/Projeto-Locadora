package com.projeto.controller;

import java.io.IOException;

import com.projeto.model.application.AtorApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CadastrarAtorController")
public class CadastrarAtorController {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");

        int resultado = AtorApplication.incluirAtor(nome);

        if (resultado == 0) {
            // Inclusão bem-sucedida
            response.sendRedirect("ListaAtoresServlet");
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
