/*
 * Author:suixiangjun
 * Mail:630661809@qq.com
 * time:2016-11-15
 * Plug-in:Jquery-validate-1.14.1
 * function:通用验证
 * */
$(function(){
	var errorPrompt = {
		checkPhone:"*请填写正确的手机号！",
		checkEmail:"*请输入正确的邮箱！",
		checkName:"*只能由中文、字母、数字、‘-’‘_’的组合！",
		checkNonum:"*不能是纯数字！",
        checklength:"长度不正确",
        checkIsnum:"*必须是纯数字！",
		checkNameRepeat:"*用户已存在！",
        qycheckNameRepeat:"企业用户名已存在！",
        checkNamenoRepeat:"*用户不存在！",
		checkVCode:"*短信验证码不正确！",
		checkVImg:"*图形验证码不正确！",
        checkNoempty:"*请勿输入空格！",
        checkNumstr:"*必须是字母或数字或两者的组合！",
        checkPhoneRepeat:"*手机号已注册，请更换手机号！",
        checkPhoneNoRepeat:"*该手机号不存在！"
	}
	//手机号码格式验证(如果需要发送验证码需要把验证码的id传过来)
	jQuery.validator.addMethod("checkPhone",function(value,element){
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
        return this.optional(element)||(reg.test(value));
	},errorPrompt.checkPhone);
	//邮箱验证
	$.validator.addMethod("checkEmail",function(value,element,params){
        var reg = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i;  
        return this.optional(element)||(reg.test(value));  
    },errorPrompt.checkEmail); 
    //验证长度
    $.validator.addMethod("checklength",function(value,element,params){
        return this.optional(element)||(value.length===parseInt(params));
    },errorPrompt.checklength);
    //会员名(只能由中文、字母、数字、‘-’‘_’的组合)
	$.validator.addMethod("checkName",function(value,element,params){  
        var reg = /^[\u4e00-\u9fa5A-Za-z0-9_-]*$/;
        return this.optional(element)||(reg.test(value));  
    },errorPrompt.checkName);

    //由数字和密码组成的字符串
    $.validator.addMethod("checkNumstr",function(value,element,params){
        var reg = /^[A-Za-z0-9]+$/;
        return this.optional(element)||(reg.test(value));
    },errorPrompt.checkNumstr);

    //不能输入空格
    $.validator.addMethod("checkNoempty",function(value,element,params){
        var reg = /\s/g;
        return this.optional(element)||(!reg.test(value));
    },errorPrompt.checkNoempty);

    //不能是纯数字
    $.validator.addMethod("checkNonum",function(value,element,params){
        var reg = /^[0-9]*$/;
        return this.optional(element)||(!reg.test(value));
    },errorPrompt.checkNonum);
    //必须是纯数字
    $.validator.addMethod("checkIsnum",function(value,element,params){
        var reg = /^[0-9]*$/;
        return this.optional(element)||(reg.test(value));
    },errorPrompt.checkIsnum);

    //用户名已存在
    jQuery.validator.addMethod("checkNameRepeat",function(value,element,params){
        var basePath = "";
        if($("#basePath").length==1){
            basePath = $("#basePath").val();
        }
        var temp = 0;
        // $.ajax({
        //     type: "post",
        //     url: basePath+"/checkExistCustomerUsername.htm",
        //     data: {customerUsername:value},
        //     async:false,
        //     success: function(data) {
        //         if (data > 0) {
        //             temp=1;
        //         }
        //     },
        //     error: function() {
        //         temp=2;
        //     }
        // })
		return temp!=1;
	},errorPrompt.checkNameRepeat);

    //企业用户名已存在
    jQuery.validator.addMethod("qycheckNameRepeat",function(value,element,params){
        var basePath = "";
        if($("#basePath").length==1){
            basePath = $("#basePath").val();
        }
        var temp = 0;
        // $.ajax({
        //     type: "post",
        //     url: basePath+"/checkcompanyname.htm",
        //     data: {companyName:value},
        //     async:false,
        //     success: function(data) {
        //         if (data > 0) {
        //             temp=1;
        //         }
        //     },
        //     error: function() {
        //         temp=2;
        //     }
        // })
        return temp!=1;
    },errorPrompt.qycheckNameRepeat);

	//用戶名未存在
    jQuery.validator.addMethod("checkNamenoRepeat",function(value,element,params){
        var basePath = "";
        if($("#basePath").length==1){
            basePath = $("#basePath").val();
        }
        var temp = 0;
        // $.ajax({
        //     type: "post",
        //     url: basePath+"/checkExistCustomerUsername.htm",
        //     data: {customerUsername:value},
        //     async:false,
        //     success: function(data) {
        //         if (data > 0) {
        //             temp=1;
        //         }
        //     },
        //     error: function() {
        //         temp=2;
        //     }
        // })
        return temp==1;
    },errorPrompt.checkNamenoRepeat);

	//短信验证码验证(手机号码)
	jQuery.validator.addMethod("checkVCode",function(value,element,params){
        var basePath = "";
        if($("#basePath").length==1){
            basePath = $("#basePath").val();
        }
        var tem = 0;
        // $.ajax({
        //     type:"post",
        //     url: basePath+"/checkpatchcaForRegister.htm?enterValue=" + value + "&mobile=" + $(params).val(),
        //     async: false,
        //     success: function(data) {
        //         if (data == 0) {
        //             tem= 1;
        //         }
        //     }
        // })
		return tem!=1;
	},errorPrompt.checkVCode);

	//验证手机号已存在
    jQuery.validator.addMethod("checkPhoneRepeat",function(value,element,params){
        var basePath = "";
        if($("#basePath").length==1){
            basePath = $("#basePath").val();
        }
        var tem = 0;
        // $.ajax({
        //     url: basePath+"/selectCustmerByMobile.htm?customerMobile=" + value,
        //     context: document.body,
        //     async: false,
        //     success: function(data) {
        //         if (data == 1) {
        //          //已存在
        //             tem = data;
        //         }
        //     }
        // })
        if(params!=true){
            if(tem!=1){
                $(params).attr("disabled",true).css("background-color","#CCCCCC");
            }
        }
        return tem==1;
    },errorPrompt.checkPhoneRepeat);
    //验证手机号不存在
    jQuery.validator.addMethod("checkPhoneNoRepeat",function(value,element,params){
        var basePath = "";
        if($("#basePath").length==1){
            basePath = $("#basePath").val();
        }
        var tem = 0;
        // $.ajax({
        //     url:basePath+"/selectCustmerByMobile.htm?customerMobile=" + value,
        //     context: document.body,
        //     async: false,
        //     success: function(data) {
        //         if (data == 1) {
        //             //已存在
        //             tem = data;
        //         }
        //     }
        // })
        if(params!=true){
            if(tem==1){
                $(params).attr("disabled",true).css("background-color","#CCCCCC");
            }
        }
        return tem!=1;
    },errorPrompt.checkPhoneNoRepeat);
	//图片验证码验证
	jQuery.validator.addMethod("checkVImg",function(value,element,params){
	    //============================这里做推按验证码验证===============================
        var flag =false;
        $.ajax({
            type: "post",
            url: "/validCode?code="+value,
            async: false,//同步
            success: function (data) {
               flag = eval(data);
            },
            error:function () {
                console.log("99999999999");
            }
        });
        return flag;
	},errorPrompt.checkVImg);
})




