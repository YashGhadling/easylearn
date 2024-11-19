<%@ page import="java.sql.*, java.util.*" %>
<%@ page session="true" %>
<%@ page import="java.sql.Date" %>

<%
    String dbURL = "jdbc:mysql://localhost:3306/easyleran";
    String dbUser = "root";
    String dbPass = "";
    Connection conn = null;
    PreparedStatement psLeaderboard = null;
    PreparedStatement psCourses = null;
    ResultSet rsLeaderboard = null;
    ResultSet rsCourses = null;
    String selectedCourseId = request.getParameter("courseId");
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        String sqlLeaderboard = "SELECT u.uname AS username, c.course_name, qr.score, qr.quiz_date " +
                                "FROM quiz_results qr " +
                                "JOIN users u ON qr.user_id = u.uid " +
                                "JOIN courses c ON qr.course_id = c.course_id " +
                                "WHERE qr.course_id = ? " +
                                "ORDER BY qr.score DESC";
        psLeaderboard = conn.prepareStatement(sqlLeaderboard);
        if (selectedCourseId != null && !selectedCourseId.isEmpty()) {
            psLeaderboard.setString(1, selectedCourseId);
        } else {
            psLeaderboard.setString(1, "0");
        }
        
        rsLeaderboard = psLeaderboard.executeQuery();
        String sqlCourses = "SELECT course_id, course_name FROM courses";
        psCourses = conn.prepareStatement(sqlCourses);
        rsCourses = psCourses.executeQuery();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leaderboard</title>
    <style>
        table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .form-container {
            width: 60%;
            margin: 20px auto;
            text-align: center;
        }
        .form-select {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
            font-size: 16px;
            width: 200px;
            background-color: #f9f9f9;
        }
        .button-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
        .button-back:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <form action="leaderboard.jsp" method="get">
            <label for="courseId" class="form-label">Select Course:</label>
            <select name="courseId" id="courseId" class="form-select" onchange="this.form.submit()">
                <option value="" disabled selected>Select a course</option>
                <%
                    while (rsCourses.next()) {
                        String courseId = rsCourses.getString("course_id");
                        String courseName = rsCourses.getString("course_name");
                %>
                <option value="<%= courseId %>" <%= (courseId.equals(selectedCourseId) ? "selected" : "") %>><%= courseName %></option>
                <%
                    }
                %>
            </select>
        </form>

        <!-- Back Button Styled as a Button -->
        <a href="index.jsp" class="button-back">Back to Home</a>
    </div>

    <h1 style="text-align: center;">Leaderboard</h1>

    <table>
        <thead>
            <tr>
                <th> Username </th>
                <th> Course </th>
                <th> Score </th>
                <th> Quiz Date </th>
            </tr>
        </thead>
        <tbody>
            <% 
                while (rsLeaderboard.next()) {
                    String username = rsLeaderboard.getString("username");
                    String courseName = rsLeaderboard.getString("course_name");
                    int score = rsLeaderboard.getInt("score");
                    java.sql.Date quizDate = rsLeaderboard.getDate("quiz_date");
            %>
            <tr>
                <td><%= username %></td>
                <td><%= courseName %></td>
                <td><%= score %></td>
                <td><%= quizDate %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

<%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rsLeaderboard != null) rsLeaderboard.close();
            if (rsCourses != null) rsCourses.close();
            if (psLeaderboard != null) psLeaderboard.close();
            if (psCourses != null) psCourses.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
</body>
</html>
