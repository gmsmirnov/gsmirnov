<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: Artress
  Date: 26.02.2019
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <c:if test="${error != ''}">
        <div class="container" style="background-color: red">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <div class="container">
        <h2>Authorization form</h2>
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/login" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="login">Login: </label>
                <div class="col-sm-10">
                    <input type="text" name="login" id="login">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password:</label>
                <div class="col-sm-10">
                    <input type="password" name="password" id="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Login" class="btn btn-default">
                </div>
            </div>
        </form>
    </div>

</body>
</html>