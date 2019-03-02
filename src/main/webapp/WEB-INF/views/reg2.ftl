<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>注册页面</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body >
<center>
                <form action = "#"  method="post">
          <h4 >注册：</h4>
                <p >欢迎来到注册页面</p>
<label>姓名:</label>
<input type = "text" name = "name"  id = "name" />
<br>
<label>性别:</label>
<input type = "text" name = "sex"   id = "sex"  />
<br>
<label>用户名:</label>
<input type = "text"  name = "username" id = "username" />
<br>
<label>密码:</label>
<input type = "text"  name = "password" id = "password" />
<br>
<label>年龄:</label>
<input type = "text" name = "age"  id = "age"  />
<br>
<button type = "button"  onclick = "reg()">注册</button>
</form>
 <p ><small>已经有账户了？</small><a href="login.jhtml">点此登录</a>
 </center>
</body>
<script>
function reg(){

	$.ajax({
		type:'POST',
		url: "user/register.jhtml",
		data:{password:$('#password').val(),
			  username:$('#username').val(),
			  age:$('#age').val(),
			  name:$('#name').val(),
			  sex:$('#sex').val(),
			  },
		success:function(data){
			if(!data.error){
				alert(data.data);
				location.href="login.jhtml";
			}else{
				alert(data.data);
			}
		},
		dataType:'json'
	});
}
</script>
</html>