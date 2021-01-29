<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>HOME</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Sign in</a></h4>
        <h4><a href="/registration">Registration</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Sign out</a></h4>
        <h4><a href="/poster">Posts (POSTER only)</a></h4>
        <h4><a href="/visitor">Posts (VSITOR only)</a></h4>
        <h4><a href="/admin">Users (ADMIN only)</a></h4>
    </sec:authorize>
</div>
</body>
</html>
