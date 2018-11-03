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
   <script>
    function progressFormatter(value, rowData, rowIndex) {
        var htmlstr = '<div id="p" class="easyui-progressbar progressbar" data-options="value:' + value + '" style="width: 398px; height: 26px;">';
        htmlstr += '<div class="progressbar-text" style="width: 398px; height: 26px; line-height: 26px;">' + value + '%</div>';
        htmlstr += '<div class="progressbar-value" style="width: ' + value + '%; height: 26px; line-height: 26px;">';
        htmlstr += '<div class="progressbar-text" style="width: 398px; height: 26px; line-height: 26px;">' + value + '%</div>';
        htmlstr += '</div>';
        htmlstr += '</div>';
        return htmlstr;
    }
       function addCartonType(){  //打开对话框，添加箱型
       var html = "<div align='right'> <button class=\"layui-btn layui-btn-xs\" type='submit'>确认</button><button class=\"layui-btn layui-btn-primary layui-btn-xs\" onclick='quxiao()'>取消</button></div>";
           var htm = "<div align=\"center\"><form class=\"layui-form\" action=\"\"><div class=\"layui-form-item\"><label class=\"layui-form-label\">单行输入框</label><div class=\"layui-input-block\"><input type=\"text\" name=\"title\" lay-verify=\"required|title\" autocomplete=\"off\" required placeholder=\"请输入箱型名称\" class=\"layui-input\"></div></div>"+html+"</form></div>";
           
           layer.open({
                
                 area: ['600px','200px'],
                 title: ['添加纸箱箱型', 'text-align:center;'],
                 closeBtn: 2,
                 scrollbar: false,
                 btn:[],
                    content: htm
              });
       }  
      function quxiao(){ //关闭对话框
         layer.close(layer.index);
     
     }
     function deleteCartonType(){
          var a = $('#cartonType').iTreegrid("getChecked");
          if(a.length==0){
               layer.open({
                
                 area: '600px',
                 title: ['提示信息', 'text-align:center;'],
                 closeBtn: 2,
                 scrollbar: false,
                 btn:[],
                    content: '亲，请勾选左边箱型进行删除！'
              });
          }
          else if(a.length>1){
                layer.open({
                
                 area: '600px',
                 title: ['提示信息', 'text-align:center;'],
                 closeBtn: 2,
                 scrollbar: false,
                 btn:[],
                    content: '亲，一次只能勾选一种箱型删除呢！'
              });
          }
          else{
              
           
          }
     
     }
       $(function () {
        var productDg = {
            type: 'datagrid',
            id: 'userDg'
        };

        $("#userDg").iDatagrid({
            id: 'userDg',
            url: '${pageContext.request.contextPath}/jsp/cartontype/json/model.json',
            childTabs: [
                            
                            {id:'eastTabs'}
                        ],
            columns: [[
                {field: 'model_id', title: 'UID', checkbox: true},
               
                {field: 'user_company', title: '客户公司', sortable: true},
                {field: 'model_name', title: '订单号', sortable: true},
                {field: 'material_science', title: '款号', sortable: true},
                {field: 'pit_type', title: '数量', sortable: true},
                {field: 'carton_spec', title: '交货日期', sortable: true},
                {field: 'model_type', title: '状态', sortable: true}
                //{field: 'rate', title: '完成率', sortable: true, formatter: progressFormatter}
               
                
            ]]
        });

        $("#execute").iMenubutton({
            method: 'openDialog',
            extend: '#userDg-toolbar',
            iconCls: 'fa fa-plus',
            btnCls: 'topjui-btn-warm',
            dialog: {
                id: 'userAddDialog',
                height: 400,
                href: _ctx + '/jsp/user/userAdd.jsp',
                buttonsGroup: [
                    {
                        text: '保存',
                        url: _ctx + '/json/response/success.json',
                        iconCls: 'fa fa-plus',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-normal'
                    }
                ]
            }
        });

        $("#edit").iMenubutton({
            method: 'openDialog',
            extend: '#productDg-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn',
            grid: productDg,
            dialog: {
                width: 950,
                height: 500,
                href: _ctx + '/jsp/user/userAdd.jsp?uuid={uuid}',
                url: _ctx + 'json/demo/userEdit.json?uuid={uuid}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx + '/json/response/success.json',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn'
                    }
                ]
            }
        });

        $("#delete").iMenubutton({
            method: 'doAjax',
            extend: '#productDg-toolbar',
            iconCls: 'fa fa-trash',
            btnCls: 'topjui-btn-normal',
            confirmMsg: '这个是勾选复选框实现多条数据的Ajax删除提交操作，提交grid.param中指定的参数值',
            grid: {
                type: 'datagrid',
                id: 'productDg',
                uncheckedMsg: '请先勾选要删除的数据',
                param: 'uuid:uuid,code:code'
            },
            url: _ctx + '/json/response/success.json'
        });
        $("#see").iMenubutton({
            method: 'openDialog',
            extend: '#productDg-toolbar',
            iconCls: 'fa fa-eye',
            btnCls: 'topjui-btn-danger',
            grid: productDg,
            dialog: {
                width: 700,
                height: 300,
                href: _ctx + '/jsp/outStorage/cartonPurAdd.jsp?uuid={uuid}',
                url: _ctx + '/json/demo/cartonPurEdit.json?uuid={uuid}'
            }
        });

        $("#filter").iMenubutton({
            method: 'filter',
            extend: '#productDg-toolbar',
            btnCls: 'topjui-btn-warm',
            grid: productDg
        });

        $("#search").iMenubutton({
            method: 'search',
            extend: '#productDg-toolbar',
            btnCls: 'topjui-btn-danger',
            grid: productDg
        });

        $("#import").iMenubutton({
            method:'import',
            extend: '#productDg-toolbar',
            btnCls:'topjui-btn-normal',
            uploadUrl:_ctx + '/json/response/upload.json',
            url:_ctx+'/json/response/success.json'
        });
       
    });

    // 自定义方法
    /* function myQuery() {
        // 提示信息
        $.iMessager.alert('自定义方法', '自定义方法被执行了！', 'messager-info');
        // 提交参数查询表格数据
        $('#productDg').iDatagrid('reload', {
            name: $('#name').iTextbox('getValue'),
            code: $('#code').iTextbox('getValue')
        });
    } */
   </script>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left'" >
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg'">
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
            <div title="添加工序"
                 data-options="id:'eastTab0',iconCls:'fa fa-comment',
					 href:'model_info.jsp'"></div>
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
           width: 750,
           height: 400,
           id:'userAddDialog',
           href:_ctx + '/jsp/add_place.jsp',
           buttonsGroup:[
               {text:'保存',url:_ctx + '/json/response/success.json',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-normal'}
           ]
       }">新增订单</a>
   <!--  <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="
       iconCls: 'fa fa-plus',
       btnCls:'topjui-btn-normal',
     " onclick="addCartonType()">添加订单</a> -->
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
                width: 950,
                height: 500,
                href: _ctx + '/jsp/add_place.jsp?uuid={uuid}',
                url: _ctx + '/json/product/detail.json?uuid={uuid}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx + '/json/response/success.json',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn'
                    }
                ]
            }">编辑订单</a>
    <a 
       data-toggle="topjui-menubutton"
       data-options="
       btnCls:'topjui-btn-danger',
       iconCls:'fa fa-trash'
      " onclick="deleteCartonType()">删除订单</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',btnCls:'topjui-btn-danger'">查询</a>
  
</div>
</body>
</html>