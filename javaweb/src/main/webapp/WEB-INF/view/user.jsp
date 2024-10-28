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
		<form method="post" action="/javaweb/user/add">
			<fieldset>
				<legend>User 新增</legend>
				username: <input type="text" name="username" placeholder="請輸入 username" required /><p /> 
				password: <input type="text" name="password" placeholder="請輸入 password" required /><p /> 
				email: <input type="email" name="email" placeholder="請輸入 email" required /><p />
				role: <select name="role">
						<option value="ROLE_ADMIN">ADMIN</option>
						<option value="ROLE_USER">USER</option>
					  </select><p />
				<button type="reset">Reset</button>
				<button type="submit">Submit</button>	  
			</fieldset>
		</form>
		<p />
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