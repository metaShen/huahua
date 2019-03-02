<html>
<head>
   <title>用户注册</title>
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
       <lable>确认密码：</lable>
       <input type="password" name="ipassword" id="ipassword">
       <br>
       <lable>姓名：</lable>
       <input type="text" name="name" id="name">
       <br>
       <lable>用户类型：</lable>
       <input type="text" name="type" id="tyep">
       <br>
       <button type="button" onclick="reg()">注册</button>
     </form>
  </center>
</boby>
<script>
    function reg(){
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