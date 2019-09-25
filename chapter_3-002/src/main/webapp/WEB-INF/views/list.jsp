<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<div class="container">
    <h2>List of users</h2>
    <table class="table table-striped" id="table">
        <tr>
            <th>ID:</th>
            <th>Login:</th>
            <th>E-mail:</th>
            <th>Country:</th>
            <th>City:</th>
            <th>Role:</th>
        </tr>
        <%--<jsp:useBean id="users" scope="session" type="java.util.List"/>--%>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.country}"></c:out></td>
                <td><c:out value="${user.city}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/user" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Profile" class="btn btn-default">
                    </form>
                </td>
                <td>
                    <c:if test="${current_user.role == 'admin' || current_user.login == user.login}" >
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="submit" value="Update" class="btn btn-default">
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${current_user.role == 'admin' || current_user.login == user.login}" >
                        <form action="${pageContext.servletContext.contextPath}/list" method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="submit" value="Delete" class="btn btn-default">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="container">
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <input type="submit" value="Create new user" class="btn btn-default">
    </form>
</div>
<div class="container">
    <form action="${pageContext.servletContext.contextPath}/logout" method="get">
        <input type="submit" value="Exit" class="btn btn-default">
    </form>
</div>
</body>
</html>