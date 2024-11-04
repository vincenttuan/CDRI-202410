<%@ page import="javaweb.model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cart</title>
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
					<legend>${ userCert.username } 的 Cart 購物車</legend>
					<table class="pure-table pure-table-bordered">
						<thead>
							<tr>
								<th>訂單 Id</th><th>使用者 Id</th><th>訂單日期</th>
								<th>商品 Id</th><th>商品名稱</th><th>商品單價</th>
								<th>訂購數量</th><th>小計</th><th>訂單狀態</th>
							</tr>
						</thead>
						<c:forEach var="orderDto" items="${ orderDtos }">
							<tr>
								<td>${ orderDto.orderId }</td>
								<td>${ orderDto.userId }</td>
								<td>${ orderDto.orderDate }</td>
								<td>${ orderDto.productId }</td>
								<td></td>
								<td>${ orderDto.unitPrice }</td>
								<td>${ orderDto.quantity }</td>
								<td>${ orderDto.subtotal }</td>
								<td>${ orderDto.orderStatus }</td>
							</tr>
						</c:forEach>
					</table>
				</fieldset>
			</div>
		</div>
	</body>
</html>