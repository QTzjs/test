<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>jQuery实现美化版的单选框和复选框DEMO演示</title>
<link rel="stylesheet" href="../demo/jquery-labelauty.css">
<style>
ul { list-style-type: none;}
li { display: inline-block;}
li { margin: 10px 0;}
input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>
</head>

<body>
<center>
<ul class="dowebok">
	<li><input type="checkbox" name="checkbox" disabled checked data-labelauty="HTML"></li>
	<li><input type="checkbox" name="checkbox" data-labelauty="CSS"></li>
	<li><input type="checkbox" name="checkbox" data-labelauty="JavaScript"></li>
	<li><input type="checkbox" name="checkbox" data-labelauty="SEO"></li>
	<li><input type="checkbox" name="checkbox" data-labelauty="PHP"></li>
	<li><input type="checkbox" name="checkbox" data-labelauty="JAVA"></li>
	<li><input type="checkbox" name="checkbox" data-labelauty=".NET"></li>
</ul>

<script src="../demo/jquery-1.8.3.min.js"></script>
<script src="../demo/jquery-labelauty.js"></script>
<script>
$(function(){
	$(':input').labelauty();
});
</script>
</center>


</body>
</html>
