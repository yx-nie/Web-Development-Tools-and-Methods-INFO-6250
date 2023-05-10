<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css" >
<style type="text/css">
 /* Add your registration form styling here */
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
</style>
</head>
<body>
    <ul>
        <li class="logo"><img src="icons8-logo-80.png" alt="Logo"></li>
      <li class="center">My Website</li>
        
        <li><a href="index.html">Home</a></li>
        <li><a href="login">Login</a></li>
    </ul>
    <!-- Add your registration form below -->
    <div class="registration-form">
      <h2>Registration Form</h2>
      <form action="controller" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" value="${requestScope.username}" required>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" class="form-control" value ="${requestScope['email']}"required>
        <input type="hidden" name="page" value="editProfile"/>
        <input type="submit" value="Submit">
      </form>
    </div>
    
        <div class="registration-form">
      <h2>Update Password</h2>
      <form action=""controller"" method="post">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" value="" required>
        <label for="confirm_password">Confirm Password</label>
        <input type="password" id="confirm_password" name="confirm_password" class="form-control" value ="" required>
            <input type="hidden" name="page" value="editProfilePassword"/>
    
        <input type="submit" value="Submit">
      </form>
    </div>
</body>
</html>