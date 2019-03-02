(function($) {
	$.fn.ajaxDeleteMethods={};
	$.fn.ajaxDeleteMethods.addFunction=function(name,func){};
	var functions = {};
	$.fn.ajaxDelete = function(opt,ps){
		if(!$.type(opt)=='string'){
			console.error("需要一个string参数");
			return;
		}
		var func = functions[opt];
		if(!$.isFunction(func)){
			console.error("不是一个有效的function："+opt);
			return;
		}
		$(this).each(function(index,el){
			func($(el),ps);
		});
	}
	$.fn.ajaxDeleteMethods.addFunction = function(name,func){
		functions["@"+name] = func;
	};
})(jQuery);