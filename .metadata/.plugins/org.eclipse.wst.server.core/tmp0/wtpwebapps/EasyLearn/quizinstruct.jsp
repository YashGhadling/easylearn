<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.sql.*, java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
    // Check if the user is logged in
    HttpSession sessio = request.getSession(false); 
    if (session == null || session.getAttribute("userName") == null) {
        response.sendRedirect("loginuser.jsp");
        return;
    }

    String userName = (String) session.getAttribute("userName");  
    String courseName = request.getParameter("courseName");

    // Store courseName in the session
    session.setAttribute("courseName", courseName);

    boolean isCourseCompleted = false;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

        // Retrieve user ID using userName
        int userId = 0; // Add logic to retrieve user ID based on userName
        String userIdQuery = "SELECT uid FROM users WHERE uname = ?";
        ps = con.prepareStatement(userIdQuery);
        ps.setString(1, userName);
        rs = ps.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("uid");
        }

        // Check if the course is completed
        String checkCompletionQuery = "SELECT * FROM certificates WHERE user_id = ? AND course_id = (SELECT course_id FROM courses WHERE course_name = ?)";
        ps = con.prepareStatement(checkCompletionQuery);
        ps.setInt(1, userId);
        ps.setString(2, courseName);
        rs = ps.executeQuery();

        if (rs.next()) {
            isCourseCompleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
        if (ps != null) try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
        if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(); }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Main Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding-top: 50px;
            text-align: center;
        }
        h1 {
            font-size: 24px;
            font-weight: bold;
        }
        p {
            font-size: 18px;
            color: #333;
        }
        textarea {
            width: 100%;
            height: 150px;
            font-size: 16px;
            margin-bottom: 20px;
            resize: none;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 10px;
        }
        .button {
            font-size: 18px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            margin: 10px;
            text-decoration: none;
            display: inline-block;
        }
        .button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to EasyLearn, <%= userName %>!</h1>
        <p>You have selected the course: <b><%= courseName %></b></p>

        <% if (isCourseCompleted) { %>
            <!-- JavaScript alert for completed course -->
            <script>
                alert("You have already completed this course.");
                window.location.href = "index.jsp"; // Redirect to profile or any other page
            </script>
        <% } else { %>
            <textarea readonly>
Instructions:

1. Click 'Start' to begin the quiz.
2. Answer the questions.
3. You have 2 lifelines.
4. You have 150 Sec to answer questions.
5. See your score at the end.
6. To get certificate you need to score marks more than 70.
            </textarea>

            <form action="QuizServlet" method="post">
                <button type="submit" class="button">Start Quiz</button>
            </form>
        <% } %>
    </div>
</body>
</html>
