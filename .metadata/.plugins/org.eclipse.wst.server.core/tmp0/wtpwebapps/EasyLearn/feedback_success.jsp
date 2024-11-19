<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Submitted</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .message-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .message-container h1 {
            color: #28a745;
            margin-bottom: 1rem;
        }
        .message-container p {
            color: #333;
            font-size: 1.1rem;
        }
        .message-container a {
            display: inline-block;
            margin-top: 1.5rem;
            padding: 0.75rem 1.5rem;
            background-color: #28a745;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .message-container a:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="message-container">
        <h1>Feedback Submitted!</h1>
        <p>Thank you for your feedback. We appreciate your input and will use it to improve our service.</p>
        <a href="index.jsp">Return to Home</a>
    </div>
</body>
</html>
