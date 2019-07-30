<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up!</title>
</head>
<body>
<% if(request.getAttribute("wrongInput")==null && request.getAttribute("dup")==null ){
    out.println("Sign Up!");
    }else if(request.getAttribute("dup") != null){
		out.println("Dupped User name or mail address");
    }
  else{
    out.println("Wrong input!");
  }
%>
<form action="/signUp" method="post">
    username:   <input type="text" name="username">
    <br>
    (username and password can be only used by number or charactor , less than 20)
    <br>
    password:   <input type="password" name="password" >
    <br>
    check again:<input type="password" name="checkpass" >
    <br>
    mail address:<input type="text" name="mail">
    <br>
    (special character except '@' can't be input here) 
    <br>
    capcha:
    <br>
    <input type="submit" value="Sign Up"/>
</form>
<a href="/index.html">Back to index!</a>

</body>
</html>