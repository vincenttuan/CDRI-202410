<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lottery</title>
	</head>
	<body>
		<h1 style="color: red">
			<%=request.getAttribute("n1") %>
			<%=request.getAttribute("n2") %>
			<%=request.getAttribute("n3") %>
			<%=request.getAttribute("n4") %>
		</h1>
	</body>
</html>