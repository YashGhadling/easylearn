<%@ page import="java.sql.*, javax.sql.*" %>
<%@ page session="true" %>
<%
    // Get the course_id from the request
    String courseId = request.getParameter("course_id");
    String url = "jdbc:mysql://localhost:3306/easyleran";
    String dbUser = "root";
    String dbPassword = "";

    if (courseId != null && !courseId.isEmpty()) {
        try {
            int id = Integer.parseInt(courseId); // Ensure the course_id is a valid integer

            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                // Load MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish the connection
                conn = DriverManager.getConnection(url, dbUser, dbPassword);

                // SQL query to delete the course by course_id
                String sql = "DELETE FROM courses WHERE course_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);

                int rowsAffected = pstmt.executeUpdate(); // Execute the delete operation

                if (rowsAffected > 0) {
                    // Set success message if the course is deleted
                    session.setAttribute("message", "Course deleted successfully.");
                } else {
                    // Set failure message if no rows were affected
                    session.setAttribute("message", "Failed to delete course. Please try again.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("message", "Error deleting the course.");
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (NumberFormatException e) {
            session.setAttribute("message", "Invalid Course ID.");
        }
    } else {
        session.setAttribute("message", "No Course ID provided.");
    }

    // Redirect back to manage courses page after the operation
    response.sendRedirect("managecourse.jsp");
%>
