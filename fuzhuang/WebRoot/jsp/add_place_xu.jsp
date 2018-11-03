<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="editTable">
    <tr>
        <td class="label">工序名称</td>
        <td>
             <input id="pla" type="hidden" name="place" value="">
                 <input type="text" name="techmology_name"
                        data-toggle="topjui-combobox"
                        data-options="required:true,
                    prompt:'必填',
                    panelHeight:460,
                    valueField:'name',
                    textField:'name',
                    url:'teList.action?biao=1'">
                   
        </td>
    </tr>
     <tr>
    	<td class="label">工序单价</td>
        <td>
            <!--  <input type="text"   name="price" data-toggle="topjui-combogrid" data-options=""> -->
             <input type="text" name="price" id="price" 
             data-toggle="topjui-numberbox" 
             data-options="min:0.000,precision:3,groupSeparator:'',prompt:'保留3位小数'">
        </td>
    </tr>
</table>

<script>
 //request('place');
 var pla=window.place;
  $(function () {
        //隐藏文本框录入订单号
        $("#pla").val(pla);
        });
</script>
