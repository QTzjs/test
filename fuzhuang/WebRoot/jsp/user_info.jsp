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
<style>
   #printTable td,th{
   
       font-size:16px;
   }
   #daochu{ display: inline-block; height: 34px;width: 82px; line-height: 31px; border-radius:3px; background-color: #009688; color: #fff; text-align: center;}
	
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
           id:'userDg' }">
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
        <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:'userdeleteuserinfo.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'ids:id,packe_num:packe_num,t_name:t_name,user_name:user_name,place_num:place_num'}">删除工单</a>
      <!--  <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'export',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn',
       url:'userecxlOut'">导出ECXL</a> -->
       <a href="javascript:dayin()" id="daochu"><i class="fa fa-file-excel-o"></i>&nbsp;导出表格</a>
       </div>
      <!-- <div id="dd" class="easyui-dialog" title="下载" closed="true" style="width:250px;height:80px;"
        data-options="resizable:true,modal:true">
        <a href="" id="download">把excel表格下载到本地</a>
       </div> -->
     
<!-- 表格工具栏结束 -->

<script>
   var job_number=request('job_number');
   $(function () {
   var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
       /*  //隐藏文本框录入订单号
        $("#pla").val(place); */
        
        $("#userDg").iDatagrid({
            id: 'userDg',
            singleSelect:true,
			selectOnCheck:false,
			checkOnSelect:false,
            url: 'userfindByjob.action?job_number='+job_number,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'place_num', title: '订单号', sortable: true},
                {field: 'packe_num', title: '包号', sortable: true},
                 {field: 'number', title: '数量', sortable: true},
                 {field: 'price', title: '单价', sortable: true},
                 {field: 'money', title: '工钱', sortable: true},
                {field: 't_name', title: '工序名称', sortable: true},
                {field: 'job_number', title: '工号', sortable: true},
                {field: 'user_name', title: '提交人', sortable: true},
                 {field: 'sub_time', title: '提交时间', sortable: true},
                 {field: 'state',title:'状态',sortable:true,
                formatter:function(value,row,index){
                        if (value == '1') {
                            return '<font color=\"green\">正常</font>';
                        } else if (value == '2') {
                            return '<font color=\"red\">待批准</font>';
		                }
		                }
		                }
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
        
        //打印
        function dayin(){
        	//测试导出exsl
              $.ajax({
	                type : "post",//提交的方法为post
	                url : 'userecxlOut.action?job_number='+job_number,//对应的Action提交的路径
	                dataType: 'json',
	                success : function(data) {//当Ajax提交成功时调用的方法
	                         /* alert(data.message); */
	                          var downurl=data.downurl;
	                          var url="<%=request.getContextPath()%>/download/"+downurl;
	                       /*  alert(url);  */
	                          window.location.href = url;
                              //$("#download").attr("href",+url);
                              //$('#dd').dialog('open'); 
                         /*  download(downurl); */
	                    }
	                });
	                //console.log("打印");
        }
      /* *function download(src) { 
          var $a = $("<a></a>").attr("href", src); 
          $a[0].click(); 
          }*/
          
</script>
</body>
</html>