<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        <%@ include file="/resources/css/loginform.css"  %>
    </style>
</head>
<body>
<form action="/ch3/user/login" method="post">
    <div class="container">
        <input type="text" placeholder="Enter Username" name="userId" value="${cookie.userId.value}">
        <input type="password" placeholder="Enter Password" name="pwd">
        <input type="text" name="toURL" value="${param.toURL}">
        <input type="checkbox" name="remember" value="on" ${empty cookie.userId.value ? "":"checked"}> Remember me

        <button type="submit">Login</button>
    </div>

    <div class="container" style="background-color: #f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
</form>
</body>
</html>