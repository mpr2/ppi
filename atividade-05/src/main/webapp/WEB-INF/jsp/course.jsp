<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cursos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
    <jsp:include page="header.jsp" />

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

            <c:if test="${course != null}">
                <h2>Curso encontrado:</h2>
                <ul>
                    <li>ID: ${course.id}</li>
                    <li>ID do instrutor: ${course.instructorId}</li>
                    <li>Título: ${course.title}</li>
                    <li>Descrição: ${course.description}</li>
                    <li>ID da Categoria: ${course.categoryId}</li>
                    <li>Data de criação: ${course.createdTime}</li>
                    <li>Data de atualização: ${course.updatedTime}</li>
                </ul>
            </c:if>
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

    <!-- CONSULTA -->
    <div class="card">
        <h2>Consultar Curso</h2>

        <form action="course" method="post">
            <input type="hidden" name="find">

            <label for="id">ID do Curso:</label>
            <input type="number" name="id" required>

            <button type="submit">Consultar Curso</button>
        </form>
    </div>

    <!-- ALTERAR -->
    <div class="card">
        <h2>Alterar Curso</h2>

        <form action="course" method="post">
            <input type="hidden" name="update">

            <label>ID do Curso</label>
            <input type="number" name="id" required>
    
            <label>Novo título</label>
            <input type="text" name="title" required>

            <label>Nova Descrição</label>
            <textarea name="description" rows="4" required></textarea>

            <label>ID da Categoria</label>
            <input type="text" name="category_id">

            <button type="submit">Alterar Curso</button>
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
