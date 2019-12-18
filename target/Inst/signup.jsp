
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.12.2019
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
<div class="container">
    <div class="frame">
        <div class="nav">
            <ul class>
                <li class="inactive"><a onclick="location.href='signin.jsp'">Войти</a></li>
                <li class="active"><a>Зарегистрироваться</a></li>
            </ul>
        </div>
        <div>
            <p class="message">${mes}</p>
            <form class="form" action="signUp" method="post" name="form">
                <label>Логин</label>
                <input class="form-styling" type="text" name="login" placeholder="" value="${login}"/>
                <p class="smessage">${loginMes}</p>
                <label>Имя</label>
                <input class="form-styling" type="text" name="name" placeholder="" value="${name}"/>
                <p class="smessage">${nameMes}</p>
                <label>Пароль</label>
                <input class="form-styling" type="text" name="password" placeholder="" value="${password}"/>
                <p class="smessage">${passwordMes}</p>
                <input type="submit" value="Зарегистрироваться" class="btn-sign">
            </form>
        </div>
    </div>
</div>
</body>
</html>
