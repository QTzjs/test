<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工票查询</title>
      <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
	<!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
	<!-- 导入jqueryWeui样式 -->
  </head>
  <style>
  	body{background-color:#eee;font-size: 17px; }
	#tou{background-color:#fff; width: 100%; text-align: left; height:45px;line-height: 40px; padding-top: 6px;}
	.context{background-color: #fff; margin-top: 20px; text-align: center; padding: 20px 0 10px;}
	#shuju{margin: 0 auto; margin-top: 15px; width: 95%; text-align: center; font-size: 14px;}
	#shuju tr{height:35px;}
	#shuju th{background-color: #eee; font-size: 16px;}
  </style>
  <body>
  	<div class="weui-flex" id="tou" >
	  <c:if test="${user.user_level == 1 }">
			<div class="weui-flex__item">&nbsp;普通用户:${user.user_name }</div>
		</c:if>
		<c:if test="${user.user_level == 2 }">
			<div class="weui-flex__item">&nbsp;管理员:${user.user_name }</div>
		</c:if>
	  <div class="weui-flex__item">&nbsp;工号:${user.job_number }</div>
	  <input type="hidden" id="gonghao" value="${user.job_number }">
	</div>
	<!-- <div><font color="red">注：只能查询登录本人自己的工票</font></div> -->
	
	<div class="context">
		<!-- <table style="margin: 0 auto; width: 75%; height: 40px; text-align: center;" border="1" cellspacing="0" bordercolor="#ccc">
			<tr>
				<td><input type="radio" name="time" value="1" onclick="chaxun(1)">当天工票</td>
				<td><input type="radio" name="time" value="2" onclick="chaxun(2)">本周工票</td>
				<td><input type="radio" name="time" value="3" onclick="chaxun(3)">本月工票</td>
			</tr>
		</table> -->
			<div class="weui-cell" style="background-color: #eee; margin-bottom: 10px;">
			    <div class="weui-cell__hd"><label for="" class="weui-label">日期</label></div>
			    <div class="weui-cell__bd">
			      <input class="weui-input"  id="time" type="date" value="">
			    </div>
		    </div>
		  <div id="chaxun">
		  	<a href="javascript:chaxun(this);" class="weui-btn weui-btn_mini weui-btn_primary">查询</a>
		  </div>
		  
		<div style="margin-top: 10px;">工票张数：<span id="zhang">0</span>，合计包数：<span id="bao">0</span>，合计件数：<span id="jiang">0</span></div>
		<div id="biaoge"></div>
	</div>
	
    
	<!-- jqueryWeui样式 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
  	<script type="text/javascript">
  	$(function(){
  		var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
  		$("#time").val(currentdate);
  	});
    function chaxun(event){
    	
    	var daima="<table id=\"shuju\"  border=\"1\" cellspacing=\"0\" bordercolor=\"#ccc\"><tr class=\"diyi\"><th>日期</th><th>生产号</th><th>包号</th><th>数量</th><th>工序名称</th><th>操作</th></tr>";
    	var data = $("#time").val();
    	var job_number = $("#gonghao").val();
    	if(data == undefined || data == "" ){
    		$.alert("请选择日期再查询！", "警告！");
    	}else{
    		/* job_number="9528"; */
    		document.getElementById("chaxun").innerHTML="<a href=\"javascript:;\" class=\"weui-btn weui-btn_mini weui-btn_primary weui-btn_loading\">正在加载...</a>";
			$.ajax({
				url:"subgetInfo.action?job_number="+job_number+"&data="+data,
				type:"post",
				dataType:"json",
				success:function(data){
					/* console.log(data.data); */
					if(data.data  == undefined){
						$.alert("你当日未提交工序！");
						document.getElementById("biaoge").innerHTML="";
						document.getElementById("zhang").innerHTML="0";
					    document.getElementById("bao").innerHTML="0";
					    document.getElementById("jiang").innerHTML="0";
						document.getElementById("chaxun").innerHTML="<a href=\"javascript:chaxun(this);\" class=\"weui-btn weui-btn_mini weui-btn_primary\">查询</a>";
					}else{
						var shuju=data.data;
						for(var i=0;i<shuju.length;i++){
							var a = shuju[i].sub_time;
							var b = shuju[i].place_num;
							var c = shuju[i].packe_num;
							daima += "<tr><td>"+a.substring(11)+"</td>";
							daima += "<td>"+shuju[i].place_num+"</td>";
							daima += "<td>"+c.substring(c.length-3)+"</td>";
							daima += "<td>"+shuju[i].number+"</td>";
							daima += "<td>"+shuju[i].t_name+"</td>";
							if(shuju[i].state == 1){
								daima += "<td id=\"zuo"+shuju[i].id+"\"><a href=\"javascript:shengqing("+shuju[i].id+")\" class=\"weui-btn weui-btn_mini weui-btn_plain-primary\">申请作废</a></td></tr>";
							}else{
								daima += "<td id=\"zuo"+shuju[i].id+"\"><font color=\"red\">待批准</font></td></tr>";
							}
						}
						daima += "</table>";
					    document.getElementById("biaoge").innerHTML=daima;
					    document.getElementById("zhang").innerHTML=shuju.length;
					    document.getElementById("bao").innerHTML=shuju.length;
					    document.getElementById("jiang").innerHTML=data.bao;
					    document.getElementById("chaxun").innerHTML="<a href=\"javascript:chaxun(this);\" class=\"weui-btn weui-btn_mini weui-btn_primary\">查询</a>"
						
					}
					
					
				}
			
			})
		}
	}
	function shengqing(id){
	     
	     $.confirm("您确认申请作废吗，申请了就不可更改了?", "确认申请?", function() {
	     	$.ajax({
					url:"subtovoid.action?id="+id,
					type:"post",
					dataType:"json",
					success:function(data){
						if(data.ok == undefined){
							$.toast("申请提交失败！");
						}else{
							$.toast("申请提交成功，待管理员确认！");
							var oo="zuo"+id;
							document.getElementById(oo).innerHTML="<font color=\"red\">待批准</font>";
						}
					}
				});
	          
	        }, function() {
	          $.toast("已取消申请!", 'cancel');
	        });
		
	}
	</script>
  </body>
  
</html>
