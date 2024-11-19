<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Error</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #ffebee;
            color: #d32f2f;
            text-align: center;
            padding: 50px;
        }
        .message {
            background-color: #ffffff;
            border: 1px solid #ffcdd2;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        .button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: #ffffff;
            background-color: #d32f2f;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
        .button:hover {
            background-color: #b71c1c;
        }
    </style>
</head>
<body>
    <div class="message">
        <h1>Registration Error</h1>
        <p>Sorry, something went wrong during the registration process.</p>
        <p><%= request.getParameter("message") %></p>
        <a href="reguser.jsp" class="button">Try Again</a>
    </div>
</body>
</html>
