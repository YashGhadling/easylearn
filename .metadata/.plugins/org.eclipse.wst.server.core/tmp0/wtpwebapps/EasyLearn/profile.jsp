<%@ page import="java.sql.*" %>
<%@ page import="jakarta.servlet.*, jakarta.servlet.http.*" %>
<%
    // Retrieve email and username from session
    String email = (String) session.getAttribute("email");
    String uname = (String) session.getAttribute("userName");

    // Redirect to login page if the user is not logged in
    if (email == null || uname == null) {
        response.sendRedirect("loginuser.jsp");
        return;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

        // Prepare SQL query
        String sql = "SELECT mobile, udob FROM users WHERE uemail = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);

        // Execute query
        rs = pstmt.executeQuery();

        if (rs.next()) {
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
            background-color: white;
            background-size: cover;
            color: #ffffff;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            max-width: 600px;
            width: 100%;
            background-color: rgba(40, 44, 52, 0.9); /* Semi-transparent background */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
        h1 {
            text-align: center;
            color: #61dafb; /* Light blue color for heading */
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
            color: #ffffff; /* Label color */
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-top: 5px;
        }
        input[type="submit"] {
            background-color: #4285F4;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 20px 0;
            cursor: pointer;
            border-radius: 4px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #357ae8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Profile</h1>
        <form action="uprofile.jsp" method="post">
            <table>
                <tr>
                    <td><label for="uname">Name:</label></td>
                    <td><input type="text" id="uname" name="uname" value="<%= uname %>" readonly /></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" id="email" name="email" value="<%= email %>" readonly /></td>
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
        // Close resources
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
%>
