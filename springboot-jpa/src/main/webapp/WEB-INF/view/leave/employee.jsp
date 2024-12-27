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
		<!-- body content -->
		<div style="padding: 15px">
			
			<table class="pure-table pure-table-bordered">
				<thead>
					<tr>
						<th>編號</th><th>姓名</th><th>年假</th><th>到職日</th>
						<th>專案工作</th><th>請假紀錄</th>
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
						<td>
							<table>
								<tr>
									<c:forEach var="project" items="${ employee.projects}">
										<td>${ project.name }</td>
									</c:forEach>
									<td>
										<a href="/employee/${ employee.id }/project">修改</a>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<c:forEach var="leaveRequest" items="${ employee.leaveRequests}">
								<tr>
									<td>${ leaveRequest.type }</td>
									<td>${ leaveRequest.startDate }</td>
									<td>${ leaveRequest.endDate }</td>
									<td>${ leaveRequest.leaveDays }d</td>
									<td>${ leaveRequest.reason }</td>
									<td>${ leaveRequest.status }</td>
									<td>
										<a href="/employee/${ employee.id }/leave_request/${ leaveRequest.id }">修改</a>
									</td>
									<td>
										<a href="/employee/${ employee.id }/leave_request/delete/${ leaveRequest.id }">刪除</a>
									</td>
								</tr>
								</c:forEach>
								<tr>
									<td>
										<a href="/employee/${ employee.id }/leave_request">新增</a>
									</td>
									<td colspan="7"> </td>
								</tr>
							</table>
							
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</body>
</html>