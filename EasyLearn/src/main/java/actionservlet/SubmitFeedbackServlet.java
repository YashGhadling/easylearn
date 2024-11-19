package actionservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/feedback")
public class SubmitFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            String email = (String) session.getAttribute("email");
            String userName = (String) session.getAttribute("userName");

            
            if (email != null && userName != null) {
                Connection conn = null;
                PreparedStatement userStmt = null;
                PreparedStatement feedbackStmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

                    // Fetch userId based on email
                    String userSql = "SELECT uid FROM users WHERE uemail = ?";
                    userStmt = conn.prepareStatement(userSql);
                    userStmt.setString(1, email);
                    rs = userStmt.executeQuery();

                    int userId = -1;
                    if (rs.next()) {
                        userId = rs.getInt("uid");
                    }

                    // Proceed if userId is found
                    if (userId != -1) {
                        String feedbackText = request.getParameter("feedback_text");
                        String feedbackDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                        String sql = "INSERT INTO feedback (user_id, feedback_text, feedback_date) VALUES (?, ?, ?)";
                        feedbackStmt = conn.prepareStatement(sql);
                        feedbackStmt.setInt(1, userId);
                        feedbackStmt.setString(2, feedbackText);
                        feedbackStmt.setString(3, feedbackDate);
                        int rowsAffected = feedbackStmt.executeUpdate();

                        if (rowsAffected > 0) {
                            response.sendRedirect("feedback_success.jsp?userName=" + userName);
                        } else {
                            response.sendRedirect("feedback_error.jsp");
                        }
                    } else {
                        response.sendRedirect("feedback_error.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("feedback_error.jsp");
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (userStmt != null) try { userStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (feedbackStmt != null) try { feedbackStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            } else {
                System.out.println("Email or userName attribute is null.");
                response.sendRedirect("loginuser.jsp");
            }
        } else {
            System.out.println("Session is null.");
            response.sendRedirect("loginuser.jsp");
        }
    }
}
