<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>
</title>
<script type="text/javascript" src="jquery-1.11.1.js"></script>
<script type='text/javascript' src='strophe-custom-1.0.0.js'></script>
<script type='text/javascript' src='json2.js'></script>
<script type="text/javascript" src="easemob.im-1.0.3.js"></script>
<script type="text/javascript" src="bootstrap.js"></script> 

<script>
$("#regist").on('click', function() {
	var options = {
		username : 'zjj8',
		password : '123456',
		appKey : 'easemob-demo#chatdemoui',
		success : function(result) {
				//注册成功;
		},
		error : function(e) {
				//注册失败;
		}
	};
	Easemob.im.Helper.registerUser(options);
  });
 </script>
</head>
<body>

<div id="regist-box">
	user<input type="text" id="usename" />
	password<input type="password" id="password" />
	nickname<input type="text" id="nickname" />
	<input type="button" value="regist"  id="regist" />
</div>
</body>
</html>