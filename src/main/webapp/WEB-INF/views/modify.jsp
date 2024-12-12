<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/10
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Modify</title>
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

  <div class="form-container">
    <h2>Add or Edit Item</h2>
    <form:form modelAttribute="item" method="post" enctype="multipart/form-data">
      <div class="mb-3">
        <form:label path="name">Name:</form:label>
        <form:input path="name" type="text" class="form-control" required="true" />
        <form:errors path="name" class="text-danger" />
      </div>

      <div class="mb-3">
        <form:label path="type">Type:</form:label>
        <form:select path="type" class="form-select" required="true">
          <form:option value="" label="Select a type" />
          <form:option value="tops">Tops</form:option>
          <form:option value="bottoms">Bottoms</form:option>
        </form:select>
        <form:errors path="type" class="text-danger" />
      </div>

      <div class="mb-3">
        <form:label path="price">Price:</form:label>
        <form:input path="price" type="number" step="0.01" class="form-control" required="true" />
        <form:errors path="price" class="text-danger" />
      </div>

      <div class="mb-3">
        <form:label path="stock">Stock:</form:label>
        <form:input path="stock" type="number" class="form-control" required="true" />
        <form:errors path="stock" class="text-danger" />
      </div>

      <div class="mb-3">
        <form:label path="description">Description:</form:label>
        <form:textarea path="description" rows="4" class="form-control" required="true"></form:textarea>
        <form:errors path="description" class="text-danger" />
      </div>

      <div class="mb-3">
        <form:label path="picPath">Picture:</form:label>
        <input type="file" id="picPath" name="picPath" accept="image/*" class="form-control">
        <form:errors path="picPath" class="text-danger" />
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
</style>
</html>
