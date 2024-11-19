<%@ page import="java.sql.*, javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Questions</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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

        .action-buttons button {
            margin: 0 5px;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-warning {
            background-color: #ffc107;
        }

        .btn-danger {
            background-color: #dc3545;
        }

        .btn-warning:hover {
            background-color: #e0a800;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }

        .form-group label {
            font-weight: bold;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">Admin Panel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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

<div class="container mt-4">
    <% if (session.getAttribute("message") != null) { %>
        <div class="alert alert-info">
            <%= session.getAttribute("message") %>
        </div>
        <% session.removeAttribute("message"); %>
    <% } %>
    <h2 class="mb-4">Manage Questions</h2>
    <form action="manageQuestions.jsp" method="get" class="mb-3">
        <div class="form-group">
            <label for="course_id">Select Course:</label>
            <select name="course_id" id="course_id" class="form-control" onchange="this.form.submit()">
                <option value="">-- Select Course --</option>
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
                    String sql = "SELECT course_id, course_name FROM courses";
                    rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        int courseId = rs.getInt("course_id");
                        String courseName = rs.getString("course_name");
                        String selected = request.getParameter("course_id") != null && Integer.parseInt(request.getParameter("course_id")) == courseId ? "selected" : "";
                %>
                    <option value="<%= courseId %>" <%= selected %>><%= courseName %></option>
                <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                %>
                    <option value="">Error loading courses</option>
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
            </select>
        </div>
    </form>

    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Sr No</th>
                <th>Question Text</th>
                <th>Option A</th>
                <th>Option B</th>
                <th>Option C</th>
                <th>Option D</th>
                <th>Correct Answer</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
            String selectedCourseId = request.getParameter("course_id");
            int srNo = 1;

            if (selectedCourseId != null && !selectedCourseId.isEmpty()) {
                try {
                    conn = DriverManager.getConnection(url, user, password);
                    String sql = "SELECT * FROM questions WHERE course_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(selectedCourseId));
                    ResultSet rsQuestions = pstmt.executeQuery();

                    while (rsQuestions.next()) {
                        String questionText = rsQuestions.getString("question_text");
                        String optionA = rsQuestions.getString("option_a");
                        String optionB = rsQuestions.getString("option_b");
                        String optionC = rsQuestions.getString("option_c");
                        String optionD = rsQuestions.getString("option_d");
                        String correctAnswer = rsQuestions.getString("correct_answer");
            %>
            <tr>
                <td><%= srNo++ %></td>
                <td><%= questionText %></td>
                <td><%= optionA %></td>
                <td><%= optionB %></td>
                <td><%= optionC %></td>
                <td><%= optionD %></td>
                <td><%= correctAnswer %></td>
                <td class="action-buttons">
                    <form action="updateQuestion.jsp" method="get" style="display:inline;">
                        <input type="hidden" name="question_id" value="<%= rsQuestions.getInt("question_id") %>">
                        <button type="submit" class="btn btn-warning btn-sm">Update</button>
                    </form>
                    <form action="deleteQuestion.jsp" method="post" style="display:inline;">
                        <input type="hidden" name="question_id" value="<%= rsQuestions.getInt("question_id") %>">
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
            %>
                <tr>
                    <td colspan="8">Error retrieving questions.</td>
                </tr>
            <%
                } finally {
                    try {
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
