<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>提交错误</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
	<!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css"><!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css"><!-- 导入jqueryWeui样式 -->
	<!-- 导入jqueryWeui样式 -->
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
  	<%@include file="global.jsp" %><!-- 引入全局变量 -->
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
	    
	    var url=window.location.href;
  		var yuan = url.substring(url.indexOf("?yuan=") + 6,url.indexOf("#"));
  		var yuan1=decodeURI(yuan);
  		$.alert(yuan1);
  		document.getElementById("yuan").innerHTML=decodeURI(yuan);
	    
	    
	    
	    
	    
	});
	function fanhui(){
	    	window.location.href=host+"WxPayjump.action?urlx="+"jsp/weixin/sys.jsp";
	    	/* WeixinJSBridge.call('closeWindow'); */
	    }
  </script>
  </head>
  
  <body>
  	<div style="text-align: center; padding-top: 45%;">
	 	<i class="weui-icon-warn weui-icon_msg" ></i><br>
    	提交错误！<br><span id="yuan"></span>
    </div>
    
    <a href="javascript:;" class="weui-btn  weui-btn_plain-primary weui-footer_fixed-bottom" onclick="fanhui()">返回首页</a>
    
	<!-- jqueryWeui样式 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
	<!-- jqueryWeui样式 -->
  </body>
</html>
