<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Public Key</title>
</head>
<body>
<h1> Select your key file!</h1>
<p>Only one file could be uploaded!</p>
<form method="post" action="/function/uploadPublicKey" enctype="multipart/form-data">
	<input type="file" name="uploadFile"/>
	<br/>
	<input type="submit" value="upload"/>
</form>
</br>
<p> Click here to go back <a href="/function">fucntion</a> page!</p>
</body>
</html>