<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../demo/jquery-labelauty.css">
<style>
ul { list-style-type: none;}
li { display: inline-block;}
li { margin: 10px 0;}
input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>
<input id="pla" type="hidden" name="place" value="">
<ul id="dowebok" class="dowebok">
	<!-- <li><input class="techmology_name" type="checkbox" name="techmology_name" disabled checked data-labelauty="HTML"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="CSS"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" value="3" data-labelauty="JavaScript"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="SEO"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="PHP"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty="JAVA"></li>
	<li><input class="techmology_name" type="checkbox" name="techmology_name" data-labelauty=".NET"></li> -->
</ul>
<script src="../demo/jquery-labelauty.js"></script>
<script>
 //request('place');
 var pla=window.place;
 $(function () {
        //隐藏文本框录入订单号
        $("#pla").val(pla);
        
        /* $(':input').labelauty(); */
        $.ajax({
        	url:'teList.action?biao=1',
        	dataType:'json',
        	success:function(data){
        		var daima="";
        		for(var i=0;i<data.length;i++){
        			var techmology = data[i];
        			daima+="<li style=\"margin: 10px 3px;\"><input class=\"techmology_name\" type=\"checkbox\" name=\"techmology_name\" value=\""+techmology.name+"\" data-labelauty=\""+techmology.name+"\"></li>";
        		}
        		document.getElementById("dowebok").innerHTML=daima;
        		$('.techmology_name').labelauty();
        	}
        });
        /* $('.techmology_name').labelauty(); */
        
 });
</script>
