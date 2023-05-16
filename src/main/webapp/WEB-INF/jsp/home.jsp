<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container" style="width: fit-content; margin-top: 12rem;">
    <h1>Welcome to Your Account Home</h1>
    <br>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">Error: ${error}</div>
    <% } %>

    <div class="row">
        <div class="card" style="width: 18rem; margin: 1rem;">
            <div class="card-body">
                <h5 class="card-title">Your Balance</h5>
                <h4 class="card-text" style="margin-top: 40px; margin-bottom: 40px;">$${user.getBalance()}</h4>
                <a href="/home" class="btn btn-secondary">Refresh</a>
            </div>
        </div>

        <div class="card" style="width: 18rem; margin: 1rem;">
            <div class="card-body">
                <h5 class="card-title">Withdraw</h5>
                <p class="card-text" style="margin-top: 13px;">Enter the amount:</p>
                <form:form action="/deposit" method="post" modelAttribute="deposit">
                    <div style="margin-bottom: 20px;"><form:input class="form-control" type="number" path="withdraw"/></div>
                    <input type="submit" class="btn btn-primary" value="Submit">
                </form:form>
            </div>
        </div>

        <div class="card" style="width: 18rem; margin: 1rem;">
            <div class="card-body">
                <h5 class="card-title">Deposit</h5>
                <p class="card-text" style="margin-top: 13px;">Enter the amount:</p>
                <form:form action="/deposit" method="post" modelAttribute="deposit">
                    <div style="margin-bottom: 20px;"><form:input class="form-control" type="number" path="deposit"/></div>
                    <input type="submit" class="btn btn-primary" value="Submit">
                </form:form>
            </div>
        </div>
    </div>

</div>
</body>
</html>