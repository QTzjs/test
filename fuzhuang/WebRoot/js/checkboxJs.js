$(document)
		.ready(
				function() {
					// 全选
					$("#allChk").click(
							function() {
								$("input[name='subChk']").prop("checked",
										this.checked);
							});
					// 单选
					var subChk = $("input[name='subChk']")
					subChk
							.click(function() {
								$("#allChk")
										.prop(
												"checked",
												subChk.length == subChk
														.filter(":checked").length ? true
														: false);
							});

				});