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

@WebServlet("/pdfleaderboard")
public class LeaderboardPDFServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbURL = "jdbc:mysql://localhost:3306/easyleran";
        String dbUser = "root"; 
        String dbPass = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String selectedCourseId = request.getParameter("courseId");
        String selectedDate = request.getParameter("quizDate");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Leaderboard.pdf");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            // SQL query to get leaderboard data
            String sql = "SELECT u.uname AS username, c.course_name, qr.score, qr.quiz_date " +
                         "FROM quiz_results qr " +
                         "JOIN users u ON qr.user_id = u.uid " +
                         "JOIN courses c ON qr.course_id = c.course_id " +
                         "WHERE qr.course_id = ? ";
            
            if (selectedDate != null && !selectedDate.isEmpty()) {
                sql += "AND qr.quiz_date = ? ";
            }

            sql += "ORDER BY qr.score DESC";

            ps = conn.prepareStatement(sql);
            ps.setString(1, selectedCourseId);
            
            if (selectedDate != null && !selectedDate.isEmpty()) {
                ps.setDate(2, Date.valueOf(selectedDate));
            }

            rs = ps.executeQuery();

            // Initialize iText Document
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            // Adding logo to the top left
            String logoPath = getServletContext().getRealPath("img.jpeg");  // Replace with your image's relative path in the project
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(50, 50);  // Adjust size as needed
            PdfPTable logoTable = new PdfPTable(1);  // One column for logo
            logoTable.setWidthPercentage(100);
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER); // No border around logo
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            logoTable.addCell(logoCell);
            document.add(logoTable); // Add logo table to document

            // Adding Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("EasyLearn Leaderboard Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Empty line

            // Add Course and Date Info
            Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            String reportDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            
            Paragraph courseInfo = new Paragraph("Course: " + getCourseName(selectedCourseId, conn), infoFont);
            courseInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(courseInfo);

            if (selectedDate != null && !selectedDate.isEmpty()) {
                Paragraph dateInfo = new Paragraph("Quiz Date: " + selectedDate, infoFont);
                dateInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(dateInfo);
            }
            Paragraph generatedDate = new Paragraph("Report generated on: " + reportDate, infoFont);
            generatedDate.setAlignment(Element.ALIGN_LEFT);
            document.add(generatedDate);

            document.add(new Paragraph(" ")); // Empty line

            // Add Table to PDF
            PdfPTable table = new PdfPTable(4); // 4 columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Table Headers
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            table.addCell(new PdfPCell(new Phrase("Username", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Course", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Score", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Quiz Date", headerFont)));

            // Table Data
            while (rs.next()) {
                table.addCell(rs.getString("username"));
                table.addCell(rs.getString("course_name"));
                table.addCell(String.valueOf(rs.getInt("score")));
                table.addCell(rs.getDate("quiz_date").toString());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to get course name from the course_id
    private String getCourseName(String courseId, Connection conn) throws SQLException {
        String courseName = "";
        String sql = "SELECT course_name FROM courses WHERE course_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    courseName = rs.getString("course_name");
                }
            }
        }
        return courseName;
    }
}
