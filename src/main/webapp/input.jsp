<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter Data</title>
</head>
<body>
<h1>Enter Data</h1>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>
<form action="myservlet" method="post">
    <label for="name">Enter Name:</label>
    <input type="text" id="name" name="name"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>