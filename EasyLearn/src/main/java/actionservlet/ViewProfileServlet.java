package actionservlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/profile/view")
public class ViewProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            String userName = (String) session.getAttribute("userName");

            if (userName != null) {
                String url = "jdbc:mysql://localhost:3306/easyleran";
                String dbUser = "root";
                String dbPassword = "";

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(url, dbUser, dbPassword);

                    String query = "SELECT uname, uemail, mobile, udob FROM users WHERE uname = ?";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, userName);
                    rs = pstmt.executeQuery();

                    if (rs.next()) {
                        request.setAttribute("uname", rs.getString("uname"));
                        request.setAttribute("uemail", rs.getString("uemail"));
                        request.setAttribute("mobile", rs.getString("mobile"));
                        request.setAttribute("udob", rs.getString("udob"));

                        request.getRequestDispatcher("/updateProfile.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("profile.jsp?error=notfound");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect("profile.jsp?error=error");
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
