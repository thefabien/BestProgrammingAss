<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Success</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
    }
    h1 {
      color: #333;
    }
    p {
      font-size: 16px;
    }
    a {
      text-decoration: none;
      color: #1a73e8;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<h1>Data Inserted Successfully</h1>
<p>ID: ${id}</p>
<p>Name: ${name}</p>
<a href="index.jsp">Go back to Home</a>
</body>
</html>