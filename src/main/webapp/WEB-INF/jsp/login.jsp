<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container" style="width: fit-content; margin-top: 12rem;">
    <h1 style="font-weight: bold">Login Here!</h1>
    <br>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">Error: ${error}</div>
    <% } %>

    <form:form action="/login" method="post" modelAttribute="user">
        <div style="margin-bottom: 10px;"><form:label cssStyle="margin-bottom: 10px;" path="username">Username: </form:label> <form:input class="form-control" type="text" path="username"/></div>
        <div><form:label cssStyle="margin-bottom: 10px;" path="password">Password: </form:label> <form:input class="form-control" type="text" path="password"/></div>
        <br>
        <div><input class="btn btn-success" style="width: 15rem;" type="submit" value="Login"/></div>
    </form:form>
</div>

</body>
</html>