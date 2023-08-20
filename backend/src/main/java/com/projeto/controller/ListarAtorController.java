package com.projeto.controller;

import java.io.IOException;
import java.util.List;

import com.projeto.model.application.AtorApplication;
import com.projeto.model.domain.Ator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListarAtorController")
public class ListarAtorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ator> listaAtores = AtorApplication.listarAtor();

        request.setAttribute("listaAtores", listaAtores);
        request.getRequestDispatcher("ListaAtores.jsp").forward(request, response);
    }
}
