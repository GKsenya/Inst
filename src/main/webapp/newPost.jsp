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
</head>
<body>
<div>
    <%
        User user = new DBUserReader().getUserById(Integer.parseInt(request.getParameter("id")));
        out.print("<p>" + user.getName() + "</p>");
        request.setAttribute("id", user.getId());
    %>
    <button onclick="location.href='newPost.jsp?id=${id}'">загрузить пост</button><br><br>
    <button onclick="location.href='signin.jsp'">Выйти</button>
</div>
<form action="newPost?id=${id}" method="post">
    Картинка:
    <input type="file" name="img" accept="image/*"/>
    Текст поста:
    <input type="text" name="comment"/>
    <input type="submit" value="Upload" name="upload"/>
</form>
</body>
</html>
