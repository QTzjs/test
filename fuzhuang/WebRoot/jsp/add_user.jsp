<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="uuid" name="id">
<table class="editTable">
    <tr>
        <td class="label">员工名字</td>
        <td>
            <input type="text" name="user_name" data-toggle="topjui-textbox" data-options="required:true">
        </td>
        <td class="label">联系方式</td>
        <td>
            <input type="text" name="user_account" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
    <tr>
    	<td class="label">密码</td>
        <td>
            <input type="text" name="password" data-toggle="topjui-textbox" data-options="required:true">
        </td>
        <td class="label">权限</td>
        <td>
            <!-- <input type="text" name="user_level" data-toggle="topjui-textbox" data-options="required:true"> -->
                <input type="text" name="user_level"
                       data-toggle="topjui-combobox"
                       data-options="data:[{value:1,text:'普通员工'},{value:2,text:'管理员'}],required:true,panelHeight:65,">
        </td>
    </tr>
   
   <tr>
        <td class="label">入职日期</td>
        <td>
           <input type="text" name="user_time" data-toggle="topjui-datebox" data-options="required:true">
           <!-- <input name="user_time"
                   data-toggle="topjui-datetimebox"
                   data-options="required:true,
                   width:155,
                   showSeconds:false"> -->
        </td>
       
    </tr>
</table>

                      
