<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Sistema</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<main class="container">
    <div class="card">
        <h2>Login do Sistema</h2>
        
        <form action="login" method="post">
            <label for="username">Usuário</label>
            <input type="text" id="username" name="username" placeholder="Digite seu usuário" required>

            <label for="password">Senha</label>
            <input type="password" id="password" name="password" placeholder="Digite sua senha" required>

            <button type="submit">Entrar no Sistema</button>
        </form>
    </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>