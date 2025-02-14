<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<div>
    <nav class=" navbar navbar-dark bg-dark mb-3">
        <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/bankActions"/>">Welcome ${pageContext.request.userPrincipal.name}<sec:authentication property="authorities"/></a>
        <ul class="navbar-nav flex-row gap-3">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/bankActions"/>">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/branch'/>">Branches</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/accountForm'/>">My Accounts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/customerForm'/>">Become a Customer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/bankActions'/>">Transactions</a>
            </li>
            <sec:authorize access="hasRole('Role_Admin')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/roleForm'/>">Add Role</a>
                </li>
            </sec:authorize>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/signup/userForm'/>">Sign in</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/logout'/>">Logout</a>
            </li>
        </ul>
        </div>
    </nav>
</div>
</body>
</html>
