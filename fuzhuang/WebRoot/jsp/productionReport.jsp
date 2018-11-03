<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工生产计件报表 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="keywords" content="报表">
	<meta http-equiv="description" content="员工生产报表">
	<link rel="stylesheet" type="text/css" href="../css/fSelect.css" ><!-- 下拉多选框 -->
	<link rel="stylesheet" type="text/css" href="../css/demo.css"><!-- 下拉多选框 -->
    <script src="./js/grhtml5-6.5-min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script><!-- 下拉多选框 -->
	<script src="../js/fSelect.js"></script><!-- 下拉多选框 -->
    <script language="javascript" type="text/javascript">
    	$(function(){
    		//姓名员工号初始化
        	$.ajax({
        		url:'usergetAllUserList.action',
        		dataType:'json',
        		success:function(data){
        			var list = data.list;
        			var placeList = data.listPlace;
        			var users="<select class=\"demo\" multiple=\"multiple\">";
        			var job_numbers = "<select class=\"job_number\" multiple=\"multiple\">";
        			var place_nums = "<select class=\"place_num\" multiple=\"multiple\">";
        			var styles = "<select class=\"style\" multiple=\"multiple\">";
        			for(var i=0;i<list.length;i++){
        				var usre=list[i];
        				users+="<option value=\""+usre.user_name+"\">"+usre.user_name+"</option>";
        				job_numbers+="<option value=\""+usre.job_number+"\">"+usre.job_number+"</option>";
        			}
        			for(var i=0;i<placeList.length;i++){
        				var place = placeList[i];
        				place_nums+="<option value=\""+place.plan_num+"\">"+place.plan_num+"</option>";
        				styles+="<option value=\""+place.style+"\">"+place.style+"</option>";
        			}
        			users+="</select>";
        			job_numbers+="</select>";
        			place_nums+="</select>";
        			styles+="</select>";
        			document.getElementById("users").innerHTML=users;
        			document.getElementById("job_numbers").innerHTML=job_numbers;
        			document.getElementById("place_nums").innerHTML=place_nums;
        			document.getElementById("styles").innerHTML=styles;
        			$('.demo').fSelect();//下拉多选框初始化
        			$('.job_number').fSelect();//下拉多选框初始化
        			$('.place_num').fSelect();//下拉多选框初始化
        			$('.style').fSelect();//下拉多选框初始化
        		}
        	});
    	});
    	/*报表初始化方法 API 实现*/
        function window_onload() {
            var reportURL = "./grf/demo.grf",
                dataURL = "subReport.action",
                options = getQueryString("options"),
                reportViewer = window.rubylong.grhtml5.insertReportViewer("report_holder", reportURL, dataURL);

            /* document.title += getQueryString("title"); */ //设置当前网页标题

            if (options) {
                options = options.split(";");
                options.forEach(function (item) {
                    reportViewer.options[item] = true;
                });
            }
            reportViewer.start();
        }
        /*报表初始化方法 API 实现*/
        
    	/*筛选按钮方法实现*/
        function screen(){
         	var input = /^[\s]*$/;
        	var usre_name1=$("#user_name").text();
        	var job_number=$("#job_number").text();
        	var place_num=$("#place_num").text();
        	var style=$("#style").text();
        	var time1=$("#time1").val();
        	var time2=$("#time2").val();
        	var htt = "";
        	if(usre_name1 != "空"){
        		htt+="&user_name="+usre_name1;
        	}
        	if(job_number != "空"){
        		htt+="&job_number="+job_number;
        	}
        	if(place_num != "空"){
        		htt+="&place_num="+place_num;
        	}
        	if(style != "空"){
        		htt+="&style="+style;
        	}
        	if(!(input.test(time1))){
        		htt+="&time1="+time1;
        	}
        	if(!(input.test(time2))){
        		htt+="&time2="+time2;
        	}
        	if(input.test(htt)){//如果条件htt为空或者空格，则不添加条件加载出报表
        		var reportURL = "./grf/demo.grf",
	                dataURL = "subReport.action",
	                options = getQueryString("options"),
	                reportViewer = window.rubylong.grhtml5.insertReportViewer("report_holder", reportURL, dataURL);
	
	            /* document.title += getQueryString("title"); */ //设置当前网页标题
	
	            if (options) {
	                options = options.split(";");
	                options.forEach(function (item) {
	                    reportViewer.options[item] = true;
	                });
	            }
	            reportViewer.start();
	            scrollTo(0,0);
	            
        	}else{//如果条件htt不 为空或者空格，则要添加条件加载出报表
        		var reportURL = "./grf/demo.grf",
	                dataURL = "subReport.action?biao=1"+htt,
	                options = getQueryString("options"),
	                reportViewer = window.rubylong.grhtml5.insertReportViewer("report_holder", reportURL, dataURL);
	
	            /* document.title += getQueryString("title"); */ //设置当前网页标题
	
	            if (options) {
	                options = options.split(";");
	                options.forEach(function (item) {
	                    reportViewer.options[item] = true;
	                });
	            }
	            reportViewer.start();
	            scrollTo(0,0);
	            
        	}
        	/* if(htt.charAt(0)=="&"){
        		htt=htt.substring(1,htt.length);
        	} */
        }
        /*筛选按钮方法实现*/
		
        /*获取地址栏参数的方法实现*/
        function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return decodeURI(r[2]); //return unescape(r[2]);
            }
            return null;
        }
        /*获取地址栏参数的方法实现*/
        
        /*打印按钮方法实现*/
		function preview(){    
	        bdhtml=window.document.body.innerHTML;    
	        sprnstr="<!--startprint-->";    
	        eprnstr="<!--endprint-->";  
	        
	        //保存打印时不需要的数据
		    var beforePrnHtml = bdhtml.substr(0,bdhtml.indexOf(sprnstr)+17);
		    var afterPrnHtml = bdhtml.substr(bdhtml.indexOf(eprnstr));
		    
	        //提取欲打印的数据
	        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
	        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
	        window.document.body.innerHTML=prnhtml;
	        window.print();   //打印 
	        
	        //恢复页面，可再次打印
    		window.document.body.innerHTML=beforePrnHtml + prnhtml + afterPrnHtml;
		}
		/*打印按钮方法实现*/
		
        /*导出Excel表按钮方法实现*/
        function demo(){
        	document.getElementsByTagName("table")[2].border="1";
        	// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
            var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[2].outerHTML + "</body></html>";
            // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
            var blob = new Blob([html], { type: "application/vnd.ms-excel" });
            // 利用URL.createObjectURL()方法为a元素生成blob URL
            /* a.href = URL.createObjectURL(blob); */
            $("#demo").attr('href',URL.createObjectURL(blob));
            // 设置文件名，目前只有Chrome和FireFox支持此属性
            /* a.download = "学生成绩表.xls"; */
            $("#demo").attr('download',"员工生产计件报表.xls");
        	
        }
        /*导出Excel表按钮方法实现*/
        
    </script>
    <style>
    	*{margin: 0;padding: 0}
    	
    	/*左侧div*/
    	.div_left{border: 1px solid #fff; float: left; width: 20%;}
    	.div_left .div_screen{position: fixed;left: 5px;top: 10px;width: 250px;text-align:center;}
    	
    	/*左侧筛选表格table*/
    	.div_left .div_screen .screen_table{width: 100%; height: 280px;font-size: 14px;}
    	.div_left .div_screen .screen_table input{border-radius:3px; border: 1px solid #ccc; height: 20px; outline:none;
    	width: 180px;}
    	
    	/*左侧筛选按钮*/
    	.div_left .div_screen .screen_btn{background: gold;border: none;border-radius: 40px 10px; width: 80px;height: 
    	40px;color: #428bca; font-weight: bold; font-size: 18px;outline:none;cursor:pointer;margin-bottom: 15px;}
    	
    	/* 左侧打印按钮 */
    	.div_left .div_screen .printing{width:90px;height:35px;cursor:pointer;background: #09F;border: 0;color: #fff;
    	outline:none;}
    
    	/* 打印后的页面样式设置 */
	   @media print{}
	   
	   /*左侧导出Excel按钮*/
	  .div_left .div_screen a{display: inline-block;height: 35px;width: 90px; background: red;line-height: 35px;
	  background: #6C3;font-size: 14px;color: #fff;cursor:pointer;text-decoration:none;}
	   
	  /*右侧div*/
	  .div_right{float: left; width: 79%;padding-bottom: 10px;}
	</style>
</head>
<body onload="window_onload()">
	<div class="div_left">
		<div class="div_screen">
			<table class="screen_table">
				<tr>
					<td style="width: 30%;" align="right">姓名：</td>
					<td id="users">
						<!-- <input type="text" id="user_name"> -->
						<!-- <select class="demo" multiple="multiple">
						    <optgroup label="Languages">
						        <option value="cp">C++</option>
						        <option value="cs">C#</option>
						        <option value="oc">Object C</option>
						        <option value="c">C</option>
						    </optgroup>
						    <optgroup label="Scripts">
						        <option value="js">JavaScript</option>
						        <option value="php">PHP</option>
						        <option value="asp">ASP</option>
						        <option value="jsp">JSP</option>
						    </optgroup>
						</select> -->
					</td>
				</tr>
				<tr>
					<td align="right">员工号：</td>
					<td id="job_numbers">
						<!-- <input type="text" id="job_number" autocomplete="off"> -->
					</td>
				</tr>
				<tr>
					<td align="right">生产号：</td>
					<td id="place_nums">
						<!-- <input type="text" id="place_num" autocomplete="off"> -->
						
					</td>
				</tr>
				<tr>
					<td align="right">款式：</td>
					<td id="styles">
						<!-- <input type="text" id="style" autocomplete="off"> -->
					</td>
				</tr>
				<tr>
					<td align="right">开始于：</td>
					<td><input type="date" id="time1"></td>
					
				</tr>
				<tr>
					<td align="right">结束于：</td>
					<td><input type="date" id="time2"></td>
				</tr>
				<tr>
					<td colspan="2" style="color: red;">注：如需结算9月1号（包含1号当天）到30号（包含30号当天）的工资，选择开始于9月1号，结束于9月31号（这里推后一天）</td>
				</tr>
			</table>
			<input class="screen_btn" type="submit" onclick="screen()" value="筛选">
			<div>
				<button class="printing" onclick="preview()">打印</button>
				<a id="demo" onclick="demo()">导出Excel</a>
			</div>
		</div>
	</div>
	<div class="div_right">
			<!--startprint-->
			<center>
	    		<div id="report_holder">
	    		</div>
    		</center>
			<!--endprint-->
	</div>
</body>
</html>



