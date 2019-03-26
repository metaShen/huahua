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
<body>
 <div class="ibox-title">
                        <div class="ibox">
                            <a href="index.jhtml" class="btn btn-primary ">返回首页</a>   
                        </div>
                    </div>
    <div id="chartmain" style="width: 600px; height: 400px;"></div>
</body>
<script type="text/javascript">
    //初始化echarts
    function chushihua(myChart){
        var option = {
            title:{
                text:'收支统计'
            },            
            series:[{
                name:'访问量',
                type:'pie', 
                radius:'90%', 
                data:[
                    {value:0,name:'无'},
                ]
            }]
        };

        myChart.setOption(option);   
    }

    //从数据库读取数据赋值给echarts
    function fuzhi(myChart){
        $.ajax({
            contentType : "application/json",
            type : "GET",
            url : "reveune/list.jhtml",
            dataType : "json",
            success : function(data) {
                var servicedata=[];

                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].price; 
                    obj.value=data[i].source;
                    servicedata[i]=obj;
                }

                myChart.setOption({
                    title:{
                    text:'ECharts 数据统计'
                    },            
                    series:[{
                        name:'访问量',
                        type:'pie', 
                        radius:'60%', 
                        data:servicedata,
                    }]

                });

            }
        });
    }


    var myChart = echarts.init(document.getElementById('chartmain'));
    chushihua(myChart);
    fuzhi(myChart);    

</script>

</html>