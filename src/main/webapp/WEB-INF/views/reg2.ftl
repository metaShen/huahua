<!DOCTYPE html>
<html>
<head>
    <title>注册页面</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>    
        <div><h3>欢迎来到注册页面</h3></div>
            <form class="m-t" role="form" action="http://www.zi-han.net/theme/hplus/index.html">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" id="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码"  id="password">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="姓名" id="name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="性别" id="sex">
                </div>
                 <div class="form-group">
                    <input type="text" class="form-control" placeholder="年龄"  id="age">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="家庭身份" id="type">
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick = "reg()">注 册</button>
			 <a href="login.jhtml">点此登录</a>
            </form>
        </div>
    </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
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
			  type:0,
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
</body>
</html>
