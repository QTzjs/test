<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table class="editTable">
		
    <tr>
    	
        <td class="label">
        	<input type="hidden" id="id" name="id" >
			<input type="hidden" id="K_id" name="K_id">
        	颜色
        </td>
        <td>
        
            <input type="text" name="size" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
    <tr>
        <td class="label">尺寸</td>
        <td>
            <input type="text" name="color" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
</table>
<script>
 //request('place');
 var pla=window.K_id;
  $(function () {
        //隐藏文本框录入订单号
        $("#K_id").val(pla);
        });
</script>


                      
