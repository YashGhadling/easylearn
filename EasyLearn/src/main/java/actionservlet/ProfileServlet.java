package actionservlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userName");

        if (email == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not logged in
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", "");

            // Prepare SQL statement
            String sql = "SELECT uname, mobile, udob FROM users WHERE uemail = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            // Execute query
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve data
                String uname = rs.getString("uname");
                String mobile = rs.getString("mobile");
                Date udob = rs.getDate("udob");

                // Set attributes for JSP
                request.setAttribute("uname", uname);
                request.setAttribute("email", email);
                request.setAttribute("mobile", mobile);
                request.setAttribute("udob", udob);

                // Forward to profile.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
