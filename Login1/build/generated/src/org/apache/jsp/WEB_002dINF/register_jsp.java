package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 int i=0;
            String str="Register"; 
            String url = "NewUser.do";
        
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("<h2>Responsive Social Login Form</h2>\n");
      out.write("<h3><center>User's details:</center></h3>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");

            if((String)request.getAttribute("msg")=="del") {
                str = "Delete";
                url = "Delete.do";
            }
            else if ((String)request.getAttribute("msg") == "Admin"){
                str = "Login";
                url = "Login.do";
            }
            else if ((String)request.getAttribute("msg") == "Member"){
                str = "Member";
                url = "Login.do";
            }
            else {
                str = "Register";
                url = "NewUser.do";
            } 
        
      out.write("\n");
      out.write("        <form method=\"POST\" action=\"");
      out.print(url);
      out.write("\">     \n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <th></th>\n");
      out.write("                    <th><center>Please provide your following details</center></th>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Username:</td>\n");
      out.write("                    <td><input type=\"text\" name=\"username\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Password:</td>\n");
      out.write("                    <td><input type=\"password\" name=\"password\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr> \n");
      out.write("                    <td> <input type=\"submit\" value=\"");
      out.print(str);
      out.write("\"/></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>   \n");
      out.write("        ");

            if (i++>0 && request.getAttribute("message")!=null) {
                out.println(request.getAttribute("message"));
                i--;
            }
        
      out.write("\n");
      out.write("        </br>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "foot.jsp", out, false);
      out.write("\n");
      out.write("<p>Resize the browser window to see the responsive effect. When the screen is less than 650px wide, make the two columns stack on top of each other instead of next to each other.</p>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("  <form action=\"/action_page.php\">\n");
      out.write("    <div class=\"row\">\n");
      out.write("      <h2 style=\"text-align:center\">Signup or Login</h2>\n");
      out.write("      <div class=\"vl\">\n");
      out.write("        <span class=\"vl-innertext\">or</span>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("      <div class=\"col\">\n");
      out.write("          <div class=\"hide-md-lg\">\n");
      out.write("            <p>Sign up</p>\n");
      out.write("          </div>\n");
      out.write("          <input type=\"text\" name=\"username\" placeholder=\"Username\" required>\n");
      out.write("          <input type=\"password\" name=\"password\" placeholder=\"Password\" required>\n");
      out.write("          <input type=\"submit\" value=\"Signup\">\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("      <div class=\"col\">\n");
      out.write("        <div class=\"hide-md-lg\">\n");
      out.write("          <p>Log in</p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <input type=\"text\" name=\"username\" placeholder=\"Username\" required>\n");
      out.write("        <input type=\"password\" name=\"password\" placeholder=\"Password\" required>\n");
      out.write("        <input type=\"submit\" value=\"Login\">\n");
      out.write("      </div>\n");
      out.write("      \n");
      out.write("    </div>\n");
      out.write("  </form>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"bottom-container\">\n");
      out.write("  <div class=\"row\">\n");
      out.write("      <a href=\"#\" style=\"color:white\" class=\"btn\"style=\"text-align:center\">Forgot password?</a>\n");
      out.write("  </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
