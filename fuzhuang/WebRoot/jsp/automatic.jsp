<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/style/common.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/table_common.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">

<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/checkboxJs.js"></script>
<script src="${pageContext.request.contextPath}/js/waitDailog.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
		//初始化select标签
		
		
		//提交
function open(){
         $.ajax({
             type: "post",
             url: "indianaImagesupdate.action",
             data: {group_code:$("#bonus").val(),biao:0},
             dataType: "json",
             success: function(data){
                  alert(data);
                  alert("执行打开成功！");
              }
         });
    }
function close(){
         $.ajax({
             type: "post",
             url: "indianaImagesupdate.action",
             data: {group_code:$("#bonus").val(),biao:1},
             dataType: "json",
             success: function(data){
                  alert(data);
                  alert("执行关闭成功！");
              }
         });
    }
	</script>
	<style type="text/css">
	#one{
	height: 50px;
	}
	
	</style>
</head>
<body>
	<div id="one">
	
	</div>
	<div class=""
		style="width:90%;padding-left:50px;padding-right:50px">
			<form action="indianaImagesupdate.action?biao=0" method="post">
				<table align="center">
						<tr>
						<td><label>请筛选开启系统自动发夺宝:</label></td>
						<td>
						<select style="width:100px" type="text" id="bonus" name="group_code">
							<option value="0001">1元</option>
							<option value="0002">2元</option>
							<option value="0003">3元</option>
							<option value="0005">5元</option>
							<option value="0006">10元</option>
							<option value="0020">20元</option>
							<option value="0050">50元</option>
							<option value="0100">100元</option>
						</select>
						</td>
						<td><button class="btn btn-primary" onclick=""
								style="margin-bottom: 8px" type="submit">执行打开</button>
						
					</tr>
				</table>
			</form>
			
			
			<form action="indianaImagesupdate.action?biao=1" method="post">
				<table align="center">
						<tr>
						<td><label>请筛选关闭系统自动发夺宝:</label></td>
						<td>
						<select style="width:100px" type="text" id="bonus" name="group_code">
							<option value="0001">1元</option>
							<option value="0002">2元</option>
							<option value="0003">3元</option>
							<option value="0005">5元</option>
							<option value="0006">10元</option>
							<option value="0020">20元</option>
							<option value="0050">50元</option>
							<option value="0100">100元</option>
						</select>
						</td>
						</td>
						<td><button class="btn btn-primary" onclick=""
								style="margin-bottom: 8px" type="submit">执行关闭</button>
						</td>
					</tr>
				</table>
			</form>
			
			<form action="indianaImagesupdate.action" method="post">
				<table align="center">
						<tr>
						<td><label>请筛选关闭或开启所有夺宝:</label></td>
						<td>
						<select style="width:150px" type="text" id="bonus" name="group_code">
							<option value="y">开启所有自动夺宝</option>
							<option value="n">关闭所有自动夺宝</option>
						</select>
						</td>
						<td><button class="btn btn-primary" onclick=""
								style="margin-bottom: 8px" type="submit">执行</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		
</body>
</html>
