<%--
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
<form action="newPost" method="post">
    Картинка:
    <input type="file" name="posts"/>
    Текст поста:
    <input type="text" value="comment" name="comment"/>
    <input type="submit" value="Upload" name="upload" id="upload" />
</form>
</body>
</html>
