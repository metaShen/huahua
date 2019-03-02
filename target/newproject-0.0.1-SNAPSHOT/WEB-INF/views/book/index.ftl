
<html>
<head>
  <title>书籍</title>
</head>
<boby>
 <center>
   <h1>图书列表</h1>
   <h3>欢迎${user.name},你还可以借${user.canLend}本书<button type="button" onclick="logout()">退出系统</button></h3>
   <#if user.type = 2>
   <button type="button" onclick="addBook()">添加图书</button>
   </#if>
   <table border="1">
     <tr>
       <th>书名</th>
       <th>作者</th>
       <th>出版社</th>
       <th>价格</th>
       <th>剩余数量</th>
       <#if user.type= 2>
       <th>添加库存</th>
       <th>删除图书</th>
       <#else>
         <th>借阅</th>
       </#if>
     </tr>
     <@action uri = "pBookWeb!page" nickname = "books" />
     <#list books.data.content as book>
     <tr>
       <th>${book.name}</th>
       <th>${book.author}</th>
       <th>${book.publishing}</th>
       <th>${book.price}</th>
       <th>${book.number}</th>
       <#if user.type = 2>
         <th><button type="button" onclick="addNum('${book.id}')">添加图书</button></th>
         <th><button type="button" onclick="delBook('${book.id}')">删除图书</button></th>
         <#else>
           <th><button type="button" onclick="lend('${user.id}','${book.id}')">借阅</button></th>
       </#if>
     </tr>
     </#list>
   </table>
     <button type="button" onclick="go('${books.data.pageNumber-1}')">上一页</button>
   第${books.data.pageNumber}/${books.data.totalPages}页，每页
   <form action="#" method="get">
   <input type="hidden" name="pageNumber" id="pn" value="${RequestParameters.pageNumber!}"/>
   <select name="pageSize">
   <option value="5">5</option>
   <option value="10">10</option>
   <option value="20">20</option>
   </select>
   </form>
   条
   <button type="button" onclick="go('${books.data.pageNumber+1}')">下一页</button>
 </center>
</boby>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script>
$().ready(function(){
	<#if RequestParameters.pageSize?? >
	<#if RequestParameters.pageSize = '5'>  
	$("select").val("5");
	</#if>
	<#if RequestParameters.pageSize = '10'>
	$("select").val("10");
	</#if>
	<#if RequestParameters.pageSize = '20'>
	$("select").val("20");
	</#if>
	</#if>  
	$("select").change(function(){
		$("form").submit();
	})
})
function go(pn){
	$("#pn").val(pn);
	$("form").submit();
}
function logout(){
	$.ajax({
		type:'POST',
		url:"user/logout.jhtml",
		success: function(data){
			if(!data.error){
				alert(data.data);
				location.href="login.jhtml";
			}else{
				alert(data.data);
			}
		},
		dataType:"json"
	});
}
function addBook(){
	location.href="book.jhtml?p=add";
}
function addNum(bookid){
	var num=prompt("请输入添加该书的数量：");
	$.ajax({
		type:'POST',
		url:"book/addNum.jhtml",
		data:{bookId:bookid,num:num},
		success:function(data){
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
function delBook(bookid){
	$.ajax({
		type:'post',
		url:"book/remove.jhtml",
		data:{id:bookid},
		success:function(data){
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
function lend(userid,bookid){
	$.ajax({
		type:'post',
		url:"record/borrow.jhtml",
		data:{userId:userid,bookId:bookid},
		success:function(data){
			if(!data.error){
				alert(data.data);
				location.reload();
			}else{
				alert(data.data);
				location.reload();
			}
		},
		dataType:"json"
	});
}
</script>
</html>