<html>
<head>
   <title>用户登录</title>
   <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
</head>
<boby>
  <center>
     <form action="#">
       <lable>用户名：</lable>
       <input type="text" name="username" id="username">
       <br>
       <lable>密码：</lable>
       <input type="password" name="password" id="password">
       <br>
       <button type="button" onclick="login()">登陆</button>
       <a href="reg.jhtml"><button type="button">注册</button></a>
     </form>
  </center>
</boby>
<script>
    function login(){
    	var username = $("#username").val();
    	var password = $("#password").val();
    	$.ajax({
    		type:'post',
    		url:"user/login.jhtml",
    		data: {username:username,password:password},
    		success:function(data){
    			if(!data.error){
    				alert(data.data);
    				location.href="book.jhtml";
    			}else{
    				alert(data.data);
    			}
    		},
    		dataType:"json"
    	});
    }
 
</script>
</html>