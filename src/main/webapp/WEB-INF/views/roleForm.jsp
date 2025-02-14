<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Role Form</title>
    <style>
        label {
            display: inLine-block;
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
<div class="container" align="center">
    <h1>Role Form</h1>

    <form:form action="saveRole" modelAttribute="role" method="post">
        <form:label path="roleId" cssClass="label">Role ID: </form:label>
        <form:input path="roleId" readonly="true"/>
        <form:errors path="roleId" cssClass="text-danger"/><br>
        <form:label path="roleName" cssClass="label">Role Name: </form:label>
        <form:input path="roleName"/>
        <form:errors path="roleName" cssClass="text-danger"/><br>

        <button type="submit" class="btn btn-primary mb-3 mt-3">Save Role</button>
    </form:form>

    <table class="table table-light table-bordered">
        <thead>
        <tr>
            <th>Role ID</th>
            <th>Role Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
            <tr>
                <td>${role.roleId}</td>
                <td>${role.roleName}</td>
                <td>
                    <a href="<c:url value="/updateRole/${role.roleId}"/>" class="btn btn-primary">Update</a>
                    <a href="<c:url value="/deleteRole/${role.roleId}"/>" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
