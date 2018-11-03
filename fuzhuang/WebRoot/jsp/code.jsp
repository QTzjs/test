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
    <title>工序列表</title>
</head>

<body id="body">
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left_right'">
        <!-- datagrid表格 -->
         <table data-toggle="topjui-datagrid"
                       data-options="id:'userDg',

			           ">
         </table>
    </div>
</div>

<!-- 表格工具栏开始 -->
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
   <form action="codemakecode.action" method="post">
         <input style="width:250px;height:30px" id="linkDataProperty" name="customer" value="${model.user_id}" autocomplete="off" type="text" placeholder="请输入客户名称查找要生成的订单"/> <!-- onkeyup="getLinkData();" -->
		 <div id="popup" style="position: absolute; z-index:9999;">
                            <table width="100%" bgcolor="#fffafa">
                               <tbody id="popupBody"></tbody>
                            </table>
          </div>
          <input type="hidden" id="plan_num" name="plan_num">
          <a id="add" href="javascript:void(0)">添加信息</a>
          <input style="width:175px;height:30px" id="sub" name="sub" value="生成二维码" type="submit" />
    </form> 
</div>
<!-- 表格工具栏结束 -->
<script type="text/javascript">
    var skt;
	$(function(){
		var loseInputMillsecs = 700;  
	    var clocker = null;  
	    $("#add").iMenubutton({
            method: 'openDialog',
            extend: '#productDg-toolbar',
            iconCls: 'fa fa-plus',
            dialog: {
                id: 'userAddDialog',
                height: 250,
                width:550,
                href: 'add_info.jsp',
                buttonsGroup: [
                    {
                        text: '保存',
                        url: 'codetoSubcontract.action',
                        iconCls: 'fa fa-plus',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-normal'
                    }
                ]
            }
        });  
        
         $("#userDg").iDatagrid({
            id: 'userDg',
            url: 'codefindById.action?place_num='+skt,
            columns: [[
                {field: 'id', title: 'UID', checkbox: true},
                {field: 'P_color', title: '颜色', sortable: true},
                {field: 'P_size', title: '尺码', sortable: true},
                {field: 'packe_num', title: '包数', sortable: true},
                {field: 'P_number', title: '件数', sortable: true},
                {field: 'Cylinder', title: '缸号', sortable: true}
            ]]
        });
         
	    //输入键盘后隔段时间的方法
	    function loadData()  
	    {  
	        /* console.info("do load data list");   */
	        
	        //联想文本框
				
	            var popupDiv = document.getElementById("popup");//获得对应的div对象
	            var popupBody = document.getElementById("popupBody");//获得对应的tbody对象
	            var linkDataProperty = document.getElementById("linkDataProperty"); //获得对应的输入框对象
	            var myString = linkDataProperty.value.trim();
                var judge = myString.indexOf("客户名：");
                if(judge > -1){
                	var newString = myString.substring(judge+4);
               		linkDataProperty = newString;
                }else{
               		linkDataProperty = linkDataProperty.value.trim();
                }
	            if(linkDataProperty != null && linkDataProperty != ""){
	            
	            clearModels();//清除联想输入提示框的内容
	            //利用ajax获取后台的模糊查询的数据，并且封装成下拉列表的形式展现出来
	            $.ajax({
	                type : "post",//提交的方法为post
	                url : '${pageContext.request.contextPath}/placefindByCustomer.action',//对应的Action提交的路径
	                data:{st:linkDataProperty},//从前台传递到后台的查询语句的参数
	                dataType: 'json',
	                error:function(){
	                   /*  alert("没有对应的数据，请查看输入的查询条件！"); */
	                },
	                success : function(data) {//当Ajax提交成功时调用的方法
	                          if(data.placeList.length==0 ){return;}
	                          setOffsets();//设置联想输入下拉列表提示框的位置
	                          var tr,td,text;
	                          for (var i = 0; i < data.placeList.length; i++) {//根据返回的值，手动的拼tbody的内容
	                          text = document.createTextNode("订单号："+data.placeList[i].plan_num+"  客户名："+data.placeList[i].customer);//从Action中返回的数据中取出linkDataProperty的值
	                          td = document.createElement("td");//创建一个td的对象           
	                          tr = document.createElement("tr");//创建一个tr的对象       
	                          inp= document.createElement("a");//创建一个a链接对象
	                          /* tex=document.createTextNode(data.placeList[i].id); */
	                          /* inp.appendChild(tex); */
	                          inp.appendChild(text);
	                          td.mouseOver = function(){this.className="mouseOver;";};
	                          td.mouseOut = function(){this.className="mouseOut;";};
	                          td.onclick = function(){populateModel(this);};//单击td是的方法为populateModel             
	                          td.appendChild(inp);
	                          /* td.appendChild(text); */
	                          tr.appendChild(td);
                          	  popupBody.appendChild(tr);
	                      }
	                    }
	                });
                }else{
                	clearModels();//清除自动完成行
                }
	            //点击下拉列表中的某个选项时调用的方法
	            function populateModel(cell) {
	                    clearSelect();
	                    $("#linkDataProperty").val(cell.firstChild.firstChild.nodeValue);
	                    //initOtherData(linkDataProperty.value);利用输入框中的数据调用其他方法，初始化其他数据
	                    clearModels();//清除自动完成行 
	                    var kt = cell.firstChild.firstChild.nodeValue;
	                    var rng = kt.indexOf("客户名：");
	                    skt = kt.substring(4,rng);
	                    $("#plan_num").val(skt);//页面保存订单号
	                   
	            }
	            //清除自动完成行，只要tbody有子节点就删除掉，并且将将外围的div的边框属性设置为不可见的
	            function clearModels() {
	                while (popupBody.childNodes.length > 0) {
	                    popupBody.removeChild(popupBody.firstChild);
	                }
	                popupDiv.style.border = "none";
	            }
	            //设置下拉列表的位置和样式
	            function setOffsets() {
	                var width = linkDataProperty.offsetWidth;//获取linkDataProperty输入框的相对宽度
	                var left = getLeft(linkDataProperty);
	                var top = getTop(linkDataProperty) + linkDataProperty.offsetHeight;
	        
	                popupDiv.style.border = "black 0px solid";
	                popupDiv.style.left = left + "px";
	                popupDiv.style.top = top + "px";
	                popupDiv.style.width = width + "px";
	                
	            }
	            //获取指定元素在页面中的宽度起始位置
	            function getLeft(e) {
	                var offset = e.offsetLeft;
	                if (e.offsetParent != null) {
	                    offset += getLeft(e.offsetParent);
	                }
	                return offset;
	            }
	            //获取指定元素在页面中的高度起始位置
	            function getTop(e) {
	                var offset = e.offsetTop;
	                if (e.offsetParent != null) {
	                    offset += getTop(e.offsetParent);
	                }
	                return offset;
	            }
	        
	        
	        clocker = null;  
	    }  
	      
	    function innerKeydown()  
	    {  
	        if(null == clocker)  
	        {  
	            clocker = setTimeout(loadData,loseInputMillsecs);  
	            
	        }  
	        else    //连续击键，重新开始计时  
	        {  
	            clearTimeout(clocker);  
	            clocker = setTimeout(loadData,loseInputMillsecs);  
	        }  
	    }  
  
    $('#linkDataProperty').keydown(function(){  
        innerKeydown();  
    });
	
	
	
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
       // var body = $("#body").html();
       // console.info(body);
      
      
	});
	function addCartonType(){  //打开对话框，添加箱型
		var linkDataProperty = $("#plan_num").val();
        if(linkDataProperty != null && linkDataProperty != ""){
       		
       		}else{
       			alert("您还未输入客户名选中订单！");
       		}
       }  
      function quxiao(){ //关闭对话框
         layer.close(layer.index);
     
     }
     
     //联想文本框
		function getLinkData() {
            var popupDiv = document.getElementById("popup");//获得对应的div对象
            var popupBody = document.getElementById("popupBody");//获得对应的tbody对象
            var linkDataProperty = document.getElementById("linkDataProperty"); //获得对应的输入框对象
            linkDataProperty = linkDataProperty.value.trim();
            clearModels();//清除联想输入提示框的内容
            //利用ajax获取后台的模糊查询的数据，并且封装成下拉列表的形式展现出来
            $.ajax({
                type : "post",//提交的方法为post
                url : '${pageContext.request.contextPath}/placefindByCustomer.action',//对应的Action提交的路径
                data:{st:linkDataProperty},//从前台传递到后台的查询语句的参数
                dataType: 'json',
                error:function(){
                    alert("没有对应的数据，请查看输入的查询条件！");
                },
                success : function(data) {//当Ajax提交成功时调用的方法
                          
                          if(data.userlist.length==0){return;}
                          setOffsets();//设置联想输入下拉列表提示框的位置
                          var tr,td,text;
                          for (var i = 0; i < data.userlist.length; i++) {//根据返回的值，手动的拼tbody的内容
                          text = document.createTextNode("用户名:"+data.userlist[i].user_name);//从Action中返回的数据中取出linkDataProperty的值
                          td = document.createElement("td");//创建一个td的对象           
                          tr = document.createElement("tr");//创建一个tr的对象       
                          inp= document.createElement("a");//创建一个a链接对象
                          tex=document.createTextNode(data.userlist[i].user_id);
                          inp.appendChild(tex);
                          td.mouseOver = function(){this.className="mouseOver;";};
                          td.mouseOut = function(){this.className="mouseOut;";};
                          td.onclick = function(){populateModel(this);};//单击td是的方法为populateModel             
                          td.appendChild(inp);
                          td.appendChild(text);
                          tr.appendChild(td);            
                          popupBody.appendChild(tr);
                      }
                    }
                });
	    
            //点击下拉列表中的某个选项时调用的方法
            function populateModel(cell) {
            	   
                    clearSelect();
                    $("#linkDataProperty").val(cell.firstChild.firstChild.nodeValue);
                    //initOtherData(linkDataProperty.value);利用输入框中的数据调用其他方法，初始化其他数据
                    clearModels();//清除自动完成行                        
            }
            //清除自动完成行，只要tbody有子节点就删除掉，并且将将外围的div的边框属性设置为不可见的
            function clearModels() {
                while (popupBody.childNodes.length > 0) {
                    popupBody.removeChild(popupBody.firstChild);
                }
                popupDiv.style.border = "none";
            }
            //设置下拉列表的位置和样式
            function setOffsets() {
                var width = linkDataProperty.offsetWidth;//获取linkDataProperty输入框的相对宽度
                var left = getLeft(linkDataProperty);
                var top = getTop(linkDataProperty) + linkDataProperty.offsetHeight;
        
                popupDiv.style.border = "black 0px solid";
                popupDiv.style.left = left + "px";
                popupDiv.style.top = top + "px";
                popupDiv.style.width = width + "px";
                
            }
            //获取指定元素在页面中的宽度起始位置
            function getLeft(e) {
                var offset = e.offsetLeft;
                if (e.offsetParent != null) {
                    offset += getLeft(e.offsetParent);
                }
                return offset;
            }
            //获取指定元素在页面中的高度起始位置
            function getTop(e) {
                var offset = e.offsetTop;
                if (e.offsetParent != null) {
                    offset += getTop(e.offsetParent);
                }
                return offset;
            }
        }
     
	
         //清空输入框中的数据
        function clearSelect() {
            var linkDataProperty=document.getElementById(linkDataProperty);
           // linkDataProperty.value="";
        }
	/* $.ajax({
	     url:remoteHost+'/system/organization/getListByCodeSetIdAndLevelId?codeSetId=A&levelId=1',
	     data:null,
	     type:"post",
	     dataType:"json",
	     success:function(data){
	         console.info(JSON.stringify(data));
	          
	     }
	});
	$.ajax({
	     url:remoteHost+'/system/organization/getListByPid?pid=3',
	     data:null,
	     type:"post",
	     dataType:"json",
	     success:function(data){
	         console.info(JSON.stringify(data));
	     }
	}); */
	
	
	
	
	
</script>
</body>
</html>
