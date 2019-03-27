<script>
var myChart = echarts.init(document.getElementById('main'));
var years = [];
var months = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
var txfArr = [];
var colors =  [
        "#ff6666","#6699FF","#40e0d0","#C1232B","#B5C334",
        "#FCCE10","#E87C25","#27727B","#FE8463","#9BCA63",
        "#FAD860","#F3A43B","#60C0DD","#D7504B","#C6E579",
        "#F4E001","#F0805A","#26C0C0"];//自定义一个颜色数组，多系时会按照顺序使用自己定义的颜色数组，若不定义则使用默认的颜色数组
// 指定图表的配置项和数据
var option = {
    //设置颜色
    color:colors,
    //提示框
    tooltip : {
        show : 'true',
        trigger: 'axis',
        axisPointer : {// 坐标轴指示器，坐标轴触发有效,默认为直线，可选为：'line' | 'shadow'
            type : '' //shadow阴影指示器移出不消失，未解决，所以直接为空不显示指示器样式   
        }
       },
       legend: {
        data: []
       },
       grid: {
        left: '3%',
        right: '8%',
        bottom: '3%',
        containLabel: true
   }, 
   xAxis: {
        //显示策略，可选为：true（显示） | false（隐藏），默认值为true  
        show: true,  
        //坐标轴类型，横轴默认为类目型'category'
        type: 'category', 
           data: months
   },
   yAxis: {
        type: 'value',//Y轴值
        name: '金额(万元)',
        nameTextStyle:{
            color :[ 'red']        //设置富文本颜色
        }
    },
   toolbox:{
       show:true,
       orient: 'horizontal',
       showTitle: true,
       feature:{
           magicType: {
               type: ['line', 'bar']
           },
           dataView:{    //dataview对象中重写optionToContent函数
               show: true,
               title: '通行费实收曲线图数据',
               readOnly:true,    //为true去掉刷新按钮
                //lang:['','关闭'],        第一个值为空时  默认取值title
               optionToContent: function(opt) {
                   var colName = "序号";
                   var typeName = "月份";
                   //获取dataview <table class="body"><tr><td colspan="4" class="title">'+dataview.title+'</td></tr></table>
                   var dataview = opt.toolbox[0].feature.dataView;  
                   var table = '';
                   table += getTable(opt,colName,typeName);
                   return table;
               }
           },
           saveAsImage:{
               type:'png',
               show:true,
               title:'保存为图片'
           },
           restore:{show:true}
       }
   },
   series: []
};
function getTable(opt,colName,typeName){
    var axisData = opt.xAxis[0].data;//获取图形的data数组
    var series = opt.series;//获取series
    var num = 0;//记录序号
    var sum = new Array();//获取合计数组（根据对应的系数生成相应数量的sum）
    for(var i=0; i<series.length; i++){
        sum[i] = 0;
    }
    var table = '<table style="width: 100%; height: 100%;" cellSpacing="0" class="List"><tr class="Head">'
        + '<td>'+colName+'</td>'
        + '<td>'+typeName+'</td>';
    for(var i=0; i<series.length;i++){
        table += '<td>'+series[i].name+'</td>'
    }
    table += '</tr><tbody>';
    for (var i = 0, l = axisData.length; i < l; i++) {
        num += 1;
        for(var n=0;n<series.length;n++){
            if(series[n].data[i]){
                sum[n] += Number(series[n].data[i]);
            }else{
                sum[n] += Number(0);
            }
 
        }
        table += '<tr>'
            + '<td>' + num + '</td>'
            + '<td>' + axisData[i] + '</td>';
        for(var j=0; j<series.length;j++){
            if(series[j].data[i]){
                table += '<td>' + series[j].data[i] + '</td>';
            }else{
                table += '<td>' + 0 + '</td>';
            }
 
        }
        table += '</tr>';
    }
    //最后一行加上合计 colspan="2" align="center"  class="Head"
    table += '<tr>'+ '<td>' + (num+1) + '</td>' + '<td>合计</td>';
    for(var n=0;n<series.length;n++){
        if(String(sum[n]).indexOf(".")>-1)
            table += '<td>' + (Number(sum[n])).toFixed(2)  + '</td>';
        else
            table += '<td>' + Number(sum[n])  + '</td>';
    }
    table += '</tr></tbody></table>';
    return table;
}
 
//myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
// 使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
$.ajax({
    type : "post",
    async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "yysjtjdoQfSrQx.do", 
    data : {},//无查询参数
    dataType : "json", //返回数据形式为json
    success : function(json) {
        var jsonstr="[{}]";
        var jsonarray = eval('('+jsonstr+')');
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        for ( var i = 0; i < json.length; i++) {
            years.push(json[i].slice(0,1));
            var arr  =
             {
                 "name" : json[i].shift(),
                 "value" : json[i]
             }
            jsonarray.push(arr);
        }
        //console.log(jsonarray.slice(1,jsonarray.length));
         //表示折线图
        var ItemLine = function () {
              return {
                    name: '',
                    type: 'line',
                    data: []
             };
        };
            var legends=[];
            //var xDateArr=[];
            var SeriesTotal=[];
            //给x轴赋值
            //xDateArr= ['1','2','3','4','5','6','7','8','9','10','11','12'];
            //alert(xDateArr +":xDateArr");
            for(var i = 1; i<jsonarray.length; i++){
                /* if(jsonarray[i].name != undefined && jsonarray[i].name != "");{
                    alert("name" + jsonarray[i].name +">>value:"+jsonarray[i].value );
                } */
                 //给legend赋值
                 legends.push(jsonarray[i].name);
                //核心，给series赋值，分开包装的思想。
                var itemLine = new ItemLine();
                itemLine.name = jsonarray[i].name;
                itemLine.data = jsonarray[i].value;
                SeriesTotal.push(itemLine);
            }
            option.legend.data = legends;
            //注意这里是xAxis[0],如果直接写xAxis会报错，因为x轴默认有2个，上半年轴和下半轴。
            //option.xAxis[0].data = xDateArr;
            option.series = SeriesTotal;
            //这里最好加上true参数，否则会出现切换图表时前面数据不会清除掉的情况。
            myChart.setOption(option,true);
    },
    error : function(errorMsg) {
        //请求失败时执行该函数
        alert("图表请求数据失败!");
        myChart.hideLoading();
    }
    });
</script>