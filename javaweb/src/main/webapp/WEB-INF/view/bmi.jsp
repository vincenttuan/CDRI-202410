<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BMI</title>
	</head>
	<body>
		<h1>
			身高: <%=request.getAttribute("height") %><p />
			體重: <%=request.getAttribute("weight") %><p />
			BMI: <%=request.getAttribute("bmi") %><p />
		</h1>
	</body>
</html>