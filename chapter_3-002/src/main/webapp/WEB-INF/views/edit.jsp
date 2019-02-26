<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.servlets.model.User" %>
<%@ page import="ru.job4j.servlets.Constants" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%--
  Created by IntelliJ IDEA.
  User: Artress
  Date: 19.02.2019
  Time: 23:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <table>
        <tr><td>ID:</td><td><input type="text" readonly name="id" value="${user.id}"/></td></tr>
        <tr><td>Name:</td><td><input type="text" name="name" value="${user.name}"/></td></tr>
        <tr><td>Login:</td><td><input type="text" name="login" value="${user.login}"/></td></tr>
        <tr><td>E-mail:</td><td><input type="text" name="email" value="${user.email}"/></td></tr>
        <tr><td><input type="submit" value="Edit"></td></tr>
    </table>
</form>
<br/>
<form action="${pageContext.servletContext.contextPath}/list" method="get">
    <input type="submit" value="List">
</form>
</body>
</html>