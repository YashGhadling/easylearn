<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
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
        .login-container {
            max-width: 400px;
            padding: 50px;
            background-color: #282c34;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            margin-left: 800px; /* Aligns the login form to the right */
        }
        .login-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-header h3 {
            font-size: 24px;
            margin: 0;
        }
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 20px; /* Adds space between input fields */
        }
        .form-control {
            width: 100%;
            border-radius: 5px;
            border: none;
            padding: 10px; /* Uniform padding */
            margin-left: 10px; /* Space between icon and input */
        }
        .input-icon {
            color: #ffffff; /* Change icon color */
            font-size: 18px; /* Adjust icon size if needed */
        }
        .btn-primary {
            background-color: #4285F4;
            border: none;
            border-radius: 5px;
            padding: 10px;
            font-size: 16px;
            width: 100%;
            cursor: pointer;
            margin-top: 20px;
        }
        .btn-primary:hover {
            background-color: #357ae8;
        }
        .error-message {
            color: #ff4d4d;
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h3>Admin Login</h3>
        </div>
        <form action="adminlogin" method="post">
            <div class="form-group">
                <i class="fas fa-user input-icon"></i>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
            </div>
            <div class="form-group">
                <i class="fas fa-lock input-icon"></i>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</body>
</html>
