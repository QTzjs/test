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
    
    <title>提交录入</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  <!-- 调用微信js接口需要 -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/awardRotate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ui.js"></script>
	
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"><!-- 图标 -->
	
	<!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
	<!-- 导入jqueryWeui样式 -->

  </head>
  <script type="text/javascript">
	/* $(function(){ 
	  	// 防止浏览器返回 页面config功能报错
	     //防止返回历史记录
	    pushHistory();
	    function pushHistory() {
	        var state = {
	            title: "title",
	            url: "jsp/weixin/submit.jsp"    };
	        window.history.pushState(state, "title", "jsp/weixin/submit.jsp");
	        
	    };
	    window.onpopstate = function() {
	        location.href="jsp/weixin/sys.jsp";
	       
	    };
	    
	    
	});   */

	$(function (){
		var packe_num=request('packe_num');
		/* var packe_num="68LJF1804090001"; */
		//alert(packe_num);
		$.ajax({
			url:"WxPayfindByPack.action?packe_num="+packe_num,
			type:"post",
			dataType:"json",
			//async : false,//同步方式 
			success:function(data){
				var pack = data.pack;
				if(pack != undefined){
					var comList = data.comList;
					var yetList = data.yetList;
					/* console.log(pack);
					console.log(comList);
					console.log(yetList); */
					document.getElementById("baohao").innerHTML=pack.packe_num;
					document.getElementById("baohao1").value=pack.packe_num;
					document.getElementById("shengchanghao").innerHTML=pack.p_num;
					document.getElementById("dingdanhao1").value=pack.p_num;
					document.getElementById("shuliang").innerHTML=pack.p_number;
					document.getElementById("shuliang1").value=pack.p_number;
					document.getElementById("kuanshi").innerHTML=pack.girard;
					document.getElementById("chuanghao").innerHTML=pack.bed;
					document.getElementById("chima").innerHTML=pack.p_size;
					document.getElementById("yanse").innerHTML=pack.p_color;
					
					if(yetList!=undefined){
						var wwc="";
						for(var i=0;i<yetList.length;i++){
							wwc+="<div style=\"display: inline-block;  height: 35px ;width: 30%; \"><input type=\"radio\" name=\"id\" value=\""+yetList[i].id+"\"/>&nbsp;"+yetList[i].technology_name+"</div>";
						}
						document.getElementById("wei").innerHTML=wwc;
					}else{
						document.getElementById("wei").innerHTML="<font color=\"green\">此包已生产完成！</font>";
					}
					
					if(comList!=undefined){
						var ywc="";
						for(var i=0;i<comList.length;i++){
							ywc+="<div style=\"display: inline-block; height: 35px ;width: 30% ;\">"+comList[i].technology_name+"</div>";
						}
						document.getElementById("yi").innerHTML=ywc;
					}else{
						document.getElementById("yi").innerHTML="<font color=\"red\">此包还未加工任何一道工序！</font>";
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

</script>
<style>
	body{background-color:#eee;font-size: 17px; }
	#tou{background-color:#fff; width: 100%; text-align: left; height:55px;line-height: 50px; padding-top: 6px;}
	.context{background-color: #fff; margin-top: 20px;}
	.placeholder {
        margin: 5px;
        padding: 0 10px;
        background-color: #fff;
        height: 1.5em;
        line-height: 1.5em;
        text-align: left;
        color: #000;
        white-space:nowrap;overflow:hidden;text-overflow:ellipsis;
      }
      #gonghao1{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
	
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
	</div>
  	
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
	    
    </div>
    
<form id="frm" action="${pageContext.request.contextPath }/WxPaysub.action" name="myform" method="post" >
    <div class="context"  style="padding: 10px 0 ; padding-left: 10px;">
    <input type="hidden" name="packe_num" id="baohao1" value="">
    <input type="hidden" name="place_num" id="dingdanhao1" value="">
    <input type="hidden" name="job_number" value="${user.job_number }">
    <input type="hidden" name="user_name"  value="${user.user_name }">
    <input type="hidden" name="number" id="shuliang1" value="">
  		<div style="margin-bottom: 10px;">&nbsp;&nbsp;<i class="fa fa-puzzle-piece"></i>&nbsp;&nbsp;请选择本次完工的工序</div>
		<div id="wei" style="font-size: 0.87em;">
			
		</div>
	</div>
	    <div style="margin-bottom: 10px; margin-top: 20px; padding-left: 10px;">&nbsp;&nbsp;<i class="fa fa-check-square"></i>&nbsp;&nbsp;已完成的工序</div>
			<div id="yi" style="font-size: 0.87em; padding-left: 10px; ">
				
	  		</div>
	
	<input  style="margin: 30px 0;" type="button" name="submit1"   class="weui-btn weui-btn_primary" value="提交" onclick="check(this.form)">
	<!-- <a href="javascript:;" id="show-confirm" class="weui-btn weui-btn_primary" type="submit" >显示 Confirm</a> -->
 
  		
</form>
	<!-- jqueryWeui样式 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
	<script>
		function check(form){  
	         if(form.id.value==''){
	         	 $.alert("请选择要提交工序！", "警告！");
	         	 return false;
	         }
	         
	         $.confirm("您确定要提交这个工序吗,以及后续默认提交此道工序?", "确认提交?", function() {
	          $.toast("已提交工序!");
        	  document.myform.submit();
	        }, function() {
	          $.toast("已取消提交!", 'cancel');
	        });
	        
		}  

	</script>
  </body>
</html>
