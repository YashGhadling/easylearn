<%@ page import="java.util.List" %>
<%@ page import="adminservlet.Question" %>
<%
    HttpSession sesion = request.getSession(false);

    if (session == null || session.getAttribute("userName") == null || session.getAttribute("courseName") == null || session.getAttribute("questions") == null) {
        response.sendRedirect("loginuser.jsp");
        return;
    }

    String userName = (String) session.getAttribute("userName");
    String courseName = (String) session.getAttribute("courseName");
    List<Question> questions = (List<Question>) session.getAttribute("questions");
    Integer currentQuestionIndex = (Integer) session.getAttribute("currentQuestionIndex");
    Long quizStartTime = (Long) session.getAttribute("quizStartTime");
    Integer timeRemaining = (Integer) session.getAttribute("QUIZ_DURATION");

    if (currentQuestionIndex == null || currentQuestionIndex >= questions.size()) {
        response.sendRedirect("errorPage.jsp");
        return;
    }

    if (timeRemaining == null) {
        timeRemaining = 150;
    }

    if (quizStartTime != null) {
        long elapsedTime = (System.currentTimeMillis() - quizStartTime) / 1000;
        timeRemaining = Math.max(0, timeRemaining - (int) elapsedTime);
    }

    if (session.getAttribute("lifelineFiftyFiftyUsed") == null) {
        session.setAttribute("lifelineFiftyFiftyUsed", false);
    }
    if (session.getAttribute("lifelineShowAnswerUsed") == null) {
        session.setAttribute("lifelineShowAnswerUsed", false);
    }

    boolean lifelineFiftyFiftyUsed = Boolean.TRUE.equals(session.getAttribute("lifelineFiftyFiftyUsed"));
    boolean lifelineShowAnswerUsed = Boolean.TRUE.equals(session.getAttribute("lifelineShowAnswerUsed"));

    Question currentQuestion = questions.get(currentQuestionIndex);
    String previousAnswer = null;
    List<String> userAnswers = (List<String>) session.getAttribute("userAnswers");
    if (userAnswers != null && currentQuestionIndex < userAnswers.size()) {
        previousAnswer = userAnswers.get(currentQuestionIndex);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
         body {
            font-family: 'Poppins', sans-serif;
            background-color: #2c2e43;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #3b3e5b;
            border-radius: 12px;
            padding: 40px;
            width: 100%;
            max-width: 600px;
            text-align: center;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.3);
        }
        h2 {
            color: #fff;
            font-size: 26px;
            margin-bottom: 20px;
        }
        .question {
            font-size: 22px;
            color: #f9f9f9;
            margin-bottom: 30px;
            padding: 10px;
            border: 2px solid #f39c12;
            border-radius: 10px;
            background: linear-gradient(90deg, rgba(243,156,18,1) 0%, rgba(192,57,43,1) 100%);
        }
        .options {
            margin: 20px 0;
        }
        .option {
            background-color: #576574;
            color: white;
            font-size: 18px;
            padding: 10px;
            border-radius: 8px;
            margin: 10px 0;
            display: block;
            transition: all 0.3s;
            cursor: pointer;
            border: none;
            width: 100%;
        }
        .option:hover {
            background-color: #f39c12;
        }
        .lifeline {
            background-color: #22a6b3;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 30px;
            font-size: 16px;
            cursor: pointer;
            margin: 5px;
            transition: background-color 0.3s ease;
        }
        .lifeline:hover {
            background-color: #0984e3;
        }
        .timer {
            font-size: 20px;
            font-weight: bold;
            color: #ff7675;
            margin-top: 20px;
        }
        .button {
            background-color: #f39c12;
            color: white;
            border: none;
            border-radius: 30px;
            padding: 12px 25px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #e67e22;
        } /* Add CSS styling */
    </style>
</head>
<body>
    <div class="container">
        <h2>Question <%= currentQuestionIndex + 1 %> of <%= questions.size() %></h2>
        <div class="question">
            <%= currentQuestion.getQuestionText() %>
        </div>
        <form id="quizForm" action="QuizServlet" method="post">
            <div class="options">
                <button class="option" id="optionA" type="radio" name="answer" value="A" <%= previousAnswer != null && previousAnswer.equals("A") ? "checked" : "" %>> 
                    A. <%= currentQuestion.getOptionA() %>
                </button>
                <button class="option" id="optionB" type="radio" name="answer" value="B" <%= previousAnswer != null && previousAnswer.equals("B") ? "checked" : "" %>> 
                    B. <%= currentQuestion.getOptionB() %>
                </button>
                <button class="option" id="optionC" type="radio" name="answer" value="C" <%= previousAnswer != null && previousAnswer.equals("C") ? "checked" : "" %>> 
                    C. <%= currentQuestion.getOptionC() %>
                </button>
                <button class="option" id="optionD" type="radio" name="answer" value="D" <%= previousAnswer != null && previousAnswer.equals("D") ? "checked" : "" %>> 
                    D. <%= currentQuestion.getOptionD() %>
                </button>
            </div>

            <input type="hidden" name="questionIndex" value="<%= currentQuestionIndex %>">
            <input type="hidden" name="courseName" value="<%= courseName %>">
        </form>

        <div class="timer">
            Time Remaining: <span id="timer"><%= timeRemaining %></span> seconds
        </div>

        <button class="lifeline" id="lifelineFiftyFifty" onclick="useFiftyFifty()" <%= lifelineFiftyFiftyUsed ? "disabled" : "" %>>50-50</button>
        <button class="lifeline" id="lifelineShowAnswer" onclick="useShowAnswer()" <%= lifelineShowAnswerUsed ? "disabled" : "" %>>Show Answer</button>
    </div>

    <script>
        var timeRemaining = <%= timeRemaining %>;
        var correctAnswer = '<%= currentQuestion.getCorrectAnswer() %>';
        var lifelineFiftyFiftyUsed = <%= lifelineFiftyFiftyUsed ? "true" : "false" %>;
        var lifelineShowAnswerUsed = <%= lifelineShowAnswerUsed ? "true" : "false" %>;

        function startTimer() {
            var timerInterval = setInterval(function() {
                timeRemaining--;
                document.getElementById("timer").innerText = timeRemaining;
                if (timeRemaining <= 0) {
                    clearInterval(timerInterval);
                    alert("Time's up!");
                    document.getElementById("quizForm").submit();
                }
            }, 1000);
        }

        function useFiftyFifty() {
            if (lifelineFiftyFiftyUsed === "true") {
                alert("50-50 lifeline already used.");
                return;
            }
            lifelineFiftyFiftyUsed = "true";
            document.getElementById("lifelineFiftyFifty").disabled = true;

            var incorrectOptions = ["A", "B", "C", "D"].filter(opt => opt !== correctAnswer);
            incorrectOptions.splice(0, 2).forEach(opt => {
                document.getElementById("option" + opt).style.display = "none";
            });

            fetch('QuizServlet', {
                method: 'POST',
                body: new URLSearchParams({ action: 'useFiftyFifty' })
            });
        }

        function useShowAnswer() {
            if (lifelineShowAnswerUsed === "true") {
                alert("Show Answer lifeline already used.");
                return;
            }
            lifelineShowAnswerUsed = "true";
            document.getElementById("lifelineShowAnswer").disabled = true;
            alert("The correct answer is: " + correctAnswer);

            fetch('QuizServlet', {
                method: 'POST',
                body: new URLSearchParams({ action: 'useShowAnswer' })
            });
        }

        startTimer();
    </script>
</body>
</html>