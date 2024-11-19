package actionservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

@WebServlet("/reguser")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    // Method to generate a 6-digit OTP
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Method to send an email
    public void sendEmail(String recipient, String otp) throws MessagingException {
        String sender = "projecteasylearn1@gmail.com";  // Your email
        String host = "smtp.gmail.com";  // SMTP server

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("projecteasylearn1@gmail.com", "ujgd tnxt nrsj rfbq"); // Your email credentials
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);

        Transport.send(message);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String dob = request.getParameter("dob");
        String password = request.getParameter("password");

        // Validate input fields
        if (name == null || email == null || mobile == null || dob == null || password == null ||
            name.isEmpty() || email.isEmpty() || mobile.isEmpty() || dob.isEmpty() || password.isEmpty()) {
            out.println("All fields are required.");
            return;
        }

        // Generate OTP and store in session
        String otp = generateOTP();
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("mobile", mobile);
        session.setAttribute("dob", dob);
        session.setAttribute("password", password);

        // Send OTP to the user's email
        try {
            sendEmail(email, otp);
        } catch (MessagingException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=" + e.getMessage());
            return;
        }

        // Redirect to the OTP verification page
        response.sendRedirect("otp_verification.jsp");
    }
}