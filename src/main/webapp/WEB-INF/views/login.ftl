<html>
<title>登陆页面</title>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<center>
<form action = "#">
<label>用户名:</label>
<input type ="text" name="username" id = "username"/>
<br>
<label>密码:</label>
<input type = "password" name = "password" id = "password" />
<br>
<button type = "button" onclick = "login()">登陆</button>
<a href="reg.jhtml"><button type="button" >注册</button></a>
</form>
</center>
</body>
<script>
function login(){
	$.ajax({
		type:'POST',
		url: "user/login.jhtml",
		data:{username:$('#username').val(),password:$('#password').val()},
		success:function(data){
			if(!data.error){
				location.href=data.data;
			}else{
				alert(data.data);
			}
		},
		dataType:'json'
	});
}
</script>
</html>