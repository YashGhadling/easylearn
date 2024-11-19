package adminservlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/mails")
public class MailCertificateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"certificates.pdf\"");

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Add logo
            String logoPath = getServletContext().getRealPath("img.jpeg");
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(50, 50);
            logo.setAlignment(Element.ALIGN_LEFT);
            document.add(logo);

            // Title and date
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Easy Learn", titleFont);
            Paragraph reportTitle = new Paragraph("Certificate Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            reportTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(reportTitle);

            // Get selected course ID and name from request parameters
            String selectedCourseId = request.getParameter("courseId");
            if (selectedCourseId == null || selectedCourseId.isEmpty()) {
                document.add(new Paragraph("No course selected or invalid course ID."));
                document.close();
                return;
            }

            String courseName = "";
            String dbURL = "jdbc:mysql://localhost:3306/easyleran";
            String dbUser = "root";
            String dbPass = "";
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            // Fetch course name
            String courseQuery = "SELECT course_name FROM courses WHERE course_id = ?";
            try (PreparedStatement courseStmt = conn.prepareStatement(courseQuery)) {
                courseStmt.setString(1, selectedCourseId);
                ResultSet courseRs = courseStmt.executeQuery();
                if (courseRs.next()) {
                    courseName = courseRs.getString("course_name");
                }
                courseRs.close();
            }

            // Display the course name immediately after the report title
            Font courseFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph courseParagraph = new Paragraph("Course: " + courseName, courseFont);
            courseParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(courseParagraph);

            String generatedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph generatedDateParagraph = new Paragraph(" Generated on: " + generatedDate, dateFont);
            generatedDateParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(generatedDateParagraph);
            document.add(new Paragraph(" "));

            // Fetch certificates
            String sql = "SELECT u.uname, c.course_name, cert.score, cert.issue_date " +
                         "FROM certificates cert " +
                         "JOIN users u ON cert.user_id = u.uid " +
                         "JOIN courses c ON cert.course_id = c.course_id " +
                         "WHERE cert.course_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, selectedCourseId);
                ResultSet rs = stmt.executeQuery();

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Table headers
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                table.addCell(new PdfPCell(new Phrase("SR No", headerFont)));
                table.addCell(new PdfPCell(new Phrase("User Name", headerFont)));
                table.addCell(new PdfPCell(new Phrase("Course Name", headerFont)));
                table.addCell(new PdfPCell(new Phrase("Score", headerFont)));
                table.addCell(new PdfPCell(new Phrase("Issue Date", headerFont)));

                int srNo = 1;
                if (!rs.isBeforeFirst()) {
                    document.add(new Paragraph("No certificates found for the selected course."));
                } else {
                    while (rs.next()) {
                        String userName = rs.getString("uname");
                        String certCourseName = rs.getString("course_name");
                        int score = rs.getInt("score");
                        java.sql.Date issueDate = rs.getDate("issue_date");

                        table.addCell(String.valueOf(srNo++));
                        table.addCell(userName);
                        table.addCell(certCourseName);
                        table.addCell(String.valueOf(score));
                        table.addCell(issueDate != null ? issueDate.toString() : "N/A");
                    }
                    document.add(table);
                }
                rs.close();
            }
            conn.close();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error generating PDF: " + e.getMessage());
        } finally {
            response.getOutputStream().flush();
        }
    }
}