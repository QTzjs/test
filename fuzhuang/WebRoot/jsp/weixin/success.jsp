<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交成功</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css"><!-- 导入jqueryWeui样式 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css"><!-- 导入jqueryWeui样式 -->
	<%@include file="global.jsp" %><!-- 引入全局变量 -->
  </head>
  <script type="text/javascript">
  
	$(function(){ 
		pushHistory();
	    function pushHistory() {
	        window.history.pushState({page : 'state1'},'state',window.location.href+'#state1');
			window.history.pushState({page : 'state2'},'state',window.location.href+'#state2');
	    };
	    window.onpopstate = function(event) {
	        if (event.state.page === 'state1') {
		        WeixinJSBridge.call('closeWindow');
	      	}
	    };
		
		var url=window.location.href;
  		var t_name = url.substring(url.indexOf("t_name=") + 7,url.indexOf(";place_num="));
  		var p_num = url.substring(url.indexOf(";place_num=") + 11,url.indexOf(";packe_num="));
  		var pack = url.substring(url.indexOf(";packe_num=") + 11,url.indexOf("#state1#state2"));
  		var packe_num = pack.substring(pack.length-4);
  		$.alert(packe_num+"包的"+decodeURI(t_name)+"工序提交成功!");
  		/* $.hideLoading(); */
		$.ajax({
			url:"WxPaygetSelect.action",
			type:"post",
			data:{place_num:p_num},
			dataType:"json",
			success:function(data){
				$("#select1").html("");
				$("#select2").html("");
				var pList=data.pList;
				var ptList = data.ptList;
				var parents = $("#select1");
				var pare = $("#select2");
				var gongxu=decodeURI(t_name);
				for(var i=0;i<pList.length;i++){
					if(pList[i].plan_num == p_num){
						parents.append("<option value=\""+pList[i].plan_num+"\" selected>"+pList[i].plan_num+"</option>");
					}else{
						parents.append("<option value=\""+pList[i].plan_num+"\">"+pList[i].plan_num+"</option>");
					}
				}
				for(var j=0;j<ptList.length;j++){
					if(ptList[j].technology_name == gongxu){
						pare.append("<option value=\""+ptList[j].technology_name+"\" selected>"+ptList[j].technology_name+"</option>");
					}else{
						pare.append("<option value=\""+ptList[j].technology_name+"\">"+ptList[j].technology_name+"</option>");
					}
				}
				
				
			}
		});
  		
		$.ajax({
			url:"WxPaygetAcc.action",
			type:"post",
			dataType:"json",
			data:{urlx:"jsp/weixin/success.jsp"},
			async : false,//同步方式 
			success:function(data){
			    var timeStamp = data.s.timeStamp;
			    var nonceStr = data.s.noncestr;
			    var signature = data.s.signature;
			    
				wx.config({
				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: appid, // 必填，公众号的唯一标识
				    timestamp:timeStamp, // 必填，生成签名的时间戳
				    nonceStr: nonceStr, // 必填，生成签名的随机串
				    signature: signature,// 必填，签名，见附录1
				    jsApiList: [ 'checkJsApi', 
			                        'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				
				 wx.ready(function(){
	                wx.checkJsApi({  
			            jsApiList : ['scanQRCode'],  
			            success : function(res) {  
			            }  
			        }); 
						  
			        //扫描二维码  
			        document.querySelector('#scanQRCode').onclick = function() { 
			            wx.scanQRCode({  
			                needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
			                scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
			                success : function(res) {  
			                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
			                    console.log(result);
			                    console.log($("#baohao1").val());
			                    $("#baohao1").val(result);
			                    console.log($("#baohao1").val());
			                    console.log($("#job_number").val());
			                    console.log($("#user_name").val());
			                    console.log($("#select1").val());
			                    console.log($("#select2").val());
			                    document.myform.submit();
			                    $.showLoading();
			                    setTimeout(function() {
						          $.hideLoading();
						        }, 2000)
			                }
			            });  
			        };//end_document_scanQRCode 
			        
				       	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				});
				
			}
	 	});
	});
	
	function demo(){
		//console.log("测试");
		$("#baohao1").val("87BLJ1805210003");
        console.log($("#baohao1").val());
        $("#job_number").val("9528")
        console.log($("#job_number").val());
        $("#user_name").val("张")
        console.log($("#user_name").val());
        console.log($("#select1").val());
        console.log($("#select2").val());
        document.myform.submit();
	}
	
	function changeparent(v){
		$("#select2").html("");
		$.ajax({
			url:"WxPaygetSelect.action?biao=2",
			type:"post",
			data:{place_num:v},
			dataType:"json",
			success:function(data){
				$("#select2").html("");
				var ptList = data.ptList;
				var pare = $("#select2");
				for(var j=0;j<ptList.length;j++){
						pare.append("<option value=\""+ptList[j].technology_name+"\">"+ptList[j].technology_name+"</option>");
				}
			}
		});
	}
	
	function fanhui(){
	    	window.location.href=host+"WxPayjump.action?urlx="+"jsp/weixin/sys.jsp";
	    }
  </script>
  <body>
 
  
 	<div style="text-align: center; padding-top: 45%;">
	  <i class="weui-icon-success weui-icon_msg" ></i><br>
	    提交成功!
	<%-- <form id="frm" action="${pageContext.request.contextPath }/WxPaysub.action?biao=3" name="myform" method="post" > --%>
	<form id="frm" action="${pageContext.request.contextPath }/WxPaysub.action?biao=3" name="myform" method="post" >
		<input type="hidden" name="packe_num" id="baohao1" value="">
	    <input type="hidden" name="job_number" id="job_number" value="${user.job_number }">
	    <input type="hidden" name="user_name" id="user_name"  value="${user.user_name }">
	    <input type="hidden" name="number" id="shuliang1" value="">
	  <div class="weui-cells">
		  <div class="weui-cell weui-cell_select weui-cell_select-after">
	        <div class="weui-cell__hd">
	          <label for="" class="weui-label">生产号</label>
	        </div>
	        <div class="weui-cell__bd">
	          <select class="weui-select" name="place_num" id="select1" onchange="changeparent(this.value)">
	          </select>
	        </div>
	      </div>
	      <div class="weui-cell weui-cell_select weui-cell_select-after">
	        <div class="weui-cell__hd">
	          <label for="" class="weui-label">提交工序</label>
	        </div>
	        <div class="weui-cell__bd">
	          <select class="weui-select" name="t_name" id="select2">
	          </select>
	        </div>
	      </div>
	   </div>
    </form>
    </div>
    <a href="javascript:;" id="scanQRCode" class="weui-btn weui-btn_warn weui-footer_fixed-bottom"  style="margin-bottom: 65px;">工票回收</a>
    <a href="javascript:;" class="weui-btn  weui-btn_plain-primary weui-footer_fixed-bottom" onclick="fanhui()">返回首页</a>
    
    <!-- jqueryWeui样式 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<!-- jqueryWeui样式 -->
	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
  </body>
</html>
