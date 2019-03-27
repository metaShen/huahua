<html>
<head>
    <meta http-equiv="Content-Type"  charset=utf-8" />
</head>
 <script src="https://echarts.baidu.com/dist/echarts.min.js"></script>
<script type="text/javascript" src=" https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="static/plug/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="static/plug/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="static/plug/hplus/css/animate.min.css" rel="stylesheet">
    <link href="static/plug/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<body class="gray-bg">
 <div class="ibox-title">
                        <div class="ibox">
                            <a href="index.jhtml" class="btn btn-primary ">返回首页</a>   
                        </div>
                    </div>
     <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-6">
            <div class="ibox float-e-margins">
                        	<div class="ibox-content">
    <div id="revenuemain" style="width: 600px; height: 400px;"></div>
    						 </div>
   			 		</div>
    		</div>
    		            <div class="col-sm-6">
            <div class="ibox float-e-margins">
                        	<div class="ibox-content">
    <div id="paymain" style="width: 600px; height: 400px;"></div>
    						 </div>
   			 		</div>
    		</div>
 	   </div>
  </div>

</body>
<script type="text/javascript">
    //初始化echarts
    function chushihua(myChart){
        var option = {
            title:{
                text:'收支统计'
            },      
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:[]
            },
            series:[{
                name:'',
                type:'pie', 
                radius:'70%', 
                center:'45%,55%',
                data:[
                	{value:0,name:'wu'}
                ]
            
            }]
        };

        myChart.setOption(option);   
    }


    function fuzhi(myChart){
    	var item = {
    			name:[],
    			value:[]
    	}
        $.ajax({
            contentType : "application/json",
            type : "get",
            url : "/home/revenue/list.jhtml",
            dataType : "json",
            success : function(data) {
                var prices=[];
                var names=[];

				if (data) {
                    for(var i=0;i<data.data.length;i++){       
                    	names.push(data.data[i].source); 
                     }
                    for(var i=0;i<data.data.length;i++){ 
                    	var item = new Object();
                    	item.name= data.data[i].source;
                    	item.value = data.data[i].price;
                    	prices.push(item);
                      }
                myChart.setOption({
                    title:{
                    text:'收入统计'
                    }, 
		
                    legend:{
                    	data:names
                    },
                    series:[{
                    	name:['收入金额'],
                        type:'pie', 
                        radius:'70%', 
                        center:'45%,55%',
                        data:prices
                    }]

                });
          

            }
            }
        });
    }


    var myChart = echarts.init(document.getElementById('revenuemain'));
    chushihua(myChart);
    fuzhi(myChart);    

    
</script>
<script type="text/javascript">
    //初始化echarts
    function chushihua(myChart){
        var option = {
            title:{
                text:'收入统计'
            },    
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:[]
            },
            series:[{
                name:'访问量',
                type:'pie', 
                radius:'70%', 
                center:'45%,55%',
                data:[]
            }]
        };

        myChart.setOption(option);   
    }


    function fuzhi(myChart){
    	var items = {
    			name:[],
    			value:[]
    	}
        $.ajax({
            contentType : "application/json",
            type : "get",
            url : "/home/pay/list.jhtml",
            dataType : "json",
            success : function(data) {
                var prices=[];
                var proposes=[];
				if (data) {
                    for(var i=0;i<data.data.length;i++){       
                    	proposes.push(data.data[i].purpose); 
                     }
                    for(var i=0;i<data.data.length;i++){ 
                     	var items = new Object();
                    	items.name= data.data[i].purpose;
                    	items.value = data.data[i].price;
                    	prices.push(items); 
                      }
                    console.log(proposes);
                    console.log(prices);
                myChart.setOption({
                    title:{
                    text:'支出统计'
                    },      
                    legend: {
                        data:proposes
                    },
                    series:[{
                        name:'支出金额',
                        type:'pie', 
                        radius:'70%', 
                        center:'45%,55%',
                        data:prices
                    }]

                });
				}
            }
        });
    }


    var myChart = echarts.init(document.getElementById('paymain'));
    chushihua(myChart);
    fuzhi(myChart);    

</script>


</html>