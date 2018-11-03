<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <!-- 导入公共样式 -->
    <%@ include file="ap.jsp" %> 
    <script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="js/jquery.browser.js"></script>
	<script type="text/javascript" src="js/jquery.qrcode.min.js"></script>
	<script type="text/javascript" src="js/jquery.jokeer.js"></script>
    <title>二维码页面</title>
</head>
<style type="text/css">
.one{
    width:170px;
    height:120px;
    border:1px red solid;
    background-color: white;
    float:left;
}

p{
   font-size:1px;
   font-family:verdana;
   }
body{
background-color: gray;
}

</style>
<body id="body">
		<!--startprint-->
		<input style="width:150px;height:35px" onclick="preview()" value="打印二维码" type="button" />
		<div class="codeBox"></div>
		<!--endprint-->

</body>
<!-- <script language="javascript" src="LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object> -->
<script type="text/javascript" src="js/jquery.jokeer.js"></script>
	<script type="text/javascript">
	    //根据订单号查询所有的二维码信息
	    $(function () {
	    var id=request('id');
	    $.ajax({
        url: "codefindById.action?place_num="+id,
        type: "post",
        data: {"id":id},
        dataType: "json",
        async: !1,
        success: function(data) {
           var size=data.rows.length;
           for(var i=0;i<size;i++){
           var ArrCode = [size];//需要生成二维码的数据
           for(var j=0;j<size;j++){
           ArrCode[j]=data.rows[j].packe_num;
           }
			if(ArrCode) {
				var _html = "";
				for(var i = 0; i < ArrCode.length; i++) {
					var packnum = data.rows[i].packe_num;
				    var bao=packnum.substr(packnum.length-3);
				    _html+="<div class='one' id='code" + i + "' ><p>生产号："+data.rows[i].p_num+"</p><p>件数："+data.rows[i].p_number+"&nbsp;尺码："+data.rows[i].p_size+"</p><img id='img" + i + "'/><p>颜色："+data.rows[i].p_color+"&nbsp;包号："+bao+"</p></div>";
					//_html += "<div class='one'><table id='tw'><tr><td rowspan=5 id='code" + i + "'><img id='img" + i + "'/></td><td>颜色："+data.rows[i].p_color+"</td></tr><tr><td>尺寸："+data.rows[i].p_size+"</td></tr><tr><td>件数："+data.rows[i].p_number+"</td></tr><tr><td>包号："+bao+"</td></tr><tr><td>缸号："+data.rows[i].cylinder+"</td></tr><tr><td>生产号："+data.rows[i].p_num+"</td></tr><tr><td></td></tr></table></div>";
				}
				$(".codeBox").html("");
				$(".codeBox").html(_html);
				for(var i = 0; i < ArrCode.length; i++) {
					$('#code' + i).qrcode({
						width: 50,
						height: 50,
						correctLevel: 0,
						text: utf16to8(ArrCode[i])
					});
					var canvas=$('#code' + i).find('canvas').get(0);  
					var data = canvas.toDataURL('image/jpg');
				    $('#img'+i).attr('src',data) ;
					$('#code' + i).find('canvas').remove();
				}
				
			} else {
				console.log("输入失败");
			}

		
           
           
           }
        }
    });
});
		
		function utf16to8(str) {
			var out, i, len, c;
			out = "";
			
			len = str.length;
			for(i = 0; i < len; i++) {
				c = str.charCodeAt(i);
				if((c >= 0x0001) && (c <= 0x007F)) {
					out += str.charAt(i);
				} else if(c > 0x07FF) {
					out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
					out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
					out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
				} else {
					out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
					out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
				}
			}
			return out;
		}

		
		
		//proDuctCode(ArrCode);
		
		//打印
		function preview(){    
	        bdhtml=window.document.body.innerHTML;    
	        sprnstr="<!--startprint-->";    
	        eprnstr="<!--endprint-->";    
	        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
	        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
	        window.document.body.innerHTML=prnhtml;    
	        window.print();    
	}  
		 function print(){
			         $(".codeBox").jqprint({
			            debug:false,
			            importCSS:true,
			            printContainer:true,
			            operaSupport:false
			         });
			      }
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
</html>
