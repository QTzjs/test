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
    
    <link rel="stylesheet" href="../demo/jquery-labelauty.css">
    <script src="../demo/jquery-labelauty.js"></script>
    
   
</head>
<style>
   #printTable td,th{
   
       font-size:16px;
   }
   ul { list-style-type: none;}
li { display: inline-block;}
li { margin: 10px 0;}
input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>
<body>
<table id="configEdatagrid" data-toggle="topjui-edatagrid"
       data-options="id:'configEdatagrid',
       idField:'uuid',
       reloadAfterSave:true,
       url:'placefindByT.action?place=${param.plan_num}',
       saveUrl:_ctx + '/json/response/save.json',
       updateUrl:_ctx + '/json/response/update.json',
       destroyUrl:_ctx + '/json/response/delete.json'">
    <thead>
    <tr>
        <th data-options="field: 'id', title: 'UID', checkbox: true"></th>
        <th data-options="field: 'place_number', title: '订单编号', sortable: true"></th>
        <th data-options="field: 'technology_name', title: '工序名称', sortable: true"></th>
        <th data-options="field: 'price', title: '工序单价', sortable: true,editor:{type:'textbox',options:{required:true,height:30}}"></th>
    </tr>
    </thead>
</table>

<!-- 表格工具栏 -->
<div id="configEdatagrid-toolbar" data-options="grid:{
           type:'edatagrid',
           id:'configEdatagrid'
       }" class="topjui-toolbar">
       <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-plus',
       dialog:{
            width: 500,
           height: 450,
           id:'userAddDialog',
           href:'add_place_xu.jsp?place=${param.plan_num}',
           buttonsGroup:[
               {text:'保存',url:'placeadd_gong.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">添加工序</a> 
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'edatagrid',
       extend: '#configEdatagrid-toolbar',
       iconCls:'fa fa-save',
       btnCls:'topjui-btn-brown',
       type:'saveRow'">保存</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'edatagrid',
       extend: '#configEdatagrid-toolbar',
       iconCls:'fa fa-remove',
       btnCls:'topjui-btn-black',
       type:'cancelRow'">取消</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'edatagrid',
       extend: '#configEdatagrid-toolbar',
       iconCls:'fa fa-remove',
       btnCls:'topjui-btn-blue',
       type:'destroyRow'">删除</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#configEdatagrid-toolbar',
       btnCls:'topjui-btn-black'">过滤</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#configEdatagrid-toolbar',
       btnCls:'topjui-btn-blue'">查询</a>
</div>
    
<!-- 表格工具栏结束 -->
</body>
</html>