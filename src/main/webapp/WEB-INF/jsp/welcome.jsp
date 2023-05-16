<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Welcome</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
    <div class="container" style="width: fit-content; margin-top: 12rem;">
        <h1>Welcome to Bank App</h1>
        <br>

        <div class="row">
            <div class="card" style="width: 18rem; margin: 1rem;">
                <div class="card-body">
                    <h5 class="card-title">Login</h5>
                    <p class="card-text">Login to manage your bank account.</p>
                    <a href="/login" class="btn btn-primary stretched-link">Here</a>
                </div>
            </div>

            <div class="card" style="width: 18rem; margin: 1rem;">
                <div class="card-body">
                    <h5 class="card-title">Register</h5>
                    <p class="card-text">Register a new bank account now.</p>
                    <a href="/register" class="btn btn-secondary stretched-link">Here</a>
                </div>
            </div>
        </div>

    </div>
    </body>
</html>