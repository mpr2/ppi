<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cursos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
        }

        h1, h2 {
            color: #333;
        }

        h1 {
            border-bottom: 4px solid #fdb950;
            padding-bottom: 8px;
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
            text-align: left;
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

    <h1>Gerenciamento de Cursos</h1>

    <c:if test="${message != null}">
        <div class="message">${message}</div>
    </c:if>

    <!-- LISTA DE CURSOS -->
    <c:if test="${courses != null}">
        <div class="card">
            <h2>Lista de Cursos</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Instrutor</th>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Criado em</th>
                    <th>Atualizado em</th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.id}</td>
                        <td>${course.instructorId}</td>
                        <td>${course.title}</td>
                        <td>${course.description}</td>
                        <td>${course.createdTime}</td>
                        <td>${course.updatedTime}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <!-- CADASTRO -->
    <div class="card">
        <h2>Cadastrar Curso</h2>

        <div class="requirements">
            <strong>Requisitos para cadastro:</strong>
            <ul>
                <li>O instrutor deve estar previamente cadastrado (ID do instrutor válido).</li>
                <li>A categoria do curso deve existir no sistema.</li>
            </ul>
        </div>

        <form action="course" method="post">
            <input type="hidden" name="create">

            <label>ID do Instrutor</label>
            <input type="number" name="instructor_id" required>

            <label>Título do Curso</label>
            <input type="text" name="title" required>

            <label>Descrição</label>
            <textarea name="description" rows="4" required></textarea>

            <label>ID da Categoria</label>
            <input type="text" name="category_id" required>

            <button type="submit">Cadastrar Curso</button>
        </form>
    </div>

    <!-- ALTERAR DESCRIÇÃO -->
    <div class="card">
        <h2>Alterar Descrição do Curso</h2>

        <form action="course" method="post">
            <input type="hidden" name="change_description">

            <label>ID do Curso</label>
            <input type="number" name="id" required>

            <label>Nova Descrição</label>
            <textarea name="new_description" rows="4" required></textarea>

            <button type="submit">Alterar Descrição</button>
        </form>
    </div>

    <!-- REMOVER -->
    <div class="card">
        <h2>Remover Curso</h2>

        <form action="course" method="post">
            <input type="hidden" name="remove">

            <label>ID do Curso</label>
            <input type="number" name="id" required>

            <button type="submit">Remover Curso</button>
        </form>
    </div>

</div>
</body>
</html>
