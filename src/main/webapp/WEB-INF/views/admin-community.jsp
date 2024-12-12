<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 20:56
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
            <a class="nav-link" href="admin">Items</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="admin-order">Order</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="admin-community">Community</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-danger" href="logout">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
      <th>Publisher</th>
      <th>Title</th>
      <th>Content</th>
      <th>Picture</th>
      <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="share" items="${shareList}">
      <tr>
        <td class="userId">${share.user.userId}</td>
        <td class="title">${share.title}</td>
        <td class="content text-truncate" style="max-width: 200px;">${share.content}</td>
        <td class="picture">
          <img class="share-img" src="${share.picture}" alt="${share.title}">
        </td>
        <td class="action-links">
          <a href="deleteShare?id=${share.shareId}" class="btn btn-danger btn-sm">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
<style>
  .share-button-container {
    margin-bottom: 20px;
    text-align: right;
  }
  .share-img {
    max-width: 100px;
    height: auto;
    margin-top: 10px;
  }
  table {
    margin-top: 20px;
  }
  th, td {
    text-align: center;
    vertical-align: middle;
  }
  .action-links a {
    margin-right: 10px;
  }
</style>
</html>
