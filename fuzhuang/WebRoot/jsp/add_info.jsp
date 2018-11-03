<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="uuid" name="id">
<input id="num" name="P_num" type="hidden" value=""/>
<style>
		#zhiban{width: 450px;margin: 20px auto;border: 1px solid #ccc;text-align: center;}
        #zhiban th{font-size:20px;font-family:'黑体'; height: 30px;}
        #zhiban td{font-size:16px; line-height: 20px;  height: 30px;}
</style>
	
<table id="zhiban" border="1" >
    <tr>
        <th width="10%">操作</th>
        <th width="25%">颜色</th>
        <th width="20%">尺码</th>
        <th width="25%">包数</th>
        <th width="20%">件数</th>
    </tr>
    <tr>
        <td><a href="javascript:void(0)" title="删除" onclick="delet1(this)"><i class="fa fa-minus"></i></a></td>
        <td><input type="text" name="p_color" data-toggle="topjui-textbox" data-options="width:110,required:true,validType:['length[0,10]']"></td>
        <td><input type="text" name="p_size" data-toggle="topjui-textbox" data-options="width:90,required:true,validType:['length[0,10]']"></td>
        <td><input type="text" name="packe_num" data-toggle="topjui-numberbox" data-options="width:110,required:true,groupSeparator:'',min:0,max:300"></td>
        <td><input type="text" name="p_number" data-toggle="topjui-numberbox" data-options="width:90,required:true,groupSeparator:'',validType:['length[0,10]'],min:0"></td>
    </tr>
    <tr id="tr1">
        <td><a href="javascript:void(0)" title="新增" onclick="add1()"><i class="fa fa-plus"></i></a></td><!-- <i class="fa fa-minus"></i> -->
    </tr>
</table>
<script type="text/javascript">
    /* 纸板材料 */
    function add1(){
        var html1="";
        html1 += "<tr>";
        html1 += "<td><a href=\"javascript:void(0)\" title=\"删除\" onclick=\"delet1(this)\"><i class=\"fa fa-minus\"></i></a></td>";
        html1 += "<td><input name=\"p_color\" data-toggle=\"topjui-textbox\" data-options=\"width:110,required:true,validType:['length[0,10]']\"></td>";
        html1 += "<td><input name=\"p_size\" data-toggle=\"topjui-textbox\" data-options=\"width:90,required:true,validType:['length[0,10]']\"/></td>";
        html1 += "<td><input name=\"packe_num\" data-toggle=\"topjui-numberbox\" data-options=\"width:110,required:true,groupSeparator:'',max:300,min:0\"></td>";
        html1 += "<td><input name=\"p_number\" data-toggle=\"topjui-numberbox\" data-options=\"width:90,required:true,groupSeparator:'',validType:['length[0,10]'],min:0\"/></td>";
        html1 += "</tr>";
        $("#tr1").before(html1);
        $.parser.parse($("#tr1").parent());//重新渲染组件
    };
    function delet1(e1){
        e1.parentElement.parentElement.remove();
    };
</script>
                
<!-- <table class="editTable">
    <tr>
        <td class="label">颜色</td>
        <td>
            <input type="text" name="p_color" data-toggle="topjui-textbox" data-options="required:true">
        </td>
        <td class="label">尺码</td>
        <td>
            <input type="text" name="p_size" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
    <tr>
        <td class="label">包数</td>
        <td>
            <input type="text" name="packe_num" data-toggle="topjui-textbox" data-options="required:true">
        </td>
        <td class="label">件数</td>
        <td>
            <input type="text" name="P_number" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
    
</table> -->
<script>
 //request('place');
        var pla=window.place; 
      /* var linkDataProperty = $("#plan_num").val(); */
        if(pla != null && pla != ""){
       		$("#num").val(pla);
       		}else{
       			alert("请先选中订单！");
       		}
       
</script>
                      
