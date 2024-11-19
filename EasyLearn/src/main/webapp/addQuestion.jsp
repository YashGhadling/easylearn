<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add a New Question</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333333;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
            font-size: 14px;
        }

        .form-control, .form-select {
            margin-bottom: 15px;
            font-size: 14px;
        }

        .btn-primary {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Add a New Question</h1>
    <form action="addQuestion" method="post" class="question-form">
        <div class="mb-3">
            <label for="courseId" class="form-label">Select Course:</label>
            <select name="courseId" id="courseId" class="form-select" required>
                <option value="" disabled selected>Select a course</option>
                <%
                    Connection connection = null;
                    PreparedStatement statement = null;
                    ResultSet resultSet = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");
                        String sql = "SELECT course_id, course_name FROM courses";
                        statement = connection.prepareStatement(sql);
                        resultSet = statement.executeQuery();

                        while (resultSet.next()) {
                            String courseId = resultSet.getString("course_id");
                            String courseName = resultSet.getString("course_name");
                %>
                <option value="<%= courseId %>"><%= courseName %></option>
                <%
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (resultSet != null) resultSet.close();
                            if (statement != null) statement.close();
                            if (connection != null) connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </select>
        </div>

        <div class="mb-3">
            <label for="questionText" class="form-label">Question:</label>
            <textarea name="questionText" id="questionText" class="form-control" rows="4" required></textarea>
        </div>

        <div class="mb-3">
            <label for="optionA" class="form-label">Option A:</label>
            <input type="text" name="optionA" id="optionA" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="optionB" class="form-label">Option B:</label>
            <input type="text" name="optionB" id="optionB" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="optionC" class="form-label">Option C:</label>
            <input type="text" name="optionC" id="optionC" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="optionD" class="form-label">Option D:</label>
            <input type="text" name="optionD" id="optionD" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="correctAnswer" class="form-label">Correct Answer:</label>
            <select name="correctAnswer" id="correctAnswer" class="form-select" required>
                <option value="" disabled selected>Select correct answer</option>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Add Question</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
