<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
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
      <th>OrderId</th>
      <th>Name</th>
      <th>Address</th>
      <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
      <tr>
        <td class="order-link">
          <a href="admin-order-detail?id=${order.orderId}">${order.orderId}</a>
        </td>
        <td class="userId">${order.user.username}</td>
        <td class="address">${order.address}</td>
        <td class="date">${order.date}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
<style>
  table {
    margin-top: 20px;
  }
  th, td {
    text-align: center;
    vertical-align: middle;
  }
  .order-link a {
    text-decoration: none;
    color: #007bff;
  }
  .order-link a:hover {
    text-decoration: underline;
  }
</style>
</html>
