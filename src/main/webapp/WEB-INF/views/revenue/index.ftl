
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
                        <h5>当月家庭收入情况</h5>
                        <div class="ibox-tools">
                            <button class="btn btn-primary btn-xs" id="add" >创建新收入</button>
                            <a href="index.jhtml" class="btn btn-primary btn-xs">返回首页</a>   
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <th class="project-title">序号</th>
                                        <th class="project-title">姓名</th>
                                        <th class="project-title">收入金额</th>
                                        <th class="project-title">收入时间</th>                          
                                        <th class="project-title">收入来源</th>
                                    </tr>
                                     	 <@action uri = "pRevenueWeb!page" nickname = "revenues" />
								<#list revenues.data.content as revenue>
                               		<tr>
                                        <td class="project-title">${revenue.number!}</td>
                                        <td class="project-title">${revenue.userid!}</td>                                        
                                        <td class="project-title">${revenue.price!}</td>
                                        <td  class="project-title">${revenue.time!}</td>
                                        <td class="project-title" >${revenue.source!}</td>
                                        <#if user.type=1>
                                        <td class="project-actions">
                                            <button  class="btn btn-white btn-sm" id="edit"><i class="fa fa-folder"></i> 编辑 </button>
                                            <button class="btn btn-white btn-sm"  onclick="del();"><i class="fa fa-pencil"></i> 删除 </button>
                                        </td>
                                        </#if>
                                    </tr>
                                     </#list>
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
			url:"revenue/delete.jhtml",
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
		$summerLayer("修改收入信息","revenue.jhtml?p=edit",["500px","400px"]);
	});
	$("#add").click(function(){
		$summerLayer("创建新收入","revenue.jhtml?p=add",["500px","400px"]);
	});
    </script>
    </body>
    </html>

