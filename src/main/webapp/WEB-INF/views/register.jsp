<%--
  Created by IntelliJ IDEA.
  User: 22971
  Date: 2024/12/7
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header text-center bg-primary text-white">
            <h3>Welcome to Wardrobe</h3>
          </div>
          <div class="card-body">
            <p class="text-center text-muted">${message}</p>
            <form:form modelAttribute="user" method="post">
              <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <form:input path="username" id="username" class="form-control" placeholder="Enter your name" />
                <form:errors path="username" class="text-danger" />
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <form:input path="password" id="password" class="form-control" type="password" placeholder="Enter your password" />
                <form:errors path="password" class="text-danger" />
              </div>
              <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <form:input path="address" id="address" class="form-control" placeholder="Enter your address" />
                <form:errors path="address" class="text-danger" />
              </div>
              <button type="submit" class="btn btn-primary w-100">Register</button>
            </form:form>
            <form action="login" method="get" class="mt-3">
              <button type="submit" class="btn btn-secondary w-100">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
