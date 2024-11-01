<%@ page import="javaweb.model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		
		<!-- body content -->
		<div style="padding: 15px">
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
								<th>修改</th><th>刪除</th>
							</tr>
						</thead>
						<c:forEach var="userDto" items="${ userDtos }">
							<tr>
								<td>${ userDto.userId }</td>
								<td>${ userDto.username}</td>
								<td>${ userDto.email }</td>
								<td>${ userDto.active }</td>
								<td>${ userDto.role }</td>
								<td><a href="/javaweb/user/get?username=${ userDto.username }" class="button-secondary pure-button">修改</a></td>
								<td><a href="/javaweb/user/delete?userId=${ userDto.userId }" class="button-error pure-button">刪除</a></td>
							</tr>
						</c:forEach>
					</table>
				</fieldset>
			</div>
		</div>
	</body>
</html>