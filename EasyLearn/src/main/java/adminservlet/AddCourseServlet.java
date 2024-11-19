package adminservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addCourse")
@MultipartConfig(maxFileSize = 16177215) // 15MB limit for file upload
public class AddCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/easyleran";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form input data
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");
        
        // Get file part for the uploaded image
        Part filePart = request.getPart("courseImage");
        InputStream imageInputStream = null;
        if (filePart != null) {
            imageInputStream = filePart.getInputStream();
        }

        // Validate input
        if (courseName == null || courseName.isEmpty() || description == null || description.isEmpty() || filePart == null) {
            request.setAttribute("message", "All fields are required");
            request.setAttribute("messageType", "alert-danger");
            request.getRequestDispatcher("addCourse.jsp").forward(request, response);
            return;
        }

        // Database connection and insertion
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            
            String sql = "INSERT INTO courses (course_name, description, course_image) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, courseName);
                statement.setString(2, description);
                
                if (imageInputStream != null) {
                    statement.setBlob(3, imageInputStream);
                }

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    // Redirect to dashboard or show success message
                    request.setAttribute("message", "Course added successfully");
                    request.setAttribute("messageType", "alert-success");
                    request.getRequestDispatcher("addCourse.jsp").forward(request, response);
                } else {
                    // Stay on the same page if insertion failed
                    request.setAttribute("message", "Failed to add course");
                    request.setAttribute("messageType", "alert-danger");
                    request.getRequestDispatcher("addCourse.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Database error: " + e.getMessage());
            request.setAttribute("messageType", "alert-danger");
            request.getRequestDispatcher("addCourse.jsp").forward(request, response);
        }
    }
}
