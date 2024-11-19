<%@ page import="java.sql.*" %>
<%@ page import="jakarta.servlet.*, jakarta.servlet.http.*" %>
<%
String email = (String) session.getAttribute("email");
if (email == null) {
    out.println("User not logged in.");
    return;
}

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

    String sql = "SELECT uname, mobile, udob FROM users WHERE uemail = ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, email);

    rs = pstmt.executeQuery();

    if (rs.next()) {
        String uname = rs.getString("uname");
        String mobile = rs.getString("mobile");
        Date udob = rs.getDate("udob");
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"], .back-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px 0;
            cursor: pointer;
            border-radius: 4px;
        }
        input[type="submit"]:hover, .back-button:hover {
            background-color: #45a049;
        }
        .back-button {
            background-color: #f44336; /* Red background */
            display: inline-block; /* Ensure it acts like a button */
            text-align: center; /* Center the text */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Profile</h1>

        <% String status = request.getParameter("status"); %>
        <% if ("success".equals(status)) { %>
            <p style="color:green;">Profile updated successfully!</p>
        <% } %>

        <form action="UpdateProfileServlet" method="post">
            <table>
                <tr>
                    <td><label for="uname">Name:</label></td>
                    <td><input type="text" id="uname" name="uname" value="<%= uname %>" /></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" id="email" name="email" value="<%= email %>" /></td>
                </tr>
                <tr>
                    <td><label for="mobile">Mobile:</label></td>
                    <td><input type="text" id="mobile" name="mobile" value="<%= mobile %>" /></td>
                </tr>
                <tr>
                    <td><label for="dob">Date of Birth:</label></td>
                    <td><input type="date" id="dob" name="dob" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(udob) %>" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Update Profile" />
                    </td>
                    <td colspan="2">
                        <a href="index.jsp" class="back-button">BACK</a> <!-- Use the back-button class -->
                    </td>
                </tr>
            </table>
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
    try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
    try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
}
%>
