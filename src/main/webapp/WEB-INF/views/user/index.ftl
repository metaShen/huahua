
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
                        <h5>家庭成员管理</h5>  
                        <div class="ibox-tools">                   
                            <a href="index.jhtml" class="btn btn-primary btn-xs">返回首页</a>  
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <th class="project-title">用户名</th>
                                        <th class="project-title">姓名</th>
                                        <th class="project-title">年龄</th>
                                        <th class="project-title">性别</th>                          
                                        <th class="project-title">家庭身份</th>
                                    </tr>
                           <#if user.type=1>
                                     <@action uri = "pUserWeb!page" nickname = "users" />
								<#list users.data.content?sort_by("age") as user>
                               		<tr>
                                        <td class="project-title" >${user.username }</td>
                                        <td class="project-title" >${user.name }</td>                                        
                                        <td class="project-title" >${user.age }</td>
                                        <td class="project-title" >${user.sex }</td>
                                        <td class="project-title" >${user.fam }</td>
                                        <td class="project-actions">
                         <button  class="btn btn-white btn-sm edit" onclick="give('${user.id}')" ><i class="fa fa-folder"></i> 赋权 </button>
                       <button class="btn btn-white btn-sm"  onclick="del('${user.id}');"><i class="fa fa-pencil"></i> 删除 </button>
                                        </td>
                                    </tr>
                                    </#list>
                                    <#else>
                              <@action uri = "pUserWeb!page" nickname = "users" />
								<#list users.data.content?sort_by("age") as user>
                               		<tr>
                                        <td class="project-title" >${user.username }</td>
                                        <td class="project-title" >${user.name }</td>                                        
                                        <td class="project-title" >${user.age }</td>
                                        <td class="project-title" >${user.sex }</td>
                                        <td class="project-title" >${user.fam }</td>
                                    </tr>
                                    </#list>
                                    </#if>
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
	    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <script>
	function del(id){
		$.ajax({
			type:'POST',
			url:"user/delete.jhtml",
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
        <script>
	function give(id){
		$.ajax({
			type:'POST',
			url:"user/give.jhtml",
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

