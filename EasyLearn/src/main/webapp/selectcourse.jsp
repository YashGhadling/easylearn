<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.io.*, java.sql.*, java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
    // Check if session exists and the user is logged in
    HttpSession sessio = request.getSession(false); 
    if (session == null || session.getAttribute("userName") == null) {
        response.sendRedirect("loginuser.jsp");
        return;
    }

    // Get the user's name from the session
    String userName = (String) session.getAttribute("userName");

    // Connect to the database
    String url = "jdbc:mysql://localhost:3306/easyleran";  // Update with your DB details
    String dbUser = "root";
    String dbPassword = "";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    List<String> courseList = new ArrayList<>(); // List to store available courses

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
        conn = DriverManager.getConnection(url, dbUser, dbPassword);

        // Fetch the available courses from the 'courses' table
        String query = "SELECT course_name FROM courses"; // Update with the correct column and table name
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            courseList.add(rs.getString("course_name"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
        if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
        if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select Course</title>
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
        select {
            font-size: 18px;
            padding: 10px;
            margin: 20px 0;
            width: 100%;
            max-width: 300px;
        }
        .button {
            font-size: 18px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Select a Course</h1>
        <form action="SelectCourseServlet" method="post">
            <select name="courseName" required>
                <option value="">Select a course</option>
                <% for (String course : courseList) { %>
                    <option value="<%= course %>"><%= course %></option>
                <% } %>
            </select>
            <br>
            <button type="submit" class="button">Submit</button>
        </form>
    </div>
</body>
</html>
