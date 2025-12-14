<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Aulas</title>
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

        .card h2, .card h3 {
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

        button, .link-btn {
            margin-top: 15px;
            padding: 10px 16px;
            background-color: #fdb950;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            color: black;
            display: inline-block;
        }

        button:hover, .link-btn:hover {
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

    <h1>Gerenciamento de Aulas</h1>

    <c:if test="${message != null}">
        <div class="message">${message}</div>
    </c:if>

    <!-- LISTA DE CURSOS -->
    <c:if test="${courses != null}">
        <div class="card">
            <h2>Selecione um Curso</h2>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Instrutor</th>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Criado em</th>
                    <th>Atualizado em</th>
                    <th>Ação</th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.id}</td>
                        <td>${course.instructorId}</td>
                        <td>${course.title}</td>
                        <td>${course.description}</td>
                        <td>${course.createdTime}</td>
                        <td>${course.updatedTime}</td>
                        <td>
                            <a class="link-btn" href="lesson?course_id=${course.id}">
                                Gerenciar Aulas
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <!-- AULAS DO CURSO -->
    <c:if test="${course != null}">
        <div class="card">
            <h2>${course.title}</h2>
            <p>${course.description}</p>
        </div>

        <div class="card">
            <h3>Aulas do Curso</h3>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Conteúdo</th>
                </tr>
                <c:forEach items="${lessons}" var="lesson">
                    <tr>
                        <td>${lesson.id}</td>
                        <td>${lesson.title}</td>
                        <td>${lesson.content}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- CRIAR AULA -->
        <div class="card">
            <h3>Criar Aula</h3>

            <div class="requirements">
                <strong>Requisitos:</strong>
                <ul>
                    <li>O curso deve estar previamente cadastrado.</li>
                    <li>O título da aula é obrigatório.</li>
                    <li>O conteúdo da aula não pode ser vazio.</li>
                </ul>
            </div>

            <form action="lesson" method="post">
                <input type="hidden" name="create">
                <input type="hidden" name="course_id" value="${course.id}">

                <label>Título da Aula</label>
                <input type="text" name="title" required>

                <label>Conteúdo</label>
                <textarea name="content" rows="4" required></textarea>

                <button type="submit">Criar Aula</button>
            </form>
        </div>

        <!-- ALTERAR CONTEÚDO -->
        <div class="card">
            <h3>Alterar Conteúdo da Aula</h3>

            <form action="lesson" method="post">
                <input type="hidden" name="change_content">
                <input type="hidden" name="course_id" value="${course.id}">

                <label>ID da Aula</label>
                <input type="number" name="id" required>

                <label>Novo Conteúdo</label>
                <textarea name="new_content" rows="4" required></textarea>

                <button type="submit">Alterar Conteúdo</button>
            </form>
        </div>

        <!-- REMOVER AULA -->
        <div class="card">
            <h3>Remover Aula</h3>

            <form action="lesson" method="post">
                <input type="hidden" name="remove">
                <input type="hidden" name="course_id" value="${course.id}">

                <label>ID da Aula</label>
                <input type="number" name="id" required>

                <button type="submit">Remover Aula</button>
            </form>
        </div>
    </c:if>

</div>
</body>
</html>
