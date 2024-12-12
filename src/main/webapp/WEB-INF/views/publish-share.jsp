<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Publish share</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
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

    <div class="form-container">
        <h2>Publish a Share</h2>
        <form:form modelAttribute="share" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <form:label path="title">Title:</form:label>
                <form:input path="title" type="text" class="form-control" required="true" />
                <form:errors path="title" class="text-danger" />
            </div>

            <div class="mb-3">
                <form:label path="content">Content:</form:label>
                <form:textarea path="content" rows="4" class="form-control" required="true"></form:textarea>
                <form:errors path="content" class="text-danger" />
            </div>

            <div class="mb-3">
                <form:label path="picture">Picture:</form:label>
                <input type="file" id="picture" name="picture" accept="image/*" class="form-control">
                <form:errors path="picture" class="text-danger" />
            </div>

            <div class="btn-container">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form:form>
    </div>
</div>
</body>
<style>
    .form-container {
        margin-top: 30px;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #f9f9f9;
    }
    .form-container h2 {
        margin-bottom: 20px;
    }
    .form-container label {
        font-weight: bold;
    }
    .btn-container {
        margin-top: 20px;
        display: flex;
        justify-content: space-between;
    }
    .back-link {
        margin-top: 15px;
        display: inline-block;
    }
</style>
</html>
