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
           width: 500,
           height: 400,
           id:'userAddDialog',
           href:'add_info.jsp',
           buttonsGroup:[
               {text:'保存',url:'codetoSubcontract.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">添加信息</a>
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
                width: 590,
                height: 250,
                href: 'add_info1.jsp',
                url: 'codefindByPack.action?packe_num={packe_num}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: 'codeupdate.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">修改包信息</a> 
        <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:'codedelete.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'id:id,packe_num:packe_num,p_num:p_num,p_number:p_number'}">删除信息</a>
    </div>
    
<!-- 表格工具栏结束 -->

<script>
   var place=request('plan_num');
  /*  alert(place); */
   $(function () {
       /*  //隐藏文本框录入订单号
        $("#pla").val(place); */
        var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
       $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'codefindByIdPage.action?place_num='+place,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'p_num', title: '订单编号', sortable: true},
                {field: 'p_color', title: '颜色', sortable: true},
                {field: 'p_size', title: '尺码', sortable: true},
                {field: 'packe_num', title: '包号', sortable: true},
                {field: 'p_number', title: '件数', sortable: true}
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