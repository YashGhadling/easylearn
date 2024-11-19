<%@ page import="java.sql.*, javax.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Save Question</title>
</head>
<body>
    <%
    String url = "jdbc:mysql://localhost:3306/easyleran";
    String user = "root";
    String password = "";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);

        String sql = "UPDATE questions SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_answer = ? WHERE question_id = ?";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, request.getParameter("question_text"));
        pstmt.setString(2, request.getParameter("option_a"));
        pstmt.setString(3, request.getParameter("option_b"));
        pstmt.setString(4, request.getParameter("option_c"));
        pstmt.setString(5, request.getParameter("option_d"));
        pstmt.setString(6, request.getParameter("correct_answer"));
        pstmt.setInt(7, Integer.parseInt(request.getParameter("question_id")));

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
    %>
            <script>
                alert('Question updated successfully.');
                window.location.href = 'manageQuestions.jsp';
            </script>
    <%
        } else {
    %>
            <script>
                alert('Failed to update question.');
                window.location.href = 'manageQuestions.jsp';
            </script>
    <%
        }
    } catch (Exception e) {
        e.printStackTrace();
    %>
        <script>
            alert('Error updating question.');
            window.location.href = 'manageQuestions.jsp';
        </script>
    <%
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    %>
</body>
</html>
