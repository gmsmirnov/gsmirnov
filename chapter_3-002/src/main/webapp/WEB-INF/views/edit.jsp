<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    /**
     * Validates if "login", "password", "email", "country" or "city" are empty and alerts.
     * If there are no empty fields form sends to the server.
     */
    function validate(form) {
        var result = true;
        if (document.getElementsByName("login")[0].value == '') {
            alert("Please correct login.");
            result = false;
        } else if (document.getElementsByName("password")[0].value == '') {
            alert("Please correct password.");
            result = false;
        } else if (document.getElementsByName("email")[0].value == '') {
            alert("Please correct email.");
            result = false;
        } else if (document.getElementsByName("country")[0].value == '') {
            alert("Please correct country.");
            result = false;
        } else if (document.getElementsByName("city")[0].value == '') {
            alert("Please correct city.");
            result = false;
        }
        if (result) {
            form.submit();
        }
    }

    /**
     * Creates POST request to jsonController and transfers new cities.
     * Updates select.
     */
    function updateSelect() {
        $.ajax("./json", {
            method : "post",
            data : {
                country : $("#country").val()
            },
            complete : function(data) {
                console.log(JSON.parse(data.responseText));
                var result = "";
                var cities = JSON.parse(data.responseText);
                for (var index = 0; index < cities.length; index++) {
                    result += "<option value=" + cities[index] + ">" + cities[index] + "</option>";
                }
                var select = document.getElementById("city");
                select.innerHTML = result;
            }
        })
    }
</script>
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
<div class="container">
    <h2>Update user</h2>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit" method="post" name="form" onsubmit="validate(this); return false;">
        <table>
            <div class="form-group">
                <label class="control-label col-sm-2" for="id">ID: </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="id" readonly name="id" value="${user.id}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="login">Login: </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="login" readonly name="login" value="${user.login}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">E-mail:</label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password: </label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="password" name="password" value="${user.password}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="country">Country: </label>
                <div class="col-sm-4">
                    <select name="country" id="country" class="form-control" onchange="return updateSelect();">
                        <c:forEach var="country" items="${countries}">
                            <option value="${country}" ${user.country == country ? 'selected' : ''}>${country}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="city">City: </label>
                <div class="col-sm-4">
                    <select name="city" id="city" class="form-control">
                        <c:forEach var="city" items="${cities}">
                            <option value="${city}" ${user.city == city ? 'selected' : ''}>${city}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <c:if test="${current_user.role == 'admin'}">
                    <label class="control-label col-sm-2" for="role">Role:</label>
                        <div class="col-sm-4">
                            <select name="role" id="role" class="form-control">
                                <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Administrator</option>
                                <option value="user" ${user.role == 'user' ? 'selected' : ''}>User</option>
                            </select>
                        </div>
                </c:if>
            </div>
            <div class="form-group">
                <c:if test="${current_user.role == 'user'}">
                    <input type="hidden" name="role" value="${user.role}">
                </c:if>
            </div>
            <div class="form-group">
                <tr><td><input type="submit" value="Edit" class="btn btn-default"></td></tr>
            </div>
        </table>
    </form>
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