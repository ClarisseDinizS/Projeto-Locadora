<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="projeto.model.domain.Diretor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Diretores</title>
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/">Voltar</a></p>
    <h1>Lista de Diretores</h1>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        <%
            List<Diretor> listarDiretor = (List<Diretor>) request.getAttribute("listarDiretor");
            
            if (listarDiretor != null) {
                for (Diretor diretor : listarDiretor) {
        %>
        <tr>
            <td><%= diretor.getId() %></td>
            <td><%= diretor.getNome() %></td>
            <td><a href="editarDiretor?id=<%= diretor.getId() %>"><button>Editar</button></a><a href="excluirDiretor?id=<%= diretor.getId() %>"><button>Excluir</button></a></td>
        </tr>
        <%
                }
            } else {
        %>
        <p>Nenhum diretor encontrado!</p>
        <%
            }
        %>
    </table>
    <br>
    <a href="novoDiretor.jsp"><button>Criar</button></a>
</body>
</html>