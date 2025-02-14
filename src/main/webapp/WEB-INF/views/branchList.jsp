<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>All Branches</title>
</head>
<body>
<jsp:include page="home.jsp"/>
<div align="center" >
    <h1>All Branches</h1>
    <a href="<c:url value="/branchForm"/>">Branch Form</a>

    <table class="table table-light mt-3">
        <tr>
            <th>Branch ID</th><th>Branch Name</th><th>Branch City</th><th>Branch State</th><th>Phone Number</th><th>Actions</th>
        </tr>
        <c:forEach items="${branches}" var="branch">
            <tr>
                <td>${branch.branchId}</td>
                <td>${branch.branchName}</td>
                <td>${branch.branchAddress.city}</td>
                <td>${branch.branchAddress.state}</td>
                <td>${branch.branchAddress.phoneNumber}</td>

                <td>
                    <a href="<c:url value="/updateBranch/${branch.branchId}"/>" class="btn btn-primary"> Update </a>
                    <a href="<c:url value="/deleteBranch/${branch.branchId}"/>" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
