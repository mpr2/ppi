<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categorias</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
<jsp:include page="header.jsp" />

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

            <c:if test="${category != null}">
                <h2>Categoria Encontrada:</h2>
                <ul>
                    <li>ID: ${category.id}</li>
                    <li>Nome: ${category.name}</li>
                    <li>Descrição: ${category.description}</li>
                </ul>
            </c:if>
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

    <!-- CONSULTA -->
    <div class="card">
        <h2>Consultar Categoria</h2>

        <form action="category" method="post">
            <input type="hidden" name="find">

            <label>ID da Categoria</label>
            <input type="text" name="id" required>

            <button type="submit">Consultar Categoria</button>
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
    <jsp:include page="footer.jsp" />

</div>
</body>
</html>
