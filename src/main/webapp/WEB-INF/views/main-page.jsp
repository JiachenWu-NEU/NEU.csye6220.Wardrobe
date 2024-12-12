<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/7
  Time: 16:14
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

    <div class="search-bar mb-4">
        <form action="search" method="get" class="d-flex">
            <input type="text" name="query" class="form-control me-2" placeholder="Search for items..." required />
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        <p class="text-danger mt-2">${errors}</p>
    </div>

    <div class="mb-4 text-center">
        <button class="btn btn-outline-primary me-2" onclick="filterItems('tops')">Tops</button>
        <button class="btn btn-outline-secondary" onclick="filterItems('bottoms')">Bottoms</button>
    </div>

    <table class="table table-striped">
        <thead class="table-dark">
        <tr>
            <th scope="col">Item</th>
            <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr class="item-row" data-type="${item.type}">
                <td>
                    <div class="d-flex flex-column">
                        <a href="item-detail?id=${item.itemId}" class="text-decoration-none mb-2">${item.name}</a>
                        <img src="${item.picPath}" alt="${item.name}" class="img-thumbnail" style="max-width: 200px; height: auto">
                    </div>
                </td>
                <td class="price">${item.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>
    function filterItems(type) {
        const rows = document.querySelectorAll('.item-row');
        rows.forEach(row => {
            if (!type || row.dataset.type === type) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }
</script>
</html>
