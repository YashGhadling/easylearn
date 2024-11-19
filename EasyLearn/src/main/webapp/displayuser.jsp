<%@ page import="java.sql.*,javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Data</title>
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

        .container {
            margin-top: 20px;
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
                <span class="nav-link" href="">Hello, admin</span>
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

<div class="container">
    <h2>User List</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Date of Birth</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <%
                String url = "jdbc:mysql://localhost:3306/easyleran";
                String user = "root";
                String password = "";

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    conn = DriverManager.getConnection(url, user, password);

                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM users";
                    rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        int id = rs.getInt("uid");
                        String uname = rs.getString("uname");
                        String uemail = rs.getString("uemail");
                        String mobile = rs.getString("mobile");
                        Date udob = rs.getDate("udob");
                        String upass = rs.getString("upass");
            %>
                        <tr>
                            <td><%= id %></td>
                            <td><%= uname %></td>
                            <td><%= uemail %></td>
                            <td><%= mobile %></td>
                            <td><%= udob %></td>
                            <td><%= upass %></td>
                        </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
            %>
                    <tr>
                        <td colspan="6" class="text-danger">Error retrieving data.</td>
                    </tr>
            <%
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </tbody>
    </table>
</div>

<script>
function updateDateTime() {
    const now = new Date();
    const dateTimeString = now.toLocaleString(); 
    document.getElementById('currentDateTime').textContent = dateTimeString;
}

updateDateTime();
setInterval(updateDateTime, 1000);
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
