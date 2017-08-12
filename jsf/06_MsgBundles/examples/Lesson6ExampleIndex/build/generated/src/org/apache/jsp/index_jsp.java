package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"res/styles.css\" type=\"text/css\">\n");
      out.write("    <title>Lesson 6: Examples Index</title>\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <h1>Lesson 6: Message Bundles</h1>\n");
      out.write("    <table>\n");
      out.write("      <thead>\n");
      out.write("        <tr>\n");
      out.write("          <th>Link</th>\n");
      out.write("          <th>Sources</th>\n");
      out.write("        </tr>\n");
      out.write("      </thead>\n");
      out.write("      <tbody>\n");
      out.write("        <tr>\n");
      out.write("          <td><a href=\"/MessagingDemo\">Using Message BundlesDemo</a></td>\n");
      out.write("          <td>\n");
      out.write("            <ul>\n");
      out.write("              <li><a href=\"sources/MsgDemo/index.xhtml.html\">MsgDemo:/index.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/MsgDemo/internationalized-messages.xhtml.html\">MsgDemo:/internationalized-messages.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/MsgDemo/parameterized-messages.xhtml.html\">MsgDemo:/parameterized-messages.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/MsgDemo/simple-messages.xhtml.html\">MsgDemo:/simple-messages.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/MsgDemo/success1.xhtml.html\">MsgDemo:/success1.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/MsgDemo/success2.xhtml.html\">MsgDemo:/success2.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/MsgDemo/success3.xhtml.html\">MsgDemo:/success3.xhtml</a></li>\n");
      out.write("              <li><a href=\"sources/ManagedBeans/Person.java.html\">CDI Beans:/Person.java</a></li>\n");
      out.write("              <li><a href=\"sources/ManagedBeans/Person1.java.html\">CDI Beans:/Person1.java</a></li>\n");
      out.write("              <li><a href=\"sources/ManagedBeans/Person2.java.html\">CDI Beans:/Person2.java</a></li>\n");
      out.write("              <li><a href=\"sources/ManagedBeans/Person3.java.html\">CDI Beans:/Person3.java</a></li>\n");
      out.write("              <li><a href=\"sources/Properties/messages1.properties.html\">Properties:/messages1.properties<a></li>\n");
      out.write("              <li><a href=\"sources/Properties/messages2.properties.html\">Properties:/messages2.properties</a></li>\n");
      out.write("              <li><a href=\"sources/Properties/messages2_fr.properties.html\">Properties:/messages2_fr.properties</a></li>\n");
      out.write("               <li><a href=\"sources/Properties/messages3_es.properties.html\">Properties:/messages3_es.properties</a></li>\n");
      out.write("            </ul>\n");
      out.write("          </td>\n");
      out.write("        </tr>\n");
      out.write("       </tbody>\n");
      out.write("    </table>\n");
      out.write("  </body>\n");
      out.write("</html>");
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
