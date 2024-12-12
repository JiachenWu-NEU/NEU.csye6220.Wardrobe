<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Community</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Wardrobe</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="main-page">Items</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="order">Order</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="community">Community</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-danger" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="share-button-container" style="margin-bottom: 20px; text-align: center">
        <a href="publish-share" class="btn btn-primary">Publish Share</a>
    </div>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th scope="col">Publisher</th>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
            <th scope="col">Picture</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="share" items="${shareList}">
            <tr>
                <td class="text-center align-middle">${share.user.userId}</td>
                <td class="text-center align-middle text-primary">${share.title}</td>
                <td class="text-wrap">${share.content}</td>
                <td class="text-center">
                    <img class="share-img" src="${share.picture}" alt="${share.title}" style="max-width: 400px; height: auto">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<style>
    .table th:nth-child(1),
    .table td:nth-child(1) {
        width: 10%;
    }
    .table th:nth-child(2),
    .table td:nth-child(2) {
        width: 15%;
    }
    .table th:nth-child(3),
    .table td:nth-child(3) {
        width: 45%;
    }
    .table th:nth-child(4),
    .table td:nth-child(4) {
        width: 30%;
    }
</style>
</html>
