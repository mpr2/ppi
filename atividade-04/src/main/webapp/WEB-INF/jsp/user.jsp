<%@ page language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usuários</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 5px 10px;
            }
        </style>
    </head>
    <body>
        <c:if test="${message != null}">
            <span class="message">${message}</span>
        </c:if>

        <c:if test="${users != null}">
            <h2>Lista de Usuários</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nome de Usuário</th>
                    <th>Email</th>
                    <th>Senha</th>
                    <th>Nome</th>
                    <th>Criado em</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.getId()}</td>
                        <td>${user.getUsername()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getPasswordHash()}</td>
                        <td>${user.getName()}</td>
                        <td>${user.getCreatedTime()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <h2>Cadastrar Usuário</h2>
        <form action="user" method="post">
            <input type="hidden" name="create">

            <label for="username">Nome de Usuário:</label>
            <input type="text" name="username" required>
            <br>
            <label for="email">Email:</label>
            <input type="email" name="email" required>
            <br>
            <label for="password">Senha:</label>
            <input type="password" name="password" required>
            <br>
            <label for="name">Nome:</label>
            <input type="text" name="name" required>
            <br>
            <input type="submit" value="Cadastrar">
        </form>

        <h2>Remover Usuário</h2>
        <form action="user" method="post">
            <input type="hidden" name="remove">
            <label for="id">ID:</label>
            <input type="number" name="id" required>
            <input type="submit" value="Remover">
        </form>

        <h2>Trocar senha</h2>
        <form action="user" method="post">
            <input type="hidden" name="change_password">

            <label for="id">ID:</label>
            <input type="number" name="id" required>
            <br>
            <label for="current_password">Senha atual:</label>
            <input type="password" name="current_password" required>
            <br>
            <label for="new_password">Nova senha:</label>
            <input type="password" name="new_password" required>
            <br>
            <input type="submit" value="Trocar Senha">
        </form>
    </body>
</html>
