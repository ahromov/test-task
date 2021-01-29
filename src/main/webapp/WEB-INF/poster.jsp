<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>POSTS</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h2>POSTS for poster</h2>
    <a href="/post">Create post</a>
    <div>
        <table>
            <label> Search posts:
                <form:form modelAttribute="search" method="post" action="/poster/search">
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
            <th>PostEditor</th>
            </thead>
            <c:forEach items="${apprPosts}" var="post">
                <tr>
                    <td>${post.id}</td>
                    <td>${post.title}</td>
                    <td>${post.body}</td>
                    <td>${post.createdAt}</td>
                    <td>${post.status}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/post/edit" method="post">
                            <input type="hidden" name="postId" value="${post.id}"/>
                            <input name="title" type="text" value="${post.title}"/>
                            <input name="body" type="text" value="${post.body}"/>
                            <input type="hidden" name="action" value="edit"/>
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a href="/">HOME</a>
</div>
</body>
</html>
