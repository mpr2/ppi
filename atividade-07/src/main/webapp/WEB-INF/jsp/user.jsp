<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuários</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
    <jsp:include page="header.jsp" />

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
                    <th>Senha</th>
                    <th>Nome</th>
                    <th>Criado em</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                            <td>${user.passwordHash}</td>
                        <td>${user.name}</td>
                        <td>${user.createdTime}</td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${user != null}">
                <h2>Usuário Selecionado:</h2>
                <ul>
                    <li>ID: ${user.id}</li>
                    <li>Nome de usuário: ${user.username}</li>
                    <li>Email: ${user.email}</li>
                    <li>Senha: ${user.passwordHash}</li>
                    <li>Nome: ${user.name}</li>
                    <li>Data de criação: ${user.createdTime}</li>
                </ul>
            </c:if>
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

    <!-- CONSULTA -->
    <div class="card">
        <h2>Consultar Usuário</h2>

        <form action="user" method="post">
            <input type="hidden" name="find">

            <label>ID</label>
            <input type="number" name="id" required>

            <button type="submit">Consultar Usuário</button>
        </form>
    </div>

    <!-- ALTERAR -->
    <div class="card">
            <h2>Alterar Usuário</h2>

        <form action="user" method="post">
            <input type="hidden" name="update">

            <label>ID do Usuário</label>
            <input type="number" name="id" required>

            <label>Usuário</label>
            <input type="text" name="username" required>
            
            <label>Email</label>
            <input type="email" name="email" required>

            <label>Senha Atual</label>
            <input type="password" name="current_password" required>

            <label>Nova Senha</label>
            <input type="password" name="new_password" required>

            <label for="name">Nome:</label>
            <input type="text" name="name" required>
            
            <button type="submit">Alterar Usuário</button>
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
    <jsp:include page="footer.jsp" />

</div>
</body>
</html>
