<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Login with your user to see all the complaints</h2>
	<form action="/complaintsystem/showComplaints" method="post" id="pwdForm">
		User: <br>
		<input type="text" name="username">
		<br>
		Passwd: <br>
		<input type="password" name="userpwd">
		<br>
		<input type="submit" value="Complain">
	</form>
</body>
</html>