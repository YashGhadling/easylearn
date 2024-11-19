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
    String selectedDate = request.getParameter("quizDate");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        
        // SQL query to filter by course and date
        String sqlLeaderboard = "SELECT u.uname AS username, c.course_name, qr.score, qr.quiz_date " +
                                "FROM quiz_results qr " +
                                "JOIN users u ON qr.user_id = u.uid " +
                                "JOIN courses c ON qr.course_id = c.course_id " +
                                "WHERE qr.course_id = ? ";
        
        // Add date filter only if selectedDate is not null or empty
        if (selectedDate != null && !selectedDate.isEmpty()) {
            sqlLeaderboard += "AND qr.quiz_date = ? "; // Use this format
        }
        
        sqlLeaderboard += "ORDER BY qr.score DESC";
        psLeaderboard = conn.prepareStatement(sqlLeaderboard);

        // Set parameters
        psLeaderboard.setString(1, selectedCourseId != null && !selectedCourseId.isEmpty() ? selectedCourseId : "0");
        
        // Set date parameter if it exists
        if (selectedDate != null && !selectedDate.isEmpty()) {
            psLeaderboard.setDate(2, Date.valueOf(selectedDate)); // Convert string to SQL Date
        }

        rsLeaderboard = psLeaderboard.executeQuery();
        
        // Fetch courses
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
        .form-select, .form-date {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
            font-size: 16px;
            width: 200px;
            background-color: #f9f9f9;
            margin: 10px;
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
        .submit-btn {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <form action="aleaderboard.jsp" method="get">
            <label for="courseId" class="form-label">Select Course:</label>
            <select name="courseId" id="courseId" class="form-select">
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
            
            <!-- Date input field -->
            <label for="quizDate" class="form-label">Select Date:</label>
            <input type="date" name="quizDate" id="quizDate" class="form-date" value="<%= selectedDate != null ? selectedDate : "" %>">
            
            <!-- Submit button to fetch results -->
            <button type="submit" class="submit-btn">Submit</button>
        </form>

        <!-- Button to download PDF -->
        <form action="pdfleaderboard" method="post">
            <input type="hidden" name="courseId" value="<%= selectedCourseId %>">
            <input type="hidden" name="quizDate" value="<%= selectedDate != null ? selectedDate : "" %>">
            <button type="submit" class="submit-btn">Download PDF</button>
        </form>

        <!-- Back Button Styled as a Button -->
        <a href="dashboard.jsp" class="button-back">Back to Home</a>
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
                if (!rsLeaderboard.isBeforeFirst()) { // Check if the result set is empty
                    out.println("<tr><td colspan='4'>No results found for the selected course and date.</td></tr>");
                } else {
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
