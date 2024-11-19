<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Courses</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Navbar Styling */
        .navbar {
            background-color: #000;
            padding: 10px 20px;
        }

        .navbar-brand {
            font-size: 1.5em;
            color: #fff;
        }

        .navbar-nav .nav-link {
            color: #f8f9fa;
            padding: 10px 15px;
            transition: background-color 0.3s, color 0.3s;
        }

        .navbar-nav .nav-link:hover {
            background-color: #343a40;
            color: #ffffff;
            border-radius: 5px;
        }

        .nav-item span {
            color: #f8f9fa;
            font-size: 1em;
        }

        .navbar-toggler {
            border-color: #f8f9fa;
        }

        /* Page Layout Styling */
        .container {
            margin-top: 20px;
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

        /* Table Styling */
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

        /* Action Buttons */
        .action-buttons input {
            margin: 0 5px;
            background-color: #dc3545;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .action-buttons input:hover {
            background-color: #c82333;
        }

        .add-course-btn {
            margin-top: 20px;
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
        }

        .add-course-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="#">Admin Panel</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
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

    <div class="container">
        <!-- Display Session Message -->
        <% if (session.getAttribute("message") != null) { %>
            <div class="alert alert-info">
                <%= session.getAttribute("message") %>
            </div>
            <% session.removeAttribute("message"); %>
        <% } %>

        <h2>Manage Courses</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String url = "jdbc:mysql://localhost:3306/easyleran";
                    String dbUser = "root";
                    String dbPassword = "";

                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection(url, dbUser, dbPassword);
                        String sql = "SELECT * FROM courses";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            int courseId = rs.getInt("course_id");
                            String courseName = rs.getString("course_name");
                            String description = rs.getString("description");
                            Timestamp createdAt = rs.getTimestamp("created_at");
                            Timestamp updatedAt = rs.getTimestamp("updated_at");
                %>
                            <tr>
                                <td><%= courseId %></td>
                                <td><%= courseName %></td>
                                <td><%= description %></td>
                                <td>
                                    <img src="imageServlet?courseId=<%= courseId %>" alt="Course Image" style="width: 100px; height: auto;">
                                </td>
                                <td><%= createdAt %></td>
                                <td><%= updatedAt %></td>
                                <td class="action-buttons">
                                    <!-- Delete Form -->
                                    <form action="deletecourse.jsp" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete the course <%= courseName %>?');">
                                        <input type="hidden" name="course_id" value="<%= courseId %>">
                                        <input type="submit" value="Delete" class="btn btn-danger btn-sm">
                                    </form>
                                </td>
                            </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                        <tr>
                            <td colspan="7" class="text-danger">Error retrieving data.</td>
                        </tr>
                <%
                    } finally {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    }
                %>
            </tbody>
        </table>
        <a href="addCourse.jsp" class="add-course-btn">Add New Course</a>
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
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
