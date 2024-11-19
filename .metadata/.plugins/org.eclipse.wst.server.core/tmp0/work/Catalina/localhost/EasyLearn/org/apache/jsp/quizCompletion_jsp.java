/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.27
 * Generated at: 2024-11-02 10:52:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.sql.*;
import jakarta.servlet.http.*;
import java.util.*;

public final class quizCompletion_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

    HttpSession sessio= request.getSession(false);
    if (session == null) {
        response.sendRedirect("loginuser.jsp");
        return;
    }

    // Retrieve alert message from the session
    String alertMessage = (String) session.getAttribute("alertMessage");

    // Remove the alert message from session to prevent it from showing again
    session.removeAttribute("alertMessage");

    // Display alert message using JavaScript if it exists
    if (alertMessage != null) {

      out.write("\r\n");
      out.write("        <script>\r\n");
      out.write("            alert(\"");
      out.print( alertMessage );
      out.write("\");\r\n");
      out.write("        </script>\r\n");

    }

    String userName = (String) session.getAttribute("userName");
    String courseName = (String) session.getAttribute("courseName");
    Integer score = (Integer) session.getAttribute("score");

    if (userName == null || courseName == null || score == null) {
        out.println("<script>alert('Session data missing. Please try the quiz again.');</script>");
        return;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Integer userId = null;
    Integer courseId = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Use appropriate driver
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyleran", "root", ""); // Replace with your DB credentials

        // Get User ID
        String getUserIdSQL = "SELECT uid FROM users WHERE uname = ?";
        pstmt = conn.prepareStatement(getUserIdSQL);
        pstmt.setString(1, userName);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("uid");
        }
        rs.close();
        pstmt.close();

        // Get Course ID
        String getCourseIdSQL = "SELECT course_id FROM courses WHERE course_name = ?";
        pstmt = conn.prepareStatement(getCourseIdSQL);
        pstmt.setString(1, courseName);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            courseId = rs.getInt("course_id");
        }
        rs.close();
        pstmt.close();

        if (userId == null || courseId == null) {
            out.println("<script>alert('User or Course information not found in the database.');</script>");
            return;
        }

        // Display the quiz score and user/course details
        out.println("<h1 style='color: green;'>Quiz Completed</h1>");
        out.println("<p>Congratulations, <strong>" + userName + "</strong>! You completed the course: <strong>" + courseName + "</strong> and scored: <strong>" + score + "</strong> points.</p>");
        out.println("<p>Thank you for choosing <strong>EASYLEARN.</strong></p>");

        // Check if the user already has a score for the course
        String checkScoreSQL = "SELECT score FROM quiz_results WHERE user_id = ? AND course_id = ?";
        pstmt = conn.prepareStatement(checkScoreSQL);
        pstmt.setInt(1, userId);
        pstmt.setInt(2, courseId);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            // User exists, update their score
            String updateScoreSQL = "UPDATE quiz_results SET score = ? WHERE user_id = ? AND course_id = ?";
            pstmt = conn.prepareStatement(updateScoreSQL);
            pstmt.setInt(1, score); // New score
            pstmt.setInt(2, userId);
            pstmt.setInt(3, courseId);
            
            int updatedRows = pstmt.executeUpdate();
            if (updatedRows > 0) {
              //  out.println("<p style='color: green;'>Your score has been updated successfully!</p>");
            } else {
              //  out.println("<p style='color: red;'>Error updating your score. Please try again.</p>");
            }
        } else {
            // User does not exist, insert a new score
            String insertScoreSQL = "INSERT INTO quiz_results (user_id, course_id, score, quiz_date) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertScoreSQL);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseId);
            pstmt.setInt(3, score); // New score
            pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis())); // Current date

            int insertedRows = pstmt.executeUpdate();
            if (insertedRows > 0) {
              //  out.println("<p style='color: green;'>Your score has been added successfully!</p>");
            } else {
              //  out.println("<p style='color: red;'>Error adding your score. Please try again.</p>");
            }
        }

    } catch (Exception e) {
        out.println("<script>alert('Error: " + e.getMessage() + "');</script>");
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    session.removeAttribute("score");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<a href=\"index.jsp\" style=\"display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px;\">Back to Dashboard</a>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
