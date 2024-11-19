package adminservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deletecourse")
public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/easyleran";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("course_id");

        if (courseId == null || courseId.isEmpty()) {
            request.setAttribute("message", "Invalid course ID");
            response.sendRedirect("manageCourses.jsp");
            return;
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM courses WHERE course_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(courseId));
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    request.getSession().setAttribute("message", "Course deleted successfully");
                } else {
                    request.getSession().setAttribute("message", "Failed to delete course");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Database error: " + e.getMessage());
        }

        response.sendRedirect("manageCourses.jsp");
    }
}
