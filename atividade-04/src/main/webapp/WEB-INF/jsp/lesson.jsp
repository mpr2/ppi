<%@ page language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Aulas</title>
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
                    <th></th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.getId()}</td>
                        <td>${course.getInstructorId()}</td>
                        <td>${course.getTitle()}</td>
                        <td>${course.getDescription()}</td>
                        <td>${course.getCreatedTime()}</td>
                        <td>${course.getUpdatedTime()}</td>
                        <td><a href="lesson?course_id=${course.getId()}">Selecionar curso</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${course != null}">
            <h2>${course.getTitle()}</h2>
            <p>${course.getDescription()}</p>

            <h3>Aulas</h3>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Conteúdo</th>
                </tr>
                <c:forEach items="${lessons}" var="lesson">
                    <tr>
                        <td>${lesson.getId()}</td>
                        <td>${lesson.getTitle()}</td>
                        <td>${lesson.getContent()}</td>
                    </tr>
                </c:forEach>
            </table>

            <h3>Criar aula</h3>
            <form action="lesson" method="post">
                <input type="hidden" name="create">
                <input type="hidden" name="course_id" value="${course.getId()}">

                <label for="title">Título:</label>
                <input type="text" name="title" required>
                <br>
                <label for="content">Conteúdo:</label>
                <textarea name="content" required></textarea>
                <br>
                <input type="submit" value="Criar">
            </form>

            <h3>Alterar conteúdo</h3>
            <form action="lesson" method="post">
                <input type="hidden" name="change_content">
                <input type="hidden" name="course_id" value="${course.getId()}">

                <label for="id">ID da aula:</label>
                <input type="number" name="id" required>
                <br>
                <label for="new_content">Novo conteúdo:</label>
                <textarea name="new_content" required></textarea>
                <br>
                <input type="submit" value="Alterar conteúdo">
            </form>

            <h3>Remover aula</h3>
            <form action="lesson" method="post">
                <input type="hidden" name="remove">
                <input type="hidden" name="course_id" value="${course.getId()}">

                <label for="id">ID da aula:</label>
                <input type="number" name="id" required>
                <input type="submit" value="Remover">
            </form>
        </c:if>
    </body>
</html>
