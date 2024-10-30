<%@ page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	Random random = new Random(); // 物件變數
	int getLotto() { // 物件方法
		int n = random.nextInt(10);
		return n;
	}
%>
    
<%
	//Random random = new Random(); // 區域變數
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
		<h1>
			<%=getLotto() %>
		</h1>
		<h1>
			<%=session.getId() %>
		</h1>
		
	</body>
</html>

