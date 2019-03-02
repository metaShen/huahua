/**
 * 
 * @author BingYi
 * @date 2015/12/2
 * 
 * @desc 用于多个关联选择框
 * 
 * @用法
 * html:
 * 		<p class="relMultiSelect"></p>
 * js：
 * 
	$relMultiSelect({
		id : ".relMultiSelect",
		kv :　["bmzm00","bmzhi0"],
		names : ["","ZYE000"],
		urls : ["old/code/ZYLB.jhtml","old/code/ZYByLB.jhtml?zylbbm="]
	});
 */
var kk,vk;
function relMultiSelectGetVaildValue(id){
	var t = $(id+" select[relMultiSelect]:has(option):last");
		if(t.val()==""&&t.children("option").size()==1){
			t=t.prev("select[relMultiSelect]");
		}
		$(id+" input[relMultiSelect]").val(t.val());
}
function relMultiSelectPut(id,t){
	if(t.size()==0){
		relMultiSelectGetVaildValue(id);
		return;
	}
	var t2 = t.prev("select[relMultiSelect]");
	//选择空时，后面的select清空内容
	if(t2.size()>0&&t2.val()==""){
		t.empty();
		t.nextAll("select[relMultiSelect]").empty();
		relMultiSelectGetVaildValue(id);
		return;
	}
	var url = t.attr("url")+(t2.size()>0?t2.val():"");
	var sv = t.attr("relMultiSelect");
	t.attr("relMultiSelect","");
	$post({
		url:url,
		success:function(data){
			t.empty();
			t.nextAll("select[relMultiSelect]").empty();
			t.append("<option ></option>");
			for(i in data){
				t.append('<option value="'+data[i][vk]+'" '+(sv&&data[i][vk]==sv?"selected":"")+'>'+data[i][kk]+'</option>');
			}
			relMultiSelectPut(id,t.next("select[relMultiSelect]"));
		},
		error:function(data){
			//$layerError(data);
			$layerError("获取失败！请刷新页面重试！");
		}
	});
}
function relMultiSelectSetSize(tag){
	t = tag.children("select[relMultiSelect]");
	n = t.size();
	w = tag.width()/n-5;
	h = tag.height();//本来+5
	t.width(w);
	t.height(h);
	t.css("margin-right","3px");
}
function $relMultiSelectSelect(id,selects){
	var si;
	selects = selects?selects:[];
	tag = $(id+" select[relMultiSelect]");
	n = tag.size();
	for(si=0;si<n;si++){
		t = tag.eq(si);
		if(selects[si]){
			t.attr("relMultiSelect",selects[si]);
		}
	}
	relMultiSelectPut(id,tag.eq(0));
}
function $relMultiSelect(obj){
	id = obj.id;    //id
	kk = obj.kv[0]; //select的option中value值对应所获取json的key
	vk = obj.kv[1]; //select的option中显示的值对应所获取json的key
	names = obj.names;//select对应的name
	inputName = obj.inputName;//select的结果放入input里
	urls = obj.urls; //用于获取json值的url（生成select的内容）
	selects = obj.selects;//默认选择select的value值
	n = names.length;
	tag = $(id);
	for(i in names){
		tag.append('<select relMultiSelect url="'+urls[i]+'" name="'+names[i]+'"></select>');
	}
	if(inputName){
		tag.append('<input hidden relMultiSelect name="'+inputName+'"/>');
	}
	relMultiSelectSetSize(tag);
	
	$relMultiSelectSelect(id,selects);
	$(id+" select[relMultiSelect]").on("change",function(){
		relMultiSelectPut(id,$(this).next("select[relMultiSelect]"));
	});
}