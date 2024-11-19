<%@ page import="java.util.List" %>
<%@ page import="adminservlet.Course"%>
<%@ page import="adminservlet.CourseDAO" %>
<%
    java.sql.Connection connection = (java.sql.Connection) application.getAttribute("connection");

    if (connection != null) {
        CourseDAO courseDAO = new CourseDAO(connection);
        List<Course> courses = courseDAO.getAllCourses();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Courses</title>
</head>
<body>
    <h1>Courses</h1>
    <a href="addCourse.jsp">Add New Course</a>
    <table border="1">
        <thead>
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Course course : courses) {
            %>
            <tr>
                <td><%= course.getId() %></td>
                <td><%= course.getName() %></td>
                <td><%= course.getDescription() %></td>
                <td>
                    <a href="questions.jsp?courseId=<%= course.getId() %>">View/Add Questions</a>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
<%
    } else {
        // If the connection is null, show an error message
        out.println("Database connection is not available.");
    }
%>
