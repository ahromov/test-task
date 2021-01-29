<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>REGISTRATION</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div id="formContent">
    <form:form id="formFooter" method="POST" modelAttribute="userForm">
        <h2>REGISTRATION</h2>
        <div>
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="username"></form:errors>
            <div class="error">${usernameError}</div>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <div>
            <label for="role">Choose a user role:
                <form:select name="roles" id="role" path="roles" multiple="none">
                    <form:option value="1">ADMIN</form:option>
                    <form:option value="2">POSTER</form:option>
                    <form:option value="3">VISITOR</form:option>
                </form:select>
            </label>
        </div>
        <button type="submit">Registration</button>
    </form:form>
    <a href="/">Home</a>
</div>
</body>
</html>