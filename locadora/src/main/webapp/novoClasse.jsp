<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Novo Classe</title>
</head>
<body>
    <h1>Novo Classe</h1>
    <p><a href="listarClasse">Voltar</a></p>
    <form action="salvarClasse" method="post">
        Nome: <input type="text" name="nome" value="${nome}"><br>
        Valor: <input type="text" name="valor" value="${valor}"><br>
        Prazo de Devolução: <input type="date" name="prazoDevolucao" value="${prazoDevolucao}"><br>
        <input type="submit" value="Salvar">
    </form>
</body>
</html>