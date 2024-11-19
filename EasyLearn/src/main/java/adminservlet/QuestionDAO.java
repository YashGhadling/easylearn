package adminservlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private Connection connection;

    public QuestionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Question> getQuestionsByCourseId(int courseId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE course_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int questionId = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_text");
                String optionA = resultSet.getString("option_a");
                String optionB = resultSet.getString("option_b");
                String optionC = resultSet.getString("option_c");
                String optionD = resultSet.getString("option_d");
                String correctAnswer = resultSet.getString("correct_answer");

                Question question = new Question(questionId, courseId, questionText, optionA, optionB, optionC, optionD, correctAnswer);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
