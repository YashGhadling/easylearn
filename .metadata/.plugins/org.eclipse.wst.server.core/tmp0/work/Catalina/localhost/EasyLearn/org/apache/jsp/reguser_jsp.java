/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.27
 * Generated at: 2024-10-05 11:04:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class reguser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>Register</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\">\r\n");
      out.write("    <style>\r\n");
      out.write("        body {\r\n");
      out.write("            margin: 0;\r\n");
      out.write("            font-family: Arial, sans-serif;\r\n");
      out.write("            background-image: url(\"page.jpg\");\r\n");
      out.write("            background-repeat: no-repeat;\r\n");
      out.write("            background-size: cover;\r\n");
      out.write("            color: #ffffff;\r\n");
      out.write("            display: flex;\r\n");
      out.write("            justify-content: center;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("            height: 100vh;\r\n");
      out.write("        }\r\n");
      out.write("        .header {\r\n");
      out.write("            position: absolute;\r\n");
      out.write("            top: 20px;\r\n");
      out.write("            left: 20px;\r\n");
      out.write("            display: flex;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("        }\r\n");
      out.write("        .header img {\r\n");
      out.write("            height: 50px;\r\n");
      out.write("            margin-right: 10px;\r\n");
      out.write("        }\r\n");
      out.write("        .header h2 {\r\n");
      out.write("            font-size: 24px;\r\n");
      out.write("            margin: 0;\r\n");
      out.write("        }\r\n");
      out.write("        .container {\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            padding: 50px;\r\n");
      out.write("            background-color: #282c34;\r\n");
      out.write("            border-radius: 10px;\r\n");
      out.write("            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);\r\n");
      out.write("            width: 300px;\r\n");
      out.write("            margin-left: 800px;\r\n");
      out.write("        }\r\n");
      out.write("        .container h1 {\r\n");
      out.write("            font-size: 24px;\r\n");
      out.write("            margin-bottom: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        .container form {\r\n");
      out.write("            display: flex;\r\n");
      out.write("            flex-direction: column;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("        }\r\n");
      out.write("        .input-group {\r\n");
      out.write("            display: flex;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            margin: 10px 0;\r\n");
      out.write("        }\r\n");
      out.write("        .input-group i {\r\n");
      out.write("            margin-right: 10px;\r\n");
      out.write("            color: #ffffff; /* Change the icon color if needed */\r\n");
      out.write("        }\r\n");
      out.write("        .container input[type=\"text\"], \r\n");
      out.write("        .container input[type=\"password\"],\r\n");
      out.write("        .container input[type=\"email\"], \r\n");
      out.write("        .container input[type=\"date\"],\r\n");
      out.write("        .container input[type=\"tel\"] {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            padding: 10px;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            border: none;\r\n");
      out.write("        }\r\n");
      out.write("        .container button {\r\n");
      out.write("            background-color: #4285F4;\r\n");
      out.write("            color: #fff;\r\n");
      out.write("            padding: 10px 20px;\r\n");
      out.write("            border: none;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        .container button:hover {\r\n");
      out.write("            background-color: #357ae8;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"header\">\r\n");
      out.write("        <img src=\"img.jpeg\" alt=\"Logo\">\r\n");
      out.write("        <h2>EasyLearn</h2>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <h1>Register</h1>\r\n");
      out.write("        <form action=\"RegisterServlet\" method=\"post\">\r\n");
      out.write("            <div class=\"input-group\">\r\n");
      out.write("                <i class=\"fas fa-user\"></i>\r\n");
      out.write("                <input type=\"text\" name=\"name\" placeholder=\"Name\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"input-group\">\r\n");
      out.write("                <i class=\"fas fa-envelope\"></i>\r\n");
      out.write("                <input type=\"email\" name=\"email\" placeholder=\"Email\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"input-group\">\r\n");
      out.write("                <i class=\"fas fa-phone\"></i>\r\n");
      out.write("                <input type=\"tel\" name=\"mobile\" placeholder=\"Mobile Number\" required pattern=\"[0-9]{10}\" title=\"Enter a valid 10-digit mobile number\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"input-group\">\r\n");
      out.write("                <i class=\"fas fa-calendar-alt\"></i>\r\n");
      out.write("                <input type=\"date\" name=\"dob\" placeholder=\"Date of Birth\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"input-group\">\r\n");
      out.write("                <i class=\"fas fa-lock\"></i>\r\n");
      out.write("                <input type=\"password\" name=\"password\" placeholder=\"Password\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("            <button type=\"submit\">Register</button>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
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
