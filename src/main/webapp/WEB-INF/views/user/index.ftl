
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-1">
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <ul class="category-list" style="padding: 0">
                                <li>
                                    <a href="account.jhtml"> <i class="fa fa-circle text-navy"></i>卡务信息</a>
                                </li>
                                <li>
                                    <a href="revenue.jhtml"> <i class="fa fa-circle text-danger"></i>收入账单</a>
                                </li>
                                <li>
                                    <a href="pay.jhtml"> <i class="fa fa-circle text-primary"></i> 支出账单</a>
                                </li>
                                <li>
                                    <a href="total.jhtml"> <i class="fa fa-circle text-info"></i> 账单统计</a>
                                </li>
                                <li>
                                    <a href="property.jhtml"> <i class="fa fa-circle text-warning"></i> 自主缴费</a>
                                </li>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-11 animated fadeInRight">
                            <div class="mail-box-header">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-sm btn-primary" onclick="logout();">退出
                                </button>
                            </div>
                        </div>
                </div>
                <div class="mail-box">     
                    <table class="table table-hover table-mail">
                        <tbody>
                            <tr class="unread">
                                <td class="check-mail">
                                    <input type="checkbox" class="i-checks">
                                </td>
                                <td class="mail-ontact"><a href="mail_detail.html">支付宝</a>
                                </td>
                                <td class="mail-subject"><a href="mail_detail.html">支付宝提醒</a>
                                </td>
                                <td class=""><i class="fa fa-paperclip"></i>
                                </td>
                                <td class="text-right mail-date">昨天 10:20</td>
                            </tr>
                            <tr class="unread">
                                <td class="check-mail">
                                    <input type="checkbox" class="i-checks" checked>
                                </td>
                                <td class="mail-ontact"><a href="mail_detail.html">Amaze UI</a>
                                </td>
                                <td class="mail-subject"><a href="mail_detail.html">Amaze UI Beta2 发布</a>
                                </td>
                                <td class=""></td>
                                <td class="text-right mail-date">上午10:57</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    <script src="static/plug/hplus/js/plugins/iCheck/icheck.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
          
           <script> function logout(){
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
</body>
</html>

