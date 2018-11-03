<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<title>服装生产管理</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="keywords" content="虹领巾-让你买的放心" />
<meta name="description" content="虹领巾-让你买的放心" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/js/awardRotate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ui.js"></script>

<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css"><!-- 导入jqueryWeui样式 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css"><!-- 导入jqueryWeui样式 -->


<%@include file="global.jsp" %><!-- 引入全局变量 -->
<script type="text/javascript">
	$(function(){ 
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

	$(document).ready(function() {
		$.ajax({
			url:"WxPaygetAcc.action",
			type:"post",
			dataType:"json",
			data:{urlx:"jsp/weixin/sys.jsp"},
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
			                     window.location.href=host+"jsp/weixin/submit.jsp?packe_num="+result;
			                }
			                  
			            });  
			        };//end_document_scanQRCode 
			        //扫描二维码  
			        document.querySelector('#scanQRCode1').onclick = function() { 
			            wx.scanQRCode({  
			                needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
			                scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
			                success : function(res) {  
			                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果 
			                    
			                     window.location.href=host+"jsp/weixin/adminQery.jsp?packe_num="+result;
			                }
			                  
			            });  
			        };//end_document_scanQRCode 
			        
				       	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				});
			}
	 	});
	});
	
	function cx(){
		window.location.href=host+"WxPayjump.action?urlx="+"jsp/weixin/query.jsp";
	}
	
	/* function jiazai(){
		document.getElementById("xianshi").innerHTML="<img src=\"../../image/jiazai.png\"  width=\"100%\">";
	} */
	
</script>



</head>
<style>
	body { background-color:#eee;font-size: 18px; }
	.tou{background-color: #33CCFF; width: 100%; text-align: left; color: #fff; height:45px;line-height: 45px;}
	.body{background-color: #fff; margin-top: 5px; padding: 8px 0; height: 160px; width: 100%;}
	.body1{width: 70%; margin: 0 auto;}
	/* #scanQRCode{  margin:0 auto 10px;  width:60%; background-repeat: no-repeat;height: 45%;} */
	/* #cx{ margin:10px auto 0;  width:60%; background-repeat: no-repeat;height: 45%;} */
	img { pointer-events: none; }/*禁止浏览器点击放大图片，禁止浏览器长按弹出保存图片*/
	#jiazai{display: none; }
	#gonghao1{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
	
</style>
<body>
<div class="weui-loadmore" id="jiazai">
  <i class="weui-loading"></i>
  <span class="weui-loadmore__tips">正在加载</span>
</div>
<div class="tou">
	<div class="weui-flex">
		<c:if test="${user.user_level == 1 }">
			<div class="weui-flex__item">&nbsp;普通用户:${user.user_name }</div>
		</c:if>
		<c:if test="${user.user_level == 2 }">
			<div class="weui-flex__item">&nbsp;管理员:${user.user_name }</div>
		</c:if>
		<div class="weui-flex__item" id="gonghao1">工号:${user.job_number }</div>
	</div>
</div>
<div>
	<img alt="" src="${pageContext.request.contextPath }/image/zhuye.jpg" width="100%">
</div>
<div class="body">
	<div class="body1">
		<!-- <div id="scanQRCode"><img src="../../image/gphs.png"  width="100%"></div>
		<div id="cx" onclick="cx()" ><img src="../../image/gpcx.png" width="100%"></div> -->
		<a href="javascript:;" id="scanQRCode" class="weui-btn weui-btn_warn">工票回收</a>
		<a href="javascript:;" onclick="cx()" class="weui-btn weui-btn_primary">工票查询</a>
		<c:if test="${user.user_level == 2 }">
			<a href="javascript:;" id="scanQRCode1" class="weui-btn weui-btn_plain-primary">返工</a>
		</c:if>
		
	</div>
</div>

<!-- jqueryWeui样式 -->
<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
<!-- jqueryWeui样式 -->
<!-- 如果使用了某些拓展插件还需要额外的JS -->
<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>


</body>

</html>