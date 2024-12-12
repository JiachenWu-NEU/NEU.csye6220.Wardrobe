<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cart</title>
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

  <div class="total text-center mb-4">
    <h2>Total Price: <span class="text-success">
            <c:set var="totalPrice" value="0" />
            <c:forEach var="cartItem" items="${cartItems}">
              <c:set var="itemTotal" value="${cartItem.price * cartItem.amount}" />
              <c:set var="totalPrice" value="${totalPrice + itemTotal}" />
            </c:forEach>
            <fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="2" />
        </span></h2>
    <form method="post" class="d-inline-block mt-3">
      <button type="submit" class="btn btn-success">Place Order</button>
    </form>
  </div>

  <c:choose>
    <c:when test="${empty cartItems}">
      <div class="alert alert-info text-center">No items in your cart.</div>
    </c:when>
    <c:otherwise>
      <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
          <th scope="col">Item</th>
          <th scope="col">Amount</th>
          <th scope="col">Price</th>
          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cartItem" items="${cartItems}">
          <tr>
            <td>
              <div class="d-flex flex-column align-items-center">
                <a href="item-detail?id=${cartItem.itemId}" class="text-decoration-none text-primary">${cartItem.name}</a>
                <img class="img-thumbnail mt-2" src="${cartItem.picture}" alt="${cartItem.name}">
              </div>
            </td>
            <td class="text-center align-middle">
              <input type="number" class="form-control" style="width: 80px; margin: auto;"
                     value="${cartItem.amount}"
                     data-id="${cartItem.cartItemId}"
                     onchange="updateAmount(this)" min="1">
            </td>
            <td class="text-center align-middle text-success">${cartItem.price}</td>
            <td class="text-center align-middle">
              <a href="deleteCartItem?id=${cartItem.cartItemId}" class="btn btn-danger btn-sm">Delete</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</body>
<script>
  function updateAmount(inputElement) {
    const newAmount = inputElement.value;
    const cartItemId = inputElement.getAttribute("data-id");
    console.log(cartItemId);
    console.log(newAmount);
    fetch('updateCartItemAmount?id=' + cartItemId + '&amount=' + newAmount, {
      method: 'POST'
    }).then(response => {
              if (response.ok) {
                console.log('Amount updated successfully');
              } else {
                console.error('Failed to update amount');
              }
            })
            .catch(error => console.error('Error:', error));
  }
</script>
</html>
