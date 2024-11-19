<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Feedback</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .nav-link:hover {
            background-color: #343a40;
        }

        .navbar {
            background-color: #000000;
        }

        .nav-link {
            padding: 10px 15px;
            border-radius: 4px;
        }

        .nav-link.active {
            background-color: #495057;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f8f9fa;
        }

        h2 {
            margin-top: 20px;
            font-size: 24px;
            color: #343a40;
        }

        body {
            background-color: #f8f9fa;
            padding: 20px;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">Admin Panel</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <span class="nav-link">Hello, admin</span>
            </li>
            <li class="nav-item">
                <span class="nav-link" id="currentDateTime"></span>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="dashboard.jsp">Dashboard</a>
            </li>
        </ul>
    </div>
</nav>

<h2>User Feedback List</h2>

<table>
    <thead>
        <tr>
            <th>Sr. No.</th>
            <th>Name</th>
            <th>Email</th>
            <th>Feedback Text</th>
            <th>Feedback Date</th>
        </tr>
    </thead>
    <tbody>
        <%
            String url = "jdbc:mysql://localhost:3306/easyleran"; // Ensure the database name is correct
            String user = "root"; // Update if your DB username is different
            String password = ""; // Update password if applicable

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            int srNo = 1; // Counter for Sr. No.

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the JDBC driver is included in your project

                conn = DriverManager.getConnection(url, user, password);

                stmt = conn.createStatement();
                String sql = "SELECT u.uname, u.uemail, f.feedback_text, f.feedback_date FROM users u INNER JOIN feedback f ON u.uid = f.user_id";

                rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    String uname = rs.getString("uname");
                    String uemail = rs.getString("uemail");
                    String feedbackText = rs.getString("feedback_text");
                    Date feedbackDate = rs.getDate("feedback_date");
        %>
                    <tr>
                        <td><%= srNo++ %></td> <!-- Incrementing Sr. No. -->
                        <td><%= uname %></td>
                        <td><%= uemail %></td>
                        <td><%= feedbackText %></td>
                        <td><%= feedbackDate %></td>
                    </tr>
        <%
                }

            } catch (Exception e) {
                e.printStackTrace();
        %>
                <tr>
                    <td colspan="5">Error retrieving data: <%= e.getMessage() %></td>
                </tr>
        <%
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        %>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function updateDateTime() {
        const now = new Date();
        const dateTimeString = now.toLocaleString();
        document.getElementById('currentDateTime').textContent = dateTimeString;
    }

    updateDateTime();
    setInterval(updateDateTime, 1000);
</script>
</body>
</html>
