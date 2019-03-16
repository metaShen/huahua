
<!DOCTYPE html>
<html>
    <title>收入状况</title>
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
                        <h5>卡务信息管理</h5>
                        <div class="ibox-tools">
                            <button class="btn btn-primary btn-xs" id="add" >添加新卡</button>
                            <a href="index.jhtml" class="btn btn-primary btn-xs">返回首页</a>   
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <th class="project-title">卡号</th>
                                        <th class="project-title">所属银行</th>
                                        <th class="project-title">卡类型</th>
                                        <th class="project-title">卡上余额</th>                          
                                        <th class="project-title">还款金额</th>
                                        <th class="project-title">还款日期</th>
                                    </tr>
                               		<tr>
                                        <td class="project-title">{account.banknumber}</td>
                                        <td class="project-title">{account.bank}</td>                                        
                                        <td class="project-title">{account.type}</td>
                                        <td  class="project-title">{account.balance}</td>
                                        <td class="project-title" >{account.repayment}</td>
                                        <td class="project-title" >{account.time}</td>
                                        <td class="project-actions">
                                            <button  class="btn btn-white btn-sm" id="edit"><i class="fa fa-folder"></i> 编辑 </button>
                                            <button class="btn btn-white btn-sm"  onclick="del('{account.id}');"><i class="fa fa-pencil"></i> 删除 </button>
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
	<script src="static/plug/jquery-summer/core.js"></script>
	<script src="static/plug/jquery-summer/ajax.js"></script>
	<script src="static/plug/jquery-summer/form.js"></script>
	<script src="static/plug/jquery-summer/encapsulate-1.2.js"></script>
	<script type="text/javascript" src=" https://code.jquery.com/jquery-3.3.1.js"></script>
	  <script src="static/plug/layer/layer.min.js"></script>
        <script>
	function del(id){
		$.ajax({
			type:'POST',
			url:"account/delete.jhtml",
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
	$("#edit").click(function(){
		$summerLayer("修改卡务信息","account.jhtml?p=edit",["500px","400px"]);
	});
	$("#add").click(function(){
		$summerLayer("添加新卡","account.jhtml?p=add",["500px","400px"]);
	});
    </script>
    </body>
    </html>

