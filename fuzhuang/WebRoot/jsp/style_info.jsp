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
    
    
   
</head>
<style>
   #printTable td,th{
   
       font-size:16px;
   }
</style>
<body>
<!-- layout布局 开始 -->
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_right_bottom'"
                 style="height:80%">
                <!-- datagrid表格 -->
                <table data-toggle="topjui-datagrid"
                       data-options="id:'userDg',
                       singleSelect:true,
						selectOnCheck:false,
						checkOnSelect:false,

			           ">
                    
                </table>
            </div>
            
        </div>
    </div>
    
</div>
<!-- 表格工具栏开始 -->
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
      <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-plus',
       dialog:{
           width: 450,
           height: 200,
           id:'userAddDialog',
           href:'styleC_add.jsp?K_id',
           buttonsGroup:[
               {text:'保存',url:'styleaddStyleC.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">添加详情</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-green',
            grid: {
                type: 'datagrid',
                id: 'userDg'
            },
            dialog: {
                width: 450,
                height: 200,
                href:'styleC_add.jsp',
                url: 'stylefindByCid.action?id={id}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: 'styleupdateStyleC.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">编辑详情</a>
   <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:'styledeleteStyleC.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'id:id'}">删除详情</a>
    </div>
    
<!-- 表格工具栏结束 -->

<script>
   var K_id=request('id');
   $(function () {
       /*  //隐藏文本框录入订单号
        $("#pla").val(place); */
        var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
        $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'stylegetStylec.action?id='+K_id,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'K_id', title: '款式id', hidden: true},
                {field: 'size', title: '颜色', sortable: true},
                {field: 'color', title: '尺寸', sortable: true}
            ]]
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
</body>
</html>