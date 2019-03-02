(function($) {
	$.fn.formMethods={};
	$.fn.formMethods.addRule=function(name,func,defs){};
	$.fn.formMethods.addValidateType=function(name,showFunc,hideFunc){};
	$.fn.formMethods.addTodoFunction=function(name,func){};
	$.fn.formMethods.getSimpleErrorMsg=function(target,defs,arr,i){};
	var formDefaults;
	var formValidateType;
	var formRules;
	var formTodo={};
	var internalMethods={
		_checkItem:function(form,inp,name,array){},
		_getRulesFromArray:function(array){},
		_onsubmit:function(form,options){},
		_item:function(form,options){}
	}
	
	internalMethods._checkItem = function(form,inp,name,array){
		var rules = internalMethods._getRulesFromArray(array);
		var require;
		for(var i in rules){
			var rule = rules[i];
			var ruleFunc = rule[0];
			var ruleDefs = rule[1];
			var ruleData = rule[2];
			var name = rule[3];
			var errorMsg;
			if(name=="#"){
				require = true;
			}else if(!require&&inp.val()==""){
				return;
			}
			errorMsg = ruleFunc(inp,ruleDefs,ruleData);
			if(errorMsg){
				return errorMsg;
			}
		}
	}
	internalMethods._getRulesFromArray = function(array){
		var ruleName;
		var ruleData = [];
		var rules = [];
		for(var i = 0;i<array.length;i++){
			var name = array[i];
			//识别#开头，说明是rule
			if(jQuery.type(name)=='string'&&name.indexOf('#')==0){
				if(ruleName){
					var ruleFunc = formRules[ruleName];
					if(ruleFunc==null){
						console.error("找不到rule:"+ruleName);
					}
					rules.push([ruleFunc,formDefaults[ruleName],ruleData,ruleName]);
				}
				ruleName = name;
				ruleData = [];
				
			}else{//其他参数变成rule的参数
				ruleData.push(name);
			}
			if(i==array.length-1){
				var ruleFunc = formRules[ruleName];
				if(ruleFunc==null){
					console.error("找不到rule:"+ruleName);
				}
				rules.push([ruleFunc,formDefaults[ruleName],ruleData,ruleName]);
			}
		}
		return rules;
	}
	internalMethods._onsubmit = function(form,options){
		var rules = options.rules;
		if(rules==null){
			rules = {};
		}
		if(!jQuery.isPlainObject(rules)){
			console.error("rules不是一个PlainObject");
			return;
		}
		for(rule in rules){
			var key = rule;
			var value = rules[key];
			if(!jQuery.isArray(value)){
				console.error("rules."+key+"不是一个Array");
				break;
			}
			var inps = form.find("[name='"+key+"']");
			var errorMsg;
			var inp;
			inps.each(function(i,e){
				inp = $(e);
				var oldValue = inp.data("Summer.form.oldValue");
				if(oldValue&&oldValue!=inp.val()){
					inp.data("Summer.form.errorMessage",null);
					inp.data("Summer.form.oldValue",null);
				}
				if(errorMsg){return;}
				errorMsg = inp.data("Summer.form.errorMessage");
				if(errorMsg){return;}
				errorMsg = internalMethods._checkItem(form,inp,key,value);
				inp.data("Summer.form.errorMessage",errorMsg);
			});
			if(errorMsg){
				inp.data("Summer.form.oldValue",value);
				formValidateType[options.validateType].show(inp,errorMsg,options);
				return;
			}
		}
		var todo = options.todo;
		if($.isFunction(todo)){
			todo(form,form.serialize());
		}else if(jQuery.type(todo)=='string'&&todo.indexOf('@')==0){
			formTodo[todo](form,form.serialize(),options.url);
		}
	};
	internalMethods._item = function(form,options){
		form.attr("onsubmit","return false;");
		form.submit(function(){
			internalMethods._onsubmit($(this),options);
		});
		form.find("[name]").focus(function(){
			formValidateType[options.validateType].hide($(this));
		});
	}
	
	$.fn.form = function(options){
		if(!jQuery.isPlainObject(options)){
			console.error("需要一个PlainObject参数");
			return;
		}
		if(!options.validateType){
			options.validateType = "def";
		}
		$(this).each(function(index,form){
			internalMethods._item($(form),options);
		});
	}
	formDefaults = {
		"#":["必填"],
		"#date":["日期格式不正确:如2014-01-01"],
		'#email':['邮箱格式不正确'],
		'#number':['请输入数字'],
		'#url':['URL格式不正确'],
		'#maxLength':['字符的长度不能大于{0}个字符'],
		'#rangeLength':['字符的长度限制在{0}-{1}个字符'],
		'#range':['请输入{0}-{1}的数字'],
		'#telephone':['手机号码格式不正确'],
		'#equalTo':['值不一致'],
		'#remote':["remote验证不成功"],
		'#+float':["请输入正浮点数"],
		'#+int':["请输入正整数"],
	
	};
	formValidateType = {
		def:{
			show:function(inp,msg,options){
				this.hide(inp);
				var p = inp.parent();
				p.css("position","relative");
				var _msg = msg;
				var msg = $("<span class='summer-form-error-message' style='color:red;display:block;position:absolute;'></span>").html(msg);
				var left = inp.offset().left-p.offset().left+inp.outerWidth()+10;
				var top = inp.offset().top-p.offset().top;
				msg.css("top",top);
				msg.css("left",left);
				msg.css("width",Summer.visualLength(_msg, p.css("font-size"))+5);
				msg.height(inp.outerHeight());
				msg.css("line-height",inp.outerHeight()+'px');
				if(options.errorCss){
					msg.css(options.errorCss);
					
				}
				p.append(msg);
			},
			hide:function(inp,options){
				var p = inp.parent();
				p.children(".summer-form-error-message").remove();
			}
		},
		'0':{
			show:function(inp,msg){
				this.hide(inp);
				var form = inp.parents("form").eq(0);
				var id = form.attr("id");
				var _id = form.find("[for='"+id+"']").show();
				var _for = form.find("[for='"+inp.attr("name")+"']").show();
				_id.html(msg).val(msg);
				_for.html(msg).val(msg);
			},
			hide:function(inp){
				var form = inp.parents("form").eq(0);
				var id = form.attr("id");
				var _id = form.find("[for='"+id+"']").hide();
				var _for = form.find("[for='"+inp.attr("name")+"']").hide();
				_id.html("").val("");
				_for.html("").val("");
			}
		}
	}
	formRules = {
		"#func":function(target,defs,arr){
			if(arr.length<1||!$.isFunction(arr[0])){
				console.error("#func需要一个参数（Function）");
			}
			var r = arr[0](target,target.parents("form").eq(0));
			if(r){
				return r;
			}
		},
		"#date":function(target,defs,arr){
			var _m =  /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
			if(!_m.test(target.val())){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		},
		"#remote":function(target,defs,arr){
			var val = $.trim(target.val());
			if(arr.length<1){
				console.error("#remote至少需要一个参数（URL）");
			}
			var result = false;
			$.ajax({
				type: "get",
				async:false,
				url: arr[0]+target.val(),
				data:{time:Math.random()},
				success: function(data){
					data = (data+"").toUpperCase();
					if(data.indexOf('TRUE')>=0){
						result = true;
					}
				}
			});
			if(!result){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,1);
			}
		},
		"#equalTo":function(target,defs,arr){
			var val = $.trim(target.val());
			if(arr.length<2){
				console.error("#equalTo至少需要两个参数");
			}
			var oldVal = $(arr[0]).val();
			if(val!=oldVal){
				return arr[1];
			}
		},
		"#telephone":function(target,defs,arr){
			var _m =  /^1\d{10}$/;
			if(!_m.test(target.val())){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		},
		"#rangeLength":function(target,defs,arr){
				var val = $.trim(target.val());
				if(arr.length<2||!$.isNumeric(arr[0])||!$.isNumeric(arr[1])){
					console.error("#rangeLength至少需要两个数字参数");
				}
				var l1 = parseInt(arr[0]);
				var l2 = parseInt(arr[1]);
				if(val.length<l1||val.length>l2){
					var msg = $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,2);
					msg = msg.replace("{0}",l1).replace("{1}",l2);
					return msg;
				}
		},
		"#range":function(target,defs,arr){
			var val = $.trim(target.val());
			if(arr.length<2||!$.isNumeric(arr[0])||!$.isNumeric(arr[1])){
				console.error("#range至少需要两个数字参数");
			}
			var l1 = parseInt(arr[0]);
			var l2 = parseInt(arr[1]);
			var n = parseInt(val);
			if(!$.isNumeric(val)||n<l1||n>l2){
				var msg = $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,2);
				msg = msg.replace("{0}",l1).replace("{1}",l2);
				return msg;
			}
		},
		"#maxLength":function(target,defs,arr){
			var val = $.trim(target.val());
			if(arr.length==0||!$.isNumeric(arr[0])){
				console.error("#maxLength至少需要一个数字参数，且是第一个");
			}
			var l = parseInt(arr[0]);
			if(val.length>l){
				var msg = $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,1);
				msg = msg.replace("{0}",l);
				return msg;
			}
		},
		"#number":function(target,defs,arr){
			if(!$.isNumeric(target.val())){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		},
		"#email":function(target,defs,arr){
			var _m = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
			if(!_m.test(target.val())){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		},
		'#url':function(target,defs,arr){
			var _m =  /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
			if(!_m.test(target.val())){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		},
		'#+float':function(target,defs,arr){
			var _m =  /^((\d+\.\d*[1-9]\d*)|(\d*[1-9]\d*\.\d+)|(\d*[1-9]\d*))$/i;
			if(!_m.test(target.val())){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		},
		'#+int':function(target,defs,arr){
		    var _m =  /^\+?[1-9][0-9]*$/i;
		    if(!_m.test(target.val())){
			return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
		    }
		},
		'#':function(target,defs,arr){
			if($.trim(target.val())==""){
				return $.fn.formMethods.getSimpleErrorMsg(target, defs, arr,0);
			}
		}
	};
	$.fn.formMethods.getSimpleErrorMsg = function(target,defs,arr,i){
		if(arr.length>i){
			if($.isFunction(arr[i])){
				return arr[i](target,defs,arr);
			}else{
				return arr[i];
			}
		}else{
			return defs[0];
		}
	}
	$.fn.formMethods.addRule=function(name,func,defs){
		formDefaults["#"+name] = defs;
		formRules["#"+name] = func;
	};
	$.fn.formMethods.addValidateType=function(name,showFunc,hideFunc){
		formValidateType["@"+name]={};
		formValidateType["@"+name]["show"] = showFunc;
		formValidateType["@"+name]["hide"] = hideFunc;
	};
	$.fn.formMethods.addTodoFunction=function(name,func){
		formTodo["@"+name] = func;
	};
})(jQuery);