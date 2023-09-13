<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="projeto.model.domain.Classe" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Classes</title>
</head>
<body>
    
    <p><a href="${pageContext.request.contextPath}/">Voltar</a></p>
    <h1>Lista de Classes</h1>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Valor</th>
            <th>Prazo de Devolução</th>
            <th>Ações</th>
        </tr>
        <%
            List<Classe> listarClasse = (List<Classe>) request.getAttribute("listarClasse");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            if (listarClasse != null) {
                for (Classe classe : listarClasse) {
        %>
        <tr>
            <td><%= classe.getId() %></td>
            <td><%= classe.getNome() %></td>
            <td><%= classe.getValor() %></td>
            <td><%= dateFormat.format(classe.getPrazoDevolucao()) %></td>
            <td>
                <a href="editarClasse?id=<%= classe.getId() %>"><button>Editar</button></a>
                <a href="excluirClasse?id=<%= classe.getId() %>"><button>Excluir</button></a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <p>Nenhum classe encontrado.</p>
        <%
            }
        %>
    </table>
    <br>
    <a href="novoClasse.jsp"><button>Criar</button></a>
</body>
</html>