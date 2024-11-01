<%@ page import="javaweb.model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Update Password</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		
		<div style="padding: 15px">
			<form class="pure-form" method="post" action="/javaweb/user/update/password">
				<fieldset>
					<legend>修改密碼</legend>
					帳號: ${ userCert.username }<p /> 
					原密碼: <input type="text" name="oldPassword" placeholder="請輸入原密碼" required /><p /> 
					新密碼: <input type="text" name="newPassword" placeholder="請輸入新密碼" required /><p /> 
					<button type="reset" class="button-warning pure-button">Reset</button>
					<button type="submit" class="button-success pure-button">Update</button>	  
				</fieldset>
			</form>
		</div>
	</body>
</html>