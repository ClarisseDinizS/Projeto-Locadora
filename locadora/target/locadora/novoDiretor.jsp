<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Novo Diretor </title>
</head>
<body>
    <h1>Novo Diretor</h1>
    <p><a href="listarDiretor">Voltar</a></p>
    <form action="salvarDiretor" method="post">
        Nome: <input type="text" name="nome"><br>
        <br>
        <input type="submit" value="Salvar">
    </form>
</body>
</html>