<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" name="id">

<input type="hidden" name="plan_num" id="plan_num">
<input type="hidden" name="customer" >
            <input type="hidden" name="number">
<table class="editTable">
    <tr>
        <td class="label">工艺说明</td>
        <td>
            <input type="text" name="technology" data-toggle="topjui-textbox" data-options="required:false">
        </td>
        <td class="label">款式</td>
        <td>
             <input type="text" name="style" value="请选择款式"
                   data-toggle="topjui-combogrid"
                   data-options="id:'style',
                   width:150,
                   idField: 'style_name',
                   textField: 'style_name',
                   url:'stylegetStyles.action',
                   columns:[[
                       {field: 'style_name', title: '款式名称'}
                   ]]">
        </td>
    </tr>
    
    <tr>
        <td class="label">里料</td>
        <td>
            <input type="text" name="material" data-toggle="topjui-textbox" data-options="required:false">
        </td>
        <td class="label">主布</td>
        <td><input type="text" name="cloth" data-toggle="topjui-textbox" data-options="required:false"></td><!-- data-options="precision:3" 小数点 -->
    </tr>
    <tr>
        <td class="label">配布</td>
        <td><input type="text" name="distribution" data-toggle="topjui-textbox" data-options="required:false"></td><!-- data-options="precision:3" 小数点 -->
        
    	<td class="label">面料说明</td>
        <td><input type="text" name="fabric" data-toggle="topjui-textbox" data-options="required:false"></td><!-- data-options="precision:3" 小数点 -->
    </tr>
    <tr>
        <td class="label">下单日期</td>
        <td>
        	<input type="text" name="place_time" data-toggle="topjui-datebox" data-options="required:true">
           <!-- <input name="place_time"
                   data-toggle="topjui-datetimebox"
                   data-options="required:true,
                   width:155,
                   showSeconds:false"> -->
        </td>
        <td class="label">交货日期</td>
        <td>
            <input type="text" name="delivery_time" data-toggle="topjui-datebox" data-options="required:true">
            <!-- <input name="delivery_time"
                   data-toggle="topjui-datetimebox"
                   data-options="required:true,
                   width:155,
                   showSeconds:false"> -->
        </td>
    </tr>
   <tr>
        
        <td class="label">录入人</td>
        <td >
            <input type="text" name="input_name" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
</table>

                      
