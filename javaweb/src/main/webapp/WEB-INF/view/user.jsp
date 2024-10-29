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
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" action="/javaweb/user/add">
			<fieldset>
				<legend>User 新增</legend>
				帳號: <input type="text" name="username" placeholder="請輸入 username" required /><p /> 
				密碼: <input type="text" name="password" placeholder="請輸入 password" required /><p /> 
				電郵: <input type="email" name="email" placeholder="請輸入 email" required /><p />
				權限: <select name="role">
						<option value="ROLE_ADMIN">ADMIN</option>
						<option value="ROLE_USER">USER</option>
					  </select><p />
				<button type="reset" class="button-warning pure-button">Reset</button>
				<button type="submit" class="button-success pure-button">Submit</button>	  
			</fieldset>
		</form>
		<p />
		<div class="pure-form">
			<fieldset>
				<legend>User 列表</legend>
				<table class="pure-table pure-table-bordered">
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