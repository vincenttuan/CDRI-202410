<%@page import="javaweb.model.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserDto userDto = (UserDto)request.getAttribute("userDto");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User 修改</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" action="/javaweb/user/update">
			<fieldset>
				<legend>User 修改</legend>
				序號: <input type="text" name="userId" value="<%=userDto.getUserId() %>" readonly /><p /> 
				帳號: <input type="text" name="username" value="<%=userDto.getUsername() %>" readonly /><p /> 
				電郵: <input type="email" name="email" value="<%=userDto.getEmail() %>" readonly /><p />
				狀態: <input type="text" name="active" value="<%=userDto.getActive() %>" /><p />
				權限: <input type="text" name="role" value="<%=userDto.getRole() %>"><p />
				<button type="submit" class="button-success pure-button">Update</button>	  
			</fieldset>
		</form>
	</body>
</html>