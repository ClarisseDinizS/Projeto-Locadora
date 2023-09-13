<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Novo Ator</title>
</head>
<body>
    <h1>Novo Ator</h1>
    <p><a href="listarAtor">Voltar</a></p>
    <form action="salvarAtor" method="post">
        Nome: <input type="text" name="nome"><br>
        <br>
        <input type="submit" value="Salvar">
    </form>
</body>
</html>