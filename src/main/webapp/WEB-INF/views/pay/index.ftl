
<!DOCTYPE html>
<html>
    <title>支出状况</title>
    <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="gray-bg cfontsize">

    <div class="wrapper wrapper-content animated fadeInUp">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>当月家庭支出情况</h5>  
                        <div class="ibox-tools">
                        <#if user.type=1>
                      <button class="btn btn-primary btn-xs" id="add" >创建新支出</button>   
                      </#if>                       
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
                                        <th class="project-title">支出金额</th>
                                        <th class="project-title">支出时间</th>                          
                                        <th class="project-title">支出用途</th>
                                    </tr>
                                    <@action uri = "pPayWeb!page" nickname = "pays" />
								<#list pays.data.content?sort_by("number") as pay>
                               		<tr>
                                        <td class="project-title" >${pay.number!}</td>
                                        <td class="project-title" >${pay.userid!}</td>                                        
                                        <td class="project-title" >${pay.price!}</td>
                                        <td class="project-title" >${pay.time!}</td>
                                        <td class="project-title" >${pay.purpose!}</td>
                                        <#if user.type=1>
                                        <td class="project-actions">
                                            <button  class="btn btn-white btn-sm edit" ><i class="fa fa-folder"></i> 编辑 </button>
                                            <button class="btn btn-white btn-sm"  onclick="del('${pay.id}');"><i class="fa fa-pencil"></i> 删除 </button>
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
        <script src="static/plug/layer/layer.min.js"></script>
	<script src="static/plug/jquery-summer/core.js"></script>
	<script src="static/plug/jquery-summer/ajax.js"></script>
	<script src="static/plug/jquery-summer/form.js"></script>
	<script src="static/plug/jquery-summer/encapsulate-1.2.js"></script>
    <script>
	function del(id){
		$.ajax({
			type:'POST',
			url:"pay/delete.jhtml",
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
	$(".edit").click(function(){
		$summerLayer("修改支出信息","pay.jhtml?p=edit",["500px","550px"]);
	});
	$("#add").click(function(){
		$summerLayer("创建新支出","pay.jhtml?p=add",["500px","550px"]);
	});
    </script>
    </body>
    </html>

