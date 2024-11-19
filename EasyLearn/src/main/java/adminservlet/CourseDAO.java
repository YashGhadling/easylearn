package adminservlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection connection;

    
    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCourse(String name, String description) throws SQLException {
        String query = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.executeUpdate();
        }
    }

   
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("name"),
                    rs.getString("description")
                );
                courses.add(course);
            }
        }
        return courses;
    }
}
