<%@ page import="data.UserSession" %>
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
            <a class="navbar-brand" href="#"><%
                UserSession userSession = (UserSession) session.getAttribute("usersession");%>
                Welcome <% out.append(userSession.getUsername()); %></a>
            <div class="navbar-nav ms-auto mb-2 mb-lg-0">
                <a class="nav-link active" style="position: relative;" aria-current="page" href="controller?action=ViewAllPost">Home</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="controller?action=ViewMyPost">My Posts</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="controller?action=UserProfile">Profile</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="logout">Logout</a>
            </div>
        </div>
    </nav>
</header>


<!-- Add your registration form below -->
<div class="login-form">

    <h2>Change My Profile</h2>

    <form method="post" action="controller">
        <div>
            <div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="form-control" value="<%=userSession.getUsername()%>">
                <small class="text-muted">You are not allowed to change username</small>
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" value="<%=userSession.getEmail()%>">
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" value="<%=userSession.getPwd()%>">
            </div>
            <div>
                <label for="repassword">Retype-Password</label>
                <input type="password" id="repassword" name="repassword" class="form-control" value="">
            </div>


            <button type="submit" class="btn btn-primary" onclick="submitForm()">Confirm</button>
            <button id="cancel" name="cancel" class="btn btn-secondary" onclick="clearFunction()" Value="Cancel">Cancel</button>
        </div>
        <input type="hidden" name="action" value="UserProfile"/>
    </form>
</div>

<script>
    function submitForm(){
        var password=document.getElementById("password").value
        var repass=document.getElementById("repassword").value
        if(password!==repass){
            alert("Passwords are not matched")
            return false
        }else{
            alert("Profile updated successfully")
        }
    }
    function clearFunction(){
        document.getElementById("email").innerHTML=""
        document.getElementById("password").innerHTML=""
        document.getElementById("repassword").innerHTML=""
    }
</script>

</body>
</html>

