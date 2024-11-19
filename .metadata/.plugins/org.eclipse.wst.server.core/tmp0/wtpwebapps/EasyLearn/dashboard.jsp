<%@ page import="java.sql.*, jakarta.servlet.http.*, jakarta.servlet.*" %>
<%
    HttpSession s = request.getSession(false); 
    if (session == null || session.getAttribute("adminUser") == null) {
        response.sendRedirect("adminlogin.jsp");
        return;
    }
%>
<% 
    String message = (String) session.getAttribute("message");
    if (message != null) { 
%>
    <div class="alert alert-success"><%= message %></div>
<% 
    session.removeAttribute("message"); 
} 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
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

        .main-content {
            flex-grow: 1;
            padding: 20px;
            background-color: #f8f9fa;
            min-height: 100vh;
        }

        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: none;
            border-radius: 10px;
            transition: transform 0.2s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card h5 {
            font-size: 24px;
            font-weight: bold;
        }

        .card p {
            font-size: 16px;
            margin-bottom: 0;
        }

        .row > .col-md-3 {
            margin-bottom: 20px;
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
                <span class="nav-link" href="">Hello,admin</span>
            </li>
            <li class="nav-item">
                <span class="nav-link" id="currentDateTime"></span>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="dashboard.jsp">Dashboard</a>
            </li>
           <li class="nav-item">
                <a class="nav-link" href="index.jsp">UserPanel</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout1">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="d-flex">
    <div class="bg-dark text-white p-3" style="width: 250px; height: 100vh;">
        <h4>Admin Menu</h4>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link text-white" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="#add-course">Add Course</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="#add-question">Add Question</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="displayuser.jsp">User</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="managecourse.jsp">Manage Course</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="manageQuestions.jsp">Manage Question</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="displayfeedback.jsp">Feedback</a>
            </li>
             <li class="nav-item">
                <a class="nav-link text-white" href="aleaderboard.jsp">ScoreBoard</a>
            </li>
           
            <li class="nav-item">
                <a class="nav-link text-white" href="qualification.jsp">Certification</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="certificates.jsp">Certificate</a>
            </li>
           <!--  <li class="nav-item">
                <a class="nav-link text-white" href="#">Report</a>
            </li> -->
        </ul>
    </div>

    <div class="main-content" id="mainContent">
        <div class="row">
            <%
                 String url = "jdbc:mysql://localhost:3306/easyleran";
                String user = "root";
                String password = "";

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                int userCount = 0;
                int feedbackCount = 0;
                int courseCount = 0;
                int questionCount = 0;
                int QuizCount = 0;
                int certificateCount = 0;

                try {
                   
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    
                    conn = DriverManager.getConnection(url, user, password);

                   
                    stmt = conn.createStatement();

                    
                    String userCountQuery = "SELECT COUNT(*) AS count FROM users";
                    rs = stmt.executeQuery(userCountQuery);
                    if (rs.next()) {
                        userCount = rs.getInt("count");
                    }

                    
                    String feedbackCountQuery = "SELECT COUNT(*) AS count FROM feedback";
                    rs = stmt.executeQuery(feedbackCountQuery);
                    if (rs.next()) {
                        feedbackCount = rs.getInt("count");
                    }

                    String QuizCountQuery = "SELECT COUNT(*) AS count FROM quiz_results";
                    rs = stmt.executeQuery(QuizCountQuery);
                    if (rs.next()) {
                        QuizCount = rs.getInt("count");
                    }
                    
                    String courseCountQuery = "SELECT COUNT(*) AS count FROM courses";
                    rs = stmt.executeQuery(courseCountQuery);
                    if (rs.next()) {
                        courseCount = rs.getInt("count");
                    }

                    
                    String questionCountQuery = "SELECT COUNT(*) AS count FROM questions";
                    rs = stmt.executeQuery(questionCountQuery);
                    if (rs.next()) {
                        questionCount = rs.getInt("count");
                    }

                   
                    String certificateCountQuery = "SELECT COUNT(*) AS count FROM certificates";
                    rs = stmt.executeQuery(certificateCountQuery);
                    if (rs.next()) {
                        certificateCount = rs.getInt("count");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Close resources
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>

            <div class="col-md-2">
                <div class="card text-white bg-info mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= userCount %></h5>
                        <p class="card-text">Users <i class="fas fa-users"></i></p>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= courseCount %></h5>
                        <p class="card-text">Courses <i class="fas fa-book"></i></p>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= feedbackCount %></h5>
                        <p class="card-text">Feedback <i class="fas fa-comment"></i></p>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card text-white bg-warning mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= questionCount %></h5>
                        <p class="card-text">Questions <i class="fas fa-question"></i></p>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card text-white bg-dark mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= QuizCount %></h5>
                        <p class="card-text">Quiz Result <i class="fas fa-question"></i></p>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="card text-white bg-danger mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= certificateCount %></h5>
                        <p class="card-text">Certificates <i class="fas fa-certificate"></i></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<script>
function updateDateTime() {
    const now = new Date();
    const dateTimeString = now.toLocaleString(); 
    document.getElementById('currentDateTime').textContent = dateTimeString;
}

updateDateTime();
setInterval(updateDateTime, 1000);

function loadContent(url) {
    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.getElementById('mainContent').innerHTML = data;
        })
        .catch(error => console.log('Error:', error));
} 

document.querySelector('.nav-link[href="#add-course"]').addEventListener('click', function(e) {
    e.preventDefault();
    loadContent('addCourse.jsp');
});

document.querySelector('.nav-link[href="#add-question"]').addEventListener('click', function(e) {
    e.preventDefault();
    loadContent('addQuestion.jsp');
});
</script>

</body>
</html>
