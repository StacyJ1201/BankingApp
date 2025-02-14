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
    <title>Customer Form</title>

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
<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>
<jsp:include page="home.jsp"/>
<div align="center">
    <h1>Customer Form</h1>

    <form:form modelAttribute="customer" method="POST" action="/saveCustomer" class="form-control">
        <form:hidden path="user.userId"/>
        <form:label path="customerId" cssClass="label">Customer ID: </form:label>
        <form:input path="customerId" readonly="true"/><br>
        <form:label path="customerName">Name:</form:label>
        <form:input path="customerName" />
        <form:errors path="customerName" cssClass="text-danger"/><br>
        <form:label path="customerGender" cssClass="label">Gender:</form:label>
        <form:radiobutton path="customerGender" value="MALE" label="Male"/>
        <form:radiobutton path="customerGender" value="FEMALE" label="Female"/>
        <form:radiobutton path="customerGender" value="OTHERS" label="Others"/>
        <form:errors path="customerGender" cssClass="text-danger"/><br>
        <form:label path="customerDOB">Date of Birth: </form:label>
        <form:input path="customerDOB" type="date"/>
        <form:errors path="customerDOB" cssClass="text-danger"/><br>
        <form:label path="customerAddress.addressLine1">Address Line 1:</form:label>
        <form:input path="customerAddress.addressLine1"/>
        <form:errors path="customerAddress.addressLine1" cssClass="text-danger"/><br>
        <form:label path="customerAddress.addressLine2">Address Line 2:</form:label>
        <form:input path="customerAddress.addressLine2"/><br>
        <form:label path="customerAddress.city">City:</form:label>
        <form:input path="customerAddress.city"/>
        <form:errors path="customerAddress.city" cssClass="text-danger"/><br>
        <form:label path="customerAddress.state">State:</form:label>
        <form:input path="customerAddress.state"/>
        <form:errors path="customerAddress.state" cssClass="text-danger"/><br>
        <form:label path="customerAddress.country">Country:</form:label>
        <form:input path="customerAddress.country"/>
        <form:errors path="customerAddress.country" cssClass="text-danger"/><br>
        <form:label path="customerAddress.zipCode">Zip Code:</form:label>
        <form:input path="customerAddress.zipCode"/>
        <form:errors path="customerAddress.zipCode" cssClass="text-danger"/><br>
        <form:label path="customerAddress.phoneNumber">Phone Number:</form:label>
        <form:input path="customerAddress.phoneNumber"/>
        <form:errors path="customerAddress.phoneNumber" cssClass="text-danger"/><br>
        <form:label path="customerSSN">SSN:</form:label>
        <form:input path="customerSSN"/>
        <form:errors path="customerSSN" cssClass="text-danger"/><br>
        <input type="submit" value="Save Customer" class="mt-3"/><br><br>
    </form:form>

    <table class="table table-light">
        <tr>
            <th>Customer ID<th>Name</th><th>Gender</th><th>DOB</th><th>Address Line 1</th><th>Address Line 2</th><th>City</th><th>State</th>
            <th>Country</th><th>Zip Code</th><th>Phone Number</th><th>SSN</th><th>Actions</th><th>Create An Account</th>
        </tr>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.customerName}</td>
                <td>${customer.customerGender}</td>
                <td>${customer.customerDOB}</td>
                <td>${customer.customerAddress.addressLine1}</td>
                <td>${customer.customerAddress.addressLine2}</td>
                <td>${customer.customerAddress.city}</td>
                <td>${customer.customerAddress.state}</td>
                <td>${customer.customerAddress.country}</td>
                <td>${customer.customerAddress.zipCode}</td>
                <td>${customer.customerAddress.phoneNumber}</td>
                <td>${customer.customerSSN}</td>
                <td>
                <a href="<c:url value="/updateCustomer/${customer.customerId}"/>" class="btn btn-primary">Update</a>
                <a href="<c:url value="/deleteCustomer/${customer.customerId}"/>" class="btn btn-danger">Delete</a>
                </td>
                <td>
                    <a href="<c:url value="/accountForm"/>" class="btn btn-primary">Create</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
