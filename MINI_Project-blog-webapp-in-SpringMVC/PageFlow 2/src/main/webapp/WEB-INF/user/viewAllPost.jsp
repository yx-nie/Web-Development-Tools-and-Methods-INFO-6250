<%@page import="java.util.ArrayList"%>
<%@page import="data.UserRegistration"%>
<%@page import="data.UserSession"%>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.allPostDao" %>
<%@ page import="data.Post" %>
<%@ page import="java.util.List" %>
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

    .card{
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 5px;
        padding: 5px;
        box-shadow: 0px 0px 5px #ccc;
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




<!-- Add your table of data below -->
<div style="padding: 50px">
    <div class="card-list">
        <%
            List<Post> posts=(List<Post>) request.getAttribute("posts");
            for(Post post: posts){
        %>

            <form method ="get" action="controller">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><%=post.getUsername()%></h5>
                    <div class="row">
                        <div class="col-10">
                            <p class="card-text"><%=post.getPosts()%></p>
                        </div>
                        <div class="col-2">
                            <button type="submit" class="btn btn-secondary">Comment</button>
                        </div>
                    </div>
                </div>
            </div>
                <input type="hidden" name="_id" value="<%=post.get_id()%>"/>

                <input type="hidden" name="action" value="AddReply"/>
            </form>

        <%
            }
        %>
    </div>
</div>


</body>
</html>
