<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Error</title>
	</head>
	<body>
		<h1>
			錯誤訊息: <%=request.getAttribute("message") %>
		</h1>
	</body>
</html>