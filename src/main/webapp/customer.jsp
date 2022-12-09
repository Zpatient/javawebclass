<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<form action="/a" method="post">
    <p>编号&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;&nbsp;&nbsp;年龄&nbsp;&nbsp;&nbsp;&nbsp;手机号</p>
    <c:forEach var="customer" items="${customers}">
        ${customer.id}&nbsp;&nbsp;&nbsp;&nbsp;
        ${customer.name}&nbsp;&nbsp;&nbsp;&nbsp;
        ${customer.age}&nbsp;&nbsp;&nbsp;&nbsp;
        ${customer.phoneNumber} <br/>
    </c:forEach>
    <br/><br/>

    按编号查询<input type="text" name = "searchId"><input type="submit" text="查询"><br/>
    ${customer.id}&nbsp;&nbsp;&nbsp;&nbsp;${customer.name}&nbsp;&nbsp;&nbsp;&nbsp;
    ${customer.age}&nbsp;&nbsp;&nbsp;&nbsp;${customer.phoneNumber} <br/>

    按编号删除<input type="text" name = "deleteId"><input type="submit" text="删除">
    <br/><br/>

    编号<input type="text" name = "id">姓名<input type="text" name = "name">
    年龄<input type="text" name = "age">手机号<input type="text" name = "phoneNumber"><input type="submit" text="插入">

</form>

</body>
</html>
