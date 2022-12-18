<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="css/loginorregister.css">
</head>
<body>
<header>
    <div class="header-line"></div>
</header>
<div class="content">
    <img class="content-logo" src="picture/form_logo.png" alt="logo">
    <h1 class="content-title">Change&nbsp;&nbsp;Password</h1>
    <div class="content-form">
        <form method="post" action="">
            <div id="change_margin_1">
                <input class="user" type="text" name="id" placeholder="请输入账号">
            </div>
            <div id="change_margin_2">
                <input id  = "psw" class="password" type="password" name="password" placeholder="请输入新密码"
                       oninput="oBlur_2()" onblur="oBlur_2()" onfocus="oFocus_2()">
            </div>
            <div id="change_margin_3">
                <input id = "c_psw" class="password" type="password" name="changepassword" placeholder="请再次输入新密码"
                       oninput="oBlur_2()" onblur="oBlur_2()" onfocus="oFocus_2()" >
                <p id="remind_1"></p>
            </div>
            <div id="change_margin_4">
                <input  class="content-form-signup" type="submit" value="修改密码">
            </div>
        </form>
    </div>
</div>
<script src="js/loginorrigister.js"></script>
</body>
</html>
