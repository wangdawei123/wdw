
/*
* 回到顶部
* */
function navClick() {
    $('html,body').animate({scrollTop:0}, 800);
}

$(function () {
    /*
    * 调用魔窗打开app
    * */
    function linkToAppByMLink(ele, id, appUrl) {

        // 打开app
        // var link='https://aih3gj.mlinks.cc/A060?articleDetail?cid=:cid&url=:url&from=h5';//短链地址
        return {
                mlink: "A060",
                button: document.querySelector(ele),
                autoLaunchApp : false,
                autoRedirectToDownloadUrl: true,
                downloadWhenUniversalLinkFailed: false,
                inapp : false,
                params: {
                    cid:id,
                    url:appUrl,
                    from:'h5'
                },
            }
    }

    FastClick.attach(document.body);

    // 获取页面数据/web
    var id = kf.getSearchParam('id');
    kf.post('/news/getNewDetail', {id: id}, null, function (res) {
        // console.log(res);
        if(res.error_code){
            console.log(res.error_msg);
            $('body').html('<section class="error"><div class="network-fault"><img src="/static/img/share/404.png" alt="网络出错了" title="网络出错了"></div><div class="prompt">额，还没有内容〜</div></section>');
            return;
        }

        // 更新页面浏览次数
        kf.get('/news/updateviews'+location.search, null, null, function (res) {
            // console.log(res);
            if(res.error_code){
                console.log(res.error_msg);
                return;
            }
        }, null, function (err) {
            console.log(err);
        });

        var data = res.data;
        var shareObj = res.data.share;
        var activitys = res.data.activitys;
        var options = [];

        if(data.title){
            document.title = data.title;
        }
        // 渲染头部
        $('#header').html('<h1 class="n-tit">'+data.title+'</h1><div class="source-date"><span>'+data.source+'</span><span>'+data.createTime+'</span></div>');
        $('.hr').show();
        $('#videoOpenAPPSection').show();
        options.push(linkToAppByMLink('a#videoOpenAPP', id, data.appUrl));

        // 渲染底部轮播
        if(activitys.length){
            var swiperHtml = '';
            swiperHtml += '<div class="swiper-container">';
            swiperHtml += '    <div class="swiper-wrapper">';
            activitys.forEach(function (t, v) {
                swiperHtml += '     <div class="swiper-slide">';
                swiperHtml += '         <img src="'+t.ico+'" alt="">';
                swiperHtml += '         <span>'+t.title+'</span>';
                swiperHtml += '     </div>';
            })
            swiperHtml += '    </div>';
            swiperHtml += '    <div class="swiper-pagination"></div>';
            swiperHtml += '</div>';
            swiperHtml += '<a id="btnOpenApp" href="javascript:void(0);" class="footer-download">打开</a>';
            $('.footer-wraper').html(swiperHtml).show();
            if(activitys.length > 1){
                var swiper = new Swiper('.swiper-container', {
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                        renderBullet: function(index, className){
                            return '<span class="' + className + '"></span>';
                        }
                    },
                    direction : 'horizontal',
                    loop: true,
                    autoplay: {
                        delay: 2500,
                        disableOnInteraction: false,
                    },
                    speed: 300,
                })
            }
            options.push(linkToAppByMLink('a#btnOpenApp', id, data.appUrl));
        }

        // 渲染视频
        console.log(data.extent[0].video_img);
        if(data.extent[0].video && data.extent[0].video_img){
            $('#video').html('<div class="video"><img class="cover" src="'+data.extent[0].video_img+'"><img class="btn-play" id="playBtn" src="/static/img/share/big-play-3x.png"></div><video style="display: none;" src="'+data.extent[0].video+'" autobuffer autoloop controls poster="'+data.extent[0].video_img+'"></video>');
            // $('#video').html('<div class="video"><img class="cover" src="'+data.extent.video_img+'"><img class="btn-play" id="playBtn" src="/static/img/share/big-play-3x.png"></div><video style="display: none;" src="http://cdn.vrcdkj.cn/Act-ss-mp4-hd/67731c8e42534edc951ddf8a6cfd0ec4/15118509600898h5b554xk6j.mp4" autobuffer autoloop controls poster="'+data.extent.video_img+'"></video>');
            $('#playBtn').on('click', function () {
                $('.video').hide().siblings('video').show();
                setTimeout(function () {
                    $('video').attr('autoplay', 'true').attr('preload', 'true').get(0).play();
                }, 100);

                var playCount = 0;
                var inApp = (kf.isInApp() || kf.App)? 'in': 'out';
                var obj = {id: id, type: 1, locf: inApp};
                if(kf.isInApp() || kf.App){
                    obj.fromApp = 1;
                }
                $('video')[0].addEventListener("play",function(){
                    playCount++;
                    console.log("playing");

                    if(playCount == 1){
                        // 更新页面视频播放量 type:1视频 2直播  fromApp:1app
                        kf.post('/web/content/videoView', obj, null, function (res) {
                            console.log('videoplayupdate');
                            if(res.error_code){
                                console.log(res.error_msg);
                                return;
                            }
                        }, null, function (err) {
                            console.log(err);
                        });
                    }
                })
            })

            // 唤起app
            options.push(linkToAppByMLink('a#videoOpenAPP', id, data.appUrl));
        }

        // 渲染文章内容
        $('#content').html(data.contentDetail);

        if($('#article').height() >= $(window).innerHeight() - $('#header').outerHeight()){
            $('#article').css({
                'height': ($(window).innerHeight() - $('#header').outerHeight()) * .75 +'px',
                'overflow': 'hidden'
            });
            $('#read-more').show();
        }else {
            $('#videoOpenAPPSection').css('padding-top', '0.55rem')
        }

        // 渲染评论
        if(data.commentList){
            var commentList = '';
            commentList += '<h3>评论</h3>';
            commentList += '<div class="c-box" id="commentList">';
            data.commentList.forEach(function (t, v) {
                commentList += '<a href="javascript:void(0);">';
                commentList += '    <div class="box-top">';
                commentList += '        <img class="user-head" src="'+t.user_image+'">';
                commentList += '        <div class="name-reg">';
                commentList += '            <span class="nick-name">'+t.create_user+'</span>';
                // commentList += '            <span class="region">地区地区</span>';
                commentList += '        </div>';
                commentList += '        <time class="c-time">'+t.create_time+'</time>';
                commentList += '    </div>';
                commentList += '    <span class="c-con">'+t.content+'</span>';
                commentList += '</a>';
                if(v+1 < data.comment_list.length){
                    commentList += '<hr class="hr w-100">';
                }
            })
            commentList += '</div>'
            $('#comment').html(commentList);
            $('#commentOpenAppSection').show();

            options.push(linkToAppByMLink('a#commentOpenApp', id, data.appUrl));
        }

        // 渲染推荐
        if(data.bindShareList){
            $('#recommend h3').show();
            var bindListHtml = '';
            bindListHtml += '<h3>推荐内容</h3>';
            bindListHtml += '<div class="r-box" id="recommendList">';
            data.bindShareList.forEach(function (t, v) {
                bindListHtml += '<a class="recommandItem" id="recommandItem'+v+'" href="javascript:void(0);" data-id="'+ t.bind_id +'" data-appurl="'+ t.appUrl +'">';
                if(t.thumbimg){
                    bindListHtml += '   <div class="box-left">';
                } else {
                    bindListHtml += '   <div class="box-left  no-pic">';
                }
                bindListHtml += '       <div class="tit">';
                bindListHtml += '           <span>'+ t.title +'</span>';
                bindListHtml += '       </div>';
                bindListHtml += '       <div class="date-label">';
                bindListHtml += '           <span class="date">'+ t.create_time +'</span>';
                bindListHtml += '           <span class="label"><img src="/static/img/share/app-read-3x.png" alt="App查看"></span>';
                bindListHtml += '       </div>';
                bindListHtml += '   </div>';
                if(t.thumbimg){
                    bindListHtml += '   <div class="box-right">';
                    bindListHtml += '       <img src="'+ t.thumbimg +'">';
                    bindListHtml += '   </div>';
                }
                bindListHtml += '</a>';
                if(v+1 < data.bind_list.length){
                    bindListHtml += '<hr class="hr w-100">';
                }
            })
            bindListHtml += '</div>';
            $('#recommend').html(bindListHtml);
            $('#recommendOpenAppSection').show();

            options.push(linkToAppByMLink('a#recommendOpenApp', id, data.appUrl));
            $('.recommandItem').each(function (t, v) {
                options.push(linkToAppByMLink('a#'+v.id, $(v).data('id'), $(v).data('appurl')));
            })
        }
        new Mlink(options);

        // 分享
        kf.appShare(0, shareObj.title, shareObj.subtitle, shareObj.img, shareObj.url);
        kf.get("/web/weixin/sign",{url:document.location.href},null,function(package){
            var wxshare=new wechat(package.appId,package.timestamp,package.nonceStr,package.signature);
            wxshare.share(shareObj.title, shareObj.subtitle,shareObj.url,shareObj.img);
        });

        // 点击查看更多
        $('.read-more').on('click', function () {
            $('#article').css({
                'height': 'auto',
                'overflow': 'auto'
            });
            $('#read-more').hide();
        })


    }, null, function (err) {
        console.log(err);
    });


    // 监听回到顶部是否显示
    window.addEventListener('scroll', function () {
        // console.log($(window).scrollTop());
        if($(window).scrollTop() >= window.screen.height){
            $('#back-top').show();
        } else {
            $('#back-top').hide();
        }
    })


})