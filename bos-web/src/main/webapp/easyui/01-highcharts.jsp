<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '01-layout.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- easyui -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/modules/exporting.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#test").highcharts({
		        title: {
		            text: '各浏览器份额'
		        },
		        series: [{
		            type: 'pie',
		            name: '浏览器访问量占比',
		            data: [
		                ['Firefox',   45.0],
		                ['IE',       26.8],
		                ['Safari',    8.5],
		                ['Opera',     6.2],
		                ['其他',   0.7]
		            ]
		        }]
		    });
		});
	</script>
  </head>
  
  <body>
  	<div id="test"></div>
  </body>
</html>
