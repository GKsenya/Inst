<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.12.2019
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
<div class="container">
    <div class="frame-in">
        <div class="nav">
            <ul class>
                <li class="active"><a>Войти</a></li>
                <li class="inactive"><a onclick="location.href='signup.jsp'">Зарегистрироваться</a></li>
            </ul>
        </div>
        <div>
            <p class="message">${mes}</p>
            <form class="form" action="signIn" method="post" name="form">
                <label>Логин</label>
                <input class="form-styling" type="text" name="login" placeholder=""/>
                <label>Пароль</label>
                <input class="form-styling" type="text" name="password" placeholder=""/>
                <input type="submit" value="Войти" class="btn-sign">
            </form>
        </div>
    </div>
</div>

</body>
</html>
