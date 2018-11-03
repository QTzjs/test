<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="uuid" name="id">
<table class="editTable">
    <tr>
        <td class="label">订单号</td>
        <td>
            <input type="text" name="plan_num" data-toggle="topjui-textbox" data-options="required:true,validType:['checkMaterial','length[0,20]']">
        </td>
        <td class="label">款式</td>
        <td>
         <!-- <input type="text" name="style  value="请选择款式"
                        data-toggle="topjui-combogrid"
                   data-options="id:'style',
                   width:150,
                   idField: 'style_name',
                   textField: 'style_name',
                   url:'stylegetStyles.action',
                   columns:[[
                       {field: 'style_name', title: '款式名称'}
                   ]]"> -->
              <input type="text" name="style"
                       data-toggle="topjui-combobox"
                       data-options="required:true,
                       panelHeight:300,
                       valueField:'style_name',
                       textField:'style_name',
                       url:'stylegetStyles.action?biao=1'">
        </td>
    </tr>
    <tr>
    	<td class="label">客户名称</td>
        <td>
            <input type="text" name="customer" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,20]']">
        </td>
        <td class="label">数量</td>
        <td>
            <input type="text" name="number" data-toggle="topjui-numberbox" data-options="min:0,required:true,groupSeparator:'',validType:['length[0,10]']">
        </td>
    </tr>
    <tr>
    	<td class="label">交货日期</td>
        <td>
            <input type="text" name="delivery_time" 
            data-toggle="topjui-datebox" 
            data-options="" value="javascript:Initialization()">
        </td>
        <td class="label">下单日期</td>
        <td>
        	 <input type="text" id="place_time" name="place_time" 
        	 data-toggle="topjui-datebox" 
        	 data-options="" value="javascript:Initialization()"> 
        </td>
    </tr>
    <tr>
        <td class="label">录入人</td>
        <td >
            <input type="text" name="input_name" data-toggle="topjui-textbox" data-options="validType:['length[0,20]']" value="${sessionScope.admin.admin_name }">
        </td>
    </tr>
    <!-- <tr>
        <td class="label">里料</td>
        <td>
            <input type="text" name="material" data-toggle="topjui-textbox" data-options="required:false">
        </td>
        <td class="label">主布</td>
        <td><input type="text" name="cloth" data-toggle="topjui-textbox" data-options="required:false"></td>
    </tr>
    <tr>
        <td class="label">配布</td>
        <td><input type="text" name="distribution" data-toggle="topjui-textbox" data-options="required:false"></td>
        
    	<td class="label">面料说明</td>
        <td><input type="text" name="fabric" data-toggle="topjui-textbox" data-options="required:false"></td>
    </tr> -->
     <!-- <tr>
       <td class="label">工艺说明</td>
        <td>
            <input type="text" name="technology" data-toggle="topjui-textbox" data-options="required:false">
        </td> 
        
    </tr>-->
   
</table>
<script type="text/javascript">
	function Initialization(){
		var dat = new Date().Format("yyyy-MM-dd");
		$("#place_time").iDatebox("setValue",dat);
	}
</script>

                      
