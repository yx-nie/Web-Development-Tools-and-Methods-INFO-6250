<%@page import="data.UserSession"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../../css/styles.css" >
</head>
<body>

	<ul>
      <li class="center"><%
		  UserSession userSession = (UserSession) session.getAttribute("usersession");%>
		  Welcome <% out.append(userSession.getUsername()); %></li>
		<li><a href="controller?action=viewAllPost">Home</a></li>
		<li><a href="controller?action=viewMyPost">My Posts</a></li>
		<li><a href="logout">Logout</a></li>
	</ul>

	<!-- Add your content below -->


</body>
</html>
