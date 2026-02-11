<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
    <jsp:include page="WEB-INF/jsp/header.jsp" />
    <jsp:useBean id="userBean" scope="page" class="org.atividade08.bean.UserBean" />
    <jsp:useBean id="fileUpload" scope="page" class="org.atividade08.bean.FileUploadBean" />
    <jsp:setProperty name="fileUpload" property="directory" value="profile" />
    <jsp:setProperty name="fileUpload" property="size" value="1" />
    <jsp:setProperty name="fileUpload" property="allowedExtensions" value="jpg, png" />

    <%
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (userBean.initialize(id)) {
    %>
    <h1><%= userBean.getName() %></h1>
    <%
                if (fileUpload.doFilePost(request, application)) {
                    userBean.updatePic(fileUpload.getFilename());
    %>
    <h2>Foto alterada com sucesso!: <a href="<%= "profile/" + fileUpload.getFilename() %>"><%= fileUpload.getFilename() %></a></h2>
    <%
                } else if (fileUpload.getErrorMessage() != null) {
    %>
    <h2><%= fileUpload.getErrorMessage() %></h2>
    <%
                }
                String pic = "default.png";
                if (userBean.getProfilePic() != null) {
                    pic = userBean.getProfilePic();
                }
    %>
    <img src="profile/<%= pic %>" height="250px">
    <h3>Contato: <%= userBean.getEmail() %></h3>
    <h3>Membro desde <%= userBean.getCreatedTime() %></h3>
    <hr>
    <h2>Alterar Foto de Perfil</h2>
    <form method="post" action="profile.jsp?id=<%= id %>" enctype="multipart/form-data">
        Arquivo: <input type="file" name="profile_pic">
        <button type="submit">Enviar</button>
    </form>
    <%
            }
            else {
    %>
    <h1><%= userBean.getErrorMessage() %></h1>
    <%
            }
        }
    %>

    <h1></h1>


</div>
</body>
</html>
