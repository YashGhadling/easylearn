package actionservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/uprofile")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email == null) {
            response.sendRedirect("loginuser.jsp");
            return;
        }

        String uname = request.getParameter("uname");
        String mobile = request.getParameter("mobile");
        String dob = request.getParameter("dob");

        // Basic validation
        if (uname == null || uname.isEmpty() || mobile == null || mobile.isEmpty() || dob == null || dob.isEmpty()) {
            response.sendRedirect("uprofile.jsp?status=error&message=All fields are required.");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

            // Prepare SQL update statement
            String sql = "UPDATE users SET uname = ?, mobile = ?, udob = ? WHERE uemail = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname);
            pstmt.setString(2, mobile);
            pstmt.setDate(3, java.sql.Date.valueOf(dob)); // Convert string to SQL Date
            pstmt.setString(4, email);

            // Execute update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("uprofile.jsp?status=success"); // Redirect with success status
            } else {
                response.sendRedirect("uprofile.jsp?status=error&message=Profile update failed.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("uprofile.jsp?status=error&message=Database driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("uprofile.jsp?status=error&message=Database error occurred.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.sendRedirect("uprofile.jsp?status=error&message=Invalid date format.");
        } finally {
            // Close resources
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
