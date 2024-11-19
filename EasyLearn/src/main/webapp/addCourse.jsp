<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add a New Course</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-bottom: 20px;
            font-size: 20px;
            color: #333333;
            text-align: center;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
            font-size: 14px;
        }
        .form-control {
            margin-bottom: 10px;
            font-size: 14px;
        }
        .btn-primary {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border-radius: 5px;
        }
        .alert {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add a New Course</h1>
    
    <!-- Error or Success Message -->
    <c:if test="${not empty message}">
        <div class="alert ${messageType}">${message}</div>
    </c:if>
    
    <!-- Add Course Form -->
    <form action="addCourse" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="courseName" class="form-label">Course Name:</label>
            <input type="text" name="courseName" id="courseName" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description:</label>
            <textarea name="description" id="description" class="form-control" rows="3" required></textarea>
        </div>
        <div class="mb-3">
            <label for="courseImage" class="form-label">Course Image:</label>
            <input type="file" name="courseImage" id="courseImage" class="form-control" accept="image/*" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Course</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
