<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url("page.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            color: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .header {
            position: absolute;
            top: 20px;
            left: 20px;
            display: flex;
            align-items: center;
        }
        .header img {
            height: 50px;
            margin-right: 10px;
        }
        .header h2 {
            font-size: 24px;
            margin: 0;
        }
        .container {
            text-align: center;
            padding: 50px;
            background-color: #282c34;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            width: 300px;
            margin-left: 800px; /* Adjust this value to move left */
        }
        .container h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .container form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .input-group {
            display: flex;
            align-items: center;
            width: 100%;
            margin: 10px 0;
        }
        .input-group i {
            margin-right: 10px;
            color: #ffffff; /* Change the icon color if needed */
        }
        .container input[type="email"], 
        .container input[type="password"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
        }
        .container button {
            background-color: #4285F4;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 20px;
        }
        .container button:hover {
            background-color: #357ae8;
        }
        .forgot-password, .create-account {
            margin-top: 10px;
        }
        .forgot-password a,
        .create-account a {
            color: #61dafb;
            text-decoration: none;
            font-size: 14px;
        }
        .forgot-password a:hover,
        .create-account a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="header">
        <img src="img.jpeg" alt="Logo">
        <h2>EasyLearn</h2>
    </div>
    <div class="container">
        <h1>Login</h1>
        <form action="loginuser" method="post">
            <div class="input-group">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" placeholder="Email" required>
            </div>
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <div class="forgot-password">
            <a href="forgotpassword.jsp">Forgot Password?</a>
        </div>
        <div class="create-account">
            <p>New to EasyLearn? <a href="reguser.jsp">Create an account</a></p>
        </div>
    </div>
</body>
</html>
