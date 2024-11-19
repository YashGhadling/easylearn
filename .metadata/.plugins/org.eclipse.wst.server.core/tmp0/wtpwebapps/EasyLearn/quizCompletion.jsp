<%@ page import="java.sql.*, jakarta.servlet.http.*, java.util.*" %>
<%
    HttpSession sessio= request.getSession(false);
    if (session == null) {
        response.sendRedirect("loginuser.jsp");
        return;
    }

    // Retrieve alert message from the session
    String alertMessage = (String) session.getAttribute("alertMessage");

    // Remove the alert message from session to prevent it from showing again
    session.removeAttribute("alertMessage");

    // Display alert message using JavaScript if it exists
    if (alertMessage != null) {
%>
        <script>
            alert("<%= alertMessage %>");
        </script>
<%
    }

    String userName = (String) session.getAttribute("userName");
    String courseName = (String) session.getAttribute("courseName");
    Integer score = (Integer) session.getAttribute("score");

    if (userName == null || courseName == null || score == null) {
        out.println("<script>alert('Session data missing. Please try the quiz again.');</script>");
        return;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Integer userId = null;
    Integer courseId = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Use appropriate driver
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", ""); // Replace with your DB credentials

        // Get User ID
        String getUserIdSQL = "SELECT uid FROM users WHERE uname = ?";
        pstmt = conn.prepareStatement(getUserIdSQL);
        pstmt.setString(1, userName);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("uid");
        }
        rs.close();
        pstmt.close();

        // Get Course ID
        String getCourseIdSQL = "SELECT course_id FROM courses WHERE course_name = ?";
        pstmt = conn.prepareStatement(getCourseIdSQL);
        pstmt.setString(1, courseName);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            courseId = rs.getInt("course_id");
        }
        rs.close();
        pstmt.close();

        if (userId == null || courseId == null) {
            out.println("<script>alert('User or Course information not found in the database.');</script>");
            return;
        }

        // Display the quiz score and user/course details
        out.println("<h1 style='color: green;'>Quiz Completed</h1>");
        out.println("<p>Congratulations, <strong>" + userName + "</strong>! You completed the course: <strong>" + courseName + "</strong> and scored: <strong>" + score + "</strong> points.</p>");
        out.println("<p>Thank you for choosing <strong>EASYLEARN.</strong></p>");

        // Check if the user already has a score for the course
        String checkScoreSQL = "SELECT score FROM quiz_results WHERE user_id = ? AND course_id = ?";
        pstmt = conn.prepareStatement(checkScoreSQL);
        pstmt.setInt(1, userId);
        pstmt.setInt(2, courseId);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            // User exists, update their score
            String updateScoreSQL = "UPDATE quiz_results SET score = ? WHERE user_id = ? AND course_id = ?";
            pstmt = conn.prepareStatement(updateScoreSQL);
            pstmt.setInt(1, score); // New score
            pstmt.setInt(2, userId);
            pstmt.setInt(3, courseId);
            
            int updatedRows = pstmt.executeUpdate();
            if (updatedRows > 0) {
              //  out.println("<p style='color: green;'>Your score has been updated successfully!</p>");
            } else {
              //  out.println("<p style='color: red;'>Error updating your score. Please try again.</p>");
            }
        } else {
            // User does not exist, insert a new score
            String insertScoreSQL = "INSERT INTO quiz_results (user_id, course_id, score, quiz_date) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertScoreSQL);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseId);
            pstmt.setInt(3, score); // New score
            pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis())); // Current date

            int insertedRows = pstmt.executeUpdate();
            if (insertedRows > 0) {
              //  out.println("<p style='color: green;'>Your score has been added successfully!</p>");
            } else {
              //  out.println("<p style='color: red;'>Error adding your score. Please try again.</p>");
            }
        }

    } catch (Exception e) {
        out.println("<script>alert('Error: " + e.getMessage() + "');</script>");
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    session.removeAttribute("score");
%>

<a href="index.jsp" style="display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px;">Back to Dashboard</a>
