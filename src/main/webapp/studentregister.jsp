<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginorregister.css">
</head>
<body>
<header>
    <div class="header-line"></div>
</header>
<div class="content">
    <img class="content-logo" src="${pageContext.request.contextPath}/picture/form_logo.png" alt="logo">
    <h1 class="content-title">Sign Up</h1>
    <div class="content-form">
        <form method="post" action="${pageContext.request.contextPath}/student/register">
            <div id="change_margin_1">
                <input class="user" type="text" name="name" placeholder="请输入用户名">
            </div>
            <div id="change_margin_2">
                <input class="user" type="text" name="id" placeholder="请输入学号">
            </div>
            <div id="change_margin_5">
                <input class="password" type="password" name="password" placeholder="请输入密码"
                       oninput="oBlur_2()" onblur="oBlur_2()" onfocus="oFocus_2()">
            </div>
            <div id="change_margin_3">
                <input id = "c_psw" class="password" type="password" name="confirmPassword" placeholder="请输入确认密码"
                       oninput="oBlur_2()" onblur="oBlur_2()" onfocus="oFocus_2()" >
                <p id="remind_1"></p>
            </div>
            <div id="change_margin_4">
                <input class="content-form-signup" type="submit" value="创建账户">
            </div>
            <p class="content-login-description">已经拥有账户？</p>
            <a class="content-login-link" href="${pageContext.request.contextPath}/student/login">登录</a>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/loginorrigister.js"></script>
</body>
</html>
