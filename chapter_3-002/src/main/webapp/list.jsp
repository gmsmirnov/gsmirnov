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
        <% for (User user : ValidateService.getSingletonValidateServiceInstance().findAll()) { %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td>
                <form action='<%=String.format("%s%s", request.getContextPath(), Constants.PAGE_UPDATE)%>' method='get'>
                    <input type='hidden' name='id' value='<%=user.getId()%>'>
                    <input type='submit' value='Update'>
                </form>
            </td>
            <td>
                <form action='<%=String.format("%s%s", request.getContextPath(), Constants.PAGE_LIST)%>' method='post'>
                    <input type='hidden' name='id' value='<%=user.getId()%>'>
                    <input type='submit' value='Delete'>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <br/>
    <form action='<%=String.format("%s%s", request.getContextPath(), Constants.PAGE_CREATE)%>' method='get'>
        <input type='submit' value='Create new user'>
    </form>
</body>
</html>