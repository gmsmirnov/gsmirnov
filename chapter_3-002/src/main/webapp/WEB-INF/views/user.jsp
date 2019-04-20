<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: Artress
  Date: 24.02.2019
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User profile</title>
</head>
<body>
    <div class="container">
        <h2>User profile:</h2>
        <div class="form-group">
            <label>ID: ${user.id}</label><br/>
            <label>Login: ${user.login}</label><br/>
            <label>E-mail: ${user.email}</label><br/>
            <label>Password: ${user.password}</label><br/>
            <label>Country: ${user.country}</label><br/>
            <label>City: ${user.city}</label><br/>
            <label>Role: ${user.role}</label><br/>
        </div>
        <form action="${pageContext.servletContext.contextPath}/list" method="get">
            <input type="submit" value="List" class="btn btn-default">
        </form>
        <br/>
        <form action="${pageContext.servletContext.contextPath}/logout" method="get">
            <input type="submit" value="Exit" class="btn btn-default">
        </form>
    </div>
</body>
</html>