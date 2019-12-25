<%@ page import="InstagramV01.WorkClasses.User" %>
<%@ page import="InstagramV01.WorkClasses.DBUserReader" %>
<%@ page import="InstagramV01.WorkClasses.Post" %>
<%@ page import="InstagramV01.WorkClasses.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Ксеня
  Date: 15.12.2019
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Commenting</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/Inst.css">
</head>
<body>
<div>
    <div class="user">
        <%
            HttpSession httpSession = request.getSession(true);
            int id = (Integer) httpSession.getAttribute("id");
            User user = new DBUserReader().getUserById(id);
            out.print("<p>" + user.getName() + "</p>");
            int postID = Integer.parseInt(request.getParameter("postId"));
            int userID = Integer.parseInt(request.getParameter("userId"));
            request.setAttribute("postID", postID);
            request.setAttribute("userID", userID);
            User userComm = new DBUserReader().getUserById(userID);
            Post post = new DBUserReader().getPostById(postID);

        %>
        <button onclick="location.href='signin.jsp'" style="float: right">Выйти</button>
        <button onclick="location.href='newPost.jsp'" style="float: right">Загрузить пост</button>
        <button onclick="location.href='myFollowers'" style="float: right">Подписчики</button>
        <button onclick="location.href='allFriend'" style="float: right">Подписки</button>
        <button onclick="location.href='posts'" style="float: right">Вернуться к ленте</button>

    </div>
    <div class="post">
        <p class="commessage">${commentMes}</p>
        <div class="posts">
            <%
                out.print("<div class =\"userPost\"><div class ='aboutUser'><p class = \"userName\">" + userComm.getName() + "</p><p class = \"userDate\">"
                        + post.getDate() + "</p></div>");
                out.print("<div style = 'width: 100%; margin-top: 40px;'>");
                if (!post.getImg().equalsIgnoreCase("")) {
                    out.println("<hr style = 'width: 126%; margin-left:-13%;'><table><tr><td><img src ='" + post.getImg().substring(post.getImg().lastIndexOf("upload")) + "' class = \"pic\"/></td><td><div class = \"comm\">"
                            + post.getComment() + "</div></td></tr></table>");
                } else {
                    out.println("<table><tr><td><div class = \"comm1\">" + post.getComment() + "</div></td></tr></table>");
                }
                out.print("</div><div style = 'width = 100%;'>");
                if(post.getComments() != null){
                    out.println("<div class =\"userPost\"><hr style = 'width: 126%; margin-left:-13%; margin-top:40px;'><table>");
                    for(Comment comment:post.getComments()){
                        out.println("<tr><td class = \"comments1\"><p>" + comment.getUser().getName() + "</p></td><td class = \"userDate\">"
                                + comment.getDate() + "</td></tr><tr><td></td><td>" + comment.getComment() + "</td></tr>");
                    }
                    out.println("</table></div>");
                }
                out.println("</div></div>");
            %>
        </div>
        <div class="comment">
            <table>
                <form action="newComment?postId=${postID}" method="post">
                    <textarea name="comment" cols="50" rows="10" placeholder="Ваш комментарий..."></textarea><br/><br/>
                    <input type="submit" value="Отправить комментарий"/>
                </form>
            </table>
        </div>
    </div>
</div>
</body>
</html>
