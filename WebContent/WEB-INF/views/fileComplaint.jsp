<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Message: <br>
	<textarea type="text" name="complaint" style="height:50px; font-size: 14pt;" form="complaintForm" ></textarea>
	
	<form action="/complaintsystem/submitComplaint" method="post" id="complaintForm">
		
		Email: <br>
		<input type="text" name="email">
		<br>
		Name: <br>
		<input type="text" name="name">
		<br>
		<input type="submit" value="Complain">
	</form>
</body>
</html>