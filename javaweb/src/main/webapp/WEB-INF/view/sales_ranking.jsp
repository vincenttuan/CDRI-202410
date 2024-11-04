<%@ page import="javaweb.model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Ranking</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		
		<!-- body content -->
		<div style="padding: 15px">
			<div class="pure-form">
				<fieldset>
					<legend>銷售排行</legend>
					${ salesRankingMap }
				</fieldset>
			</div>
		</div>
	</body>
</html>