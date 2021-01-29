<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div id="formContent">
    <form:form id="formFooter" method="POST" modelAttribute="postForm" action="/post">
        <h2>Create/Edit post</h2>
        <div>
            <form:input type="text" path="title" placeholder="Title"></form:input>
        </div>
        <div>
            <form:input type="text" path="body" placeholder="Message"></form:input>
        </div>
        <button type="submit">Create/Edit post</button>
    </form:form>
    <a href="/">HOME</a>
    <a href="/poster">POSTS</a>
</div>
</body>
</html>
