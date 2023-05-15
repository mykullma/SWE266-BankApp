<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>

        <form:form action="/register" method="post" modelAttribute="user">
            <form:label path="username">Username: </form:label> <form:input type="text" path="username"/>
            <form:label path="password">Password: </form:label> <form:input type="text" path="password"/>
            <form:label path="balance">Balance: </form:label> <form:input type="number" path="balance"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>