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
    <title>Instagram V0.1</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/Inst.css">
</head>
<body>
<div>
    <div class="user">
        <div class="userAbout">
            <%
                User user = (User) request.getAttribute("user");
                out.print("<p>" + user.getName() + "</p>");
                request.setAttribute("id", user.getId());
                DBUserReader db = new DBUserReader();
            %>
            <button onclick="location.href='signin.jsp'" style="float: right">Выйти</button>
            <button onclick="location.href='newPost.jsp'" style="float: right">Загрузить пост</button>
            <button onclick="location.href='myFollowers'" style="float: right">Подписчики</button>
            <button onclick="location.href='allFriend'" style="float: right">Подписки</button>
        </div>
    </div>
        <%
            for (UserPost userPost : (List<UserPost>) request.getAttribute("posts")) {
                out.print("<div class=\"posts\"><div class =\"userPost\"><div class ='aboutUser'><p class = \"userName\">" + userPost.getUser().getName() + "</p><p class = \"userDate\">"
                            + userPost.getPosts().get(0).getDate() + "</p></div>");
                Post post = userPost.getPosts().get(0);
                out.print("<div style = 'width: 100%; margin-top: 40px;'>");
                if (!post.getImg().equalsIgnoreCase("")) {
                    out.println("<hr style = 'width: 126%; margin-left:-13%;'><table><tr><td><img src ='" + post.getImg().substring(post.getImg().lastIndexOf("upload")) + "' class = \"pic\"/></td><td><div class = \"comm\">"
                            + post.getComment() + "</div></td></tr></table>");
                } else {
                    out.println("<hr style = 'width: 126%; margin-left:-13%;'><table><tr><td><div class = \"comm1\">" + post.getComment() + "</div></td></tr></table>");
                }
                if (user.getId() != userPost.getUser().getId()) {
                    if(!user.getFriendsId().contains(userPost.getUser().getId())) {
                        out.print("</div><div style = 'width = 100%;'><button onclick=\"location.href='newFriend?userId=" + userPost.getUser().getId() + "'\" class=\"friends1\">Подписаться</button>");
                    }else{
                        out.print("</div><div style = 'width = 100%;'><button onclick=\"location.href='newFriend?userId=" + userPost.getUser().getId() + "'\" class=\"friends1\">Отписаться</button>");

                    }
                } else {
                    out.print("</div><div style = 'width = 100%;'>");
                }
                if(user.getLikes().contains(post.getId())) {
                    out.println("<table style=\"float: right\"><tr><td><button onclick=\"location.href='newComment.jsp?postId=" + post.getId() + "&userId=" + userPost.getUser().getId() + "'\" class='commenting'></button></td><td><button onclick=\"location.href='like?postId=" + post.getId()
                            + "'\" class='like'></button></td><td>" + post.getLikes() + "</td></tr></table>");
                }else{
                    out.println("<table style=\"float: right\"><tr><td><button onclick=\"location.href='newComment.jsp?postId=" + post.getId() + "&userId=" + userPost.getUser().getId() + "'\" class='commenting'></button></td><td><button onclick=\"location.href='like?postId=" + post.getId()
                            + "'\" class='notLike'></button></td><td>" + post.getLikes() + "</td></tr></table>");
                }
                if(post.getComments() != null){
                    out.println("<hr style = 'width: 126%; margin-left:-13%; margin-top:40px;'><div class =\"userPost\"><table>");
                    for(Comment comment:post.getComments()){
                        out.println("<tr><td class = \"comments1\"><p>" + comment.getUser().getName() + "</p></td><td class = \"userDate\"><p>"
                                + comment.getDate() + "</p></td></tr><tr><td></td><td>" + comment.getComment() + "</td></tr>");
                    }
                    out.println("</table></div>");
                }
                out.println("</div></div></div>");
            }
        %>

</div>
</body>
</html>
