package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CookieTester extends HttpServlet{
  public void doGet(HttpServletRequest request, 
      HttpServletResponse response)throws IOException, ServletException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();


    out.println("<html><body>");
    out.println("<a href=\"" + response.encodeURL("/CookieAndURLTest/form.html") + "\">click me</a>");
    out.println("</body></html>");

  }
}

