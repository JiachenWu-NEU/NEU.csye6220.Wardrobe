<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

    <div class="details">
      <c:set var="totalPrice" value="0" />
      <c:forEach var="orderItem" items="${order.orderItems}">
        <c:set var="itemTotal" value="${orderItem.price * orderItem.amount}" />
        <c:set var="totalPrice" value="${totalPrice + itemTotal}" />
      </c:forEach>
      <h2>Total Price:
        <fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="2" />
      </h2>
      <h2>Destination: ${order.address}</h2>
      <h2>Order Date: ${order.date}</h2>
    </div>

  <table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
      <th>Item</th>
      <th>Amount</th>
      <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="orderItem" items="${order.orderItems}">
      <tr>
        <td>
          <div>${orderItem.name}</div>
          <img class="item-img" src="${orderItem.picture}" alt="${orderItem.name}">
        </td>
        <td>${orderItem.amount}</td>
        <td>${orderItem.price}</td>
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
  .item-img {
    max-width: 100px;
    height: auto;
    margin-top: 10px;
  }
  .total, .details {
    margin-top: 20px;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background-color: #f9f9f9;
  }
  .details h2 {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }
</style>
</html>
