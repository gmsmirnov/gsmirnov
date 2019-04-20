<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: Artress
  Date: 24.02.2019
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 page not found</title>
</head>
<body>
    <div class="container">
        <h1>404</h1>
        <h2>The page you requested was not found.</h2>
        <form action="${pageContext.servletContext.contextPath}/list" method="get">
            <input type="submit" value="List" class="btn btn-default">
        </form>
    </div>
</body>
</html>