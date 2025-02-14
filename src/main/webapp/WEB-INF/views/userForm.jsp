<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User Form</title>
    <style>
        label {
            display: inline-block;
            width: 120px;
        }

        button {
            margin-top: 15px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<jsp:include page="home.jsp"/>
<div align="center">
    <h1>User Form</h1>

    <form:form action="/signup/saveUser" modelAttribute="user" method="post">
        <form:label path="userId" cssClass="label">User ID: </form:label>
        <form:input path="userId" readonly="true"/><br>
        <form:label path="username" cssClass="form-label">Username: </form:label>
        <form:input path="username" cssClass=""/>
        <form:errors path="username" cssClass="text-danger"/><br>
        <form:label path="password" cssClass="label">Password: </form:label>
        <form:input path="password"/>
        <form:errors path="password" cssClass="text-danger"/><br>
        <form:label path="email" cssClass="label">Email: </form:label>
        <form:input path="email"/>
        <form:errors path="email" cssClass="text-danger"/><br>
        <form:label path="roles" cssClass="label">Roles: </form:label>
       <form:checkboxes path="roles" items="${roles}" itemLabel="roleName" itemValue="roleId"/>
        <form:errors path="roles" cssClass="text-danger"/><br>
        <button type="submit" class="button">Save User</button>
    </form:form>

    <table class="table table-light">
        <thead>
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
            <th>Customer</th>
            <th>Roles</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.customer.customerId}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">
                        ${role.roleName}
                    </c:forEach>
                </td>
                <td>
                    <a href="<c:url value="/signup/updateUser/${user.userId}"/>" class="btn btn-primary">Update</a>
                    <sec:authorize access="hasRole('Role_Admin')">
                        <a href="<c:url value="/signup/deleteUser/${user.userId}"/>" class="btn btn-danger">Delete</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

