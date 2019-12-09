<%@ page import="java.util.List" %>
<%@ page import="InstagramV01.WorkClasses.Post" %>
<%@ page import="InstagramV01.WorkClasses.UserPost" %><%--
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
    <link rel="stylesheet" type="text/css" href="Inst.css">
    <style type="text/css"></style>
</head>
<body>
<div>
    <table>
        <%
            try{
                for(UserPost userPost: (List<UserPost>) request.getAttribute("posts")){
                    out.print("<tr><td>"+userPost.getUser().getName()+ "     " + userPost.getPosts().get(0).getDate() +"</td><td></td></tr>");
                    out.println("<tr><td><img src ='" + userPost.getPosts().get(0).getImg() + "'></td><td>"
                            + userPost.getPosts().get(0).getComment() + "</td></tr>");
                    out.print(userPost.getPosts().get(0).getImg());
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        %>
    </table>
</div>
</body>
</html>
