/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.27
 * Generated at: 2024-11-10 06:06:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.sql.*;
import javax.sql.*;

public final class manageQuestions_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_packages.add("javax.sql");
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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Manage Questions</title>\r\n");
      out.write("    <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <style>\r\n");
      out.write("        .nav-link:hover {\r\n");
      out.write("            background-color: #343a40;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .navbar {\r\n");
      out.write("            background-color: #000000;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .nav-link {\r\n");
      out.write("            padding: 10px 15px;\r\n");
      out.write("            border-radius: 4px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .nav-link.active {\r\n");
      out.write("            background-color: #495057;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .container {\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        table {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            border-collapse: collapse;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        th, td {\r\n");
      out.write("            border: 1px solid #ddd;\r\n");
      out.write("            padding: 12px;\r\n");
      out.write("            text-align: left;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        th {\r\n");
      out.write("            background-color: #f8f9fa;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        h2 {\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("            font-size: 24px;\r\n");
      out.write("            color: #343a40;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        body {\r\n");
      out.write("            background-color: #f8f9fa;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .action-buttons button {\r\n");
      out.write("            margin: 0 5px;\r\n");
      out.write("            color: #fff;\r\n");
      out.write("            border: none;\r\n");
      out.write("            padding: 5px 10px;\r\n");
      out.write("            border-radius: 4px;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .btn-warning {\r\n");
      out.write("            background-color: #ffc107;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .btn-danger {\r\n");
      out.write("            background-color: #dc3545;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .btn-warning:hover {\r\n");
      out.write("            background-color: #e0a800;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .btn-danger:hover {\r\n");
      out.write("            background-color: #c82333;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .form-group label {\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark\">\r\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">Admin Panel</a>\r\n");
      out.write("    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("    </button>\r\n");
      out.write("    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n");
      out.write("        <ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <span class=\"nav-link\">Hello, admin</span>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <span class=\"nav-link\" id=\"currentDateTime\"></span>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link\" href=\"dashboard.jsp\">Dashboard</a>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("<div class=\"container mt-4\">\r\n");
      out.write("    ");
 if (session.getAttribute("message") != null) { 
      out.write("\r\n");
      out.write("        <div class=\"alert alert-info\">\r\n");
      out.write("            ");
      out.print( session.getAttribute("message") );
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
 session.removeAttribute("message"); 
      out.write("\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("    <h2 class=\"mb-4\">Manage Questions</h2>\r\n");
      out.write("    <form action=\"manageQuestions.jsp\" method=\"get\" class=\"mb-3\">\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <label for=\"course_id\">Select Course:</label>\r\n");
      out.write("            <select name=\"course_id\" id=\"course_id\" class=\"form-control\" onchange=\"this.form.submit()\">\r\n");
      out.write("                <option value=\"\">-- Select Course --</option>\r\n");
      out.write("                ");

                String url = "jdbc:mysql://localhost:3306/easyleran";
                String user = "root";
                String password = "";

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    stmt = conn.createStatement();
                    String sql = "SELECT course_id, course_name FROM courses";
                    rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        int courseId = rs.getInt("course_id");
                        String courseName = rs.getString("course_name");
                        String selected = request.getParameter("course_id") != null && Integer.parseInt(request.getParameter("course_id")) == courseId ? "selected" : "";
                
      out.write("\r\n");
      out.write("                    <option value=\"");
      out.print( courseId );
      out.write('"');
      out.write(' ');
      out.print( selected );
      out.write('>');
      out.print( courseName );
      out.write("</option>\r\n");
      out.write("                ");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                
      out.write("\r\n");
      out.write("                    <option value=\"\">Error loading courses</option>\r\n");
      out.write("                ");

                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                
      out.write("\r\n");
      out.write("            </select>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("    <table class=\"table table-striped table-bordered\">\r\n");
      out.write("        <thead>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>Sr No</th>\r\n");
      out.write("                <th>Question Text</th>\r\n");
      out.write("                <th>Option A</th>\r\n");
      out.write("                <th>Option B</th>\r\n");
      out.write("                <th>Option C</th>\r\n");
      out.write("                <th>Option D</th>\r\n");
      out.write("                <th>Correct Answer</th>\r\n");
      out.write("                <th>Actions</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </thead>\r\n");
      out.write("        <tbody>\r\n");
      out.write("            ");

            String selectedCourseId = request.getParameter("course_id");
            int srNo = 1;

            if (selectedCourseId != null && !selectedCourseId.isEmpty()) {
                try {
                    conn = DriverManager.getConnection(url, user, password);
                    String sql = "SELECT * FROM questions WHERE course_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(selectedCourseId));
                    ResultSet rsQuestions = pstmt.executeQuery();

                    while (rsQuestions.next()) {
                        String questionText = rsQuestions.getString("question_text");
                        String optionA = rsQuestions.getString("option_a");
                        String optionB = rsQuestions.getString("option_b");
                        String optionC = rsQuestions.getString("option_c");
                        String optionD = rsQuestions.getString("option_d");
                        String correctAnswer = rsQuestions.getString("correct_answer");
            
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print( srNo++ );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( questionText );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( optionA );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( optionB );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( optionC );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( optionD );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( correctAnswer );
      out.write("</td>\r\n");
      out.write("                <td class=\"action-buttons\">\r\n");
      out.write("                    <form action=\"updateQuestion.jsp\" method=\"get\" style=\"display:inline;\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"question_id\" value=\"");
      out.print( rsQuestions.getInt("question_id") );
      out.write("\">\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-warning btn-sm\">Update</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <form action=\"deleteQuestion.jsp\" method=\"post\" style=\"display:inline;\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"question_id\" value=\"");
      out.print( rsQuestions.getInt("question_id") );
      out.write("\">\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-danger btn-sm\">Delete</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
            
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td colspan=\"8\">Error retrieving questions.</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            ");

                } finally {
                    try {
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            
      out.write("\r\n");
      out.write("        </tbody>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("function updateDateTime() {\r\n");
      out.write("    const now = new Date();\r\n");
      out.write("    const dateTimeString = now.toLocaleString(); \r\n");
      out.write("    document.getElementById('currentDateTime').textContent = dateTimeString;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("updateDateTime();\r\n");
      out.write("setInterval(updateDateTime, 1000);\r\n");
      out.write("</script>\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js\"></script>\r\n");
      out.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
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