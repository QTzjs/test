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
    <title>款式列表</title>
</head>
<body id="body">
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left_right'">
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg',
                    url:'stylegetStyleList.action',
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
                <th data-options="field:'sid',title:'SID',checkbox:true"></th>
                <th data-options="field:'cid',title:'CID',checkbox:true"></th>
                <th data-options="field:'style_name', title: '款式名称',sortable:true"></th>
                <th data-options="field:'size', title: '颜色',sortable:true"></th>
                <th data-options="field:'color', title: '尺寸',sortable:true"></th>
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
           height: 170,
           id:'userAddDialog',
           href:_ctx + 'add_style.jsp',
           buttonsGroup:[
               {text:'保存',url:'placeadd.action',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-normal'}
           ]
       }">新增款式</a> 
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
                width: 750,
                height: 400,
                href: _ctx + '/jsp/materialLoss/r_cartonLossAdd.jsp?transaction_id={uuid}',
                url: _ctx + '/jsp/materialLoss/json/r_cartonLossEdit.json?transaction_id={uuid}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx + '/json/response/success.json',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn'
                    }
                ]
            }">编辑款式</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-normal',
       iconCls:'fa fa-trash',
       url:_ctx + '/json/response/success.json',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'uuid:uuid,code:code'}">删除款式</a>
   
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
