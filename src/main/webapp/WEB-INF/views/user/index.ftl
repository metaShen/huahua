<!DOCTYPE html>
<html>
<head>
 	  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
</head>
<body>
    <div>
        <div class="row">
                        <a href="account.jhtml">卡务信息</a>
                    </div>
		<div class="row">
                       <a href="revenue.jhtml">收入</a>
                    </div>
        <div class="row">
                        <a href="pay.jhtml">支出</a>
                    </div>
 		<div class="row">
                        <a href="total.jhtml">统计</a>
                    </div>           
 		<div class="row">
                       <a href="property.jhtml">自主缴费</a>
                    </div>                                 
        </div>
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
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    </body>
</html>
