
<html>
<head>
  <title>借还记录</title>
</head>
<boby>
 <center>
   <h1>记录列表</h1>
   <table border="1">
     <tr>
       <th>用户</th>
       <th>图书</th>
       <th>类型</th>
     </tr>
     <@action uri = "pRecordWeb!page" nickname = "records" />
     <#list records.data.content as record>
     <tr>
       <th>${record.userId}</th>
       <th>${record.bookId}</th>
       <th>${record.type}</th>
     </tr>
     </#list>
   </table>
     <button type="button" onclick="go('${records.data.pageNumber-1}')">上一页</button>
   第${records.data.pageNumber}/${records.data.totalPages}页，每页
   <form action="#" method="get">
   <input type="hidden" name="pageNumber" id="pn" value="${RequestParameters.pageNumber!}"/>
   <select name="pageSize">
   <option value="5">5</option>
   <option value="10">10</option>
   <option value="20">20</option>
   </select>
   </form>
   条
   <button type="button" onclick="go('${records.data.pageNumber+1}')">下一页</button>
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
</script>
</html>