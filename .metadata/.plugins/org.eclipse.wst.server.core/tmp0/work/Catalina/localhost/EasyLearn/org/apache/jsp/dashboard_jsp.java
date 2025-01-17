/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.27
 * Generated at: 2024-10-05 16:09:43 UTC
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
import jakarta.servlet.*;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    HttpSession s = request.getSession(false); 
    if (session == null || session.getAttribute("adminUser") == null) {
        response.sendRedirect("adminlogin.jsp");
        return;
    }

      out.write('\r');
      out.write('\n');
 
    String message = (String) session.getAttribute("message");
    if (message != null) { 

      out.write("\r\n");
      out.write("    <div class=\"alert alert-success\">");
      out.print( message );
      out.write("</div>\r\n");
 
    session.removeAttribute("message"); 
} 

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Admin Panel</title>\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">\r\n");
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
      out.write("        .main-content {\r\n");
      out.write("            flex-grow: 1;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("            background-color: #f8f9fa;\r\n");
      out.write("            min-height: 100vh;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .card {\r\n");
      out.write("            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\r\n");
      out.write("            border: none;\r\n");
      out.write("            border-radius: 10px;\r\n");
      out.write("            transition: transform 0.2s;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .card:hover {\r\n");
      out.write("            transform: translateY(-5px);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .card h5 {\r\n");
      out.write("            font-size: 24px;\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .card p {\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            margin-bottom: 0;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .row > .col-md-3 {\r\n");
      out.write("            margin-bottom: 20px;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark\">\r\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">Admin Panel</a>\r\n");
      out.write("    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("    </button>\r\n");
      out.write("    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n");
      out.write("        <ul class=\"navbar-nav ms-auto\">\r\n");
      out.write("         <li class=\"nav-item\">\r\n");
      out.write("                <span class=\"nav-link\" href=\"\">Hello,admin</span>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <span class=\"nav-link\" id=\"currentDateTime\"></span>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link\" href=\"dashboard.jsp\">Dashboard</a>\r\n");
      out.write("            </li>\r\n");
      out.write("           <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link\" href=\"index.jsp\">UserPanel</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link\" href=\"logout1\">Logout</a>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("<div class=\"d-flex\">\r\n");
      out.write("    <div class=\"bg-dark text-white p-3\" style=\"width: 250px; height: 100vh;\">\r\n");
      out.write("        <h4>Admin Menu</h4>\r\n");
      out.write("        <ul class=\"nav flex-column\">\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"#\">Home</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"#add-course\">Add Course</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"#add-question\">Add Question</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"displayuser.jsp\">User</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"managecourse.jsp\">Manage Course</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"manageQuestions.jsp\">Manage Question</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"displayfeedback.jsp\">Feedback</a>\r\n");
      out.write("            </li>\r\n");
      out.write("             <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"aleaderboard.jsp\">ScoreBoard</a>\r\n");
      out.write("            </li>\r\n");
      out.write("           \r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"qualification.jsp\">Certification</a>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"certificates.jsp\">Certificate</a>\r\n");
      out.write("            </li>\r\n");
      out.write("           <!--  <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link text-white\" href=\"#\">Report</a>\r\n");
      out.write("            </li> -->\r\n");
      out.write("        </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"main-content\" id=\"mainContent\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            ");

                 String url = "jdbc:mysql://localhost:3306/easyleran";
                String user = "root";
                String password = "";

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                int userCount = 0;
                int feedbackCount = 0;
                int courseCount = 0;
                int questionCount = 0;
                int QuizCount = 0;
                int certificateCount = 0;

                try {
                   
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    
                    conn = DriverManager.getConnection(url, user, password);

                   
                    stmt = conn.createStatement();

                    
                    String userCountQuery = "SELECT COUNT(*) AS count FROM users";
                    rs = stmt.executeQuery(userCountQuery);
                    if (rs.next()) {
                        userCount = rs.getInt("count");
                    }

                    
                    String feedbackCountQuery = "SELECT COUNT(*) AS count FROM feedback";
                    rs = stmt.executeQuery(feedbackCountQuery);
                    if (rs.next()) {
                        feedbackCount = rs.getInt("count");
                    }

                    String QuizCountQuery = "SELECT COUNT(*) AS count FROM quiz_results";
                    rs = stmt.executeQuery(QuizCountQuery);
                    if (rs.next()) {
                        QuizCount = rs.getInt("count");
                    }
                    
                    String courseCountQuery = "SELECT COUNT(*) AS count FROM courses";
                    rs = stmt.executeQuery(courseCountQuery);
                    if (rs.next()) {
                        courseCount = rs.getInt("count");
                    }

                    
                    String questionCountQuery = "SELECT COUNT(*) AS count FROM questions";
                    rs = stmt.executeQuery(questionCountQuery);
                    if (rs.next()) {
                        questionCount = rs.getInt("count");
                    }

                   
                    String certificateCountQuery = "SELECT COUNT(*) AS count FROM certificates";
                    rs = stmt.executeQuery(certificateCountQuery);
                    if (rs.next()) {
                        certificateCount = rs.getInt("count");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Close resources
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <div class=\"col-md-2\">\r\n");
      out.write("                <div class=\"card text-white bg-info mb-3\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h5 class=\"card-title\">");
      out.print( userCount );
      out.write("</h5>\r\n");
      out.write("                        <p class=\"card-text\">Users <i class=\"fas fa-users\"></i></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-md-2\">\r\n");
      out.write("                <div class=\"card text-white bg-primary mb-3\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h5 class=\"card-title\">");
      out.print( courseCount );
      out.write("</h5>\r\n");
      out.write("                        <p class=\"card-text\">Courses <i class=\"fas fa-book\"></i></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-md-2\">\r\n");
      out.write("                <div class=\"card text-white bg-success mb-3\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h5 class=\"card-title\">");
      out.print( feedbackCount );
      out.write("</h5>\r\n");
      out.write("                        <p class=\"card-text\">Feedback <i class=\"fas fa-comment\"></i></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-md-2\">\r\n");
      out.write("                <div class=\"card text-white bg-warning mb-3\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h5 class=\"card-title\">");
      out.print( questionCount );
      out.write("</h5>\r\n");
      out.write("                        <p class=\"card-text\">Questions <i class=\"fas fa-question\"></i></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-md-2\">\r\n");
      out.write("                <div class=\"card text-white bg-dark mb-3\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h5 class=\"card-title\">");
      out.print( QuizCount );
      out.write("</h5>\r\n");
      out.write("                        <p class=\"card-text\">Quiz Result <i class=\"fas fa-question\"></i></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"col-md-2\">\r\n");
      out.write("                <div class=\"card text-white bg-danger mb-3\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h5 class=\"card-title\">");
      out.print( certificateCount );
      out.write("</h5>\r\n");
      out.write("                        <p class=\"card-text\">Certificates <i class=\"fas fa-certificate\"></i></p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("function updateDateTime() {\r\n");
      out.write("    const now = new Date();\r\n");
      out.write("    const dateTimeString = now.toLocaleString(); \r\n");
      out.write("    document.getElementById('currentDateTime').textContent = dateTimeString;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("updateDateTime();\r\n");
      out.write("setInterval(updateDateTime, 1000);\r\n");
      out.write("\r\n");
      out.write("function loadContent(url) {\r\n");
      out.write("    fetch(url)\r\n");
      out.write("        .then(response => response.text())\r\n");
      out.write("        .then(data => {\r\n");
      out.write("            document.getElementById('mainContent').innerHTML = data;\r\n");
      out.write("        })\r\n");
      out.write("        .catch(error => console.log('Error:', error));\r\n");
      out.write("} \r\n");
      out.write("\r\n");
      out.write("document.querySelector('.nav-link[href=\"#add-course\"]').addEventListener('click', function(e) {\r\n");
      out.write("    e.preventDefault();\r\n");
      out.write("    loadContent('addCourse.jsp');\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("document.querySelector('.nav-link[href=\"#add-question\"]').addEventListener('click', function(e) {\r\n");
      out.write("    e.preventDefault();\r\n");
      out.write("    loadContent('addQuestion.jsp');\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
