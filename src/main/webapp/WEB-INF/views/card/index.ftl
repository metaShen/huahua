
<!DOCTYPE html>
<html>
    <title>卡务信息</title>
    <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="gray-bg">

    <div class="wrapper wrapper-content animated fadeInUp">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>卡务信息</h5>  
                        <div class="ibox-tools">
                                            
                            <a href="index.jhtml" class="btn btn-primary btn-xs">返回首页</a>  
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <th class="project-title">卡号</th>
                                        <th class="project-title">持卡人</th>
                                        <th class="project-title">绑定时间</th>
                                    </tr>
                               		<tr>
                                        <td class="project-title" >bankid</td>
                                        <td class="project-title" >userid</td>                                        
                                        <td class="project-title" >time</td>
                                        <td class="project-actions">
                                            <button class="btn btn-white btn-sm"  onclick="del('${card.id}');"><i class="fa fa-pencil"></i> 删除 </button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script src="static/plug/hplus/js/jquery.min63b9.js?v=2.1.4"></script>
    <script src="static/plug/hplus/js/bootstrap.min14ed.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
        <script src="static/plug/layer/layer.min.js"></script>
	<script src="static/plug/jquery-summer/core.js"></script>
	<script src="static/plug/jquery-summer/ajax.js"></script>
	<script src="static/plug/jquery-summer/form.js"></script>
	<script src="static/plug/jquery-summer/encapsulate-1.2.js"></script>
    <script>
	function del(id){
		$.ajax({
			type:'POST',
			url:"card/delete.jhtml",
			data:{id:id},
			success: function(data){
				if(!data.error){
					alert(data.data);
					location.reload();
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

