<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div id="formContent">
    <form id="formFooter" method="POST" action="/login">
        <h2>SIGN IN</h2>
        <div>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <div>
                <button type="submit">Log In</button>
            </div>
            <h4><a href="/registration">Registration</a></h4>
        </div>
    </form>
</div>

</body>
</html>