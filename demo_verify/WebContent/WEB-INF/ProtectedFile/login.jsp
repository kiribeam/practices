<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<% if(request.getAttribute("wrongInput")==null ){
    out.println("You need log in!");
    request.setAttribute("wrongInput", true);
    }
  else {
    out.println("Wrong Input of username or password!");
  } 
%>
<!-- form pass -->
<% Object username = session.getAttribute("username"); %>
<form action="/login" method="post">
    username:<input type="text" 
    			value="<%= null == username ? "" : username.toString() %>"
    			name="username">
    <br>
    password:<input type="password" name="password" >
    <br>
    <input type="submit" value="Login"/>
</form>
<br>
<p>If you forget the password, you can <a href="/changePassword">here</a>!</p>
<p>Or, if you are a new user , click <a href="/signUp">here</a>to sign up</p>
<br>
<p>click <a href="/index.html">here</a> to go to index page </p>
</body>
</html>