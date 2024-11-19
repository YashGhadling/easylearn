<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EasyLearn</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Montserrat', sans-serif;
            background-color: #f4f4f4;
            color: #333;
        }

        a {
            text-decoration: none;
            color: inherit;
        }

        ul {
            list-style: none;
        }

        /* Navbar */
        .navbar {
            margin-bottom: 30px;
            background-color: #FFC300;
            padding: 10px; /* Smaller navbar */
        }

        .navbar .navbar-brand {
            font-size: 20px;
            font-weight: 700;
            color: #000; /* Changed text color to black */
        }

        /* Logo size set properly */
        .navbar-brand img {
            width: 100px; /* Adjusted logo size */
            height: auto;
        }

        .navbar-nav .nav-link {
            font-size: 18px; /* Increased font size */
            color: #000; /* Changed text color to black */
            padding: 10px 15px;
            transition: background 0.3s;
        }

        .navbar-nav .nav-link:hover {
            background-color: #444;
            border-radius: 4px;
        }

        .navbar .dropdown-menu {
            background-color: #333;
            min-width: 150px;
        }

        .navbar .dropdown-item {
            color: #fff;
            padding: 10px 15px;
        }

        .navbar .dropdown-item:hover {
            background-color: #444;
        }

        /* Main Banner */
        .main-banner {
            position: relative;
            height: 100vh;
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        .header-text h6 {
            font-size: 18px;
            color: #fff;
            margin-bottom: 10px;
        }

        .header-text h2 {
            font-size: 48px;
            color: #fff;
            font-weight: 900;
        }

        .main-button a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #ff4a57;
            color: #fff;
            font-size: 16px;
            border-radius: 25px;
            transition: background 0.3s;
        }

        .main-button a:hover {
            background-color: #ff2a35;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .navbar-nav {
                flex-direction: column;
            }

            .main-banner h2 {
                font-size: 36px;
            }

            .main-banner h6 {
                font-size: 16px;
            }
        }

        /* Footer */
        footer {
            background-color: #FFC300;
            color: #000; /* Changed text color to black */
            font-size: 16px; /* Increased font size */
        }
        .bold-text {
    font-weight: bold;
}
    </style>
</head>

<body>
    <% 
        HttpSession s = request.getSession(false); 
        String userName = null; 
        if (s != null) { 
            userName = (String) s.getAttribute("userName"); 
        } 
    %>

    
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href=""><img src="img.jpeg" alt="Logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#"><strong>Home</strong></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                       <strong>Courses</strong>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <% 
                                Connection con = null;
                                PreparedStatement ps = null;
                                ResultSet rs = null;

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

                                    String query = "SELECT course_name FROM courses ORDER BY course_name";
                                    ps = con.prepareStatement(query);
                                    rs = ps.executeQuery();

                                    while (rs.next()) { 
                            %>
                            <li><a class="dropdown-item" href="quizinstruct.jsp?courseName=<%= URLEncoder.encode(rs.getString("course_name"), "UTF-8") %>"><%= rs.getString("course_name") %></a></li>
                            <% 
                                    } 
                                } catch (Exception e) {
                                    e.printStackTrace();
                            %>
                            <li><a class="dropdown-item" href="#">Error loading courses</a></li>
                            <% 
                                } finally {
                                    if (rs != null) try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
                                    if (ps != null) try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
                                    if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(); }
                                }
                            %>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="profile.jsp"><strong>Profile</strong></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="leaderboard.jsp"><strong>Leaderboard</strong></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="feedback.jsp"><strong>Feedback</strong></a>
                    </li>
                </ul>
                <div class="d-flex ms-auto">
                    <% 
                        if (userName != null) { 
                    %>
                      <span class="nav-item nav-link text-black me-3 bold-text"><%= userName %></span>
  						<a href="LogoutServlet" class="nav-item nav-link text-black"><strong><i class="fas fa-sign-out-alt"></i> Logout</strong></a>
                    <% 
                        } else { 
                    %>
                        <a href="loginuser.jsp" class="nav-item nav-link text-black"><strong><i class="fas fa-sign-in-alt"></i> Login</strong></a>
                    <% 
                        } 
                    %>
                </div>
            </div>
        </div>
    </nav>


    <section class="hero-section text-center">
    <div class="container">
        <h1 style="font-size: 48px; font-weight: bold;">Welcome to EasyLearn - Learn to Code for Free!</h1>
        <p style="font-size: 20px; font-weight: bold;">Join millions of learners from around the world on their journey to mastering coding and technology.</p>

        <div class="features mt-4">
            <p style="font-size: 22px; font-weight: bold;"><strong>Why Choose EasyLearn?</strong></p>
            <ul class="list-unstyled" style="font-size: 20px; font-weight: bold;">
                <li>100% Free Courses in Coding and more</li>
                <li>Hands-on Projects to Build Your Portfolio</li>
                <li>Interactive Quizzes and Challenges</li>
                <li>Learn from Expert Instructors and a Global Community</li>
            </ul>
        </div>

      
    </div>
</section>


    <section class="courses-section py-5">
    <div class="container">
        <h2 class="text-center mb-4" style="font-size: 28px; font-weight: bold;">Popular Courses</h2>
        <div class="row">
            <% 
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

                    String query = "SELECT course_id, course_name, description FROM courses";
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();

                    while (rs.next()) { 
                        int courseId = rs.getInt("course_id");
                        String courseName = rs.getString("course_name");
                        String courseDescription = rs.getString("description");
            %>
           <div class="col-lg-4 col-md-6 mb-4">
    <div class="card h-100">
        <!-- Using ImageServlet to display the image -->
        <img src="ImageServlet?courseId=<%= courseId %>" class="card-img-top" alt="<%= courseName %>" style="width: 100%; height: 250px; object-fit: cover;">
        <div class="card-body">
            <h5 class="card-title" style="font-size: 22px; font-weight: bold;"><%= courseName %></h5>
            <p class="card-text"><%= courseDescription %></p>
        </div>
        <div class="card-footer">
            <a href="quizinstruct.jsp?courseName=<%= URLEncoder.encode(courseName, "UTF-8") %>" class="btn btn-primary">Start Learning</a>
        </div>
    </div>
</div>

            <% 
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
            %>
            <p class="text-danger">Error loading courses: <%= e.getMessage() %></p>
            <% 
                } finally {
                    if (rs != null) try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
                    if (ps != null) try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
                    if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(); }
                }
            %>
        </div>
    </div>
</section>
<footer class="text-black text-center py-3" style="background-color: #FFC300; color: #000;">
    <p style="font-size: 20px; font-weight: bold; margin: 0;">&copy; 2024 EasyLearn. All rights reserved.</p>
    <p style="font-size: 20px; font-weight: bold; margin: 0;">Contact us: <a href="" style="color: #000;">projecteasylearn1@gmail.com</a> | Phone: <a href="" style="color: #000;">+91 9075852242</a></p>
</footer>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>