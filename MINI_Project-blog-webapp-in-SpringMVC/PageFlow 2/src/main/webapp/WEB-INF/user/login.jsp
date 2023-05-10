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
 /* Add your registration form styling here */
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
    .login-form {
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
      
      .error {
        color: red;
        font-weight: bold;
        text-align: center;
        padding: 10px;
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
</style>


<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">My Website</a>
            <div class="navbar-nav ms-auto mb-2 mb-lg-0">
                <a class="nav-link active" style="position: relative;" aria-current="page" href="index.html">Home</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="registration">Register</a>
            </div>
        </div>
    </nav>
</header>


	<!-- Add your registration form below -->
   <div class="login-form">
      <h2>Login Form</h2>
      <%
      if(request.getAttribute("error")!=null){
      %>
            <p class="error"><%=request.getAttribute("error")%> </p>
      <%} %>
      
      <form action="login" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" required>

        <input type="submit" value="Submit">
      </form>
    </div>

</body>
</html>
