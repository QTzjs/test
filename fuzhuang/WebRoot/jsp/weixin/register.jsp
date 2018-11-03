<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>激活页面</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 引入jQuery Mobile框架配置文件 -->
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
	<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
	<!-- 引入jQuery Mobile框架配置文件 -->
	
  </head>
  <style>
  	
  	input::-webkit-outer-spin-button,           
  	input::-webkit-inner-spin-button{-webkit-appearance: none !important;}
  	input[type="number"]{-moz-appearance:textfield;}
  	#pageone{background: url('../../image/jihuo.jpg') -497px 0px no-repeat;}
  	table {color: #FFF}
  </style>
  <body><!-- rel="external" data-ajax="false" -->
  	<div data-role="page" id="pageone">
  		<div data-role="content">
		    <form action="${pageContext.request.contextPath }/WxPayregister.action" method="post" enctype="multipart/form-data" data-ajax="false" >
		    	<table align="center">
		    		<tr>
		    			<td align="right">手机号：</td>
		    			<td align="left"><input type="number" name="user_account" required="required"   placeholder="请输入手机号"/></td>
		    		</tr>
		    		<tr>
		    			<td align="right">工号：</td>
		    			<td align="left"><input type="text" name="job_number" required="required"  placeholder="请输入工号"/></td>
		    		</tr>
		    		<tr align="center">
		    			<td colspan="2"><font color="red">${sessionScope.erro }</font></td>
		    		</tr>
		    		<tr align="center"  >
		    			<td colspan="2"><input type="submit" value="激活"/></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
  </body>
</html>
