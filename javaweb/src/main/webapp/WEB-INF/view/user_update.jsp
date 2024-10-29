<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User 修改</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" action="/javaweb/user/update?userId=">
			<fieldset>
				<legend>User 修改</legend>
				序號: <input type="text" name="userId" disabled /><p /> 
				帳號: <input type="text" name="username" disabled /><p /> 
				電郵: <input type="email" name="email" disabled /><p />
				狀態: <input type="checkbox" name="active" /><p />
				權限: <select name="role">
						<option value="ROLE_ADMIN">ADMIN</option>
						<option value="ROLE_USER">USER</option>
					  </select><p />
				<button type="reset" class="button-warning pure-button">Reset</button>
				<button type="submit" class="button-success pure-button">Submit</button>	  
			</fieldset>
		</form>
	</body>
</html>