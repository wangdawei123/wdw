(function(wx){

    wechat = function(appid,timestamp,nonceStr,signature){

        this.debug=false;

        this.config(appid,timestamp,nonceStr,signature);
    }

    wechat.prototype.config=function(appid,timestamp,nonceStr,signature){

        wx.config({ 
            debug: this.debug,     
            appId: appid, 
            timestamp: timestamp, 
            nonceStr: nonceStr, 
            signature: signature, 
            jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline', 'onMenuShareQQ', 'onMenuShareQZone'] 
        }); 
    }

    wechat.prototype.share = function(title,desc,link,imgUrl) {

        wx.ready(function () {

            var obj={
                title: title,
                desc: desc,
                link: link,
                imgUrl: imgUrl,
                trigger: function (res) {
              
                },
                success: function (res) {
                    if(this.debug)
                    alert('已分享');
                },
                cancel: function (res) {
                    if(this.debug)
                    alert('已取消');
                },
                fail: function (res) {
                    if(this.debug)
                    alert(JSON.stringify(res));
                }
            };

            //发送给朋友
            wx.onMenuShareAppMessage(obj);
            //发送到朋友圈
            wx.onMenuShareTimeline(obj);
            //QQ好友
            wx.onMenuShareQQ(obj);
            //QQ空间
            wx.onMenuShareQZone(obj);
        });
    }
})(wx)


