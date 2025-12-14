<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuários</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
        }

        h1 {
            border-bottom: 4px solid #fdb950;
            padding-bottom: 8px;
            color: #333;
        }

        .container {
            max-width: 1000px;
            margin: auto;
        }

        .card {
            background: #ffffff;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 25px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        .card h2 {
            margin-top: 0;
            color: #fdb950;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            box-sizing: border-box;
        }

        button {
            margin-top: 15px;
            padding: 10px 16px;
            background-color: #fdb950;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        button:hover {
            opacity: 0.9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
        }

        th {
            background-color: #fdb950;
        }

        .message {
            padding: 12px;
            background-color: #fff3d6;
            border-left: 5px solid #fdb950;
            margin-bottom: 20px;
        }

        .requirements {
            background-color: #fff9ec;
            padding: 15px;
            border-left: 4px solid #fdb950;
            margin-bottom: 15px;
        }
    </style>
</head>

<body>
<div class="container">

    <h1>Gerenciamento de Usuários</h1>

    <c:if test="${message != null}">
        <div class="message">${message}</div>
    </c:if>

    <!-- LISTA DE USUÁRIOS -->
    <c:if test="${users != null}">
        <div class="card">
            <h2>Lista de Usuários</h2>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Usuário</th>
                    <th>Email</th>
                    <th>Nome</th>
                    <th>Criado em</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.createdTime}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <!-- CADASTRO -->
    <div class="card">
        <h2>Cadastrar Usuário</h2>

        <div class="requirements">
            <strong>Requisitos:</strong>
            <ul>
                <li>O nome de usuário deve ser único.</li>
                <li>O email deve ser válido.</li>
                <li>A senha deve ser informada no momento do cadastro.</li>
            </ul>
        </div>

        <form action="user" method="post">
            <input type="hidden" name="create">

            <label>Nome de Usuário</label>
            <input type="text" name="username" required>

            <label>Email</label>
            <input type="email" name="email" required>

            <label>Senha</label>
            <input type="password" name="password" required>

            <label>Nome Completo</label>
            <input type="text" name="name" required>

            <button type="submit">Cadastrar Usuário</button>
        </form>
    </div>

    <!-- ALTERAR SENHA -->
    <div class="card">
        <h2>Alterar Senha</h2>

        <form action="user" method="post">
            <input type="hidden" name="change_password">

            <label>ID do Usuário</label>
            <input type="number" name="id" required>

            <label>Senha Atual</label>
            <input type="password" name="current_password" required>

            <label>Nova Senha</label>
            <input type="password" name="new_password" required>

            <button type="submit">Alterar Senha</button>
        </form>
    </div>

    <!-- REMOVER -->
    <div class="card">
        <h2>Remover Usuário</h2>

        <form action="user" method="post">
            <input type="hidden" name="remove">

            <label>ID do Usuário</label>
            <input type="number" name="id" required>

            <button type="submit">Remover Usuário</button>
        </form>
    </div>

</div>
</body>
</html>
