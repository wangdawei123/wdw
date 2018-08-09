/**
 * Created by suixiangjun on 2018/2/7.
 */
$(function () {
    //注销
    $(".logout").on("click",function () {
        $.ajax({
            url:'/logout',
            success:function(){
                console.log("success");
                location.href="/";
            },
            error:function(){
                console.log("fail.");
            }
        });

     //   pageRefresh();
    })
    //更多
    $(".basic_menu_more_font,.basic_menu_more_ul").on("mouseover",function () {
       $(".basic_menu_more_ul").stop().slideDown(300);
    })
    $(".basic_menu_more_font,.basic_menu_more_ul").on("mouseout",function () {
        $(".basic_menu_more_ul").stop().slideUp(300);
    })

    //第三方登录
   $('.loginusers').click(function(){
        //type 0:微博 1：微信 2:qq
        var type=$(this).attr('data-id');
        if(!type && type>3){
            layer.msg('非法操作');
            return false;
        }

       //微博
       if(type == 0){
           var client_id = '1944164462';
           var path = 'http://java-web.vrcdkj.cn/';
           window.open('https://api.weibo.com/oauth2/authorize?client_id='+ client_id +'&response_type=code&redirect_uri='+path);
       }

       //微信
       if(type == 1){
           var path = encodeURIComponent("http://java.kanfanews.com/weixinCallBack");//登录后回调的地址
           var appid1 = 'wx592fd002d6a0bacd';//注册申请的appid
           window.open('https://open.weixin.qq.com/connect/qrconnect?appid='+appid1+'&redirect_uri='+path +'&response_type=code&scope=snsapi_login&state=666666#wechat_redirect');
       }

       //QQ
       if(type == 2){
           var client_id = '101463083';
           var path =  'http://java-web.vrcdkj.cn/mycb';
           window.open('https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id='+client_id+'&redirect_uri='+path+'&state=88888qq');
       }


   });

    $(window).scroll(function(){
        //显示返回头部按钮
        if($(window).scrollTop()>100){
            $(".return-top").fadeIn();
        }else{
            $(".return-top").fadeOut();
        }
    })
    //刷新
    $(".refresh").on("click",function () {
        pageRefresh();
    })
    //返回
    $(".return-top").on("click",function () {
        $("html,body").animate({scrollTop:0}, 400);
    })
    //发送验证码
    $(".get-phone-code").on("click",function () {
        var userphoneReg = $("#loginform").validate().element($(".userphone"));
        if(!userphoneReg){
            $(".userphone").focus();
           return
       };
        var verificationCodeReg = $("#loginform").validate().element($(".verification-code"));
       if(!verificationCodeReg){
           $(".verification-code").focus();
           return;
       }
    ////异步请求 倒计时放到成功
        $.ajax({
            url:"/sendPhoneCode?number="+$(".userphone").val(),
            type:"post",
            success:function () {
                //倒计时
                countDown($(".get-phone-code"));
            }
        });

    })

    //倒计时
    function countDown(whoDx){
        //alert("手机号："+$(".userphone").val()+" 图形验证码为："+$(".verification-code").val());
        //发送短信 短信发送成功后执行以下函数
        var wait=60;
        whoDx.attr("disabled",true);
        var countdown = setInterval(function() {
            if(wait==1){
                whoDx.text("获取验证码");
                window.clearInterval(countdown);
                whoDx.removeAttr("disabled");
                wait=60;
                // whoFrom.find(".userphone").trigger("input");
            }else{//发送验证码
                wait--;
                whoDx.text("重新发送" + wait);
            }
        },1000);
    }

    //登录验证
    var loginform = $("#loginform").validate({
        errorElement: "em",
        rules: {
            userPhone: {
                required: true,
                checkPhone: true
            },
            verificationCode: {
                required: true,
                checkVImg:true
            },
            phoneCode: {
                required: true
            }
        },
        messages: {
            userPhone: {
                required: "*手机号不能为空！",
                checkPhone: "*请正确填写手机号!"
            },
            verificationCode: {
                required: "*验证码不能为空！"
            },
            phoneCode: {
                required: "*手机验证码不能为空！"
            }
        },
        submitHandler: function (form) {
            //alert("手机号："+$(".userphone").val()+" 手机验证码为："+$(".phone-code").val()+" 是否一个月免登陆"+$(".avoid-landing").is(':checked'));
            var isRemeber = $(".avoid-landing").is(':checked');
            var phoneNum = $(".userphone").val();
            //设置浏览器cookie
            console.log(isRemeber);

            $(".login-but").text("登录中...").attr("disabled",true);
            $.ajax({
                url:"/checkPhoneCode?number=" + phoneNum + "&code=" + $(".phone-code").val()+"&isRemeber="+isRemeber,
                type:"post",
                success:function (data) {
                    if (data.status == 200) {
                        //登录成功刷新页面
                        pageRefresh();
                    }
                },
                error:function () {
                    console.log("error");
                    //验证码错误
                    loginform.showErrors({"phoneCode": "手机验证码不正确！"});
                    $(".login-but").text("登录").removeAttr("disabled");
                    return
                }
            });
        }
    });

    //反馈验证
    var feedbackForm = $(".feedbackform").validate({
        errorElement: "em",
        rules: {
            feedbackInfo:{
                required: true
            },
            feedbackPhone: {
                checkPhone: true,
                required: true
            }
        },
        messages: {
            feedbackInfo:{
                required: "*反馈内容不能为空！"
            },
            feedbackPhone: {
                checkPhone: "*请正确填写手机号!",
                required: "*联系方式不能为空！"
            }
        },
        submitHandler: function (form) {
            $(".eedfback-but").text("提交中...").attr("disabled",true);
           // alert($("#feedbackInfo").val()+" 联系方式为："+$("#feedbackPhone").val());
            var advice = $("#feedbackInfo").val();
            var phone = $("#feedbackPhone").val();
            $.ajax({
                url:"/feedBack?advice=" + advice + "&phone=" + phone,
                type:"post",
                success:function(data){
                    if (data.status == 200) {
                        //登录成功刷新页面
                        alert("提交成功");
                        window.location.href = "/";
                    }
                    if(data.status == 100){
                        alert("您还未登陆，请先登陆");
                        window.location.href = "/";
                    }
                    if(data.status == 500){
                        feedbackForm.showErrors({"feedbackInfo": "系统繁忙，请稍后再试！"});
                        $(".eedfback-but").text("提交").removeAttr("disabled");
                        return;
                    }
                    pageRefresh();
                },
                error:function () {
                    feedbackForm.showErrors({"feedbackInfo": "系统繁忙，请稍后再试！error"});
                    $(".eedfback-but").text("提交").removeAttr("disabled");
                    return;
                }
            });
        }
    });

    //搜索事件
    $(".search").on("click",".btn",function () {
        var text = $(".search-text").val();
        if(text == null || text == "" || text == undefined){
            text = $("#searchKey").val();
        }
        //跳转页面
        window.location.href = "/search/"+text;
    })
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $(".search").find(".btn").trigger("click");
        }
    });
    //点击更换图片验证码
    $("#seccode").on("click",function () {
        this.src = "/createCode?id="+new Date();
    })


    });
    //刷新页面
    function pageRefresh(){
        window.location.reload()
    }

