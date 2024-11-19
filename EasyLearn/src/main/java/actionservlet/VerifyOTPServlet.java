package actionservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/VerifyOTP")
public class VerifyOTPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String otpEntered = request.getParameter("otp");
        String otpGenerated = (String) session.getAttribute("otp");

        // Validate OTP
        if (otpEntered.equals(otpGenerated)) {
            // OTP is valid, proceed to insert the user into the database
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            String mobile = (String) session.getAttribute("mobile");
            String dob = (String) session.getAttribute("dob");
            String password = (String) session.getAttribute("password");

            final String jdbcURL = "jdbc:mysql://localhost:3306/easyleran";
            final String dbUser = "root";
            final String dbPassword = "";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                    String sql = "INSERT INTO users (uname, uemail, mobile, udob, upass) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, mobile);
                    statement.setString(4, dob);
                    statement.setString(5, password);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        response.sendRedirect("success.jsp");
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?message=" + e.getMessage());
            }
        } else {
            // Invalid OTP
            response.sendRedirect("otp_verification.jsp?error=Invalid OTP. Please try again.");
        }
    }
}
