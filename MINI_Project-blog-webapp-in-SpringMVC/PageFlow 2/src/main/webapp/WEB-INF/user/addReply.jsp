<%@ page import="data.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="data.UserSession" %>
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

    .reply-card-body {
        display: flex;
        flex-direction: column;
    }

    .reply-card-title {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .reply-card-text {
        font-size: 14px;
        margin-bottom: 0;
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
                <a class="nav-link active" style="position: relative;" aria-current="page" href="logout">Logout</a>
            </div>
        </div>
    </nav>
</header>


<!-- Add your table of data below -->
<div style="padding: 50px">
    <div class="card-list">
        <%
            Post post=(Post) request.getAttribute("post");
            %>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><%=post.getUsername()%></h5>
                <p class="card-text"><%=post.getPosts()%></p>
            </div>
        </div>
        <div>
        <%
            List<Reply> replies=(List<Reply>) request.getAttribute("replies");
            if(replies!=null){
                for(Reply reply: replies){
        %>
        <c:forEach var="reply" items="<%=reply%>">
            <form method="post" action="controller">
                <div class="reply-card">
                    <div class="reply-card-body">
                        <div class="row">
                            <div class="col-10">
                                <h6 class="reply-card-title"><%=reply.getUsername()%> <small class="text-muted">reply to</small> <%=post.getUsername()%></h6>
                            </div>
                            <%
                                if(reply.getUsername().equals(userSession.getUsername())){
                            %>
                            <div class="col-2">
                                <button type="submit" class="btn btn-secondary">Delete</button>
                            </div>
                            <%
                                }
                            %>
                        </div>

                        <p class="reply-card-text"><%=reply.getPost()%></p>
                    </div>
                </div>
                <input type="hidden" name="action" value="DeleteMyReply"/>
                <input type="hidden" name="post_id" value="<%=post.get_id()%>">
                <input type="hidden" name="reply_id" value="<%=reply.get_id()%>">
            </form>
        </c:forEach>

        <%
                }
            }
        %>
        </div>
    </div>
</div>

<div style="padding: 50px">
    <form method="post" action="controller">
        <div class="row">
            <input type="text" id="newreply" name="newreply" style="width: 800px; height: 100px" placeholder="reply">
        </div>
        <div class="row">
            <button type="submit" class="btn btn-secondary">Reply</button>
        </div>
        <input type="hidden" name="username" value="<%=userSession.getUsername()%>"/>
        <input type="hidden" name="email" value="<%=userSession.getEmail()%>"/>
        <input type="hidden" name="post_id" value="<%=post.get_id()%>"/>
        <input type="hidden" name="action" value="AddReply"/>

    </form>
</div>

</body>
</html>

