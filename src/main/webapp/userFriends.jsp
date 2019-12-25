<%@ page import="java.util.List" %>
<%@ page import="javax.swing.*" %>
<%@ page import="java.awt.*" %>
<%@ page import="InstagramV01.WorkClasses.*" %><%--
  Created by IntelliJ IDEA.
  User: Ксеня
  Date: 07.12.2019
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friends</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/Inst.css">
</head>
<body>
<div>
    <div class="user">
        <div class="userAbout">
            <%
                User user = (User) request.getAttribute("user");
                out.print("<p><a href='posts' class = 'home'>" + user.getName() + "</a></p>");
                request.setAttribute("id", user.getId());
                DBUserReader db = new DBUserReader();
            %>
            <button onclick="location.href='signin.jsp'" style="float: right">Выйти</button>
            <button onclick="location.href='newPost.jsp'" style="float: right">Загрузить пост</button>
            <button onclick="location.href='myFollowers'" style="float: right">Подписчики</button>
        </div>
    </div>
    <div class="posts">
        <div class="post">
            <table>
<%
        for (User friend : (List<User>) request.getAttribute("friends")) {
            out.print("<tr><td style = 'width: 80px;'>"+friend.getName()+"</td><td> "+friend.getDate().substring(0,friend.getDate().lastIndexOf(" "))+"</td></tr><button onclick=\"location.href='delFriend?userId=" + friend.getId() + "'\" class=\"friends\">Отписаться</button>");
        }
%>

            </table>
        </div>
    </div>
</div>
</body>
</html>