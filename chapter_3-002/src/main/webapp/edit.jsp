<%@ page import="ru.job4j.servlets.User" %>
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
    <form action='<%=String.format("%s%s", request.getContextPath(), Constants.PAGE_UPDATE)%>' method='post'>
        <table>
            <% User user = ValidateService.getSingletonValidateServiceInstance().findById(Integer.parseInt(request.getParameter(Constants.PARAM_ID))); %>
            <tr><td>ID:</td><td><input type='text' readonly name='id' value='<%=user.getId()%>'/></td></tr>
            <tr><td>Name:</td><td><input type='text' name='name' value='<%=user.getName()%>'/></td></tr>
            <tr><td>Login:</td><td><input type='text' name='login' value='<%=user.getLogin()%>'/></td></tr>
            <tr><td>E-mail:</td><td><input type='text' name='email' value='<%=user.getEmail()%>'/></td></tr>
            <tr><td><input type='submit' value='Edit'></td></tr>
        </table>
    </form>
    <br/>
    <form action='<%=String.format("%s%s", request.getContextPath(), Constants.PAGE_LIST)%>' method='get'>
        <input type='submit' value='List'>
    </form>
</body>
</html>