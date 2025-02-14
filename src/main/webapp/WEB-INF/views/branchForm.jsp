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
    <title>Branch Form</title>
    <style>
        label {
            display: inline-block;
            width: 120px;
        }
    </style>
</head>
<body>
<jsp:include page="home.jsp"/>
<div align="center">
    <h1>Branch Form</h1>

    <form:form action="/branchForm" modelAttribute="branch" method="post">
        <div class="form-group mb-3">
            <form:hidden path="branchId"/>
        <label>Branch Name: </label>
            <form:input path="branchName"/>
            <form:errors path="branchName" cssClass="text-danger"/> <br>
            <label>Address Line 1: </label>
            <form:input path="branchAddress.addressLine1"/>
            <form:errors path="branchAddress.addressLine1" cssClass="text-danger"/> <br>
            <label>Address Line 2: </label>
            <form:input path="branchAddress.addressLine2"/>
            <form:errors path="branchAddress.addressLine2" cssClass="text-danger"/> <br>
            <label>City: </label>
            <form:input path="branchAddress.city"/>
            <form:errors path="branchAddress.city" cssClass="text-danger"/> <br>
            <label>State: </label>
            <form:input path="branchAddress.state"/>
            <form:errors path="branchAddress.state" cssClass="text-danger"/> <br>
            <label>Country: </label>
            <form:input path="branchAddress.country"/>
            <form:errors path="branchAddress.country" cssClass="text-danger"/> <br>
            <label>Zipcode: </label>
            <form:input path="branchAddress.zipCode"/>
            <form:errors path="branchAddress.zipCode" cssClass="text-danger"/> <br>
            <label>Phone Number: </label>
            <form:input path="branchAddress.phoneNumber"/>
            <form:errors path="branchAddress.phoneNumber" cssClass="text-danger"/> <br>
            <button type="submit" class="btn btn-primary mt-3">Save Branch</button>
        </div>
    </form:form>

    <a href="<c:url value="/branch"/>">See All Branches</a>



</div>
<script src="https://cdn.bootstrap.cdn/bootstrap.bundle.min.js"></script>
</body>
</html>
