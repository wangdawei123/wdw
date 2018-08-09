;
(function($) {
    var APP_SHARE_ALL = 0,
        APP_SHARE_WECHAT = 1,
        APP_SHARE_WECHAT_ZONE = 2,
        APP_SHARE_SINA = 3,
        APP_SHARE_QQ = 4,
        APP_SHARE_QQ_ZONE = 5,
        APP_SHARE_COPY_LINK = 6;

    var KF = function(){
        this.inApp = typeof(window.kfApp) == 'object';
        this.debug=1;
        this.is_login=0;
        this.urlHost = '';
        this.state=0;
        this.u = navigator.userAgent;
        this.isAndroid = this.u.indexOf('Android') > -1 || this.u.indexOf('Adr') > -1;
        this.isiOS = !!this.u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
        this.App = false;
        //添加文章评论记录值
        this.addNewsComment = false;
    }

    KF.prototype.ajax = function(method, type, url, data, before, success, complete, error) {
        $.ajax({
            url: url,
            data: data,
            type: method,
            dataType: type,
            beforeSend: function() {
                if(typeof(before) == 'function'){
                    return before();
                }
            },
            success: function(data) {
                typeof(success) == 'function' && success(data);
            },
            complete: function() {
                typeof(complete) == 'function' && complete();
            },
            error: function() {
                typeof(error) == 'function' && error();
            }
        });
    };

    KF.prototype.post = function(url, data, before, success, complete, error) {
        this.ajax('POST', "json", url, data, before, success, complete, error)
    }

    KF.prototype.get = function(url, data, before, success, complete, error) {
        this.ajax('GET', "json", url, data, before, success, complete, error)
    }
    /**
     * 调用app内分享底部弹框
     * @param  {int} type  type = 0 全部 1 微信 2 朋友圈 3 新浪微博 4 qq 5 qq空间  6  复制链接
     * @param  {string} title 分享的标题
     * @param  {string} desc  分享的简介
     * @param  {string} image 分享的图片
     * @param  {string} url   分享的链接
     * @return
     */
    KF.prototype.appShare = function(type, title, desc, image, url) {
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.appShareNative) == 'function') {
            kfApp.appShareNative(title, desc, image, url, type);
        } else if(this.App) {
            // alert('appShareNative')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('appShareNative',JSON.stringify({title:title, desc:desc, image:image, url:url, type:type}),function(response){
                    console.log(response)
                })
            })
        }
    };


    // =============抽奖活动开始2017.11.11================
    /**
     * 调用app内loadComplete，通知app，页面js已加载完成
     * @return
     */
    KF.prototype.loadComplete = function() {
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.loadComplete) == 'function') {
            kfApp.loadComplete();
        }
    };

    /**
     * app内调用
     * @param  {string} state 活动用户登陆、分享、绑定手机号的状态
     *      1.未登录，未分享
     *      2.未登录，已分享
     *      3.已登录，已分享，但是没有手机号
     *      4.已登录，已分享，并且已绑定手机号
     *      5.已登录，未分享
     * @return
     */
    KF.prototype.setActivityState = function(state) {
        this.state = state;
        if(this.state == 6){
            $("#btnDraw").text('明天再来');
            $("#btnDraw").removeClass('btn-draw').addClass('btn-draw-no');
        }
    };

    /**
     * 调用app内getActivityState
     * @return
     */
    KF.prototype.getActivityState = function() {
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.getActivityState) == 'function') {
            this.state=kfApp.getActivityState();
        }
        return this.state;
    };

    /**
     * 调用app内startClickPrize，通知客户端用户可跳转到手机绑定页面
     * @return
     */
    KF.prototype.startClickPrize = function() {
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.startClickPrize) == 'function') {
            kfApp.startClickPrize();
        }
    };

    /**
     * 调用app内clickTurntable，通知客户端用户已经抽完奖
     * @return
     */
    KF.prototype.clickTurntable = function() {
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.clickTurntable) == 'function') {
            kfApp.clickTurntable();
        }
    };
    /**
     * 调用app内getSessionId，获取SessionId
     * @return
     */
    KF.prototype.getSessionId = function() {
        var sessionId = '';
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.getSessionId) == 'function') {
            return kfApp.getSessionId();
        } else if(this.App) {
            // alert('getSessionId')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('getSessionId',{},function(sessionid){
                    console.log(sessionid);
                    document.cookie="PHPSESSID="+sessionid+";path=/";
                })
            })
        }
    };
    // =============抽奖活动结束2017.11.11================


    /**
     * 调用app内relatedNews:文章详情页
     */
    KF.prototype.relatedNews = function(cid, url, title) {
        if(typeof(window.kfApp) == 'object') {
            if(typeof(kfApp.relatedNews) == 'function'){
                kfApp.relatedNews(cid, url, title);
            }
        } else if(this.App) {
            // alert('relatedNews')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('relatedNews',JSON.stringify({id:cid, url:url, title:title}),function(response){
                    console.log(response)
                })
            })
        }
    };

    /**
     * 调用app内showPic:文章详情页
     */
    KF.prototype.showPic = function(src, imgs_obj) {
        if(typeof(window.kfApp) == 'object') {
            if(typeof(kfApp.showPic) == 'function'){
                kfApp.showPic(src, imgs_obj);
            }
        } else if(this.App) {
            // alert('showPic')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('showPic',JSON.stringify({pos:src, url:imgs_obj}),function(response){
                    console.log(response)
                })
            })
        }
    };

    /**
     * 调用app内评论addComment:文章详情页
     */
    KF.prototype.addToAppComment = function(replayId, userName) {
        if(typeof(window.kfApp) == 'object') {
            window.location.href = "fawan://detail/news/comment/"+replayId+"?username="+userName;
        } else if(this.App) {
            // alert('addToAppComment')
            kf.connectWebViewJavascriptBridge(function(bridge){
                //添加评论添加成功的回掉函数
                bridge.registerHandler("addToAppCommentCallback", function(data) {
                    _this.addNewsComment=true;
                });
                bridge.callHandler('addToAppComment',JSON.stringify({replayId:replayId, username:userName}),function(response){
                    console.log(response)
                })
            })
        }
    };

    /**
     * 调用app内redirectContentDetail:专题页
     */
    KF.prototype.redirectContentDetail = function(type, id, url, extraStr, extraInt) {
        if(typeof(window.kfApp) == 'object') {
            if(typeof(kfApp.redirectContentDetail) == 'function'){
                kfApp.redirectContentDetail(type, id, url, extraStr, extraInt);
            }
        } else if(this.App) {
            // alert('redirectContentDetail')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('redirectContentDetail',JSON.stringify({type:type,id:id,url:url,extraStr:extraStr}),function(response){
                    console.log(response)
                })
            })
        }
    };


    KF.prototype.isInApp = function () {
        return typeof(window.kfApp) == 'object';
    }
    KF.prototype.log = function(message) {
        if(this.debug){
            console.log(message);
        }
    };

    KF.prototype.loading = function(show){
        if(show){
            $.fancybox.showLoading();
        }else{
            $.fancybox.hideLoading();
        }
    };

    KF.prototype.getSearchParam = function(name) {
        // var params=new URLSearchParams(document.location.search.substring(-1));
        // return params.get(param);
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    };

    KF.prototype.notice = function(content,callback) {
        $.fancybox(content, {
            minWidth:"200",
            minHeight:"auto",
            margin:10,
            padding:10,
            closeBtn:false,
            helpers:{overlay:false},
            wrapCSS:"fancybox-notice",
            afterShow:function () {
                window.setTimeout(function () {
                    $.fancybox.close();
                    typeof(callback)=='function' && callback();
                }, 1200);
            }
        });
    };

    KF.prototype.redirect = function(url) {
        document.location.href=url;
    };

    KF.prototype.login = function(url) {
        //投票JS逻辑--登录判断
        if (typeof(window.kfApp) == 'object') {
            // 跳转到app登录页
            kfApp.appLogin();
            return false;
        } else if(this.App) {
            // alert('appLogin')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('appLogin', {},function(response){
                    console.log(response)
                })
            })
        } else {

            // this.redirect('/download/index.html')
            // return false;
            // 跳转到app
            var link='https://aih3gj.mlinks.cc/A06D?activity?url=:url';//短链地址

            var options =
                {
                    mlink: "A06D",
                    button: document.querySelector('a#btnOpenApp'),
                    autoLaunchApp : false,
                    autoRedirectToDownloadUrl: true,
                    downloadWhenUniversalLinkFailed: false,
                    inapp : false,
                    params: {
                        url:url
                    },
                }
            new Mlink(options);
            $("#btnOpenApp").trigger("click");
        }
    };


    KF.prototype.getOriginUrl = function() {
        if (!window.location.origin) {
            this.urlHost = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
        } else {
            this.urlHost = window.location.origin;
        }
        return this.urlHost;
    };

    /**
     * 调用app内openMyBrokeNewsList，打开爆料列表
     */
    KF.prototype.openMyBrokeNewsList = function() {
        // alert(this.App)
        if (typeof(window.kfApp) == 'object' && typeof(kfApp.openMyBrokeNewsList) == 'function') {
            kfApp.openMyBrokeNewsList();
        } else if(this.App){
            // alert('openMyBrokeNewsList')
            kf.connectWebViewJavascriptBridge(function(bridge){
                bridge.callHandler('openMyBrokeNewsList',{},function(response){
                    console.log(response)
                })
            })
        }
    };


//**********************对应新版本app-开始

    KF.prototype.connectWebViewJavascriptBridge = function(callback) {
        var _this = this;
        if(_this.isAndroid){
            if (window.WebViewJavascriptBridge) {
                callback(WebViewJavascriptBridge)
            } else {
                // alert("callback android")
                document.addEventListener(
                    'WebViewJavascriptBridgeReady'
                    , function() {
                        callback(WebViewJavascriptBridge)
                    },
                    false
                );
            }

        }else if(_this.isiOS){
            // alert(window.WebViewJavascriptBridge)
            // alert(window.WVJBCallbacks)
            if (window.WebViewJavascriptBridge) {
                return callback(WebViewJavascriptBridge);
            }
            if (window.WVJBCallbacks) {
                return window.WVJBCallbacks.push(callback);
            }
            window.WVJBCallbacks = [callback];
            var WVJBIframe = document.createElement('iframe');
            WVJBIframe.style.display = 'none';
            WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
            document.documentElement.appendChild(WVJBIframe);
            setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
        }
    }


//**********************对应新版本app-结束

    window.kf=new KF();


    if(!(typeof(window.kfApp) == 'object')){
        // alert(kf.u)
        if(kf.u.indexOf('jsbridge') != -1 ){
            kf.App = true;
            // alert(kf.App);
        }
        kf.connectWebViewJavascriptBridge(function (bridge) {
            // alert("call")
            if (kf.isAndroid) {
                bridge.init(function (message, responseCallback) {
                    console.log(message)
                    console.log(responseCallback)
                })
            }

            bridge.registerHandler('loginCallback',function(sessionid, responseCallback) {
                // alert(sessionid)
                document.cookie="PHPSESSID="+sessionid+";path=/";
                kf.is_login=true;
            })

            // ios调用
            bridge.registerHandler('videoPaused',function(data, responseCallback) {
                // alert('videoPausedBefore')
                $('video')[0].pause();
                // alert('videoPausedAfter')
                // responseCallback({'videoPaused':'sucessed'})
            })
        })

    }else{
        window.loginCallback = function(sessionid){
            document.cookie="PHPSESSID="+sessionid+";path=/";
            kf.is_login=true;
        };
    }

    $(function(){
        kf.inApp = typeof(window.kfApp) == 'object';
    });



})(jQuery);