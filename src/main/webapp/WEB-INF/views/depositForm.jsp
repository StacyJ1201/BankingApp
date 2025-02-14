<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Deposit Form</title>

    <style>
        label {
            display: inLine-block;
            width: 300px;

        }

        body {
            background-color: grey;
        }
    </style>
</head>
<body>

<jsp:include page="home.jsp"/>
<div align="center">
    <div class="col-5 mt-4">
        <h1>Deposit Form</h1>
        <form:form modelAttribute="bankTransaction" action="/depositMoney" method="POST" class="form-control">
            <div class="form-group row align-items-center mb-3">
                <form:label path="bankTransactionToAccount" cssClass="form-label">Select account: </form:label>
                <form:select path="bankTransactionToAccount" cssClass="form-control">
                    <form:options items="${accounts}" itemValue="accountId" itemLabel="accountId"/>
                </form:select>
            </div>
            <div class="form-group">
                <form:label path="amount" cssClass="form-label">How much would you like to deposit: </form:label>
                <form:input path="amount" cssClass="form-control"/>
                <form:errors path="amount" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <form:label path="comments" cssClass="form-label">Comments: </form:label>
                <form:textarea path="comments" cssClass="form-control"/>
                <form:errors path="comments" cssClass="text-danger"/>
            </div>


            <button type="submit" class="btn btn-primary btn-lg mt-4">Deposit</button>
        </form:form>

        <h1 class="mt-4 mb-3" >Transaction History</h1>

        <table class="table table-light">
            <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Amount</th>
                <th>AccountID Deposited To</th>
                <th>Comments</th>

            </tr>
            <c:forEach items="${transactions}" var="t">
                <tr>
                    <td>${t.bankTransactionDateTime.format(DateTimeFormatter.ofPattern('dd-MM-yyyy HH:mm'))}</td>
                    <td>${t.bankTransactionType}</td>
                    <td>${t.amount}</td>
                    <td>${t.bankTransactionToAccount}</td>
                    <td>${t.comments}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>

