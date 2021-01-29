<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
    <table>
        <h2>USERS</h2>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <table>
        <h2>NOT APPROVED POSTS</h2>
        <thead>
        <th>ID</th>
        <th>PostTitle</th>
        <th>PostBody</th>
        <th>PostCreatedAt</th>
        <th>PostStatus</th>
        </thead>
        <c:forEach items="${notApprPosts}" var="post">
            <tr>
                <td>${post.id}</td>
                <td>${post.title}</td>
                <td>${post.body}</td>
                <td>${post.createdAt}</td>
                <td>${post.status}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/approve" method="post">
                        <input type="hidden" name="postId" value="${post.id}"/>
                        <input type="hidden" name="action" value="approve"/>
                        <button type="submit">Approve</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <h2>APPROVED POSTS</h2>
    <table>
        <label> Search posts:
            <form:form modelAttribute="search" method="post" action="/admin/search">
                <form:input path="searchRequest" type="text" name="searchRequest"/>
                <button type="submit">SEARCH</button>
            </form:form>
        </label>
        <thead>
        <th>ID</th>
        <th>PostTitle</th>
        <th>PostBody</th>
        <th>PostCreatedAt</th>
        <th>PostStatus</th>
        </thead>
        <c:forEach items="${searchedPosts}" var="spost">
            <tr>
                <td>${spost.id}</td>
                <td>${spost.title}</td>
                <td>${spost.body}</td>
                <td>${spost.createdAt}</td>
                <td>${spost.status}</td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <thead>
        <th>ID</th>
        <th>PostTitle</th>
        <th>PostBody</th>
        <th>PostCreatedAt</th>
        <th>PostStatus</th>
        </thead>
        <c:forEach items="${apprPosts}" var="post">
            <tr>
                <td>${post.id}</td>
                <td>${post.title}</td>
                <td>${post.body}</td>
                <td>${post.createdAt}</td>
                <td>${post.status}</td>
            </tr>
        </c:forEach>
    </table>

    <a href="/">HOME</a>
</div>
</body>
</html>
