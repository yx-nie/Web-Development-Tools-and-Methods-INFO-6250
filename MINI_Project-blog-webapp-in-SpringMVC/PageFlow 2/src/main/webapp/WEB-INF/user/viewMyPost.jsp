<%@page import="java.util.ArrayList"%>
<%@page import="data.UserRegistration"%>
<%@page import="data.UserSession"%>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.allPostDao" %>
<%@ page import="data.Post" %>
<%@ page import="dao.MyPostDao" %>
<%@ page import="java.util.List" %>
<%@ page import="data.Reply" %>
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
    .card-list{
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 10px;
        padding: 10px;
        box-shadow: 0px 0px 5px #ccc;
    }
    .card{
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 10px;
        padding: 10px;
        box-shadow: 0px 0px 5px #ccc;
    }
    .reply-card {
        border: 1px solid #b9bbbe;
        border-radius: 5px;
        padding: 10px;
        box-background: #e8e8e8;
        box-shadow: 0px 0px 5px #ccc;
        margin-left: 20px;
        margin-right: 20px;
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
                Welcome <% out.append(userSession.getUsername()); %> </a>
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

    <h1>Post what you are thinking...</h1>
    <form method="post" action="controller">
    <div>
        <input type="text" id="newpost" name="newpost" style="width: 800px; height: 100px">
        <button type="submit" class="btn btn-secondary">Post</button>
    </div>
        <input type="hidden" name="username" value="<%=userSession.getUsername()%>">
        <input type="hidden" name="email" value="<%=userSession.getEmail()%>">
        <input type="hidden" name="action" value="CreateMyPost">
    </form>
</div>
<div style="padding: 50px">
    <div class="card-list">
        <%
            List<Post> myposts=(List<Post>) request.getAttribute("myposts");
            for (Post post : myposts) {
        %>
        <c:forEach var="post" items="<%=post%>">
            <form method="post" action="controller">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-10">
                            <p class="card-text"><%=post.getPosts()%></p>
                        </div>
                        <div class="col-2">
                            <button type="submit" class="btn btn-secondary">Delete</button>
                        </div>
                        </div>
                </div>
            </div>
            <div>
                <%
                    List<Reply> replies=post.getReplies();
                    if(replies!=null){
                        for(Reply reply:replies){
                %>
                <div class="reply-card">
                    <div class="reply-card-body">
                        <h6 class="reply-card-title"><%=reply.getUsername()%> <small class="text-muted">reply to</small> <%=post.getUsername()%></h6>
                        <p class="reply-card-text"><%=reply.getPost()%></p>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
                <input type="hidden" name="_id" value="<%=post.get_id()%>">
                <input type="hidden" name="username" value="<%=post.getUsername()%>">
                <input type="hidden" name="action" value="DeleteMyPost">
            </form>
        </c:forEach>
        <%
            }
        %>
    </div>
</div>

<script type="text/javascript">


</script>

</body>
</html>
