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
    <title>Bank Actions</title>
</head>
<body>
<div align="center">

    <jsp:include page="home.jsp"/>

    <h1>Actions: </h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="d-grip gap-3">
                    <a href="<c:url value="/depositForm"/>" class="btn btn-primary btn-large fs-4 py-3">Deposit</a>

                    <a href="<c:url value="/withdrawalForm"/>" class="btn btn-primary btn-large fs-4 py-3">Withdrawal</a>

                    <a href="<c:url value="/transferForm"/>" class="btn btn-primary btn-large fs-4 py-3">Transfer</a>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>