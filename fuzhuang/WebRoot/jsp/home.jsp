<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <%@ include file="ap.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/echarts/echarts.min.js"></script>
</head>
<body>
<div class="topjui-container">
    <div class="topjui-row">
        <div class="topjui-col-md12">
            <div data-toggle="topjui-echarts"
                 data-options="id:'lineChart',
                 url:'../json/echarts/line.json',
                 width:900,height:600"></div>
        </div>
    </div>
</div>
</body>
</body>
</html>

