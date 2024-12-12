<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
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

    <div class="item-container">
        <h2>${item.name}</h2>
        <img src="${item.picPath}" alt="${item.name}" class="img-thumbnail">
        <p><strong>Price:</strong> ${item.price}</p>
        <p><strong>Description:</strong> ${item.description}</p>
        <p><strong>Stock:</strong> ${item.stock}</p>

        <div class="form-container">
            <form method="post">
                <label for="amount"><strong>Quantity:</strong></label>
                <input type="number" id="amount" name="amount" class="form-control" style="width: 80px;" min="1" max="${item.stock}" required>

                <input type="hidden" name="itemId" value="${item.itemId}">
                <button type="submit" class="btn btn-primary">Add to Cart</button>
            </form>
        </div>
    </div>

    <div class="comments-container mt-5">
        <h3>Comments</h3>
        <form method="post" action="addComment" class="mb-4">
            <input type="hidden" name="itemId" value="${item.itemId}">
            <div class="form-group">
                <textarea id="commentContent" name="content" class="form-control w-100" rows="3" placeholder="Write your comment..." required></textarea>
            </div>
            <button type="submit" class="btn btn-secondary">Post Comment</button>
        </form>

        <div class="comments-list">
            <c:forEach var="comment" items="${comments}">
                <div class="comment-item p-3 mb-3 border rounded">
                    <p><strong>${comment.user.username}</strong> <span class="text-muted small">(${comment.date})</span></p>
                    <p>${comment.comment}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<style>
    .item-container {
        margin-top: 30px;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #f9f9f9;
    }
    .item-container h2 {
        margin-bottom: 15px;
        color: #333;
    }
    .item-container img {
        display: block;
        margin: 0 auto 15px;
        max-width: 800px;
        height: auto;
    }
    .form-container {
        margin-top: 20px;
    }
    .form-container form {
        display: flex;
        align-items: center;
        gap: 10px;
    }
    .form-container label {
        margin-bottom: 0;
    }
    .form-control {
        width: auto;
    }
    .comments-container {
        margin-top: 30px;
    }
    .comments-list .comment-item {
        background-color: #f8f9fa;
    }
</style>
</html>
