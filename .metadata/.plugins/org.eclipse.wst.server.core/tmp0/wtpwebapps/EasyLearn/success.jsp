<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #e0f7fa;
            color: #00695c;
            text-align: center;
            padding: 50px;
        }
        .message {
            display: none; /* Hide the message div */
        }
        .button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: #ffffff;
            background-color: #00796b;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
        .button:hover {
            background-color: #004d40;
        }
    </style>
    <script>
        window.onload = function() {
            alert("Registration Successful! Thank you for registering.");
            window.location.href = "loginuser.jsp"; // Redirect to index.jsp
        }
    </script>
</head>
<body>
    <div class="message">
        <h1>Registration Successful</h1>
        <p>Thank you for registering! Your account has been created successfully.</p>
        <a href="loginuser.jsp" class="button">Go to Home</a>
    </div>
</body>
</html>
