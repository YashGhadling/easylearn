package actionservlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.*;
import java.util.*;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import adminservlet.Question;
import java.io.ByteArrayOutputStream;

@SuppressWarnings("deprecation")
public class QuizServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/easyleran";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final long QUIZ_DURATION = 150L; // 150 seconds

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 HttpSession session = request.getSession();
         String action = request.getParameter("action");

         if ("useFiftyFifty".equals(action)) {
             session.setAttribute("lifelineFiftyFiftyUsed", true);
         } else if ("useShowAnswer".equals(action)) {
             session.setAttribute("lifelineShowAnswerUsed", true);
         }
        HttpSession sessi = request.getSession(false);

        if (session == null || session.getAttribute("userName") == null) {
            response.sendRedirect("loginuser.jsp");
            return;
        }

        String courseName = (String) session.getAttribute("courseName");
        String userName = (String) session.getAttribute("userName");
        String userEmail = (String) session.getAttribute("email");
        Integer userId = (Integer) session.getAttribute("userId");

        // Fetch userId from the database if it's missing in the session
        if (userId == null) {
            userId = getUserIdFromDatabase(userName);
            if (userId == null) {
                System.out.println("Failed to retrieve userId for userName: " + userName);
                response.sendRedirect("loginuser.jsp");
                return;
            }
            session.setAttribute("userId", userId);
        }

        if (courseName == null || courseName.trim().isEmpty()) {
            response.sendRedirect("quizinstruct.jsp");
            return;
        }

        List<Question> questions = (List<Question>) session.getAttribute("questions");
        Integer currentQuestionIndex = (Integer) session.getAttribute("currentQuestionIndex");

        if (questions == null) {
            questions = getQuestionsForCourse(courseName);

            if (questions == null || questions.isEmpty()) {
                request.setAttribute("errorMessage", "Currently, there are no questions available for this course.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                return;
            }

            session.setAttribute("quizStartTime", System.currentTimeMillis());
            session.setAttribute("questions", questions);
            currentQuestionIndex = 0;
            session.setAttribute("currentQuestionIndex", currentQuestionIndex);
            session.setAttribute("score", 0);
            session.setAttribute("lifelineFiftyFiftyUsed", false);
            session.setAttribute("lifelineShowAnswerUsed", false);
        } else {
            String selectedAnswer = request.getParameter("answer");
            if (selectedAnswer != null) {
                List<String> userAnswers = (List<String>) session.getAttribute("userAnswers");
                if (userAnswers == null) {
                    userAnswers = new ArrayList<>();
                    session.setAttribute("userAnswers", userAnswers);
                }

                while (userAnswers.size() <= currentQuestionIndex) {
                    userAnswers.add(null);
                }

                userAnswers.set(currentQuestionIndex, selectedAnswer);

                Question currentQuestion = questions.get(currentQuestionIndex);
                String correctAnswer = currentQuestion.getCorrectAnswer();
                Integer score = (Integer) session.getAttribute("score");

                if (score == null) {
                    score = 0;
                }

                if (selectedAnswer.equals(correctAnswer)) {
                    score += 10;
                }

                session.setAttribute("score", score);
            }

            currentQuestionIndex++;
            if (currentQuestionIndex >= questions.size()) {
                Integer finalScore = (Integer) session.getAttribute("score");
                boolean emailSent = false;

                if (finalScore != null && finalScore > 70) {
                    emailSent = sendCertificateEmail(request, response, userEmail, userName, courseName, finalScore, userId);

                    if (emailSent) {
                        session.setAttribute("alertMessage", "Your course certificate is mailed to you.");
                    } else {
                        session.setAttribute("alertMessage", "Failed to send the email.");
                    }
                } else {
                    session.setAttribute("alertMessage", "You have failed the quiz. Please try again.");
                }

                response.sendRedirect("quizCompletion.jsp");
                return;
            }

            session.setAttribute("currentQuestionIndex", currentQuestionIndex);
        }

        Long quizStartTime = (Long) session.getAttribute("quizStartTime");
        if (quizStartTime == null) {
            quizStartTime = System.currentTimeMillis();
            session.setAttribute("quizStartTime", quizStartTime);
        }

        long elapsedTime = (System.currentTimeMillis() - quizStartTime) / 1000;
        long timeRemaining = QUIZ_DURATION - elapsedTime;

        if (timeRemaining <= 0) {
            response.sendRedirect("quizCompletion.jsp");
            return;
        }

        request.setAttribute("timeRemaining", timeRemaining);
        request.getRequestDispatcher("quizPage.jsp").forward(request, response);
    }

    private Integer getUserIdFromDatabase(String userName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT uid FROM users WHERE uname = ?")) { // Update the query here to match your table structure
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("uid"); // Use the correct column name for user ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Question> getQuestionsForCourse(String courseName) {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT question_text, option_a AS optionA, option_b AS optionB, "
                     + "option_c AS optionC, option_d AS optionD, correct_answer AS correctAnswer "
                     + "FROM questions WHERE course_id = (SELECT course_id FROM courses WHERE course_name = ?) ORDER BY RAND() LIMIT 15")) {

            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.setQuestionText(rs.getString("question_text"));
                question.setOptionA(rs.getString("optionA"));
                question.setOptionB(rs.getString("optionB"));
                question.setOptionC(rs.getString("optionC"));
                question.setOptionD(rs.getString("optionD"));
                question.setCorrectAnswer(rs.getString("correctAnswer"));
                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private Integer getCourseId(String courseName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT course_id FROM courses WHERE course_name = ?")) {
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("course_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void storeCertificateData(Integer userId, Integer courseId, Integer score) {
        //System.out.println("Debug Info - userId: " + userId + ", courseId: " + courseId + ", score: " + score);

        if (userId == null || courseId == null || score == null) {
          //  System.out.println("Invalid input: userId, courseId, or score is null.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("INSERT INTO certificates (user_id, course_id, score, issue_date) VALUES (?, ?, ?, ?)")) {

            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.setInt(3, score);
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    private boolean sendCertificateEmail(HttpServletRequest request, HttpServletResponse response, String userEmail, String userName, String courseName, int score, Integer userId) {
        String subject = "Your Certificate for " + courseName;
        try {
            // Generate certificate PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);

            // Set up the writer
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            // Set up fonts
            BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(baseFont, 28, Font.BOLD, new BaseColor(51, 51, 51));
            Font normalFont = new Font(baseFont, 16, Font.NORMAL, new BaseColor(119, 119, 119));
            Font nameFont = new Font(baseFont, 24, Font.BOLD, new BaseColor(0, 122, 255));
            Font courseFont = new Font(baseFont, 20, Font.BOLD, new BaseColor(255, 149, 0));
            Font scoreFont = new Font(baseFont, 18, Font.BOLD, new BaseColor(0, 122, 255));
            Font footerFont = new Font(baseFont, 14, Font.BOLD, new BaseColor(51, 51, 51));

            // Adjust margins for alignment within the borders
            document.setMargins(50, 50, 150, 100); // Adjust margins to align content

            // Add borders
            PdfContentByte canvas = writer.getDirectContent();

         // Larger golden border
         Rectangle goldenBorder = new Rectangle(40, 160, 555, 640); // Increase dimensions
         goldenBorder.setBorder(Rectangle.BOX);
         goldenBorder.setBorderWidth(4);
         goldenBorder.setBorderColor(new BaseColor(255, 215, 0));
         canvas.rectangle(goldenBorder);

         // Blue border (keeps the original size)
         Rectangle blueBorder = new Rectangle(50, 170, 545, 630);
         blueBorder.setBorder(Rectangle.BOX);
         blueBorder.setBorderWidth(2);
         blueBorder.setBorderColor(new BaseColor(0, 122, 255));
         canvas.rectangle(blueBorder);
        
            // Add content inside the borders using ColumnText
            ColumnText ct = new ColumnText(canvas);
            ct.setSimpleColumn(70, 220, 525, 620); // Define the area for text placement, moved up slightly

            // Title
            Paragraph title = new Paragraph("Certificate of Completion", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            ct.addElement(title);

            // "This certifies that"
            Paragraph certifiesText = new Paragraph("This is to certify that", normalFont);
            certifiesText.setAlignment(Element.ALIGN_CENTER);
            ct.addElement(certifiesText);

            // User name
            Paragraph userNamePara = new Paragraph(userName, nameFont);
            userNamePara.setAlignment(Element.ALIGN_CENTER);
            userNamePara.setSpacingAfter(15);
            ct.addElement(userNamePara);

            // "has successfully completed the"
            Paragraph completionText = new Paragraph("has successfully completed the", normalFont);
            completionText.setAlignment(Element.ALIGN_CENTER);
            ct.addElement(completionText);

            // Course name
            Paragraph coursePara = new Paragraph(courseName, courseFont);
            coursePara.setAlignment(Element.ALIGN_CENTER);
            coursePara.setSpacingAfter(15);
            ct.addElement(coursePara);

            // Score
            Paragraph scorePara = new Paragraph("Score Achieved: " + score, scoreFont);
            scorePara.setAlignment(Element.ALIGN_CENTER);
            ct.addElement(scorePara);

            // Date of issue
            String formattedDate = new java.text.SimpleDateFormat("MMMM dd, yyyy").format(new java.util.Date());
            Paragraph datePara = new Paragraph("Date of Issue: " + formattedDate, normalFont);
            datePara.setAlignment(Element.ALIGN_CENTER);
            ct.addElement(datePara);

            // Add all text
            ct.go();

            // Footer Section - Inside Rectangle
            float footerY = 220; // Moved up slightly for better alignment

            // Left footer text (Signature)
            ColumnText signatureText = new ColumnText(canvas);
            signatureText.setSimpleColumn(70, footerY, 300, footerY + 50); // Adjust coordinates for signature text
            Paragraph signOff = new Paragraph("Authorized Signatory", normalFont);
            Paragraph easyLearn = new Paragraph("EasyLearn", footerFont);
            signOff.setAlignment(Element.ALIGN_LEFT);
            easyLearn.setAlignment(Element.ALIGN_LEFT);
            signatureText.addElement(signOff);
            signatureText.addElement(easyLearn);
            signatureText.go();

            // Right footer logo
            String logoPath = getServletContext().getRealPath("img.jpeg");
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(50, 50);
            logo.setAbsolutePosition(485, footerY); // Adjusted position for logo
            canvas.addImage(logo);

            // Close the document
            document.close();

            // Store certificate data
            storeCertificateData(userId, getCourseId(courseName), score);

            // Send the email with the certificate attached
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.starttls.enable", "true");

            Session mailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("projecteasylearn1@gmail.com", "ujgd tnxt nrsj rfbq");
                }
            });

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("projecteasylearn1@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject(subject);

            // Prepare the email content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Congratulations, " + userName + "! You have successfully completed the course " + courseName + ". Please find your certificate attached.");

            // Attach the PDF certificate
            MimeBodyPart pdfPart = new MimeBodyPart();
            pdfPart.setDataHandler(new DataHandler(new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf")));
            pdfPart.setFileName(courseName + "_" + userName + "_Certificate.pdf");

            // Combine email content and attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(pdfPart);

            message.setContent(multipart);
            Transport.send(message);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}