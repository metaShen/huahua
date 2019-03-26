<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg cfontsize">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
        <a class="bigg"> 大号字体</a>
          <a class="small">小号字体</a>
        <div><h3 >卡务账户管理系统</h3></div>
            <form class="m-t" role="form" action="#">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" id="username" >
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" id="password">
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="login();">登 录</button>
			 <a href="reg.jhtml">注册一个新账号</a>
                </p>
            </form>
        </div>
    </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
        <script>
function login(){
	$.ajax({
		type:'POST',
		url: "user/login.jhtml",
		data:{username:$('#username').val(),password:$('#password').val()},
		success:function(data){     
			if(!data.error){
		        alert(data.data);
	            location.href = "index.jhtml";
			}else{
				alert(data.data);
			}
		},
		dataType:'json'
	});
}
</script>
<script type="text/javascript"> 
$(function(){ 
$("a").click(function(){ 
var thisEle = $("*").css("font-size"); 
var textFontSize = parseFloat(thisEle , 10); 
var unit = thisEle.slice(-2); //获取单位 
var cName = $(this).attr("class"); 
if(cName == "bigg"){ 
if( textFontSize <= 40){ 
textFontSize += 18; 
} 
}else if(cName == "small"){ 
if( textFontSize >= 12 ){ 
textFontSize -= 18; 
} 
} 
$("*").css("font-size", textFontSize + unit); 
}); 
}); 
</script> 
</body>
</html>
