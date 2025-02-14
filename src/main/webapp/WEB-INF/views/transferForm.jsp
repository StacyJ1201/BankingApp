<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Transfer Form</title>
</head>
<body>
<jsp:include page="home.jsp"/>
<div align="center">
    <div class="container col-6 row-9">
        <form:form modelAttribute="transaction" method="POST" action="/transferMoney" cssClass="form-group">

            <div class="form-group">
                <form:label path="bankTransactionFromAccount" cssClass="form-label">Select Account To Transfer From: </form:label>
                <form:select path="bankTransactionFromAccount" cssClass="form-control">
                    <form:options items="${accounts}" itemValue="accountId" itemLabel="accountId"/>
                </form:select>
            </div>

            <div class="form-group">
                <form:label path="bankTransactionToAccount" cssClass="form-label">Select Account To Transfer To: </form:label>
                <form:select path="bankTransactionToAccount" cssClass="form-control">
                    <form:options items="${accounts}" itemValue="accountId" itemLabel="accountId"/>
                </form:select>
            </div>

            <div class="form-group">
                <form:label path="amount" cssClass="form-label">Withdrawal Amount: </form:label>
                <form:input path="amount" cssClass="form-control"/>
            </div>

            <div class="form-group">
                <form:label path="comments" cssClass="form-label">Comment: </form:label>
                <form:textarea path="comments" cssClass="form-control"/>
            </div>


            <button type="submit" class="btn btn-primary btn-large mt-4 mb-5">Transfer Money</button>
        </form:form>

        <table class="table table-light">
            <thead>
            <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Account Transferred From</th>
                <th>Account Transferred To</th>
                <th>Comment</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${transactions}" var="t">
                <tr>
                    <td>${t.bankTransactionDateTime.format(DateTimeFormatter.ofPattern('dd-MM-yyyy HH:mm'))}</td>
                    <td>${t.bankTransactionType}</td>
                    <td>${t.amount}</td>
                    <td>${t.bankTransactionFromAccount}</td>
                    <td>${t.bankTransactionToAccount}</td>
                    <td>${t.comments}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>

</body>
</html>