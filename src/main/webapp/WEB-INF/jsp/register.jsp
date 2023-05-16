<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="width: fit-content; margin-top: 12rem;">
            <h1 style="font-weight: bold">Register Here!</h1>
            <br>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">Error: ${error}</div>
            <% } %>

            <form:form action="/register" method="post" modelAttribute="user">
                <div style="margin-bottom: 10px;"><form:label cssStyle="margin-bottom: 10px;" path="username">Username: </form:label> <form:input class="form-control" type="text" path="username"/></div>
                <div style="margin-bottom: 10px;"><form:label cssStyle="margin-bottom: 10px;" path="password">Password: </form:label> <form:input class="form-control" type="text" path="password"/></div>
                <div><form:label cssStyle="margin-bottom: 10px;" path="balance">Balance: </form:label> <form:input class="form-control" type="text" path="balance"/></div>
                <br>
                <div><input class="btn btn-success" style="width: 19rem;" type="submit" value="Register"/></div>
            </form:form>
        </div>

    </body>
</html>