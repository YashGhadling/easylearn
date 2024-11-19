<%@ page import="java.sql.*, javax.sql.*" %>
<%@ page session="true" %>
<%
    String url = "jdbc:mysql://localhost:3306/easyleran";
    String user = "root";
    String password = "";
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    String message = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);

        String questionId = request.getParameter("question_id");
        if (questionId != null && !questionId.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(questionId);
                String sql = "DELETE FROM questions WHERE question_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    message = "Question deleted successfully.";
                } else {
                    message = "Failed to delete question.";
                }
            } catch (NumberFormatException e) {
                message = "Invalid Question ID.";
            }
        } else {
            message = "No Question ID provided.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "Error deleting question.";
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    session.setAttribute("message", message);
    response.sendRedirect("manageQuestions.jsp");
%>
