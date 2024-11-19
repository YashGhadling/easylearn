<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Qualification</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f4f4f4;
        }
        .input-container {
            margin-top: 20px;
            text-align: center;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
        }
        .table-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex-grow: 1;
            width: 90%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        h3 {
            text-align: center;
        }
        select {
            padding: 5px;
            margin-right: 10px;
        }
        .button-container {
            margin-top: 10px;
        }
        button {
            padding: 8px 15px;
            margin: 5px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<!-- Top middle section for the input form -->
<div class="input-container">
    <h3>Select Qualification Type</h3>
    <form method="get">
        <label for="qualification">Qualification:</label>
        <select name="qualification" id="qualification">
            <option value="">--Select--</option>
            <option value="qualified" <%= "qualified".equals(request.getParameter("qualification")) ? "selected" : "" %>>Qualified</option>
            <option value="unqualified" <%= "unqualified".equals(request.getParameter("qualification")) ? "selected" : "" %>>Unqualified</option>
        </select>
        <input type="submit" value="Search">

        <!-- Back button -->
        <div class="button-container">
            <button type="button" onclick="window.location.href='dashboard.jsp'">Back</button>
        </div>
    </form>
</div>

<!-- Middle section for the table -->
<div class="table-container">
    <%
        String jdbcURL = "jdbc:mysql://localhost:3306/easyleran";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String qualification = request.getParameter("qualification");

        if (qualification != null && !qualification.isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

                String sql = "SELECT u.uid, u.uname, u.uemail, qr.score, c.course_name " +
                             "FROM quiz_results qr " +
                             "JOIN users u ON qr.user_id = u.uid " +
                             "JOIN courses c ON qr.course_id = c.course_id ";

                if ("qualified".equals(qualification)) {
                    sql += "WHERE qr.score >= 70 "; 
                } else if ("unqualified".equals(qualification)) {
                    sql += "WHERE qr.score < 70 "; 
                }

                sql += "ORDER BY qr.score DESC";

                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                out.println("<h3>" + (qualification.equals("qualified") ? "Qualified Users" : "Unqualified Users") + "</h3>");

                out.println("<table><tr><th>ID</th><th>Name</th><th>Email</th><th>Score</th><th>Course</th></tr>");

                while (resultSet.next()) {
                    out.println("<tr>");
                    out.println("<td>" + resultSet.getInt("uid") + "</td>");
                    out.println("<td>" + resultSet.getString("uname") + "</td>");
                    out.println("<td>" + resultSet.getString("uemail") + "</td>");
                    out.println("<td>" + resultSet.getInt("score") + "</td>");
                    out.println("<td>" + resultSet.getString("course_name") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    %>
</div>

<!-- Add the "Download PDF" button -->
<div class="button-container">
   <form action="<%= request.getContextPath() %>/qualifypdf" method="get">

        <input type="hidden" name="qualification" value="<%= qualification %>">
        <button type="submit">Download PDF</button>
    </form>
</div>

</body>
</html>
