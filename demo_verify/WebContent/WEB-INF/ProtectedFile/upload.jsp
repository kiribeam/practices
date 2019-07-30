<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload File</title>
</head>
<body>
<h1> Select your file!</h1>
<p>Only one file could be uploaded!</p>
<form method="post" action="/function/upload" enctype="multipart/form-data">
	<input type="file" name="uploadFile"/>
	<br/>
	<input type="submit" value="upload"/>
</form>
</br>
<p> Click here to go back <a href="/function">fucntion</a> page!</p>
</body>
</html>