<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>員工專案維護</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/leave/menu.jspf" %>
		
		<!-- body content -->
		<div style="padding: 15px">
			<!-- 員工專案維護 -->
			<sp:form class="pure-form" modelAttribute="employeeDTO" method="post" action="/employee/${employeeDTO.id}/project">
			    員工編號: ${employeeDTO.id}<p />
			    員工姓名: ${employeeDTO.username}<p />
			    專案項目: <sp:checkboxes items="${projectDTOs}" path="projects" itemLabel="name" itemValue="id" /><p />
			    <button type="submit">修改</button>
			</sp:form>

		</div>
	</body>
</html>