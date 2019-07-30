<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Functions</title>
</head>
<body>
<h1> Welcome! <% 
		out.println(session.getAttribute("username"));
	%>
</h1>
<a href="/function/upload">Upload file</a>
<br>
<a href="/function/download">Download file</a>
<br>
<a href="/function/downloadPublicKey">Download public key</a>
<br>
<a href="/function/uploadPublicKey">Upload your public key!</a>
<p>This public key must be verfified by the operation bellow before it can be used!</p>
<p>Only one key can be used at the same time! If you want to change a new key, <a href="/function/changeKey">click here!</a></p>
<p>Verify your key by <a href="/function/verifyByPhone">phonecall</a> or <a href="/function/mail">verify mail</a></p>
<p>You can Change password by <a href="/function/changePassword">click here</a>!
<p>Click here to <a href="/logoff"> Log off</a></p>

</body>
</html>