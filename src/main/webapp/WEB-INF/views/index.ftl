
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<style type="text/css">
            ul,li{
                list-style-type: none;
            }
        </style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-2">
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <ul class="category-list" style="padding: 0" id="menu">
                                <li>
                                    <a href="#"> <i class="fa fa-circle text-navy"></i>卡务信息</a>
                                    <ul class="category-list" style="padding: 0">
                                    <li>
                                    <a href="#" id="add"> <i class="fa fa-angle-right"></i>新增卡务</a>
                                </li>
                                <li>
                                    <a href="card.jhtml"> <i class="fa fa-angle-right"></i>查看卡务</a>
                                </li>
                                <li>
                                    <a href="account.jhtml"> <i class="fa fa-angle-right"></i>管理卡务</a>
                                </li>
                                    </ul>
                                </li>
                                 <li>
                                    <a href="#"> <i class="fa fa-circle text-danger"></i>收支账单</a>
                                    <ul class="category-list" style="padding: 0">
                                    <li>
                                    <a href="revenue.jhtml"> <i class="fa fa-angle-right"></i>收入管理</a>
                                </li>
                                <li>
                                    <a href="pay.jhtml"> <i class="fa fa-angle-right"></i>支出管理</a>
                                </li>
                                <li>
                                    <a href="total.jhtml"> <i class="fa fa-angle-right"></i> 账单统计</a>
                                </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-circle text-warning"></i> 自主缴费</a>
                                    <ul class="category-list" style="padding: 0">
                                    <li>
                                    <a href="property.jhtml"> <i class="fa fa-angle-right"></i>日常缴费</a>
                                </li>
                                <li>
                                    <a href="pay.jhtml"> <i class="fa fa-angle-right"></i>定时提醒</a>
                                </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="user.jhtml" > <i class="fa fa-circle text-primary"></i>成员管理</a>
                                </li>
                                <li>
                                    <a href="#" > <i class="fa fa-circle"></i>系统设置</a>
                                     <ul class="category-list" style="padding: 0">
                                    <li>
                                    <a href="" class="font"> <i class="fa fa-angle-right"></i>字体设置</a>
                                </li>
                                <li>
                                    <a onclick="logout();"> <i class="fa fa-angle-right"></i>退出</a>
                                </li>
                                    </ul>
                                </li>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>     
                <div class="col-sm-6">
                <div class="ibox float-e-margins">
               <div class="ibox-title">
                <h5>欢迎您,${user.name}</h5>
                 </div>
                 
                    <div class="ibox-title">
                        <h5><strong>您的个人信息</strong></h5>
                    </div>
                    <div>
                        <div class="ibox-content profile-content">
                            <h4><strong>用户名:</strong>${user.username }</h4>
                            <h4><strong>密码:</strong>${user.password }</h4>
                            <h4><strong>性别:</strong>${user.sex }</h4>
                            <h4><strong>年龄:</strong>${user.age }</h4>
                            <h4><strong>家庭身份:</strong>${user.fam}</h4>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    <script src="static/plug/hplus/js/plugins/iCheck/icheck.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
     <script type="text/javascript" src=" https://code.jquery.com/jquery-3.3.1.js"></script>
         	<script src="static/plug/jquery-summer/core.js"></script>
	<script src="static/plug/jquery-summer/ajax.js"></script>
	<script src="static/plug/jquery-summer/form.js"></script>
	<script src="static/plug/jquery-summer/encapsulate-1.2.js"></script>
	        <script src="static/plug/layer/layer.min.js"></script>
           <script>
           function logout(){
    		$.ajax({
    			type:'POST',
    			url:"user/logout.jhtml",
    			success: function(data){
    				if(!data.error){
    					alert(data.data);
    					location.href="login.jhtml";
    				}else{
    					alert(data.data);
    				}
    			},
    			dataType:"json"
    		});
    		}
       </script>

       <script type="text/javascript">
            $(function() {
                $("#menu ul").css("display","none");
                $("#menu a").on("click",function(){
                	$(this).next().toggle();
                });
            });
            
            $("#add").click(function(){
        		$summerLayer("添加新卡","account.jhtml?p=add",["500px","400px"]);
        	});
        </script>
        
</body>
</html>

