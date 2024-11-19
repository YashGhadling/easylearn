<%@ page import="java.util.List" %>
<%@ page import="adminservlet.Question" %>
<%@ page import="adminservlet.QuestionDAO" %>
<%@ page import="java.sql.Connection" %>
<%
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    Connection connection = (Connection) application.getAttribute("dbConnection");
    List<Question> questions = null;

    if (connection != null) {
        QuestionDAO questionDAO = new QuestionDAO(connection);
        questions = questionDAO.getQuestionsByCourseId(courseId);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Questions</title>
</head>
<body>
    <h1>Questions for Course ID: <%= courseId %></h1>
    <ul>
        <% if (questions != null) {
            for (Question question : questions) { %>
                <li><%= question.getQuestionText() %></li>
        <%  }
           } else { %>
           <p>No questions found for this course.</p>
        <% } %>
    </ul>
</body>
</html>
