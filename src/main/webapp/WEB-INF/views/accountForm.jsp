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
    <title>Account Form</title>

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
    <h1>Account Form</h1>

    <form:form action="/saveAccount"  method="post" modelAttribute="account" cssClass="form-control">
        <form:label path="accountId" cssClass="label">ID: </form:label>
        <form:input path="accountId" readonly="true"/>
        <form:errors path="accountId" cssClass="text-danger"/> <br>
        <form:label path="accountType" cssClass="label">Type: </form:label>
        <form:radiobutton path="accountType" value="CHECKING" label="CHECKING"/>
        <form:radiobutton path="accountType" value="SAVINGS" label="SAVINGS"/>
        <form:radiobutton path="accountType" value="LOAN" label="LOAN"/>
        <form:errors path="accountType" cssClass="text-danger"/> <br>
        <form:label path="accountBranch" cssClass="label">Branch: </form:label>
        <form:select path="accountBranch">
            <form:option value="">-- SELECT BRANCH --</form:option>
            <c:forEach items="${branches}" var="branch">
                <form:option value="${branch.branchId}">${branch.branchName}</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="accountBranch" cssClass="text-danger"/> <br>
        <input type="submit" value="Create Account" class="btn btn-primary btn-large mt-4">
    </form:form>

    <table class="table table-light">
        <tr>
            <th>Account Id</th>
            <th>Account Holder</th>
            <th>Account Date Opened</th>
            <th>Account Balance</th>
            <th>Account Type</th>
            <th>Account Customer</th>
            <th>Account Branch</th>
        </tr>

        <c:forEach items="${accounts}" var="account">
            <tr>
                <td>${account.accountId}</td>
                <td>${account.accountHolder}</td>
                <td>${account.accountDateOpened}</td>
                <td>${account.accountBalance}</td>
                <td>${account.accountType}</td>
                <td>${account.accountCustomer.customerName}</td>
                <td>${account.accountBranch.branchName}</td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
