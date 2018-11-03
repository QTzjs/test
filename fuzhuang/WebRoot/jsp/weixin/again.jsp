<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>操作失败</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
	<!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/weui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/jquery-weui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/js/jqueryWeui/demos.css">
	<!-- 导入jqueryWeui样式 -->
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
   <script type="text/javascript">
  	$(function(){ 
		/*这里如果点击返回键的话直接退出微信浏览器，回到消息页面*/
	  	pushHistory();
	    function pushHistory() {
	        window.history.pushState({page : 'state1'},'state','#state1');
			window.history.pushState({page : 'state2'},'state','#state2');
	    };
	  	
	    window.onpopstate = function(event) {
	        if (event.state.page === 'state1') {
	        WeixinJSBridge.call('closeWindow');
	      }
	       
	    };
	    
	    
	});
	function fanhui(){
	    	/* window.location.href="http://www.0791youxi.com/fuzhuang/WxPayjump.action?urlx="+"jsp/weixin/sys.jsp"; */
	    	WeixinJSBridge.call('closeWindow');
	    }
  </script>
  </head>
  
  <body>
  	<div style="text-align: center; padding-top: 45%;">
	 	<i class="weui-icon-warn weui-icon_msg" ></i><br>
    	操作失败<br>
    	登录过期，请退出重新进入系统！
    </div>
    
    <a href="javascript:;" class="weui-btn  weui-btn_plain-primary weui-footer_fixed-bottom" onclick="fanhui()">退出系统</a>
    
    <!-- jqueryWeui样式 -->
	<script src="${pageContext.request.contextPath }/jsp/js/jqueryWeui/jquery-2.1.4.js"></script>
	<script src="${pageContext.request.contextPath }/jsp/js/jqueryWeui/fastclick.js"></script>
	<script src="${pageContext.request.contextPath }/jsp/js/jqueryWeui/jquery-weui.js"></script>
	<!-- jqueryWeui样式 -->
  </body>
</html>
