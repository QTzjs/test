<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="editTable">
    <tr>
        <td>
              <input id="pla" type="hidden" name="place" value="">
              <input type="text" name="zd"
                       data-toggle="topjui-combobox"
                       data-options="prompt:'选择查询字段',data:[{value:'place_num',text:'订单号'},{value:'packe_num',text:'包号'}
                       ,{value:'number',text:'数量'},{value:'t_name',text:'工序名称'},{value:'job_number',text:'提交人工号'},{value:'user_name',text:'提交人'},{value:'sub_time',text:'提交时间'}]">
        </td>
        <td>
            <input type="text" name="tj"
                       data-toggle="topjui-combobox"
                       data-options="prompt:'选择查询条件',data:[{value:'>',text:'大于'},{value:'<',text:'小于'}
                       ,{value:'=',text:'等于'},{value:'>=',text:'大于或等于'},{value:'<=',text:'小于或等于'},{value:'contains',text:'包含'},{value:'!=',text:'不等于'},{value:'endwith',text:'以...结尾'},{value:'beginwith',text:'以...开头'}]">
        </td>
        <td>
             <input type="text" name="zhi" data-toggle="topjui-textbox" data-options="required:true">
        </td>
    </tr>
</table>
<script>
 //request('place');
 var pla=window.plan_num;
 $(function () {
        //隐藏文本框录入订单号
        $("#pla").val(pla);
        });
</script>