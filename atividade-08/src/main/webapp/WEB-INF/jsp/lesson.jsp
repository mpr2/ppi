<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Aulas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
    <jsp:include page="header.jsp" />

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
                    <th>Ação</th>
                </tr>
                <c:forEach items="${lessons}" var="lesson">
                    <tr>
                        <td>${lesson.id}</td>
                        <td>${lesson.title}</td>
                        <td>${lesson.content}</td>
                        <td><a href="lesson.jsp?id=${lesson.id}">Accessar aula</a></td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${lesson != null}">
                <h2>Aula encontrada:</h2>
                <ul>
                    <li>ID: ${lesson.id}</li>
                    <li>ID do Curso: ${lesson.courseId}</li>
                    <li>Título: ${lesson.title}</li>
                    <li>Conteúdo: ${lesson.content}</li>
                </ul>
            </c:if>
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

        <!-- CONSULTAR AULA -->
        <div class="card">
            <h3>Consultar Aula</h3>

            <form action="lesson" method="post">
                <input type="hidden" name="find">
                <input type="hidden" name="course_id" value="${course.id}">

                <label>ID da aula</label>
                <input type="number" name="id" required>

                <button type="submit">Consultar Aula</button>
            </form>
        </div>

        <!-- ALTERAR AULA -->
        <div class="card">
            <h3>Alterar Aula</h3>

            <form action="lesson" method="post">
                <input type="hidden" name="update">
                <input type="hidden" name="course_id" value="${course.id}">
                
                <label>ID da Aula</label>
                <input type="number" name="id" required>
                
                <label>Novo Título</label>
                <input type="text" name="title" required>
                
                <label>Novo Conteúdo</label>
                <textarea name="content" rows="4" required></textarea>
                
                <button type="submit">Alterar Aula</button>
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
    <jsp:include page="footer.jsp" />

</div>
</body>
</html>
