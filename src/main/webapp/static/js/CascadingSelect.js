(function ($) {
    $.fn.FillOptions = function (url, options) {
 
    options = $.extend({
        textfield: 'text',
        valuefiled: 'value',
        selectedindexid: 0,
        parameter: ''
    }, options || {});
 
    $this = $(this);
    if ($this.children().length === 0) {
        $this.append('<option>--请选择--</option>').attr('disabled', 'disabled');
    }
 
    $this.empty().attr('disabled', 'disabled')
    .append('<option>Loading options</option>');
 
    $.getJSON(url, function (resp) {
    	response=resp.data;
        if (response.length>0) {
            $this.empty().removeAttr("disabled");
            $this.append('<option>-请选择-</option>');
            $.each(response, function (i, item) {
                $this.append($("<option></option>")
                .attr("value", eval("item." + options.valuefiled))
                .text(eval("item." + options.textfield)));
            });
        }
        else {
            $this.empty().prop("disabled", true)
            .append('<option>No elements found</option>');
        }
    })
}
 
 
$.fn.CascadingSelect = function (target, url, options, endfn) {
    if (target[0].tagName != "SELECT") throw "target must be SELECT";
    if (url.length === 0) throw "request is required";
    if (options.parameter === undefined) throw "parameter is required";
    $(this).bind("change", function () {
        var newurl = "";
        urlstr = url.split("?");
        newurl = urlstr[0] + "?" + options.parameter + "=" + $(this).val();
        target.FillOptions(newurl, options);
        if (typeof endfn == "function") { endfn(); }
    })
}
 
$.fn.AddOption = function (text, value, selected, index) {
    option = new Option(text, value);
    this[0].options.add(option, index);
    this[0].options[index].selected = selected;
}
 
})(jQuery); 