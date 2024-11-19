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

@WebServlet("/qualifypdf")
public class QualifyPdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jdbcURL = "jdbc:mysql://localhost:3306/easyleran";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String qualification = request.getParameter("qualification");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + qualification + "-users.pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Add logo to the top left
            String logoPath = getServletContext().getRealPath("img.jpeg");  // Replace with your image's relative path in the project
            Image logo = Image.getInstance(logoPath);
            logo.setAlignment(Image.ALIGN_LEFT);
            logo.scaleToFit(50, 50);  // Adjust size if needed

            // Create a table to align logo and title (two rows)
            PdfPTable headerTable = new PdfPTable(1);  // One column for logo and title
            headerTable.setWidthPercentage(100);

            // Add logo cell
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);  // No border around logo
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);  // Align logo to the left
            logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center vertically
            headerTable.addCell(logoCell);

            // Title cell
            Font fontTitle = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph(qualification.equals("qualified") ? "Qualified Users" : "Unqualified Users", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            PdfPCell titleCell = new PdfPCell(title);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Center the title
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align title vertically in the middle
            headerTable.addCell(titleCell);  // Add title cell after logo cell
            
            // Add header table (with logo and title) to the document
            document.add(headerTable);

            // Empty line for spacing
            document.add(new Paragraph("\n"));

            // Add generated date at the right side
            String generatedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            Paragraph dateParagraph = new Paragraph("Generated on: " + generatedDate, new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC));
            dateParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(dateParagraph);

            // Another empty line for spacing
            document.add(new Paragraph("\n"));

            // Create a table with 5 columns: ID, Name, Email, Score, Course
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Adjust the relative column widths (ID smaller, Email larger)
            float[] columnWidthsTable = {1f, 2f, 4f, 2f, 2f};  // ID = 1f, Name = 2f, Email = 4f, Score = 2f, Course = 2f
            table.setWidths(columnWidthsTable);

            Font fontHeader = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            // Table Headers
            table.addCell(new PdfPCell(new Phrase("ID", fontHeader)));
            table.addCell(new PdfPCell(new Phrase("Name", fontHeader)));
            table.addCell(new PdfPCell(new Phrase("Email", fontHeader)));
            table.addCell(new PdfPCell(new Phrase("Score", fontHeader)));
            table.addCell(new PdfPCell(new Phrase("Course", fontHeader)));

            // Database connection and query execution
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            String sql = "SELECT u.uid, u.uname, u.uemail, qr.score, c.course_name " +
                         "FROM quiz_results qr " +
                         "JOIN users u ON qr.user_id = u.uid " +
                         "JOIN courses c ON qr.course_id = c.course_id ";

            if ("qualified".equals(qualification)) {
                sql += "WHERE qr.score >= 70 "; 
            } else if ("unqualified".equals(qualification)) {
                sql += "WHERE qr.score < 70 "; 
            }

            sql += "ORDER BY qr.score DESC";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Populate table rows with data from database
            while (resultSet.next()) {
                table.addCell(String.valueOf(resultSet.getInt("uid")));
                table.addCell(resultSet.getString("uname"));
                table.addCell(resultSet.getString("uemail"));
                table.addCell(String.valueOf(resultSet.getInt("score")));
                table.addCell(resultSet.getString("course_name"));
            }

            document.add(table);
        } catch (DocumentException | ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        } finally {
            document.close();
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.getOutputStream().close();  // Ensure to close the output stream
    }
}
