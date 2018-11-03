<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input id="num" name="packe_num" type="hidden" value=""/>
<input id="num" name="p_num" type="hidden" value=""/>
<table class="editTable">
    <tr>
        <td class="label">颜色</td>
        <td>
            <input type="text" name="p_color" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,10]']">
        </td>
        <td class="label">尺码</td>
        <td>
            <input type="text" name="p_size" data-toggle="topjui-textbox" data-options="required:true,validType:['length[0,10]']">
        </td>
    </tr>
    <tr>
        <td class="label">件数</td>
        <td>
            <input type="text" name="p_number" data-toggle="topjui-numberbox" data-options="required:true,groupSeparator:'',validType:['length[0,10]'],min:1">
        </td>
        <!-- <td class="label">缸号</td>
        <td>
            <input type="text" name="cylinder" data-toggle="topjui-textbox">
        </td> -->
    </tr>
</table>
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
                      
