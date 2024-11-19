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

@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/easyleran";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String questionText = request.getParameter("questionText");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String correctAnswer = request.getParameter("correctAnswer");

        if (courseId == null || courseId.isEmpty() || questionText == null || questionText.isEmpty() ||
            optionA == null || optionA.isEmpty() || optionB == null || optionB.isEmpty() ||
            optionC == null || optionC.isEmpty() || optionD == null || optionD.isEmpty() ||
            correctAnswer == null || correctAnswer.isEmpty()) {
            response.sendRedirect("addQuestion.jsp?message=All fields are required");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO questions (course_id, question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
             
            statement.setString(1, courseId);
            statement.setString(2, questionText);
            statement.setString(3, optionA);
            statement.setString(4, optionB);
            statement.setString(5, optionC);
            statement.setString(6, optionD);
            statement.setString(7, correctAnswer);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
             
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("addQuestion.jsp?message=Failed to add question");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=" + e.getMessage());
        }
    }
}
