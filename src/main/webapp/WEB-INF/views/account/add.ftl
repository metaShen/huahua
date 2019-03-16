<!DOCTYPE html>
<html>
    <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">

    <link href="static/plug/hplus/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>    
            <form class="m-t" role="form" action="#">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="所属银行" id="bank" >
                </div>
                                <div class="form-group">
                    <input type="text" class="form-control" placeholder="持卡账号" id="banknumber">
                </div>
                     <div class="form-group">
                    <select class="form-control" name="" id="type">
                                        <option value="0">储蓄卡</option>
                                        <option value="1">信用卡</option>
                                        <option value="2">其它</option>
                                    </select>
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="账户余额" id="balance" >
                </div>
                
                  <div class="form-group"  id="data_1">
                  <div class="input-group date">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input type="text" class="form-control" value="2019-01-01" id="time">
                            </div>
                </div>
                
   				  <div class="form-group">
                    <input type="text" class="form-control" placeholder="还款金额" id="repayment" >
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="add();">提交</button>
            </form>
        </div>
    </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    <script src="static/plug/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="static/plug/hplus/js/plugins/cropper/cropper.min.js"></script>
    <script src="static/plug/hplus/js/demo/form-advanced-demo.min.js"></script>
        <script src="static/plug/layer/layer.min.js"></script>
        <script>
    function add(){
    	var options = $("#type option:selected");
    	$.ajax({
    		type:'POST',
    		url: "account/add.jhtml",
    		data:{
    		bank:$('#bank').val(),
    		banknumber:$('#banknumber').val(),
    		type:options.val(),
    		repayment:$('#repayment').val(),
    		balance:$('#balance').val(),
    		time:$('#time').val(),
    		},
    		success:function(data){
    			if(!data.error){
    				alert(data.data);
    				location.reload();
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

