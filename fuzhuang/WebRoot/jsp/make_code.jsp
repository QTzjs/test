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
                    url:'placelist.action',
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
                <th data-options="field:'id',title:'UID',checkbox:true"></th>
                <th data-options="field:'customer',title:'客户公司',sortable:true"></th>
                <th data-options="field:'plan_num',title:'订单号',sortable:true"></th>
                <th data-options="field:'style',title:'款号',sortable:true"></th>
                <th data-options="field:'number',title:'数量',sortable:true"></th>
                <th data-options="field:'residue',title:'已分包',sortable:true"></th>
                <th data-options="field:'delivery_time',title:'交货日期',sortable:true"></th>
                <th data-options="field:'state',title:'状态',sortable:true,
                      formatter:function(value,row,index){
                        if (value == '1') {
                            return '<font color=\'orange\'>待生产</font>';
                        } else if (value == '2') {
                            return '<font color=\'red\'>生产中</font>';
                        } else if(value=='3'){
                            return '<font color=\'green\'>生产完成</font>';
                        } else{
                           return '';
                        }
                    }"></th>
            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'east',iconCls:'icon-chart_pie',title:'',split:true,border:false,width:'55%'">
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
           <div title="添加二维码信息"
                 data-options="id:'eastTab0',iconCls:'fa fa-comment',
					 href:'code_info.jsp?plan_num={plan_num}'"></div>
		   </div>
    </div>
</div>
<!-- layout布局 结束 -->

<!-- 表格工具栏开始 -->
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
      <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openWindow',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-black',
       href:'code_show.jsp?id={plan_num}'">二维码生成</a>
       
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-blue'">查询</a> 
        <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-black'">过滤</a>
    <form id="queryForm" class="search-box">
    
     <!--  <input type="text" value="" name="name" data-toggle="topjui-textbox"
               data-options="id:'name',prompt:'产品名称'">
           <input type="text" name="code" data-toggle="topjui-textbox"
               data-options="id:'code',prompt:'产品型号'">
        <a id="queryBtn" href="javascript:void(0)">查询</a> -->
    </form>
</div>
</body>
</html>