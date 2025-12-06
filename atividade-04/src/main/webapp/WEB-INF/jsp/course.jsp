<%@ page language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cursos</title>
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

        <c:if test="${courses != null}">
            <h2>Lista de Cursos</h2>
            <table>
                <tr>
                    <th>ID do curso</th>
                    <th>ID do instrutor</th>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Criado em</th>
                    <th>Atualizado em</th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.getId()}</td>
                        <td>${course.getInstructorId()}</td>
                        <td>${course.getTitle()}</td>
                        <td>${course.getDescription()}</td>
                        <td>${course.getCreatedTime()}</td>
                        <td>${course.getUpdatedTime()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <h2>Cadastrar Curso</h2>
        <form action="course" method="post">
            <input type="hidden" name="create">

            <label for="instructor_id">ID do Instrutor:</label>
            <input type="number" name="instructor_id" required>
            <br>
            <label for="title">Título:</label>
            <input type="text" name="title" required>
            <br>
            <label for="description">Descrição:</label>
            <textarea name="description" required></textarea>
            <br>
            <input type="submit" value="Cadastrar">
        </form>

        <h2>Alterar Descrição</h2>
        <form action="course" method="post">
            <input type="hidden" name="change_description">

            <label for="id">ID do Curso:</label>
            <input type="number" name="id" required>
            <br>
            <label for="new_description">Nova descrição:</label>
            <textarea name="new_description" required></textarea>
            <br>
            <input type="submit" value="Alterar Descrição">
        </form>

        <h2>Remover Curso</h2>
        <form action="course" method="post">
            <input type="hidden" name="remove">
            <label for="id">ID do Curso:</label>
            <input type="number" name="id" required>
            <input type="submit" value="Remover">
        </form>
    </body>
</html>
