<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
     <%@ include file="ap.jsp" %>
</head>

<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left_right'">
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg',
               singleSelect:true,
				selectOnCheck:false,
				checkOnSelect:false,
                    url:'userlist.action',
                    childTab: [{id:'eastTabs'}],
                    filter: [{
                        field: 'userName',
                        type: 'textbox',
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'sex',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            data: [{
                                label: '男',
                                value: '1'
                            }, {
                                label: '女',
                                value: '2'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'post',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            multiple: true,
                            data: [{
                                label: 'CEO',
                                value: 'CEO'
                            }, {
                                label: 'COO',
                                value: 'COO'
                            }, {
                                label: 'CTO',
                                value: 'CTO'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    }]">
            <thead>
            <tr>
                <th data-options="field:'id',title:'UUID',checkbox:true"></th>
                <th data-options="field:'job_number',title:'员工号',sortable:true"></th>
                <th data-options="field:'user_name',title:'姓名',sortable:true"></th>
                <th data-options="field:'user_account',title:'账号',sortable:true"></th>
                <th data-options="field:'password',title:'密码',sortable:true"></th>
                <th data-options="field:'user_account',title:'联系手机',sortable:true"></th>
                <th data-options="field:'user_level',title:'权限',sortable:true,
                    formatter:function(value,row,index){
                         if (value == '1') {
                            return '<font color=\'orange\'>普通员工</font>';
                        } else if (value == '2') {
                            return '<font color=\'red\'>管理</font>';
                        } else if (value == '3'){
                            return '<font color=\'green\'>厂长</font>';
                        }else{
                        	return '';
                        }
                    }"></th>
                    <th data-options="field:'user_time',title:'入职时间',sortable:true"></th>
            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'east',iconCls:'icon-chart_pie',title:'',split:true,border:false,width:'40%'">
        <div data-toggle="topjui-tabs"
             data-options="id:'eastTabs',
             fit:true,
             border:true,
             bodyCls:'border_right_none',
             parentGrid:{
                 type:'datagrid',
                 id:'userDg',
                 param:'puuid:uuid'
             }">
            <div title="员工工单提交详情"
                 data-options="id:'eastTab0',iconCls:'fa fa-th',
					 href:'user_info.jsp?job_number={job_number}'"></div>
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
           width: 650,
           height: 250,
           id:'userAddDialog',
           href:'add_user.jsp',
           buttonsGroup:[
               {text:'保存',url:'useradd.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增员工信息</a>
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
                width: 650,
                height: 250,
                href: 'add_user.jsp?id={id}',
                url:'userfindById.action?id={id}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url:'userupdate.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">编辑员工信息</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:'userdelete.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'ids:id,job_number:job_number,user_name:user_name'}">删除员工信息</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-black'">过滤</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-blue'">查询</a>
    
    </div>
<!-- 表格工具栏结束 -->
</body>
</html>