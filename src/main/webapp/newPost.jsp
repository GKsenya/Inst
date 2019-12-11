<%@ page import="InstagramV01.WorkClasses.User" %>
<%@ page import="InstagramV01.WorkClasses.DBUserReader" %><%--
  Created by IntelliJ IDEA.
  User: Ксеня
  Date: 09.12.2019
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>File Upload</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/Inst.css">
</head>
<body>
<div class="user">
    <%
        User user = new DBUserReader().getUserById(Integer.parseInt(request.getParameter("id")));
        out.print("<p>" + user.getName() + "</p>");
        request.setAttribute("id", user.getId());
    %>
    <button onclick="location.href='signin.jsp'" style="float: right">Выйти</button>
    <button onclick="location.href='newPost.jsp?id=${id}'" style="float: right">Загрузить пост</button>
    <button onclick="location.href='posts?id=${id}'" style="float: right">Вернуться к ленте</button>

</div>
<div class="post">
    <p class="message">${mes}</p>
    <table>
    <form action="newPost?id=${id}" method="post" enctype="multipart/form-data">
        <tr>
            <td><textarea name="comment" cols="40" rows="10" placeholder="Напишите пост о ярких событиях в вашей жизни"></textarea></td>
            <td style="width: 20%"><p style="float: left">Картинка:</p>
                <input type="file" name="img" value="Загрузиь" style="float: top"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Опубликовать" class="submit"/></td>
            <td></td>
        </tr>

    </form>
</div>
</body>
</html>
