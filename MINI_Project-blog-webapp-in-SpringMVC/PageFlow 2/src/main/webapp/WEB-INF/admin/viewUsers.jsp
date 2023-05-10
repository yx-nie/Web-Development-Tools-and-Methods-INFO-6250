<%@page import="java.util.ArrayList"%>
<%@page import="data.UserRegistration"%>
<%@page import="data.UserSession"%>
<%@ page import="dao.UserDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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


        /* Add your table styling below */
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
</style>

<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand"><%
                UserSession userSession = (UserSession)session.getAttribute("usersession");
            %>Welcome Admin  <%=userSession.getUsername()%></a>
            <div class="navbar-nav ms-auto mb-2 mb-lg-0">
                <a class="nav-link active" style="position: relative;" aria-current="page" href="controller?action=viewUsers">View All Users</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="controller?action=ViewPosts">View All Posts</a>
                <a class="nav-link active" style="position: relative;" aria-current="page" href="logout">Logout</a>
            </div>
        </div>
    </nav>
</header>




<!-- Add your table of data below -->
<div style="padding: 50px">

    <h1>Data Table</h1>
    <table>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>

        </tr>
        <%
            UserDao userDao=new UserDao();
            ArrayList<UserRegistration> userRegistrations = userDao.viewAllUsers("user");
            for (UserRegistration userRegistration : userRegistrations) {
        %>
        <!-- Add more rows of data as needed -->
        <tr>
            <td><%=userRegistration.getName()%></td>
            <td><%=userRegistration.getEmail()%></td>
            <td><%=userRegistration.getPassword()%></td>
        </tr>

        <%
            }
        %>
    </table>
</div>


</body>
</html>
