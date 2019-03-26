<!DOCTYPE html>
<html>
    <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="static/plug/hplus/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
</head>
<body class="gray-bg cfontsize">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>    
            <form class="m-t" role="form" action="#">
                <div class="form-group" >
                    <input type="text" class="form-control" placeholder="缴费人" id="userid"  >
                </div>
                
                     <div class="form-group">
                    <select class="form-control" name="type" id="type" >
                                        <option value="0">水费</option>
                                        <option value="1">网费</option>
                                        <option value="2">物业费</option>
                                        <option value="3">其它</option>
                                    </select>
                </div>
                                <div class="form-group">
                    <input type="text" class="form-control" placeholder="需缴费金额" id="price">
                </div>
                 <div class="form-group"  id="data_1">
                  <div class="input-group date">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input type="text" class="form-control"  id="day" placeholder="截止日期">
                            </div>
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="add();">添加</button>
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
    		url: "property/add.jhtml",
    		data:{
    		userid:$('#userid').val(),
    		price:$('#price').val(),
    		type:options.val(),
    		time:("-"),
    		day:$('#day').val(),
    		},
    		success:function(data){
    			if(!data.error){
    				alert(data.data);
    				location.href="property.jhtml";
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

