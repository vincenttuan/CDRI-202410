<%@ page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Random random = new Random();
	int n1 = random.nextInt(10); // 0~9 的隨機數
	int n2 = random.nextInt(10); // 0~9 的隨機數
	int n3 = random.nextInt(10); // 0~9 的隨機數
	int n4 = random.nextInt(10); // 0~9 的隨機數 
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lotto電腦選號</title>
	</head>
	<body>
		<H1>
		<%=n1 %>
		<%=n2 %>
		<%=n3 %>
		<%=n4 %>
		</H1>
	</body>
</html>