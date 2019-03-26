<!DOCTYPE html>
<html>
<head>
    <title>注册页面</title>
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
        <div><h3 class="cfontsize">欢迎来到注册页面</h3></div>
            <form class="m-t" action="#">
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
                    <input type="text" class="form-control" placeholder="家庭身份" id="fam">
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick = "reg()">注 册</button>
			 <a href="login.jhtml">点此登录</a>
            </form>
        </div>
    </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    	<script type="text/javascript" src=" https://code.jquery.com/jquery-3.3.1.js"></script>
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
			  fam:$('#fam').val(),
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
