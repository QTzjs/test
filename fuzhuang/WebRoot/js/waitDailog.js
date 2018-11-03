//数据加载进度条
function waitfor() {
	if ($(window.parent.document).find(".Loading_box").css("display") == "none") {
		$(window.parent.document).find(".Loading_box").css("display", "block");
		$(window.parent.document).find("#disDisable").css("display", "block");
	} else {
		$(window.parent.document).find(".Loading_box").css("display", "none");
		$(window.parent.document).find("#disDisable").css("display", "none");
	}
}


// 条件查询表单提交
function doSubmit(url) {
	//waitfor();
	
	waitfor();
	//var form=$('#form');
//	$('#form').ajaxSubmit(function (){
//		$.post($('#form').attr("action"),$('#form').serialize(),function(a,b){
//			alert(a);
//			
//		},"json");
//		return false;
//		//waitfor();
//	});
//	alert($('#form').serialize());
	jQuery.ajax({
		type : "POST",
		datatype : "json",
		url : $('#form').attr("action"),
		data : $('#form').serialize(),
		success : function(msg) {
			waitfor();
			alert("操作成功");
			window.location.href=url;
		}
	});
	// $('#form').submit(function() {
	// $(this).ajaxSubmit(function(resultJson) {
	// type : "POST",
	// url : $('#form').attr("action"),
	// data : $('#form').serialize(),
	// success : function(msg) {
	// alert(msg);//这里会弹出success
	// }
	// //回调操作
	// alert(99);
	// waitfor();
	//			   
	// });
	// return false; //阻止表单默认提交
	// });
	// $("#form").submit();

}

//条件查询表单提交
function doSubmitById(id,url) {
	var form="#"+id;
	waitfor();

	jQuery.ajax({
		type : "POST",
		datatype : "json",
		url : $(form).attr("action"),
		data : $(form).serialize(),
		success : function(msg) {
			waitfor();
			alert("操作成功");
			window.location.href=url;
		}
	});
}