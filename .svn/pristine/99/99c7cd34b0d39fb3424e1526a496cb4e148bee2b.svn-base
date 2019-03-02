<html>
<head>
   <title>添加图书</title>
   <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
</head>
<boby>
  <center>
     <form action="#">
       <lable>书名：</lable>
       <input type="text" name="name" id="name">
       <br>
       <lable>作者：</lable>
       <input type="text" name="author" id="author">
       <br>
       <lable>出版社：</lable>
       <input type="text" name="publishing" id="publishing">
       <br>
       <lable>价格：</lable>
       <input type="text" name="price" id="price">
       <br>
       <lable>数量：</lable>
       <input type="text" name="number" id="number">
       <br>
       <lable>简介：</lable>
       <input type="text" name="des" id="des">
       <br>
       <button type="button" onclick="add()">确定添加</button>
     </form>
  </center>
</boby>
<script>
    function add(){
    	var name = $("#name").val();
    	var author = $("#author").val();
    	var publishing = $("#publishing").val();
    	var price = $("#price").val();
    	var number = $("#number").val();
    	var des = $("#des").val();
    	$.ajax({
    		type:'post',
    		url:"book/add.jhtml",
    		data: {author:author,des:des,name:name,number:number,price:price,publishing:publishing},
    		success:function(data){
    			if(!data.error){
    				alert(data.data);
    				location.href="book.jhtml";
    			}else{
    				alert(data.data);
    			}
    		},
    		dataType:"json"
    	});
    }
 
</script>
</html>