<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="projeto.model.domain.Ator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Atores</title>
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/">Voltar</a></p>
    <h1>Lista de Atores</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
        </tr>
        <%
            List<Ator> listarAtor = (List<Ator>) request.getAttribute("listarAtor");
            
            if (listarAtor != null) {
                for (Ator ator : listarAtor) {
        %>
        <tr>
            <td><%= ator.getId() %></td>
            <td><%= ator.getNome() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <p>Nenhum ator encontrado.</p>
        <%
            }
        %>
    </table>
</body>
</html>