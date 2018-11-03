<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <!-- TopJUI框架样式 -->
    <link type="text/css" href="../../topjui/css/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="../../topjui/themes/default/topjui.blue.css" rel="stylesheet" id="dynamicTheme"/>
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="../../static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- layui框架样式 -->
    <link type="text/css" href="../../static/plugins/layui/css/layui.css" rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="../../static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/plugins/jquery/jquery.cookie.js"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="../../static/public/js/topjui.config.js"></script>
    <!-- TopJUI框架核心-->
    <script type="text/javascript" src="../../topjui/js/topjui.core.min.js"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="../../topjui/js/locale/topjui.lang.zh_CN.js"></script>
    <!-- layui框架js -->
    <script type="text/javascript" src="../../static/plugins/layui/layui.js" charset="utf-8"></script>
    <title>员工列表</title>
</head>

<body id="body">
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left_right'">
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg',
                    url:'userlist.action',
                    childTabs: [{id:'eastTabs'}],
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
                <th data-options="field:'job_number', title: '工号',sortable:true"></th>
                <th data-options="field:'user_name', title: '名字',sortable:true"></th>
                <th data-options="field:'user_account', title: '账号',sortable:true"></th>
                <th data-options="field:'password', title: '密码',sortable:true"></th>
                <th data-options="field:'user_account', title: '联系手机',sortable:true"></th>
                <th data-options="field:'user_level', title: '权限',sortable:true,formatter:function(value,row,index){
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
                <th data-options="field:'user_time', title: '入职时间',sortable:true"></th>
            </tr>
            </thead>
        </table>
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
           width: 550,
           height: 250,
           id:'userAddDialog',
           href:_ctx + '/jsp/add_user.jsp',
           buttonsGroup:[
               {text:'保存',url:'useradd.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-normal'}
           ]
       }">新增员工信息</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn',
            grid: {
                type: 'datagrid',
                id: 'userDg'
            },
            dialog: {
                width: 550,
                height: 250,
                href: _ctx + '/jsp/add_user.jsp?id={id}',
                url:'userfindById.action?id={id}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: 'userupdate.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn'
                    }
                ]
            }">编辑员工信息</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-normal',
       iconCls:'fa fa-trash',
       url:'userdelete.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'id:id'}">删除员工信息</a>
       <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-warm'">过滤</a>
     <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn',
            grid: {
                type: 'datagrid',
                id: 'userDg'
            },
            dialog: {
                width: 550,
                height: 250,
                href: 'money.jsp',
                url:'',
                buttonsGroup: [
                    {
                        text: '结算',
                        url: 'userupdate.action',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn'
                    }
                ]
            }">工资结算</a>
          
   <form id="queryForm" class="search-box">
           <input type="text" value="" name="customer" data-toggle="topjui-textbox"
               data-options="id:'name',prompt:'开始时间'">
           <input type="text" name="code" data-toggle="topjui-textbox"
               data-options="id:'code',prompt:'结束时间'">
        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-danger',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'productDg'}">查询</a> 
           
    </form> 
    </div>
<!-- 表格工具栏结束 -->
<script type="text/javascript">
	$(function(){
	   // alert("4164553453534");
	     //测试
	     $("#treetab th").tabs({
               onClick:function(node){          
                  alert(node.text);
               }
       });
		$('#queryBtn').iMenubutton({
            method: 'query',
            iconCls: 'fa fa-search',
            btnCls: 'topjui-btn-danger',
            form: {id: 'queryForm'},
            grid: {type: 'datagrid', 'id': 'productDg'}
        });
      
	});
	function addCartonType(){  //打开对话框
       var html = "<div align='right'> <button class=\"layui-btn layui-btn-xs\" type='submit'>确认</button><button class=\"layui-btn layui-btn-primary layui-btn-xs\" onclick='quxiao()'>取消</button></div>";
           var htm = "<div align=\"center\"><form class=\"layui-form\" action=\"teadd.action\"><div class=\"layui-form-item\"><label class=\"layui-form-label\">工序名称</label><div class=\"layui-input-block\"><input type=\"text\" name=\"name\" lay-verify=\"required|title\" autocomplete=\"off\" required placeholder=\"请输入工序名称\" class=\"layui-input\"></div><div class=\"layui-form-item\"><label class=\"layui-form-label\">工序编号</label><div class=\"layui-input-block\"><input type=\"text\" name=\"number\" lay-verify=\"required|title\" autocomplete=\"off\" required placeholder=\"请输入编号\" class=\"layui-input\"></div></div>"+html+"</form></div>";
           
           layer.open({
                
                 area: ['400px','250px'],
                 title: ['添加数据', 'text-align:center;'],
                 closeBtn: 2,
                 scrollbar: false,
                 btn:[],
                    content: htm
              });
       }  
      function quxiao(){ //关闭对话框
         layer.close(layer.index);
     }
</script>
</body>
</html>
