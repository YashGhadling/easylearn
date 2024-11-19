<%@ page import="java.sql.*" %>
<%@ page import="jakarta.servlet.*, jakarta.servlet.http.*" %>
<%
    String email = (String) session.getAttribute("email");
    String userName = (String) session.getAttribute("userName");

    if (email == null) {
    	  response.sendRedirect("loginuser.jsp");
        return;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

        String sql = "SELECT uname FROM users WHERE uemail = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String uname = rs.getString("uname");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .feedback-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            text-align: center;
        }
        .feedback-container h2 {
            margin-bottom: 1.5rem;
            color: #333;
        }
        .input-group {
            margin-bottom: 1rem;
            text-align: left;
        }
        .input-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
            color: #555;
        }
        .input-group input,
        .input-group textarea {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }
        button {
            width: 100%;
            padding: 0.75rem;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="feedback-container">
        <h2>We Value Your Feedback</h2>
        <form action="SubmitFeedbackServlet" method="POST">
            <div class="input-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="<%= uname %>" readonly>
            </div>
            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= email %>" readonly>
            </div>
            <div class="input-group">
                <label for="message">Message</label>
                <textarea id="message" name="feedback_text" rows="5" required></textarea>
            </div>
            <button type="submit">Submit Feedback</button>
        </form>
    </div>
</body>
</html>
<%
        } else {
            out.println("No user found.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("An error occurred.");
    } finally {
        // Close resources
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
%>
