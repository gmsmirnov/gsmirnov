<%@ page import="ru.job4j.servlets.Constants" %>
<%--
  Created by IntelliJ IDEA.
  User: Artress
  Date: 19.02.2019
  Time: 23:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    ID: <input type="text" name="id"/></br>
    Name: <input type="text" name="name"/></br>
    Login: <input type="text" name="login"/></br>
    E-mail: <input type="text" name="email"/></br>
    <input type="submit" value="Create">
</form>
<form action="${pageContext.servletContext.contextPath}/list" method="get">
    <input type="submit" value="List">
</form>
</body>
</html>