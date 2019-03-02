/**
 * @author	bingyi
 * @time	2015-02-05 21:00
 */

notice="加载中...";

//判断数组中包含element元素 
contains = function (arr,element) { 

  for (var i = 0; i < arr.length; i++) { 
      if (arr[i] == element) { 
          return true; 
      } 
  } 
  return false; 
};
/**
 * 
 * @param id
 * @param json
 * @param arr
 * @param fun
 * 
 * @用法
 * $toForm("#basic",json,["JYSJQJ"],function(value){
				va = value.split("至");
				return {JYSJQJ1:va[0],JYSJQJ2:va[1]};
			});
 */
function $toForm(id,json,arr,fun){
	tag = $(id);
	for(key in json){
		if(arr&&contains(arr,key.toUpperCase())){
			js = fun(json[key]);
			for(k in js){
				json[k]=js[k];
			}
		}
	}
	for(key in json){
		t = tag.find("[name='"+key.toUpperCase()+"']");
		if(t.size()>0){
			if(t.is("input")){
				if(t.attr("type")=="radio"){
					t.each(function(){
						if($(this).val()==json[key]){
							$(this).attr("checked","checked");
						}
					});
				}else{
					t.val(json[key]);
				}
			}else if(t.is("select")){
				t.children("[value='"+json[key]+"']").attr("selected","selected");
			}else if(t.is("textarea")){
				t.val(json[key]);
			}
		}
	}
}

/**
 * $formAjax
 * 
 * @使用方法1:
 * 		@参数：id,url[,rules,success,error]
 * 		@参数说明：id	 ->	form的id，（jQuery的选择器语法）
 * 			 url	 ->	form的提交链接
 * 			 rules	 ->	规则  （可选参数）
 * 			 success ->	成功操作的回调函数，回调必须含有参数  （可选参数）
 * 			 error	 ->	失败操作的回调函数，回调必须含有参数  （可选参数）
 * 			 first	 ->	提交前先执行函数，该函数必须有返回值，返回true执行提交，返回false不执行提交  （可选参数）
 * 		@示例：
  		$formAjax("#id","url",{name:["#","请输入用户名称","#maxLength",25]},function(data){},function(data){},function(){return true;})
 *
 * @使用方法2：建议使用这种方式
 * 		@参数：Object类型 -> 键：id,url[,rules,success,error]
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：
  		$formAjax({
 	    	    	id:"#formId",
 	    	    	url:'url',
 	    	    	rules:{},
 	    	    	success:function(data){},
 	    	    	error:function(data){},
 	    	    	first:function(){return true;};
 	    	 });
 	@新功能
 		rules可加新元素如下
			rules:{"@for":".pro-info-body-label"}
		用于对被rules标记为必填字段"#"，显示红色星号
 	
 */

function $formAjax(id,url,rules,success,error,first,win){
	if(id.constructor==Object){
		object=id;
		id=object.id;
		url=object.url;
		rules=object.rules;
		success=object.success;
		error=object.error;
		first=object.first;
		win=object.win;
	}
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	$formAjaxNoMsg(id,url,rules,
			function(data){
				$layerSuccess(data,function(){
				    if(success){
				    	success(data);
				    }
				},win);
		    },
		    function(data){
				$layerError(data,function(){
				    if(error){
				    	error(error);
				    }
				},win);
			},first,win);
}

function showNeedInput(id,rules){
	tag = $(id);
	forId = rules["@for"];
	delete rules["@for"];
	for(var key in rules){
		f=false;
		value = rules[key];
		for(var i in value){
			if(value[i]=="#"){
				f=true;
				break;
			}
		}
		if(f){
			t = $(id+" [name="+key+"]");
			x = t.parent().children(forId);
			if(x.size()==0){
				x = t.parent().parent().children(forId);
				if(x.size()==0){
					x = t.parent().parent().parent().children(forId);
					if(x.size()==0){
						continue;
					}
				}
			}
			if(x.children("span[needInputSpan]").size()==0){
				x.prepend('<span style="color:red;" needInputSpan>* </span>');
			}
			t.attr("title","必填选项");
		}
	}
}
function $formAjaxNoMsg(id,url,rules,success,error,first,win){
	if(id.constructor==Object){
		object=id;
		id=object.id;
		url=object.url;
		rules=object.rules;
		success=object.success;
		error=object.error;
		first=object.first;
		win=object.win;
	}
	if(rules && "@for" in rules){
		showNeedInput(id,rules);
	}
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	if(!url){
		url = $(id).attr("action");
	}
	$(id).form({
	    rules:rules,
	    todo:function(form,data){
	    	flag=true;
	    	if(first){
	    		flag=first(form);
	    	}
	    	if(flag){
				var load = layer.load(notice); 
				Summer.ajax(url,form.serialize(),
					function(data){
					    layer.close(load); 
					    if(success){
					    	success(data);
					    }
				    },
				    function(data){
			    	    layer.close(load); 
			    	    if(error){
			    	    	error(data);
			    	    }
				    });
	    	}
	    	}
	});    	
}
/**
 * 使用的是$post函数进行提交，相应支持该函数返回机制
 */
function $formAjaxNoMsg2(id,url,rules,success,error,first,win){
	if(id.constructor==Object){
		object=id;
		id=object.id;
		url=object.url;
		rules=object.rules;
		success=object.success;
		error=object.error;
		first=object.first;
		win=object.win;
	}
	if(rules && "@for" in rules){
		showNeedInput(id,rules);
	}
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	if(!url){
		url = $(id).attr("action");
	}
	$(id).form({
	    rules:rules,
	    todo:function(form,data){
	    	flag=true;
	    	if(first){
	    		flag=first(form);
	    	}
	    	if(flag){
	    		$post(url,form.serialize(),success,error);
	    	}
	    }
	});    	
}

/**
 * $summerLayer
 * 
 * @使用方法1:
 * 		@参数：title,url,area,close
 * 		@参数说明：title	->	标题
 * 			 url	->	iframe链接
 * 			 area	->	大小  比如：[ '1200px', '600px' ]
 *			 close  ->  关闭弹窗的回调函数
 * 		@示例：
               $summerLayer("title","url",[ '1200px', '600px' ],function(){})
 *
 * @使用方法2：
 * 		@参数：Object类型 -> 键：title,url,area,close
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：
  		$summerLayer({
 	    	    	title:"title"
 	    	    	url:'url',
 	    	    	area,['1200px','600px'],
 	    	    	close,function(){}
 	    	 });
 */
function $summerLayer(title,url,area,close,win,shade){
	if(typeof title === 'object'){
		object=title;
		title=object.title;
		url=object.url;
		area=object.area;
		close=object.close;
		win=object.win;
		shade=object.shade;
	}
	var $=this.$;
	if(win){
		$=win.$;
	}else{
		win=this;
	}
	overflow = win.document.documentElement.style.overflow;
	win.document.documentElement.style.overflow='hidden';
	$.layer({
		type : 2,
		shade : shade,
		fix : true,
		title : title,
		maxmin : false,
		iframe : {
			src : url,
		},
		area : area,
		end : function(index){
			win.document.documentElement.style.overflow=overflow;
			if(close){
				close();
			}
		},
		close : function(index){
			win.document.documentElement.style.overflow=overflow;
			if(close){
				close();
			}
		}
	});
}

/**
 * len方法
 * 
 * 描述：获取Object键的个数
 */
len=function(object){
	var size = 0, key;
	for (key in object) {
		if (object.hasOwnProperty(key)){ 
			size++;
		}
	}
	return size;
};


/**
 * $post
 * 
 * @使用方法1:
 * 		@参数：url,[data,success,error,dataType]
 * 		@参数说明：
 * 				url		->	提交链接
 * 				data	->	提交数据 Object格式   （可选参数）
 * 				success	->	成功操作的回调函数，回调必须含有参数  （可选参数）
 * 				error	->	失败操作的回调函数，回调必须含有参数  （可选参数）
 * 				dataType->	返回数据类型，默认json    （可选参数）
 * 		@示例：$post("url",{name:"name",psw:"psw"},function(data){},function(data){},"json");
 *
 * @使用方法2：建议使用这种方式
 * 		@参数：Object类型 -> 键：url,[data,success,error,dataType]
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：
  		$post({
 	    	    	url:'url',
	    	    	data:{name:"name",psw:"psw"},
 	    	    	success:function(data){},
 	    	    	error:function(data){},
 			dataType:"json"
 	    	 });
 	@返回机制
 		当json只有两个key（error和data），仅返回data的值，error对应error或者success的函数
 		如果json不是两个key，或者不存在data，则返回整个json值
 */

function $post(url,data,success,error,dataType,progress){
	if(url.constructor==Object){
		object=url;
		url=object.url;
		data=object.data;
		success=object.success;
		error=object.error;
		dataType=object.dataType;
		progress=object.progress==false?false:true;//没有传参数，默认true
	}
	if(data&&data.constructor==Object){
		var s=[];
		for ( var key in data){
			s.push(key+"="+data[key]);
		}
		data=encodeURI(s.join("&"));
	}
	if(dataType==undefined){
		if(error&&error.constructor==String){
		    dataType=error;
		    error=undefined;
		}else{
		    dataType='json';
		}
	}
	if(progress)var load = layer.load(notice); 
	$.ajax({
		url:url,
		type:'POST',
		data:data,
		dataType:dataType,
		success:function(data){
			if(progress)layer.close(load); 
		    if(dataType=='json'&&data.error==true){
				if(error){
				    error(data.data!=undefined&&len(data)==2?data.data:data);
				}
		    }else{
				if(success){
				    success(data.data!=undefined&&len(data)==2?data.data:data);
				}	
		    }
		},
		error:function (XMLHttpRequest, textStatus, errorThrown){
			if(progress)layer.close(load); 
		    if(error){
		    	error("请求失败！");
		    }
		}
	});
}


/**
 * $load
 * 描述：post方式加载数据，有显示"载入中"
 * 
 * @使用方法1:
 * 		@参数：url,[data,success,error,dataType]
 * 		@参数说明：
 * 				url		->	提交链接
 * 				data	->	提交数据 Object格式   （可选参数）
 * 				success	->	成功操作的回调函数，回调必须含有参数  （可选参数）
 * 				error	->	失败操作的回调函数，回调必须含有参数  （可选参数）
 * 				dataType->	返回数据类型，默认json    （可选参数）
 * 		@示例：$post("url",{name:"name",psw:"psw"},function(data){},function(data){},"json");
 *
 * @使用方法2：建议使用这种方式
 * 		@参数：Object类型 -> 键：url,[data,success,error,dataType]
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：$post({
 *	    	    	url:'url',
 *	    	    	data:{name:"name",psw:"psw"},
 *	    	    	success:function(data){},
 *	    	    	error:function(data){},
 *					dataType:"json"
 *	    	 });
 */
function $load(url,data,success,error,dataType,win){
	if(url.constructor==Object){
		object=url;
		url=object.url;
		data=object.data;
		success=object.success;
		error=object.error;
		dataType=object.dataType;
		win=object.win;
	}
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	if(error&&error.constructor==String){
		dataType=error;
		error=undefined;
	}
	var load=layer.load("加载中...");
	$post(url,data,function(data){
			layer.close(load);
			if(success)
				success(data);
		},function(data){
			layer.close(load);
			if(error)
				error(data);
		},dataType);
}

/**
 * @函数名 $urlByPost
 * @描述 以post方式跳转页面（模拟form表单的post提交方式）
 * 
 * @使用方法
 * 		@参数：url,data
 * 		@参数说明：
 * 				url		->	提交链接
 * 				data    ->  post数据（object类型）
 * 		@示例  $urlByPost("front.jhtml?p=dingdan/order",{ids:"ids",nums:"nums"});
 */
function $urlByPost(url,data){
	$("#urlByPostForm").remove();
	s="";
	for(var key in data){
		s+='<input type="hidden" name="'+key+'" value="'+data[key]+'"/>';
	}
	$("body").append('<form id="urlByPostForm" action="'+url+'" method="post" style="display:none;" >'+s+'</form>');
	$("#urlByPostForm").submit();
}

/**
 * @函数名 $layerSuccess
 * @描述 layer.msg成功提示的封装
 * 
 * @使用方法
 * 		@参数：data,callback,win
 * 		@参数说明：
 * 				url		   ->	提示消息
 * 				callback   ->   回调函数提示消息
 * 				win        ->   窗口
 * 		@示例  
				$layerSuccess("密码正确!",function(){alert(4243);},parent);
				$layerSuccess("密码正确!",null,parent);
				$layerSuccess("密码正确!",function(){alert(4243);});
				$layerSuccess("密码正确!");
 */
function $layerSuccess(data,callback,win){
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	layer.msg(data,1,1,callback);
}
/**
 * @函数名 $layerError
 * @描述 layer.msg失败提示的封装
 * 
 * @使用方法
 * 		@参数：data,callback,win
 * 		@参数说明：
 * 				url		   ->	提示消息
 * 				callback   ->   回调函数提示消息
 * 				win        ->   窗口
 * 		@示例  
				$layerError("密码正确!",function(){alert(4243);},parent);
				$layerError("密码正确!",null,parent);
				$layerError("密码正确!",function(){alert(4243);});
				$layerError("密码正确!");
 */
function $layerError(data,callback,win){
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	layer.msg(data,1,8,callback);
}
/**
 * @函数名 $layerInfo
 * @描述 layer.msg一般提示的封装
 * 
 * @使用方法
 * 		@参数：data,callback,win
 * 		@参数说明：
 * 				url		   ->	提示消息
 * 				callback   ->   回调函数提示消息
 * 				win        ->   窗口
 * 		@示例  
				$layerInfo("密码正确!",function(){alert(4243);},parent);
				$layerInfo("密码正确!",null,parent);
				$layerInfo("密码正确!",function(){alert(4243);});
				$layerInfo("密码正确!");
 */
function $layerInfo(data,callback,win){
	var layer=this.layer;
	if(win){
		layer=win.layer;
	}
	layer.msg(data,1,0,callback);
}

/**
 * @函数名 $layerChoose
 * @描述 layer确定取消框的封装
 * 
 * @使用方法
 * 		@参数：object (object类型)
 * 		@参数说明：
 * 				data	 ->	提示消息
 * 				yesBtn   -> 确定键的文字显示
 * 				noBtn    -> 取消键的文字显示
 * 				yes		 -> 确定键回调函数
 * 				no		 -> 取消键回调函数
 * 				win        ->   窗口 (可选)
 * 				
 * 		@示例  
				$layerChoose({
					data:"喜欢吗？",
					yesBtn:"是的",
					noBtn:"不是",
					yes:function(){alert("选择是的")},
					no:function(){alert("选择不是")},
					win:top
				});
	layer.confirm('您是如何看待前端开发？', {
  			btn: ['重要','奇葩'] //按钮
		}, function(){
  			layer.msg('的确很重要', {icon: 1});
		}, function(){});
 */
function $layerChoose(object){
	data=object.data;
	yesBtn=object.yesBtn;
	noBtn=object.noBtn;
	yes=object.yes;
	no=object.no;
	win=object.win;
	var $=this.$;
	if(win){
		$=win.$;
	}
	$.layer({
		shade: [0],
		area: ['auto','auto'],
		dialog: {
		    msg: data,
		    btns: 2,                    
		    type: 4,
		    btn: [yesBtn,noBtn],
		    yes: yes, 
		    no: no
		}
	});
}