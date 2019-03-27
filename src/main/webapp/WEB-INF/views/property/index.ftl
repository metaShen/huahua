
<!DOCTYPE html>
<html>
    <title>收入状况</title>
    <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg ">

    <div class="wrapper wrapper-content animated fadeInUp">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                
                    <div class="ibox-title">
                        <h5>自主缴费信息</h5>
                        <div class="ibox-tools">
                         <#if user.type=1>
                      	    <a class="btn btn-primary btn-xs " href="property.jhtml?p=add" >新缴费</a>
                            </#if>
                            <a href="index.jhtml" class="btn btn-primary btn-xs">返回首页</a>   
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <th class="project-title">需缴费人</th>
                                        <th class="project-title">缴费类型</th>
                                        <th class="project-title">需缴费金额</th>
                                        <th class="project-title">截止日期</th>                          
                                        <th class="project-title">提醒时间</th>
                                    </tr>
                                    <@action uri = "pPropertyWeb!page" nickname = "propertys" />
								<#if user.type == 0>
								<#list propertys.data.content as property>
								<#if property.userid == user.name>
                               		<tr>
                               		
                                      <td class="project-title">${property.userid}</td>                        
                    			 <#if property.type=0>                       
                                         <td class="project-title">水费</td>  
                                  <#elseif property.type=1> 
                                        <td class="project-title">网费</td>  
                                   <#elseif property.type=2>    
                                   		 <td class="project-title">物业费</td>     
                                        <#elseif property.type=3>    
                                   		 <td class="project-title">其它</td>     
                                    </#if>
                                    <td class="project-title">${property.price}</td>   
                                    <td class="project-title">${property.day}</td>                           
                                    <td class="project-title">${property.time!("-")}</td>          
                                        <td class="project-actions">
                                      <#if user.type=0>
                                            <button  class="btn btn-white btn-sm " onclick="pay('${property.id}','${property.price}');"> 立即缴费 </button>
                                            <button  class="btn btn-white btn-sm " onclick="clock('${property.id}');"> 添加提醒 </button>
                                      <#else>
                                            <button class="btn btn-white btn-sm"  onclick="del('${property.id}');"><i class="fa fa-pencil"></i> 删除 </button>
                                        </td>
                                        </#if>
                                    </tr>
                                    </#if>
                                    </#list>
                                    
                                    <#else>
                                    <#list propertys.data.content as property>
								
                               		<tr>
                               		
                                      <td class="project-title">${property.userid}</td>                        
                    			 <#if property.type=0>                       
                                         <td class="project-title">水费</td>  
                                  <#elseif property.type=1> 
                                        <td class="project-title">网费</td>  
                                   <#elseif property.type=2>    
                                   		 <td class="project-title">物业费</td>     
                                        <#elseif property.type=3>    
                                   		 <td class="project-title">其它</td>     
                                    </#if>
                                    <td class="project-title">${property.price}</td>   
                                    <td class="project-title">${property.day}</td>                           
                                    <td class="project-title">${property.time!("-")}</td>          
                                        <td class="project-actions">
                                    
                                            <button  class="btn btn-white btn-sm " onclick="pay('${property.id}','${property.price}');"> 立即缴费 </button>
                                            <button  class="btn btn-white btn-sm " onclick="clock('${property.id}');"> 添加提醒 </button>
                                   
                                            <button class="btn btn-white btn-sm"  onclick="del('${property.id}');"><i class="fa fa-pencil"></i> 删除 </button>
                                        </td>
                                       
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
	<script src="static/plug/jquery-summer/core.js"></script>
	<script src="static/plug/jquery-summer/ajax.js"></script>
	<script src="static/plug/jquery-summer/form.js"></script>
	<script src="static/plug/jquery-summer/encapsulate-1.2.js"></script>
	<script type="text/javascript" src=" https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="static/plug/hplus/js/plugins/layer/layer.min.js"></script>

    <script src="static/plug/hplus/js/plugins/layer/extend/layer.ext.js"></script>
        <script>
	function del(id){
		$.ajax({
			type:'POST',
			url:"property/delete.jhtml",
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
    function pay(id,price){
    	layer.prompt({title: '请输入卡号进行缴费', formType: 3},function(number, index){		  
    		$.ajax({
    			type:'POST',
    			url:"property/pay.jhtml",
    			data:{
    				id:id,
    				price:price,
    				number:number,
    				},
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
    		layer.close(index);
    			});
    	}
    </script>
           <script>
           var date = new Date();               
           var newyear = date.getFullYear();    
           var newmonth = date.getMonth() + 1;   
           var day = date.getDate();          
           newmonth = (newmonth<10 ? "0"+newmonth:newmonth);  
           var newdate =newyear+"-"+ newmonth + "-" + day;
           
    function clock(id){
    	layer.prompt({title: '请输入提醒日期,格式为xxxx-xx-xx', formType: 3},function(day, index){		
    		$.ajax({
    			type:'POST',
    			url:"property/clock.jhtml",
    			data:{
    				newdate:newdate,
    				id:id,
    				day:day,
    				},
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
    		layer.close(index);
    			});
    	}
    </script>
    </body>
    </html>

