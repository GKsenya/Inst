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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/Inst.css">
</head>
<body>
<div>
    <div class="user">
            <%
                User user = (User) request.getAttribute("user");
                out.print("<p>" + user.getName() + "</p>");
                request.setAttribute("id", user.getId());
            %>
        <button onclick="location.href='signin.jsp'" style="float: right">Выйти</button>
        <button onclick="location.href='newPost.jsp?id=${id}'" style="float: right">Загрузить пост</button>

    </div>
    <div class="posts">
            <%
                for (UserPost userPost : (List<UserPost>) request.getAttribute("posts")) {
                    out.print("<div class =\"userPost\"><table><tr><td class = \"userName\"><p>" + userPost.getUser().getName() + "</p></td><td class = \"userDate\">"
                            + userPost.getPosts().get(0).getDate() + "</td></tr></table>");
                    if(!userPost.getPosts().get(0).getImg().equalsIgnoreCase("")){
                    out.println("<table><tr><td><img src ='" + userPost.getPosts().get(0).getImg() + "' class = \"pic\"/></td><td><div class = \"comm\">"
                            + userPost.getPosts().get(0).getComment() + "</div></td></tr></table></div>");
                    }else {
                        out.println("<div class = \"comm1\">" + userPost.getPosts().get(0).getComment() + "</div></div>");
                    }
                }
            %>

    </div>
</div>
</body>
</html>
