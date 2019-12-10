<%@ page import="java.util.List" %>
<%@ page import="InstagramV01.WorkClasses.UserPost" %>
<%@ page import="javax.swing.*" %>
<%@ page import="java.awt.*" %>
<%@ page import="InstagramV01.WorkClasses.User" %><%--
  Created by IntelliJ IDEA.
  User: Ксеня
  Date: 07.12.2019
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Instagram V0.1</title>
    <link rel="stylesheet" type="text/css" href="css/Inst.css">
    <style type="text/css"></style>
</head>
<body>
<div>
    <div>
        <%
            User user = (User) request.getAttribute("user");
            out.print("<p>" + user.getName() + "</p>");
            request.setAttribute("id", user.getId());
        %>
        <button onclick="location.href='newPost.jsp?id=${id}'">загрузить пост</button><br><br>
        <button onclick="location.href='signin.jsp'">Выйти</button>
    </div>
    <table>
        <%
            for (UserPost userPost : (List<UserPost>) request.getAttribute("posts")) {
                byte[] imageBytes = userPost.getPosts().get(0).getImg();
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                Image image = imageIcon.getImage();
                out.print("<tr><td>" + userPost.getUser().getName() + "     "
                        + userPost.getPosts().get(0).getDate() + "</td><td></td></tr>");
                out.println("<tr><td><img src ='" + image + "'></td><td>"
                        + userPost.getPosts().get(0).getComment() + "</td></tr>");
            }
        %>
    </table>
</div>
</body>
</html>
