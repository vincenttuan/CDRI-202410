<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>員工請假維護</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/leave/menu.jspf" %>
		
		<!-- body content -->
		<div style="padding: 15px">
			<!-- 員工專案維護 -->
			<sp:form class="pure-form" modelAttribute="leaveRequestDTO" method="post" action="/employee/${employeeDTO.id}/leave_request">
			    員工編號: ${employeeDTO.id}<p />
			    員工姓名: ${employeeDTO.username}<p />
			    假別: <sp:input path="type" required="required" /><p />
			    開始日: <sp:input type="date" path="startDate" required="required" /><p />
			    結束日: <sp:input type="date" path="endDate" required="required" /><p />
			    請假理由: <sp:input path="reason" /><p />
			    假單狀態: <sp:input path="status" required="required" /><p />
			    
			    <button type="submit">新增</button>
			</sp:form>

		</div>
	</body>
</html>