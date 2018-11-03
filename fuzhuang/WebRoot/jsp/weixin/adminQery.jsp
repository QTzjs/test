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
    
    <title>返工</title>
      <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  <!-- 调用微信js接口需要 -->
	<!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
	<!-- 导入jqueryWeui样式 -->
  </head>
  <style>
  	body{background-color:#eee;font-size: 17px; }
	#tou{background-color:#fff; width: 100%; text-align: left; height:45px;line-height: 40px; padding-top: 6px;}
	.context{background-color: #fff; margin-top: 20px; padding: 20px 0 10px;}
	#shuju{margin: 0 auto; margin-top: 15px; width: 95%; text-align: center; font-size: 14px;}
	#shuju tr{height:35px;}
	#shuju th{background-color: #eee; font-size: 15px;}
	#biaoge{font-size: 14px;}
	#caozuo{width: 30px;}
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
		<div class="weui-flex">
	      <div class="weui-flex__item"><div class="placeholder" >包&nbsp;&nbsp;&nbsp;号：<span id="baohao"></span>${pack.packe_num }</div></div>
	    </div>
	  	<div class="weui-flex">
	      <div class="weui-flex__item"><div class="placeholder">生产号：<span id="shengchanghao"></span>${pack.p_num }</div></div>
	      <div class="weui-flex__item"><div class="placeholder">数&nbsp;&nbsp;&nbsp;量：<span id="shuliang"></span>${pack.p_number }</div></div>
	    </div>
	    <div class="weui-flex">
	      <div class="weui-flex__item"><div class="placeholder">款&nbsp;&nbsp;&nbsp;式：<span id="kuanshi"></span>${pack.girard }</div></div>
	      <div class="weui-flex__item"><div class="placeholder">床&nbsp;&nbsp;&nbsp;号：<span id="chuanghao"></span>${pack.bed }</div></div>
	    </div>
	    <div class="weui-flex">
	      <div class="weui-flex__item"><div class="placeholder">尺&nbsp;&nbsp;&nbsp;码：<span id="chima"></span>${pack.p_size }</div></div>
	      <div class="weui-flex__item"><div class="placeholder">颜&nbsp;&nbsp;&nbsp;色：<span id="yanse"></span>${pack.p_color }</div></div>
	    </div>
			
		
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
  	$(function (){
		var packe_num=request('packe_num');
		/* var packe_num="68LJF1804090001"; */
		//alert(packe_num);
		$.ajax({
			url:"WxPayfindByPack.action?packe_num="+packe_num+"&biao=1",
			type:"post",
			dataType:"json",
			//async : false,//同步方式 
			success:function(data){
				var pack = data.pack;
				var subList = data.subList;
				if(pack != undefined){
					/* console.log(pack);
					console.log(comList);
					console.log(yetList); */
					document.getElementById("baohao").innerHTML=pack.packe_num;
					document.getElementById("shengchanghao").innerHTML=pack.p_num;
					document.getElementById("shuliang").innerHTML=pack.p_number;
					document.getElementById("kuanshi").innerHTML=pack.girard;
					document.getElementById("chuanghao").innerHTML=pack.bed;
					document.getElementById("chima").innerHTML=pack.p_size;
					document.getElementById("yanse").innerHTML=pack.p_color;
					
					if(subList != undefined){
						var daima="<table id=\"shuju\"  border=\"1\" cellspacing=\"0\" bordercolor=\"#ccc\"><tr><td colspan=\"6\">该包所有的提交记录：<span id=\"count\"></span> 条</td></tr><tr class=\"diyi\"><th>时间</th><th>工序名称</th><th>工号</th><th>提交人</th><th>状态</th><th>操作</th></tr>";
						for(var i=0;i<subList.length;i++){
							var a = subList[i].sub_time;
							var b = subList[i].place_num;
							var c = subList[i].packe_num;
							daima += "<tr><td>"+a.substring(11)+"</td>";
							daima += "<td>"+subList[i].t_name+"</td>";
							daima += "<td>"+subList[i].job_number+"</td>";
							daima += "<td>"+subList[i].user_name+"</td>";
							if(subList[i].state == 1){
								daima += "<td><font>正常</font></td>";
							}else{
								daima += "<td><font color=\"red\">待批准</font></td>";
							}
							daima += "<td id=\"zuo"+subList[i].id+"\"><a href=\"javascript:fangong("+subList[i].id+")\" class=\"weui-btn weui-btn_mini weui-btn_plain-primary\">返工</a></td></tr>";
							
						}
						daima += "</table>";
					    document.getElementById("biaoge").innerHTML=daima;
				    	document.getElementById("count").innerHTML=subList.length;
					}
					
					/* alert(document.getElementById("dingdanhao1").val); */
				}else{
					$.toast('此包已删除！请返回主页重新扫码！', 'cancel');
				}	
				
			}
			
		});
	});
	//获取传入id
   function request(paras) {
            var url = location.href;
            var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
            var paraObj = { };
            for (var i = 0; j = paraString[i]; i++) {
                paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
            }
            var returnValue = paraObj[paras.toLowerCase()]; 
            if (typeof (returnValue) == "undefined") {
                return "";
            } else {
                return returnValue;
            }
    }
  	
	function fangong(id){
		$.confirm("您确认返工吗，确定了就不可更改了?", "确认返工?", function() {
			$.ajax({
				url:"subrecover.action?id="+id+"&biao=1",
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.fangong == undefined){
						$.toast('返工失败！', 'cancel');
					}else{
						$.toast('返工成功!');
						var oo="zuo"+id;
						document.getElementById(oo).parentNode.remove();
						var count=document.getElementById("count").innerHTML;
						document.getElementById("count").innerHTML=count-1;
					}
				}
			});
        }, function() {
          	$.toast('你取消了返工！', 'cancel');
        });
		
	}
	</script>
  </body>
  
</html>
