<%@page import="javaweb.model.dto.UserDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 得到 UserServlet 所傳來的資料
	List<UserDto> userDtos = (List<UserDto>)request.getAttribute("userDtos");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User</title>
	</head>
	<body>
		<div>
			<fieldset>
				<legend>User 列表</legend>
				<table border="1">
					<thead>
						<tr>
							<th>ID</th><th>帳號</th><th>郵件</th><th>action</th><th>角色(權限)</th>
						</tr>
					</thead>
					<% for(UserDto userDto : userDtos) { %>
						<tr>
							<td><%=userDto.getUserId() %></td>
							<td><%=userDto.getUsername() %></td>
							<td><%=userDto.getEmail() %></td>
							<td><%=userDto.getActive() %></td>
							<td><%=userDto.getRole() %></td>
						</tr>
					<% } %>
				</table>
			</fieldset>
		</div>
	</body>
</html>