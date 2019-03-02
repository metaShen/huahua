//网站公共js
//<!--author:  ljy 2013-12-31-->


//操作 opt  定义
var opt_add = 1;
var opt_update = 2;
/**
 * 
 */
function showMsg(data){
    data = eval("'"+data+"'");
    alert(data);
    var msg = '已录入数据时间：\n';
    var i;
    for (i = 0; i < data.listYes.length; i++) {
	msg += data[i] + "  ";
	if ((i + 1) % 3 == 0) {
	    msg += "\n";
	}
    }
    msg += '\n';
    
    msg += '未录入数据时间：\n';
    var i;
    for (i = 0; i < data.listNo.length; i++) {
	msg += data[i] + "  ";
	if ((i + 1) % 3 == 0) {
	    msg += "\n";
	}
    }
    alert(msg);
}

/**
 * 更新个数
 * @param vid id名
 * @param id  id值
 * @param add 个数
 */
function updateCount(vid,id,add){
    var count = $(window.parent.document).find("span["+vid+"='"+id+"']").html();    
    $(window.parent.document).find("span["+vid+"='"+id+"']").html(parseInt(count)+add);
}
/**
 * 更新个数(上上层)
 * @param vid id名
 * @param id  id值
 * @param add 个数
 */
function updateParentCount(vid,id,add){
    var count = $(window.parent.parent.document).find("span["+vid+"='"+id+"']").html();
    $(window.parent.parent.document).find("span["+vid+"='"+id+"']").html(parseInt(count)+add);
}

//select框 上下移动
function moveUpDown(up_down,id){		
        var elemObj = document.getElementById(id);
        var sel_id = elemObj.options.selectedIndex;	
        	
        if (sel_id == -1) return;
        if (up_down == "up"){
        	if(sel_id == 0){
        		alert("已经在顶部，不能再上移!");
        		return;
        	}
        	if (elemObj.length > 1 && sel_id > 0) {
        		var prevOption = elemObj.options[sel_id-1];
        		var newOption = new Option(prevOption.text, prevOption.value);
        		var selectedOption = elemObj.options[sel_id];
        		elemObj.options[sel_id-1] = new Option(selectedOption.text, selectedOption.value);
        		elemObj.options[sel_id] = newOption;
        		elemObj.focus();
        		elemObj.selectedIndex = sel_id -1;
        	}
        }else{
        	if(sel_id == elemObj.length -1){
        		alert("已经在底部，不能再下移!");
        		return;
        	}
        	if (elemObj.length > 1 && sel_id < elemObj.length -1){
        		var nextOption = elemObj.options[sel_id+1];
        		var newOption = new Option(nextOption.text, nextOption.value);
        		var selectedOption = elemObj.options[sel_id];
        		elemObj.options[sel_id+1] = new Option(selectedOption.text, selectedOption.value);
        		elemObj.options[sel_id] = newOption;
        		elemObj.focus();
        		elemObj.selectedIndex = sel_id + 1;
        	}
        }
}

//选择框全选 单选
function clickAll() {
    $(".checkOne").prop("checked", $(".checkAll").prop("checked"));
}

function clickOne() {
    var allChecked = true;
    $(".checkOne").each(function() {
	if ($(this).prop("checked") == false) {
	    allChecked = false;
	}
	;
    });
    if (allChecked) {
	$(".checkAll").prop("checked", true);
    } else {
	$(".checkAll").prop("checked", false);
    }
}

function getCheck(){
    var chk_value = [];// 定义一个数组
    $('input[name="check"]:checked').each(function() {// 遍历每一个名字为interest的复选框，其中选中的执行函数
		chk_value.push($(this).val());// 将选中的值添加到数组chk_value中
	    });
    
    return chk_value;
}