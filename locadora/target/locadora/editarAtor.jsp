<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Ator</title>
</head>
<body>
    <h1>Editar Ator</h1>
    <p><a href="listarAtor">Voltar</a></p>
    <form action="editarAtor" method="post">
        <input type="hidden" name="id" value="${id}">
        Nome: <input type="text" name="nome" value="${nome}"><br>
        <input type="submit" value="Salvar">
    </form>
</body>
</html>
