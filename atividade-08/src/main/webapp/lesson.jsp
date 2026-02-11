<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Aula</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
    <jsp:include page="WEB-INF/jsp/header.jsp" />
    <jsp:useBean id="lessonBean" scope="page" class="org.atividade08.bean.LessonBean" />
    <jsp:useBean id="fileUpload" scope="page" class="org.atividade08.bean.FileUploadBean" />
    <jsp:setProperty name="fileUpload" property="directory" value="content" />
    <jsp:setProperty name="fileUpload" property="size" value="2" />
    <jsp:setProperty name="fileUpload" property="allowedExtensions" value="pdf" />

    <%
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (lessonBean.initialize(id)) {
    %>
    <h1><%= lessonBean.getTitle() %></h1>
    <%
                if (fileUpload.doFilePost(request, application)) {
                    lessonBean.updateNotes(fileUpload.getFilename());
    %>
    <h2>Notas enviadas com sucesso!: <a href="<%= "content/" + fileUpload.getFilename() %>"><%= fileUpload.getFilename() %></a></h2>
    <%
                } else if (fileUpload.getErrorMessage() != null) {
    %>
    <h2><%= fileUpload.getErrorMessage() %></h2>
    <%
                }
    %>
    <p><%= lessonBean.getContent() %></p>
    <%
                if (lessonBean.getNotesFile() == null) {
    %>
    <h3>Não há notas de aula.</h3>
    <%
                }
                else {
    %>
    <h3>Notas de aula: <a href="content/<%= lessonBean.getNotesFile() %>"><%= lessonBean.getNotesFile() %></a></h3>
    <%
                }
    %>
    <hr>
    <h2>Enviar Notas de aula</h2>
    <form method="post" action="lesson.jsp?id=<%= id %>" enctype="multipart/form-data">
        Arquivo: <input type="file" name="notes_file">
        <button type="submit">Enviar</button> <br>
        <small>Extensões permitidas: pdf</small> <br>
        <small>Tamanho máximo: 2 MB</small>
    </form>
    <%
            }
            else {
    %>
    <h1><%= lessonBean.getErrorMessage() %></h1>
    <%
            }
        }
    %>

    <jsp:include page="WEB-INF/jsp/footer.jsp" />

</div>
</body>
</html>
