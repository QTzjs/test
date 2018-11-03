<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/style/common.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/checkboxJs.js"></script>
<script src="${pageContext.request.contextPath}/js/waitDailog.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
    
    <title>My JSP 'welcome.jsp' starting page</title>
    <script>
    	
    </script>
    
   <style>
   	#img{
   		margin:0 auto;
   		height:300px;
   		width:500px;
   		text-align:center;
   		padding-top: 150px;
   		
   	}
   	#font{
   		margin:0 auto;
   		color:#333;
   		text-align:center;
   		font-size:60px;
   		font-weight: bold;
   		padding-top: 200px;
   		
   	}
   </style>
	
  </head>
  
  <body style="background:#e9e3e3">
    <div id="img"><img height="300" width="400" src="${pageContext.request.contextPath}/jsp/Images/welcome.png"></img></div>
    <div id="font"><p>秋天文化传媒欢迎您</p></div>
  </body>
</html>
