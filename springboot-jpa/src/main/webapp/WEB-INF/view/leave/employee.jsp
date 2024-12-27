<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>      
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Employee</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/leave/menu.jspf" %>
		${ employees }
		<!-- body content -->
		<div style="padding: 15px">
			
			<table class="pure-table pure-table-bordered">
				<thead>
					<tr>
						<th>Id</th><th>username</th><th>annualLeave</th><th>arrivalDate</th>
						<th>Projects</th><th>修改專案</th><th>Leaves</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="employee" items="${ employees }">
					<tr>
						<!-- 列表 Employee -->
						<td>${ employee.id }</td>
						<td>${ employee.username }</td>
						<td>${ employee.annualLeave }</td>
						<td>${ employee.arrivalDate }</td>
						<td>${ employee.projects }</td>
						<td><a href="/employee/${ employee.id }/project">修改專案</a></td>
						<td>${ employee.leaveRequests}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</body>
</html>