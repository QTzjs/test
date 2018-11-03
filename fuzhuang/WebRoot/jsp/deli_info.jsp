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
<style type="text/css">
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
       data-options="method:'search',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-blue'">查询</a>
       <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url:_ctx + 'subrecover.action',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'id:id,packe_num:packe_num,t_name:t_name,user_name:user_name,place_num:place_num'}">工单回收</a>
      <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar',
       btnCls:'topjui-btn-black'">过滤</a>
       <a href="javascript:dayin()" id="daochu"><i class="fa fa-file-excel-o"></i>&nbsp;导出表格</a>
      <a href="javascript:void(0)">参与人数  </a> <span class="sum" style="color:red;"></span> 
      <a href="javascript:void(0)">已完成工序 : </a> <span class="gsum" style="color:red;"></span>
      <a href="javascript:void(0)">未完成工序 : </a> <span class="ugsum" style="color:red;"></span>
    </div>
<!-- 表格工具栏结束 -->

<script>
    var plan_num=request('plan_num');
    //打印
    function dayin(){
    	//测试导出exsl
          $.ajax({
             type : "post",//提交的方法为post
             url : 'subReporrinting.action?place_num='+plan_num,//对应的Action提交的路径
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
    
    function printReceipt(){ //打印
        bdhtml=window.document.body.innerHTML;    
        sprnstr="<!--startprint-->";    
        eprnstr="<!--endprint-->";    
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
        window.document.body.innerHTML=prnhtml;    
        window.print();
    }
    $(function () {
       $.ajax({
           type:"post",
           url:"subListPeople.action?place_num="+plan_num,
           success:function (num) { 
	              var json = eval("(" + num + ")");
			      //遍历json
			      for(var j in json) {
			         var key = j;
			         var value = json[j];
				   }  
	              $(".sum") .text(value);
	        }, 
	       error:function () { 
	              
	       } 
	   });
        $.ajax({
           type:"post",
           url:"subListFinish.action?place_num="+plan_num,
           success:function (num) { 
	              var json = eval("(" + num + ")");
			      //遍历json
			      for(var j in json) {
			         var key = j;
			         var value = json[j];
				   }  
	              $(".gsum") .text(value);
	        }, 
	       error:function () { 
	              
	       } 
	   });
	    $.ajax({
           type:"post",
           url:"subListUnfinish.action?place_num="+plan_num,
           success:function (num) { 
	              var json = eval("(" + num + ")");
			      //遍历json
			      for(var j in json) {
			         var key = j;
			         var value = json[j];
				   }  
	              $(".ugsum") .text(value);
	        }, 
	       error:function () { 
	              
	       } 
	   });
        var userDg = {
            type: 'datagrid',
            id: 'userDg'
        };
        $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'subList.action?place_num='+plan_num,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'place_num', title: '订单号', sortable: true},
                {field: 'packe_num', title: '包号', sortable: true},
                {field: 'number', title: '数量', sortable: true},
                {field: 'price', title: '单价', sortable: true},
                {field: 'money', title: '工钱', sortable: true},
                {field: 't_name', title: '工序名称', sortable: true},
                {field: 'job_number', title: '提交人工号', sortable: true},
                {field: 'user_name', title: '提交人', sortable: true},
                {field: 'sub_time', title: '提交时间', sortable: true},
                {field: 'state',title:'状态',sortable:true,
                formatter:function(value,row,index){
                        if (value == '1') {
                            return '<font color=\"green\">正常</font>';
                        } else if (value == '2') {
                            return '<font color=\"orange\">待批准</font>';
		                }else if(value == '3'){
		                	return '<font color=\"red\">已作废</font>';
		                }
		                }
		                }
            ]]
            
        });
        $('#queryBtn').iMenubutton({
            event: 'query',
            iconCls: 'fa fa-search',
            btnCls: 'topjui-btn-danger',
            form: {id: 'queryForm'},
            grid: {type: 'datagrid', 'id': 'userDg'}
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
</body>
</html>