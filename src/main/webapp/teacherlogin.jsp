<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>教师登录</title>
    <link rel="stylesheet" href="css/loginorregister.css">
</head>
<body>
<header>
    <div class="header-line"></div>
</header>
<div class="content">
    <img class="content-logo" src="picture/form_logo.png" alt="logo">
    <h1 class="content-title">Login</h1>
    <div class="content-form">
        <form method="post" action="">
            <div id="change_margin_1">
                <input class="user" type="text" name="user" placeholder="请输入用户名">
            </div>
            <div id="change_margin_2">
                <input class="password" type="password" name="password" placeholder="请输入密码">
            </div>
            <div id="change_margin_3">
                <input class="content-form-signup" type="submit" value="登录">
            </div>
        </form>
    </div>
</div>
</body>
</html>