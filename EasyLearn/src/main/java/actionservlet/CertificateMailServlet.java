package actionservlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.itextpdf.html2pdf.HtmlConverter;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;

public class CertificateMailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set up the response content type
        response.setContentType("application/pdf");

        // Define the output file for the PDF
        File pdfFile = new File(getServletContext().getRealPath("/") + "certificates/certificate.pdf");
        pdfFile.getParentFile().mkdirs(); // Ensure the directory exists

        // Render the JSP content to HTML format
        StringWriter stringWriter = new StringWriter();
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("certificate.jsp");
            dispatcher.include(request, (ServletResponse) new PrintWriter(stringWriter));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating certificate HTML.");
            return;
        }

        // Convert the HTML to PDF using iText
        try (OutputStream outputStream = new FileOutputStream(pdfFile)) {
            HtmlConverter.convertToPdf(stringWriter.toString(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating PDF.");
            return;
        }

        // Fetch the recipient email from the request (assuming it's passed as a parameter)
        String recipientEmail = request.getParameter("email"); // Assuming the email is passed as a request parameter
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Recipient email is missing.");
            return;
        }

        // Send the email with the PDF attachment
        sendEmailWithAttachment(recipientEmail, pdfFile);

        // Inform the user about the email status
        response.getWriter().println("Email sent successfully with the certificate attached.");
    }

    private void sendEmailWithAttachment(String recipient, File pdfFile) {
        // Define sender's email and password (ideally fetch from config or secure location)
        String fromEmail = "projecteasylearn1@gmail.com"; // Change this to your email
        String fromPassword = "ujgd tnxt nrsj rfbq"; // Change this to your email password (use secure storage for passwords)

        // Set mail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a new session for the email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your Certificate of Completion");

            // Create the message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find attached your certificate.");

            // Create a multipart message for the attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the PDF file
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(pdfFile);
            pdfBodyPart.setDataHandler(new DataHandler(source));
            pdfBodyPart.setFileName(pdfFile.getName());
            multipart.addBodyPart(pdfBodyPart);

            // Set the complete message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully with the certificate attached.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
