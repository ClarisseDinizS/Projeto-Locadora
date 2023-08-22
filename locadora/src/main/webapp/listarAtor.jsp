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
    <a href="novoAtor.jsp"><button>Criar</button></a>
    <p><a href="${pageContext.request.contextPath}/">Voltar</a></p>
    <h1>Lista de Atores</h1>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        <%
            List<Ator> listarAtor = (List<Ator>) request.getAttribute("listarAtor");
            
            if (listarAtor != null) {
                for (Ator ator : listarAtor) {
        %>
        <tr>
            <td><%= ator.getId() %></td>
            <td><%= ator.getNome() %></td>
            <td><a href="editarAtor?id=<%= ator.getId() %>"><button>Editar</button></a><a href="excluirAtor?id=<%= ator.getId() %>"><button>Excluir</button></a></td>
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