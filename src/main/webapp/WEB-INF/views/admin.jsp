<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Admin</title>
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

  <div class="share-button-container">
    <a href="add-item" class="btn btn-primary">Add Item</a>
  </div>

  <table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
      <th>Item</th>
      <th>Price</th>
      <th>Stock</th>
      <th>Modify</th>
      <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
      <tr>
        <td>
          <div class="item-name">${item.name}</div>
          <img class="item-img" src="${item.picPath}" alt="${item.name}">
        </td>
        <td class="price">${item.price}</td>
        <td>${item.stock}</td>
        <td class="action-links">
          <a href="modify?id=${item.itemId}" class="btn btn-warning btn-sm">Modify</a>
        </td>
        <td class="action-links">
          <a href="delete?id=${item.itemId}" class="btn btn-danger btn-sm">Delete</a>
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
  .item-img {
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
