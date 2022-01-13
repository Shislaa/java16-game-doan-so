<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<% int count = 1; %>
<body>
	<h1>This is JSP Page</h1>
	<h2>Page count is: <%=count %></h2>
	
	<p>Count from 1 to 10</p>
	<% for(int i = 1; i < 11; i++){%>  
		<span> <%= i %> </span>
	<%}%>
</body>
</html>