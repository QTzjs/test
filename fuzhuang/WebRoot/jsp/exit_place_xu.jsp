<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="editTable" id="editTable">
    <tr>
        <td class="label">
        	<input id="pla" type="hidden" name="place" value="">
        	工序名称
        </td>
        <td>
        	单价
        </td>
    </tr>
</table>

<script>
 //request('place');
 var pla=window.place;
  $(function () {
  		//隐藏文本框录入订单号
     	/* $("#pla").val(pla); */
        $.ajax({
        	url:"placefindAllByplace.action?place="+pla,
        	type:"post",
        	dataType: "json",
        	success:function(data){
        		document.getElementById("editTable").innerHTML="";
        		var daima="<tr><td class=\"label\"><input id=\"pla\" type=\"hidden\" name=\"place\" value=\""+pla+"\">工序名称</td><td>单价</td></tr>";
        		var list = data.list;
        		for(var i=0;i<list.length;i++){
        			daima+="<tr><td class=\"label\"><input type=\"hidden\" name=\"id\" value=\""+list[i].id+"\">"+list[i].technology_name+"</td><td><input type=\"text\" name=\"price\" value=\""+list[i].price+"\" data-toggle=\"topjui-numberbox\" data-options=\"precision:2,prompt:'保留2位小数'\"></td></tr>";
        		}
        		document.getElementById("editTable").innerHTML=daima;
        	}
        });
        });
</script>
