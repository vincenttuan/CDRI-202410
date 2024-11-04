<%@ page import="javaweb.model.dto.UserDto"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<%
Map<String, Double> rankings = (Map<String, Double>)request.getAttribute("salesRankingMap"); 
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Ranking</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	      google.charts.load('current', {'packages':['corechart']});
	      google.charts.setOnLoadCallback(drawChart);
	
	      function drawChart() {
	
	        var data = google.visualization.arrayToDataTable([
	          ['product name', 'sales total'],
	          
	          
	          
	        ]);
	
	        var options = {
	          title: 'My Daily Activities'
	        };
	
	        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	
	        chart.draw(data, options);
	      }
	    </script>
		
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
					<div id="piechart" style="width: 900px; height: 500px;"></div>
				</fieldset>
			</div>
		</div>
	</body>
</html>