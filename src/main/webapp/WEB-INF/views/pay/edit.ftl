<!DOCTYPE html>
<html>
  <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
            <link href="static/plug/hplus/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
      
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>    
            <form class="m-t" role="form" action="#">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="序号" id="number" >
                </div>
                                <div class="form-group">
                    <input type="text" class="form-control" placeholder="姓名" id="userid">
                </div>
                     <div class="form-group">
                    <input type="text" class="form-control" placeholder="支出金额" id="price" >
                </div>
                  <div class="form-group">
                    <input type="text" class="form-control" value="2019-01-01" placeholder="支出时间" id="time">
                    <div class="input-group date">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input type="text" class="form-control" value="2014-11-11">
                            </div>
                </div>
   				  <div class="form-group">
                    <input type="text" class="form-control" placeholder="支出用途" id="purpose" >
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="edit();">修改</button>
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
    function edit(){
    	$.ajax({
    		type:'POST',
    		url: "revenue/edit.jhtml",
    		data:{
    		number:$('#number').val(),
    		price:$('#price').val(),
    		usrid:$('#userid').val(),
    		source:$('#source').val(),
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

