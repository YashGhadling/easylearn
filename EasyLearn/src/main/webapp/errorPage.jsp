<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            text-align: center;
            padding-top: 50px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            display: inline-block;
            border-radius: 10px;
        }
        h1 {
            color: #d9534f;
            font-size: 24px;
        }
        p {
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p>Sorry, an error occurred while processing your request.</p>
        <p><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "An unknown error occurred." %></p>
        <a href="index.jsp">Go back to course selection</a>
    </div>
</body>
</html>
