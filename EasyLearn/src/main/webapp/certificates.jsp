<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Certificates Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 5px;
            font-size: 16px;
            color: white;
            background-color: #4CAF50;
            border: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #45a049;
        }
        .select-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 20px 0;
        }
        select {
            width: 200px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            color: #333;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h2>Certificates Information</h2>
    
    <form id="courseForm" method="get" action="">
        <div class="select-container">
            <label for="courseSelect">Select Course:</label>
            <select id="courseSelect" name="courseId">
                <option value="">--All Courses--</option>
                <%
                    Connection conn = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    try {
                        // Load JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // Establish connection
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");
                        
                        // Create statement
                        stmt = conn.createStatement();
                        
                        // Execute SQL query to get courses
                        String courseSql = "SELECT course_id, course_name FROM courses";
                        rs = stmt.executeQuery(courseSql);
                        
                        while (rs.next()) {
                            String courseId = rs.getString("course_id");
                            String courseName = rs.getString("course_name");
                %>
                            <option value="<%= courseId %>"><%= courseName %></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        // Close resources
                        try {
                            if (rs != null) rs.close();
                            if (stmt != null) stmt.close();
                            if (conn != null) conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </select>
        </div>
        <div style="text-align: center;">
            <button type="submit" class="button">Submit</button>
            <a href="dashboard.jsp" class="button">Back</a> <!-- Replace with actual previous page URL -->
        </div>
    </form>

    <%
        String selectedCourseId = request.getParameter("courseId");
        if (request.getParameter("courseId") != null && !selectedCourseId.isEmpty()) {
    %>
    <table>
        <tr>
            <th>Sr. No.</th>
            <th>User Name</th>
            <th>Course Name</th>
            <th>Score</th>
            <th>Issue Date</th>
        </tr>
        <%
            conn = null;
            stmt = null;
            rs = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");
                stmt = conn.createStatement();
                
                String sql = "SELECT u.uname, c.course_name, cert.score, cert.issue_date " +
                             "FROM certificates cert " +
                             "JOIN users u ON cert.user_id = u.uid " +
                             "JOIN courses c ON cert.course_id = c.course_id " +
                             "WHERE cert.course_id = '" + selectedCourseId + "'";
                
                rs = stmt.executeQuery(sql);
                
                int srNo = 1;
                while (rs.next()) {
                    String userName = rs.getString("uname");
                    String courseName = rs.getString("course_name");
                    int score = rs.getInt("score");
                    Date issueDate = rs.getDate("issue_date");
        %>
                    <tr>
                        <td><%= srNo++ %></td>
                        <td><%= userName %></td>
                        <td><%= courseName %></td>
                        <td><%= score %></td>
                        <td><%= issueDate != null ? issueDate.toString() : "N/A" %></td>
                    </tr>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </table>
    <center>
    <a href="<%= request.getContextPath() %>/mails?courseId=<%= selectedCourseId %>" class="button">Generate PDF</a></center>
<%
        } else {
    %>
        <p style="text-align: center;">Please select a course and click Submit to view the certificates.</p>
    <%
        }
    %>
</body>
</html>
