<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>Register</h1>
            <br>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">Error: ${error}</div>
            <% } %>

            <form:form action="/register" method="post" modelAttribute="user">
                <div><form:label path="username">Username: </form:label> <form:input class="form-control" type="text" path="username"/></div>
                <div><form:label path="password">Password: </form:label> <form:input class="form-control" type="text" path="password"/></div>
                <div><form:label path="balance">Balance: </form:label> <form:input class="form-control" type="number" path="balance"/></div>
                <br>
                <div><input class="btn btn-success" type="submit" value="Register"/></div>
            </form:form>
        </div>

    </body>
</html>