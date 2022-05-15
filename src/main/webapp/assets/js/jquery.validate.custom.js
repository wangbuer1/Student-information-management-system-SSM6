/*
 @desc:jquery validate插件提示信息自定义设置。可补充
 @exc: key:验证规格，value:提示信息
 * */
$.extend($.validator.messages,{
	required:"带*为必填字段",
	url:"请输入正确的url地址"
});

//验证手机号
jQuery.validator.addMethod('isMobile',function(value,element){
	if(value){
        var tel=/^1[0-9]{10}$/;
        return this.optional(element)||(tel.test(value));
	}
	return true;
},"请填写正确的手机号码");
//验证输入金额
jQuery.validator.addMethod('money',function(value,element){
	var money=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/
	return this.optional(element)||(money.test(value))
},"请输入正确的金额数")
//手机或者座机等联系方式
jQuery.validator.addMethod('isTel',function(value,element){
	if(value){
        var num=/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
        return this.optional(element)||(num.test(value));
	}
	return true;
},"请填写正确的联系方式");

jQuery.validator.addMethod('nickName',function(value,element){
	var nick=/(^[\u4E00-\u9FA5a-zA-Z0-9_]*$)/;
	console.log(nick.test(value))
	return this.optional(element)||(nick.test(value))
},"只支持汉字、数字、字母、下划线")
