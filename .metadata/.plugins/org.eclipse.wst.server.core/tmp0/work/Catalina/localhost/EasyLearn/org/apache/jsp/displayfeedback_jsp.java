/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.27
 * Generated at: 2024-10-26 16:24:13 UTC
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

public final class displayfeedback_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>User Feedback</title>\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
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
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark\">\r\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">Admin Panel</a>\r\n");
      out.write("    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("    </button>\r\n");
      out.write("    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n");
      out.write("        <ul class=\"navbar-nav ms-auto\">\r\n");
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
      out.write("<h2>User Feedback List</h2>\r\n");
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("    <thead>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <th>Sr. No.</th>\r\n");
      out.write("            <th>Name</th>\r\n");
      out.write("            <th>Email</th>\r\n");
      out.write("            <th>Feedback Text</th>\r\n");
      out.write("            <th>Feedback Date</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("    <tbody>\r\n");
      out.write("        ");

            String url = "jdbc:mysql://localhost:3306/easyleran"; // Ensure the database name is correct
            String user = "root"; // Update if your DB username is different
            String password = ""; // Update password if applicable

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            int srNo = 1; // Counter for Sr. No.

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the JDBC driver is included in your project

                conn = DriverManager.getConnection(url, user, password);

                stmt = conn.createStatement();
                String sql = "SELECT u.uname, u.uemail, f.feedback_text, f.feedback_date FROM users u INNER JOIN feedback f ON u.uid = f.user_id";

                rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    String uname = rs.getString("uname");
                    String uemail = rs.getString("uemail");
                    String feedbackText = rs.getString("feedback_text");
                    Date feedbackDate = rs.getDate("feedback_date");
        
      out.write("\r\n");
      out.write("                    <tr>\r\n");
      out.write("                        <td>");
      out.print( srNo++ );
      out.write("</td> <!-- Incrementing Sr. No. -->\r\n");
      out.write("                        <td>");
      out.print( uname );
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.print( uemail );
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.print( feedbackText );
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.print( feedbackDate );
      out.write("</td>\r\n");
      out.write("                    </tr>\r\n");
      out.write("        ");

                }

            } catch (Exception e) {
                e.printStackTrace();
        
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td colspan=\"5\">Error retrieving data: ");
      out.print( e.getMessage() );
      out.write("</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("        ");

            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        
      out.write("\r\n");
      out.write("    </tbody>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    function updateDateTime() {\r\n");
      out.write("        const now = new Date();\r\n");
      out.write("        const dateTimeString = now.toLocaleString();\r\n");
      out.write("        document.getElementById('currentDateTime').textContent = dateTimeString;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    updateDateTime();\r\n");
      out.write("    setInterval(updateDateTime, 1000);\r\n");
      out.write("</script>\r\n");
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
