<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

    <c:choose>
      <c:when test="${empty orders}">
        <div class="alert alert-info text-center">No items in your cart.</div>
      </c:when>
      <c:otherwise>
        <table class="table table-bordered table-hover">
          <thead class="table-dark">
          <tr>
            <th scope="col">OrderId</th>
            <th scope="col">Address</th>
            <th scope="col">Date</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="order" items="${orders}">
            <tr>
              <td>
                <div class="order">
                  <a href="order-detail?id=${order.orderId}" class="text-decoration-none text-primary">${order.orderId}</a>
                </div>
              </td>
              <td class="text-center align-middle">${order.address}</td>
              <td class="text-center align-middle text-muted">${order.date}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>
  </div>
</body>
</html>
