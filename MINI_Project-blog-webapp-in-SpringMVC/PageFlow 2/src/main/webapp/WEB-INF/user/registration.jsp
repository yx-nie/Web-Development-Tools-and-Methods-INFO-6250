<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<style>
    .registration-form {
        background-color: #f2f2f2;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0px 0px 10px 0px #ccc;
        margin: 50px auto;
        width: 500px;
    }

    .form-control {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

    input[type=submit] {
        background-color: #4CAF50;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    header {
        background-color: #333;
        color: #fff;
        padding: 20px;
    }

    nav ul {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    nav ul li {
        display: inline-block;
        margin-right: 20px;
    }

    nav ul li:last-child {
        margin-right: 0;
    }

    nav a {
        color: #fff;
        text-decoration: none;
    }

    main {
        padding: 20px;
    }

    main h2 {
        font-size: 24px;
        margin-bottom: 20px;
    }

    main ul {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    main li {
        margin-bottom: 20px;
    }

    main h3 {
        font-size: 20px;
        margin-bottom: 10px;
    }

    main p {
        font-size: 16px;
        margin-bottom: 10px;
    }

    main a {
        color: #333;
        text-decoration: none;
    }

    main a:hover {
        text-decoration: underline;
    }

    footer {
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
    }
</style>

<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">My Website</a>
            <div class="navbar-nav ms-auto mb-2 mb-lg-0">
                <a class="nav-link active" style="position: relative;" aria-current="page" href="index.html">Home</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="login">Login</a>
            </div>
        </div>
    </nav>
</header>


<!-- Add your registration form below -->
<div class="registration-form">
    <h2>Registration Form</h2>
    <%
        if(request.getAttribute("error")!=null){
    %>
    <p class="error"><%=request.getAttribute("error")%> </p>
    <%} %>
    <form action="registration" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" class="form-control" pattern="(?=.*[.@]" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" required>


        <input type="submit" value="Submit">
    </form>
</div>


</body>
</html>