<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Classe</title>
</head>
<body>
    <h1>Editar Classe</h1>
    <p><a href="listarClasse">Voltar</a></p>
    <form action="editarClasse" method="post">
        <input type="hidden" name="id" value="${id}">
        Nome: <input type="text" name="nome" value="${nome}"><br>
        Valor: <input type="text" name="valor" value="${valor}"><br>
        Prazo de Devolução: <input type="date" name="prazoDevolucao" value="${prazoDevolucao}">
        <script>
            var prazoDevolucaoInput = document.getElementsByName("prazoDevolucao")[0];
            var prazoDevolucaoDate = new Date("${prazoDevolucao}");
            var formattedDate = prazoDevolucaoDate.toISOString().substr(0, 10);
            prazoDevolucaoInput.value = formattedDate;
        </script><br>
        <input type="submit" value="Salvar">
    </form>
</body>
</html>
