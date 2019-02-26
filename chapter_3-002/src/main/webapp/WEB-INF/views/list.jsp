<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.servlets.Constants" %>
<%--
  Created by IntelliJ IDEA.
  User: Artress
  Date: 19.02.2019
  Time: 23:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>ID:</th>
        <th>Name:</th>
        <th>Login:</th>
        <th>E-mail:</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/user" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Profile">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/list" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="Create new user">
</form>
</body>
</html>