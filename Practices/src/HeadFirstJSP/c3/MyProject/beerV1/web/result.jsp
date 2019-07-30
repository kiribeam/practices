<%@ page import="java.util.*" %>
<html>
  <body>
    <h1 align="center">Beer Recommendations JSP</h1>
    <p>
    <%
    List styles = (List) (request.getAttribute("styles"));
    Iterator iter = styles.iterator();
    while(iter.hasNext())
      out.print("<br>try:</br>" + iter.next());
      %>
  </body>
</html>
