<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categorias</title>
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
            max-width: 900px;
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

        input, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
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

    <h1>Gerenciamento de Categorias</h1>

    <c:if test="${message != null}">
        <div class="message">${message}</div>
    </c:if>

    <!-- LISTA DE CATEGORIAS -->
    <c:if test="${categories != null}">
        <div class="card">
            <h2>Lista de Categorias</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Descrição</th>
                </tr>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>${category.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <!-- CADASTRO -->
    <div class="card">
        <h2>Cadastrar Categoria</h2>

        <div class="requirements">
            <strong>Requisitos:</strong>
            <ul>
                <li>O ID da categoria deve ser único.</li>
                <li>O nome da categoria é obrigatório.</li>
            </ul>
        </div>

        <form action="category" method="post">
            <input type="hidden" name="create">

            <label>ID da Categoria</label>
            <input type="text" name="id" required>

            <label>Nome</label>
            <input type="text" name="name" required>

            <label>Descrição</label>
            <textarea name="description" rows="3"></textarea>

            <button type="submit">Cadastrar Categoria</button>
        </form>
    </div>

    <!-- ALTERAÇÃO -->
    <div class="card">
        <h2>Alterar Categoria</h2>

        <form action="category" method="post">
            <input type="hidden" name="update">

            <label>ID da Categoria</label>
            <input type="text" name="id" required>

            <label>Novo Nome</label>
            <input type="text" name="name">

            <label>Nova Descrição</label>
            <textarea name="description" rows="3"></textarea>

            <button type="submit">Atualizar Categoria</button>
        </form>
    </div>

    <!-- REMOÇÃO -->
    <div class="card">
        <h2>Remover Categoria</h2>

        <form action="category" method="post">
            <input type="hidden" name="delete">

            <label>ID da Categoria</label>
            <input type="text" name="id" required>

            <button type="submit">Remover Categoria</button>
        </form>
    </div>

</div>
</body>
</html>
