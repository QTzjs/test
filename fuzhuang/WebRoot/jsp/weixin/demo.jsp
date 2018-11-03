<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'demo.jsp' starting page</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css"><!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css"><!-- 导入jqueryWeui样式 -->
	
	
	<link type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/WxPaydemo.action?biao=3" method="post">
    	<input type="text" name="packe_num" value="74LJF1804200009">
    	<input type="text" name="t_name" value="点位11111">
    	<input type="text" name="job_number" value="9528">
    	<input type="text" name="place_num" value="AAA02545">
    	<input type="text" name="user_name" value="zds">
    	<input type="text" name="number" value="10">
    	<input type="submit" value="tejiao">
    </form>
    <button onclick="demo()">测试</button>
    
    <!-- jqueryWeui样式 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$.alert("cao");
		// $.hideLoading();
		 
	});
	function demo(){
		$.hideLoading();
		
	}
	</script>
    
  </body>
</html>
