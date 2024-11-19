<%@ page import="java.sql.*, javax.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Question</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Update Question</h2>
        <%
            String questionId = request.getParameter("question_id");

            if (questionId != null) {
            	 String url = "jdbc:mysql://localhost:3306/easyleran";
                 String user = "root";
                 String password = "";

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    
                    Class.forName("com.mysql.cj.jdbc.Driver");

                   
                    conn = DriverManager.getConnection(url, user, password);

                
                    String sql = "SELECT * FROM questions WHERE question_id = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(questionId));
                    rs = pstmt.executeQuery();

                    if (rs.next()) {
                        String questionText = rs.getString("question_text");
                        String optionA = rs.getString("option_a");
                        String optionB = rs.getString("option_b");
                        String optionC = rs.getString("option_c");
                        String optionD = rs.getString("option_d");
                        String correctAnswer = rs.getString("correct_answer");
        %>
        <form action="saveQuestion.jsp" method="post">
            <input type="hidden" name="question_id" value="<%= questionId %>">
            <div class="form-group">
                <label>Question Text:</label>
                <textarea name="question_text" class="form-control" rows="4" required><%= questionText %></textarea>
            </div>
            <div class="form-group">
                <label>Option A:</label>
                <input type="text" name="option_a" class="form-control" value="<%= optionA %>">
            </div>
            <div class="form-group">
                <label>Option B:</label>
                <input type="text" name="option_b" class="form-control" value="<%= optionB %>">
            </div>
            <div class="form-group">
                <label>Option C:</label>
                <input type="text" name="option_c" class="form-control" value="<%= optionC %>">
            </div>
            <div class="form-group">
                <label>Option D:</label>
                <input type="text" name="option_d" class="form-control" value="<%= optionD %>">
            </div>
            <div class="form-group">
                <label>Correct Answer:</label>
                <input type="text" name="correct_answer" class="form-control" value="<%= correctAnswer %>">
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
        <%
                    } else {
        %>
        <p>Question not found.</p>
        <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
        %>
        <p>Error retrieving question details.</p>
        <%
                } finally {
                  
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        %>
        <a href="manageQuestions.jsp" class="btn btn-secondary mt-3">Back to Questions</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
