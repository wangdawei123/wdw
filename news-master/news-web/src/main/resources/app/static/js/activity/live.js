
$(function () {

    FastClick.attach(document.body);

    //获取cookie
    function getCookie(cname)
    {
        // console.log(document.cookie);
        var arr = document.cookie.split(';');
        for (var i = 0; i < arr.length; i++)
        {
            var item = arr[i].split('=');
            if(item[0].indexOf(cname) > -1){
                return item[1];
            };
        }
        return "";
    }

    /*
    * 判断客户端类型
    * */
    function clientType() {
        var ua = window.navigator.userAgent,
            isWindowsPhone = /(?:Windows Phone)/.test(ua),
            isSymbian = /(?:SymbianOS)/.test(ua) || isWindowsPhone,
            isAndroid = /(?:Android)/.test(ua),
            isFireFox = /(?:Firefox)/.test(ua),
            isChrome = /(?:Chrome|CriOS)/.test(ua),
            isTablet = /(?:iPad|PlayBook)/.test(ua) || (isAndroid && !/(?:Mobile)/.test(ua)) || (isFireFox && /(?:Tablet)/.test(ua)),
            isPhone = /(?:iPhone)/.test(ua) && !isTablet,
            isPc = !isPhone && !isAndroid && !isSymbian;
        return {
            isTablet: isTablet,
            isPhone: isPhone,
            isAndroid : isAndroid,
            isPc : isPc
        };
    }

    var os = clientType();
    if(os.isAndroid){
        $("textarea").css({
            'line-height': 'px2rem(28px)'
        });
    } 

    /*
    * 判断是否横屏 false横屏  true竖屏
    * */
    function orientationType() {
        var os = clientType();
        if(window.orientation == 90 || window.orientation == -90){
            if(os.isAndroid){
                return true;
            } else if(os.isPhone || os.isTablet){
                return false;
            }
        }else if(window.orientation == 0 || window.orientation == 180){
            if(os.isAndroid){
                return false;
            } else if(os.isPhone || os.isTablet){
                return true;
            }
        }
    }

    /*
    * 判断浏览器类型
    * */
    function browserType() {
        var ua = window.navigator.userAgent.toLowerCase();
        var result = '';
        if(ua.indexOf('mobile Mqqbrowser') > -1){
            result = 'qqapp';
        } else if(ua.indexOf('micromessenger') > -1){
            if(ua.indexOf('wechatdevtools') > -1){
                result = 'wechatdevtools';
            } else {
                result = 'wechatapp';
            }
        }
        return result;
    }

    /*
    * 判断是否为微信浏览器
    * */
    function is_weixin() {
        if(browserType().indexOf('wechat') > -1){
            return true;
        } else {
            return false;
        }
    }

    /*
    * 调用魔窗打开app
    * */
    function linkToAppByMLink(liveId, liveTypeId, ele) {
        // 跳转到app
        var link='https://aih3gj.mlinks.cc/Adif?livePageCourtDetail?cid=:cid&live_type_id=:live_type_id&from=h5';//短链地址

        var options = [
            {
                mlink: "Adif",
                button: document.querySelector(ele),
                autoLaunchApp : false,
                autoRedirectToDownloadUrl: true,
                downloadWhenUniversalLinkFailed: false,
                inapp : false,
                params: {
                    cid:liveId,
                    live_type_id:liveTypeId,
                    from:'h5'
                },
            }
        ];
        new Mlink(options);
    }


    //==========评论相关函数start===============
    /*
    * 将评论列表加载到页面
    * */
    function addCommentToHtml(data, comments) {
        var commentList = '';
        if(!data.length){
            return '';
        }

        commentList += '<h3>评论</h3>';
        commentList += '<div class="c-box" id="commentList">';
        data.forEach(function (t, v) {
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
            if(v+1 < data.length){
                commentList += '<hr class="hr w-100">';
            }
        })
        commentList += '</div>';

        return commentList;
    }


    /*
    * 页面其他数据相关
    * */
    function addIntroduceToHtml(data) {

        // 节目介绍相关
        var newHtml = '';

        // 介绍
        newHtml += '<div id="introduceTop">';
        newHtml += '<div class="intr-box">';
        newHtml += '    <div class="intr-tit">' + data.title + '</div>';
        newHtml += '    <div class="intr-con">' + data.live_content + '</div>';
        newHtml += '    <div class="show" id="show">';
        newHtml += '        <span>展开</span><img src="../../static/img/live/show.png" alt="">';
        newHtml += '    </div>';
        newHtml += '</div>';

        // 相关视频
        var bindList ;
        if(data.bind_list) {
            alert(data.bind_list);
            bindList = data.bind_list;

            if (bindList.length) {
                newHtml += '<div class="relevant-video clearfix">';
                newHtml += '    <div class="rele-tit">相关视频</div>';
                newHtml += '    <div class="rele-con">';
                bindList.forEach(function (item, index) {
                    newHtml += '    <div class="rele-item">';
                    newHtml += '        <a href="' + item.url + '">';
                    newHtml += '            <span class="left">';
                    newHtml += '                <img src="' + item.thumbimg + '" alt="">';
                    //直播状态，展示直播状态，0预告，1直播中，2回顾
                    if (item.type == 22) {

                        if (item.live_stat == 1) {
                            newHtml += '                <i class="label">直播中</i>';
                        } else if (item.live_stat == 0) {
                            newHtml += '                <i class="label">预告</i>';
                        } else if (item.live_stat == 2) {
                            newHtml += '                <i class="label review">回顾</i>';
                            newHtml += '                <i class="v-time">' + item.duration + '</i>';
                        }
                    } else if (item.type == 4) {
                        newHtml += '                <i class="v-time">' + item.duration + '</i>';
                    }

                    newHtml += '            </span>';
                    newHtml += '            <span class="right">' + item.title + '</span>';
                    newHtml += '        </a>';
                    newHtml += '    </div>';
                    if (index < bindList.length - 1) {
                        newHtml += '    <hr>';
                    }
                })
                newHtml += '    </div>';
                newHtml += '</div>';
            }
        }
        newHtml += '</div>';

        newHtml += '<div id="comment" class="comment">';
        // 评论相关：获取评论列表
        if(!data.comment_status && data.comment_list){
            //将评论列表加载到页面
            newHtml += addCommentToHtml(data.comment_list, data.comments);
        }

        newHtml += '</div>';

        if($('#tab').css('display') == 'none'){
            $('#introduce').css({
                'max-height': $(window).height()-$('#video').outerHeight()-$('.openApp').outerHeight()-$('.anchors-info').outerHeight()-$('.stall').outerHeight()-$('.footer-wraper').outerHeight()+'px',
                'overflow-y': 'scroll'
            })
        }else{
            $('#introduce').css({
                'max-height': $(window).height()-$('#video').outerHeight()-$('.openApp').outerHeight()-$('.anchors-info').outerHeight()-$('.stall').outerHeight()-$('#tab').outerHeight()-$('.footer-wraper').outerHeight()+'px',
                'overflow-y': 'scroll'
            })
        }

        $('#introduce').html(newHtml);
    }

    /*
    * 视频介绍展开
    * */
    // function showAll(tab) {
    //     if($('.intr-con').height() < ($('.intr-con')[0].scrollHeight-4)){
    //         $("#show").css({
    //             'display': 'block'
    //         });
    //     } else {
    //         $("#show").css({
    //             'display': 'none'
    //         });
    //
    //     }
    // }
    //==========评论相关函数end===============


    //==========聊天室相关函数start===============
    /*
    * 聊天室每条消息的html
    * current: 是否是当前用户
    * userName: 消息用户名
    * content: 消息内容
    * return：返回每条消息的html
    * */
    function addMessageTohtml(current, userName, content) {
        content = RongIMLib.RongIMEmoji.emojiToHTML(content);
        var messageHtml = '';
        if(current){
            messageHtml = '<p><span class="current-user">' + userName+ '：</span><span>' + content + '</span></p>';
        } else {
            messageHtml = '<p><span>' + userName + '：</span><span>' + content + '</span></p>';
        }
        return messageHtml;
    }


    /*
    * 设置emojiCss
    * */
    function emojiCss() {
        if(dpr == 2){
            $('.rong-emoji-content').css({
                'transform': 'scale(2)',
                'margin': '0.5rem'
            })
        } else if(dpr == 3){

            $('.rong-emoji-content').css({
                'transform': 'scale(3)'
            })
        } else {
            $('.rong-emoji-content').css({
                'transform': 'scale(1)',
                'margin': '0.3rem'
            })
        }

        if($('.emojiRow span').length != 7){
            $('.emojiRow span').css({
                'flex': 'inherit'
            })
        }

    }

    /*
    * 假消息列表
    * */
    function getDiscussion() {
        kf.post('../../web/comment/getDiscussion', {room_id: chatRoomId}, null, function (res) {

            // console.log(res);
            if(res.error_code){
                console.log(res.error_msg);
                return;
            }
            var discussion = res.data.discussion;
            var msgHtml = '';
            discussion.forEach(function (item, index) {
                msgHtml += addMessageTohtml(false, item.user_name, item.content);
            })
            $('.chat-list').append(msgHtml);


            // 此处应该放到getChatlist()、getDiscussion()
            $('#chatList').scrollTop($('#chatList')[0].scrollHeight);

        }, null, function (err) {
            console.log(err);
        })
    }

    // 发送消息
    function sendMessage() {

        var content = RongIMLib.RongIMEmoji.symbolToEmoji($('#msgInput').val());
        var msg = new RongIMLib.TextMessage({content: content, user:{id: uid, name: uname, portrait: uimage}, extra:""});;
        var conversationtype = RongIMLib.ConversationType.CHATROOM;

        RongIMClient.getInstance().sendMessage(conversationtype, chatRoomId, msg, {
                onSuccess: function (message) {
                    //message 为发送的消息对象并且包含服务器返回的消息唯一Id和发送消息时间戳
                    console.log("Send successfully");
                    // console.log(message);

                    $('.chat-list').append(addMessageTohtml(true, message.content.user.name, message.content.content));
                    emojiCss();
                    $('#msgInput').val('');
                    $('#emoji').height('0');
                    $('.send-box').css('bottom','0');

                    $('#chatList').scrollTop($('#chatList')[0].scrollHeight);
                },
                onError: function (errorCode,message) {
                    var info = '';
                    switch (errorCode) {
                        case RongIMLib.ErrorCode.TIMEOUT:
                            info = '超时';
                            break;
                        case RongIMLib.ErrorCode.UNKNOWN_ERROR:
                            info = '未知错误';
                            break;
                        case RongIMLib.ErrorCode.REJECTED_BY_BLACKLIST:
                            info = '在黑名单中，无法向对方发送消息';
                            break;
                        case RongIMLib.ErrorCode.NOT_IN_DISCUSSION:
                            info = '不在讨论组中';
                            break;
                        case RongIMLib.ErrorCode.NOT_IN_GROUP:
                            info = '不在群组中';
                            break;
                        case RongIMLib.ErrorCode.NOT_IN_CHATROOM:
                            info = '不在聊天室中';
                            break;
                        case 23408:
                            info = '你已被禁言，不能发送消息';
                            break;
                        default :
                            info = '其他';
                            break;
                    }
                    console.log(errorCode,info);
                    $('.hint-info').text(info).show();
                }
            }
        );

    }

    /*
    * 融云相关监听事件
    * */
    function rongListener() {
        // 设置连接监听状态 （ status 标识当前连接状态 ）
        // 连接状态监听器
        RongIMClient.setConnectionStatusListener({
            onChanged: function (status) {
                switch (status) {
                    case RongIMLib.ConnectionStatus.CONNECTED:
                        console.log('链接成功');
                        break;
                    case RongIMLib.ConnectionStatus.CONNECTING:
                        console.log('正在链接');
                        break;
                    case RongIMLib.ConnectionStatus.DISCONNECTED:
                        console.log('断开连接');
                        break;
                    case RongIMLib.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT:
                        console.log('其他设备登录');
                        break;
                    case RongIMLib.ConnectionStatus.DOMAIN_INCORRECT:
                        console.log('域名不正确');
                        break;
                    case RongIMLib.ConnectionStatus.NETWORK_UNAVAILABLE:
                        console.log('网络不可用');
                        break;
                }
            }
        });

        // 消息监听器
        RongIMClient.setOnReceiveMessageListener({
            // 接收到的消息
            onReceived: function (message) {
                // 判断消息类型
                switch(message.messageType){
                    case RongIMClient.MessageType.TextMessage:
                        // message.content.content => 消息内容
                        // console.log(message);
                        if(message.content.user.id){
                            $('.chat-list').append(addMessageTohtml(message.content.user.id == uid ? true: false, RongIMLib.RongIMEmoji.emojiToHTML(message.content.user.name), message.content.content));
                        }

                        // 设置emoji缩放
                        emojiCss();

                        // 设置聊天室初始化时滚动到最底部
                        if(message.offLineMessage){
                            $('#chatList').scrollTop($('#chatList')[0].scrollHeight);
                        }

                        // 新消息弹出框提示
                        if($('#chatList').scrollTop()+$('#chatList')[0].offsetHeight < $('#chatList')[0].scrollHeight){
                            $('#newMsg').show();
                        } else {
                            $('#newMsg').hide();
                        }

                        break;
                    case RongIMClient.MessageType.VoiceMessage:
                        // 对声音进行预加载
                        // message.content.content 格式为 AMR 格式的 base64 码
                        break;
                    case RongIMClient.MessageType.ImageMessage:
                        // message.content.content => 图片缩略图 base64。
                        // message.content.imageUri => 原图 URL。
                        break;
                    case RongIMClient.MessageType.DiscussionNotificationMessage:
                        // message.content.extension => 讨论组中的人员。
                        break;
                    case RongIMClient.MessageType.LocationMessage:
                        // message.content.latiude => 纬度。
                        // message.content.longitude => 经度。
                        // message.content.content => 位置图片 base64。
                        break;
                    case RongIMClient.MessageType.RichContentMessage:
                        // message.content.content => 文本消息内容。
                        // message.content.imageUri => 图片 base64。
                        // message.content.url => 原图 URL。
                        break;
                    case RongIMClient.MessageType.InformationNotificationMessage:
                        // do something...
                        break;
                    case RongIMClient.MessageType.ContactNotificationMessage:
                        // do something...
                        break;
                    case RongIMClient.MessageType.ProfileNotificationMessage:
                        // do something...
                        break;
                    case RongIMClient.MessageType.CommandNotificationMessage:
                        // do something...
                        break;
                    case RongIMClient.MessageType.CommandMessage:
                        // do something...
                        break;
                    case RongIMClient.MessageType.UnknownMessage:
                        // do something...
                        break;
                    default:
                        console.log('默认消息类型');
                }
            }
        });

    }

    function arrChange(arr, num) {

        // 改为二维数组
        var newList = new Array(Math.ceil(arr.length/num));
        for(var i = 0; i<newList.length;i++){
            newList[i] = new Array();
            // for(var j=0; j<num; j++){
            //     newList[i][j] = '';
            // }
        }
        for(var k = 0; k<arr.length;k++){
            newList[parseInt(k/num)][k%num] = arr[k];
        }
        return newList;
    }

    /*
    * 初始化融云SDK  emoji
    * */
    function startInit(appKey) {
        var config = {};

        // 初始化融云SDK
        RongIMLib.RongIMClient.init(appKey);

        // 初始化emoji
        RongIMLib.RongIMEmoji.init();
        var emojiList = RongIMLib.RongIMEmoji.list;

        if(!tourist){

            // 改为二维数组
            var newList = arrChange(emojiList, 21);

            var emojiHtml = '';
            emojiHtml += '<div class="swiper-container emojiSwiperContainer">';
            emojiHtml += '    <div class="swiper-wrapper">';

            // console.log(newList);
            newList.forEach(function (item, index) {
                emojiHtml += '        <div class="swiper-slide">';

                var newRow = arrChange(item, 7);

                // console.log(newRow);
                newRow.forEach(function (t,i) {

                    if( index == newList.length - 1 && i == newRow.length-1){

                        emojiHtml += '        <div class="emojiRow">';
                    }else {

                        emojiHtml += '        <div class="emojiRowFull">';
                    }
                    t.forEach(function (t2) {
                        emojiHtml += RongIMLib.RongIMEmoji.emojiToHTML(t2.emoji);
                    })

                    emojiHtml += '        </div>';
                })

                emojiHtml += '        </div>';
            })
            emojiHtml += '    </div>';
            emojiHtml += '    <div class="swiper-pagination"></div>';
            emojiHtml += '</div>';

            $('#emoji').html(emojiHtml);

            emojiCss();

            if(newList.length > 0){
                var swiper = new Swiper('.emojiSwiperContainer', {
                    pagination: {
                        el: '.emojiSwiperContainer .swiper-pagination',
                        renderBullet: function(index, className){
                            return '<span class="' + className + '"></span>';
                        }
                    },
                    direction : 'horizontal',
                    // loop: false,
                    speed: 300
                })
            }
        }
    }


    /*
    * 获取消息列表
    * */
    function getChatlist() {
        var count = 50; // 拉取的条数 count <= 200
        var order = 1;  // 1正序；0倒序
        RongIMClient.getInstance().getChatRoomHistoryMessages(chatRoomId, count, order, {
            onSuccess: function(list, hasMore) {
                // list => 消息数组
                // hasMore => 是否有更多的历史消息

                console.log(list);
                console.log(hasMore);
                // 消息列表的最顶部需要添加公告
                if(list.length){
                    // 将云存储消息list加载到页面

                } else {
                    // 请求PHP, 获取假的消息列表
                    getDiscussion();
                }
            },
            onError: function(error) {
                console.log('获取云存储消息错误' + error);
            }
        });

    }

    /*
    * 加入聊天室
    * */
    function joinChatRoom(){
        // chatRoomId = chatRoomId.replace(/(^\s*)|(\s*$)/g, " ");
        if(chatRoomId == ""){
            alert("聊天室 id不存在");
            return false;
        }
        var IM = RongIMClient.getInstance();
        IM.joinChatRoom(chatRoomId, 50, {
            onSuccess: function() {
                // alert("加入聊天室 " + chatRoomId + " 成功");
                console.log("加入聊天室 " + chatRoomId + " 成功");
                setInterval(function () {
                    if(!$('#chatList p').length){
                        // 请求PHP, 获取假的消息列表
                        getDiscussion();
                    }
                }, 2000)

                // 设置聊天列表最大高度
                if(!tourist){
                    $('#chatList').css('max-height',window.screen.availHeight*dpr-$('#video').outerHeight()-$('.openApp').outerHeight()-$('.anchors-info').outerHeight()-$('.stall').outerHeight()-$('#tab').outerHeight()-$('.chatSendBox').outerHeight()-20+'px');
                } else {
                    $('#chatList').css('max-height',window.screen.availHeight*dpr-$('#video').outerHeight()-$('.openApp').outerHeight()-$('.anchors-info').outerHeight()-$('.stall').outerHeight()-$('#tab').outerHeight()-$('.footer-wraper').outerHeight()-20+'px');
                }
                // 获取消息列表
                // getChatlist(chatRoomId);
            },
            onError: function(error) {
                alert("加入聊天室失败");
            }
        });
    }


    /*
    * 退出聊天室
    * */
    function quitChatRoom() {
        RongIMClient.getInstance().quitChatRoom(chatRoomId, {
            onSuccess: function() {
                // 退出聊天室成功。
                console.log('退出聊天室');
            },
            onError: function(error) {
                // 退出聊天室失败。
                console.log(error);
            }
        });
    }
    
    /*
    * 连接融云服务器
    * */
    function rongImConnect(token) {

        // 连接融云服务器
        // 并且所有除监听以外的方法都必须在 connect成功之后 再调用
        RongIMClient.connect(token, {
            onSuccess: function(userId) {
                console.log("Connect successfully.");
                // alert("Connect successfully.");

                // 加入聊天室
                joinChatRoom();
            },
            onTokenIncorrect: function() {
                console.log('token无效');
            },
            onError:function(errorCode){
                var info = '';
                switch (errorCode) {
                    case RongIMLib.ErrorCode.TIMEOUT:
                        info = '超时';
                        break;
                    case RongIMLib.ErrorCode.UNKNOWN_ERROR:
                        info = '未知错误';
                        break;
                    case RongIMLib.ErrorCode.UNACCEPTABLE_PaROTOCOL_VERSION:
                        info = '不可接受的协议版本';
                        break;
                    case RongIMLib.ErrorCode.IDENTIFIER_REJECTED:
                        info = 'appkey不正确';
                        break;
                    case RongIMLib.ErrorCode.SERVER_UNAVAILABLE:
                        info = '服务器不可用';
                        break;
                }
                console.log(errorCode);
            }
        });

    }

    /*
    * 获取游客的融云token
    * */
    function getChatroomToken(obj) {
        // 获取游客的融云token
        //url, data, before, success, complete, error
        kf.post('../../web/chatroom/getToken', obj, null, function (res) {
            // console.log('222', res);
            if(res.error_code){
                console.log(res.error_msg);
                return;
            }
            // 融云用户的token
            // 连接融云服务器
            rongImConnect(res.data.token);
        }, null, function (err) {
            console.log(err);
        });
    }
    //==========聊天室相关函数end===============


    /*
    * 更新页面视频播放量 type:1视频 2直播  fromApp:1app
    * */
    function videoPlayUpdate(id) {
        var playCount =0;
        var inApp = (kf.isInApp() || kf.App)? 'in': 'out';
        $('video')[0].addEventListener("play",function(){
            playCount++;
            console.log("playing");
            if(os.isAndroid && isWeiXinBrower && parseFloat(this.currentTime) <= 0){
                playCount--;
            }

            if(playCount == 1){

                kf.post('../../web/content/videoView', {id: id, type: 2, locf: inApp}, null, function (res) {
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
    }

    /*
    * 根据ID获取页面视频等数据
    * */
    function getLiveDetail(sessionId) {
        var id = kf.getSearchParam('id');
        kf.post('../../web/live/getLiveDetail', {'id': id, 'websessionid': sessionId}, null, function (res) {
            console.log(res);
            if(res.status != 200){
                $('body').html('<div class="error">' +
                                '<div class="network-fault">' +
                                '<img src="../../static/img/bluesky/404.png" ' +
                                'alt="网络出错了" title="网络出错了">' +
                                '</div>' +
                                '<div class="prompt">' + res.error_msg + '</div>' +
                                '</div>');
                return;
            }
            var data = res.data;
            var share = data.share;
            videoStat = data.stat;
            liveId = data.live_id;
            liveTypeId = data.live_type_id;
            review_box = data.review_box;
            chatroom_status = data.chatroom_status;
            title = data.title;
            activitysObj = data.activitys;
            // 庭审
            if(liveTypeId == 2){
                window.location.href = kf.getOriginUrl() + '../../web/live/index?id=' + liveId + '&share=1';
                return;
            }

            // 更新页面浏览次数
            kf.get('../../web/index/updateviews?id='+id+'&type='+data.type+'&locf='+kf.getSearchParam('locf'), null, null, function (res) {
                 console.log(res);
                if(res.status == 0){
                    return;
                }
            }, null, function (err) {
                console.log(err);
            });
            if(data.user){
                if(!data.user.id){
                    tourist = true;
                } else {
                    uid = data.user.id;
                    uname = data.user.nickname;
                    uimage = data.user.image;
                }
            }

            document.title = title;


            // 渲染底部轮播
            if(activitysObj.length){
                var swiperHtml = '';
                swiperHtml += '<div class="swiper-container">';
                swiperHtml += '    <div class="swiper-wrapper">';
                activitysObj.forEach(function (t, v) {
                    swiperHtml += '     <div class="swiper-slide">';
                    swiperHtml += '         <img src="'+t.ico+'" alt="">';
                    swiperHtml += '         <span>'+t.title+'</span>';
                    swiperHtml += '     </div>';
                })
                swiperHtml += '    </div>';
                swiperHtml += '    <div class="swiper-pagination"></div>';
                swiperHtml += '</div>';
                swiperHtml += '<a id="btnOpenApp" href="javascript:void(0);" class="footer-download">打开</a>';
                $('.footer-wraper').html(swiperHtml);
                if(activitysObj.length > 1){
                    $.getScript("../../static/js/lib/swiper.min.js",function () {
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
                    });
                }

                linkToAppByMLink(liveId, liveTypeId, 'a#btnOpenApp');
            }
            var os = clientType();
            var playCount = 0;
            if(os.isAndroid){
                $('.video').append('<video id="liveVideo" autoplay="autoplay" controls  -webkit-playsinline="true" ' +
                    '               x-webkit-airplay="true" poster="'+data.cover_img+'" playsinline src="'
                                    + data.live_url + '">');

                setTimeout(function () {
                    $('video').attr('autoplay', 'true').attr('preload', 'true').get(0).play();
                }, 100);

                videoPlayUpdate(id);
            } else {

                $('.video').append('<div  class="prism-player" id="J_prismPlayer"></div>');
                    
                $.getScript('//g.alicdn.com/de/prismplayer/2.4.0/aliplayer-min.js', function () {
                    var obj =
                        obj = {
                            id: "J_prismPlayer",
                            autoplay: false,
                            isLive:false,
                            playsinline:true,
                            width:"100%",
                            height:"400px",
                            controlBarVisibility:"always",
                            useH5Prism:false,
                            useFlashPrism:false,
                            x5_video_position:'top', //指定视频在上部显示
                            x5_type:'h5', //声明启用同层H5播放器，支持的值：h5
                            // x5_fullscreen:false,
                            source:data.live_url,
                            cover:data.cover_img,
                            skinLayout:[
                                {"name":"bigPlayButton","align":"blabs","x":30,"y":80},
                                {"name":"H5Loading","align":"cc"},
                                {"name":"errorDisplay","align":"tlabs","x":0,"y":0},
                                {"name":"infoDisplay","align":"cc"},
                                {"name":"controlBar","align":"blabs","x":0,"y":0,"children":[{"name":"progress","align":"tlabs","x":0,"y":0},
                                    {"name":"playButton","align":"tl","x":15,"y":26},
                                    {"name":"fullScreenButton","align":"tr","x":20,"y":25},
                                    {"name":"timeDisplay","align":"tl","x":10,"y":24},
                                    {"name":"setButton","align":"tr","x":20,"y":25},
                                    {"name":"streamButton","align":"tr","x":20,"y":23},
                                    {"name":"volume","align":"tr","x":20,"y":25}]},
                                {"name":"fullControlBar","align":"tlabs","x":0,"y":0,"children":[{"name":"fullTitle","align":"tl","x":25,"y":6},
                                    {"name":"fullNormalScreenButton","align":"tr","x":24,"y":13}]}]
                        };
                    //直播中
                    if(videoStat == 1){
                        obj.isLive = true;
                        obj.useH5Prism = true;
                    }

                    var player = new Aliplayer(obj);

                    videoPlayUpdate(id);
                })
            }

            $('.openApp').show();
            linkToAppByMLink(liveId, liveTypeId, 'a#openApp');

            $('.stall').show();
            // 主播相关
            $('#anchorsHead img').attr('src', data.source_image);
            $('#anchorsName').text(data.source_name);
            $('.anchors-info').show();

            if(videoStat == 1 && chatroom_status == 1){
                // 直播中 并且聊天室开放的状态

                $('.tab').show();
                if(tourist){
                    $('.footer-wraper').show();
                } else {
                    $('.send-box').show();
                }

                // 设置聊天室公告
                $('.broadcast').html(data.chatroom_notice);
                $('.broadcast').show();

                chatRoomId = data.chatroomid;

                $.getScript("https://cdn.ronghub.com/RongIMLib-2.2.9.min.js",function () {
                    $.getScript("https://cdn.ronghub.com/RongEmoji-2.2.6.js", function () {
                        $.getScript("../../static/js/lib/swiper.min.js",function () {

                            // 聊天室相关
                            // 初始化融云  需要appkey
                            startInit(data.rong_id);

                            // 融云监听
                            rongListener();

                            if(tourist){
                                // 游客
                                $.getScript("../../static/js/lib/fingerprint2.min.js", function () {
                                    // 设置游客ID
                                    new Fingerprint2().get(function(result, components){
                                        //console.log(result); //a hash, representing your device fingerprint
                                        getChatroomToken({'fingerId': result, 'chatroomID': chatRoomId}, data.chatroom_notice);
                                    });
                                });
                            } else {
                                if(sessionId){
                                    getChatroomToken({'sessionid': sessionId, 'chatroomID': chatRoomId}, data.chatroom_notice);
                                } else if(websessionid && websessionid != 'noauth'){
                                    getChatroomToken({'sessionid': websessionid, 'chatroomID': chatRoomId}, data.chatroom_notice);
                                }
                            }
                        });
                    });
                });

            } else if((videoStat == 1 && chatroom_status == 0) || videoStat == 2 || videoStat == 0){
                // 回顾、预告
                $('.footer-wraper').show();
                $('.chat-room').remove();
                $('#introduce').show();
            } else {
                $('.chat-room').remove();
                $('#introduce').show();
            }

            // 页面节目介绍、相关视频
            addIntroduceToHtml(data);

            // 展开是否显示：没有聊天室的场合
           // showAll(false);

            kf.get("../../web/weixin/sign",{url:share.url},null,function(package){
                console.log(package);
                var wxshare=new wechat(package.appId,package.timestamp,package.nonceStr,package.signature);
                if(!share.subtitle){
                    share.subtitle = share.title;
                }
                wxshare.share(share.title,share.subtitle,share.url,share.img);
            });

        }, null, function (err) {
            console.log(err);
        });
    }


    var chatRoomId = "";// 聊天室ID
    var uid = '';// 用户ID
    var uname = '';// 用户name
    var uimage = '';// 用户头像
    var title = '';// title
    
    var liveId = 0;// 视频ID
    var liveTypeId = 0;// 视频类型ID
    var chatroom_status = 0;// 聊天室是否显示
    var review_box = 0;// 评论框是否显示
    var videoStat = 0;// 视频状态 0: 预告 1:直播中 2:回顾
    var dpr = $('html').data('dpr');// 1安卓  2、 ios7  3、IOS7P
    var tourist = false;// 游客
    var websessionid = kf.getSearchParam('websessionid');
    var isWeiXinBrower = is_weixin();
    if(isWeiXinBrower){
        // 授权
        var sessionId = getCookie('websessionid');
        if(!sessionId){

            if(websessionid && websessionid != 'noauth'){
                // 正常微信授权用户
                // 获取页面数据
                getLiveDetail(websessionid);
            } else {
                // 获取页面数据
                getLiveDetail();
                if(websessionid == 'noauth'){
                    // 微信浏览器游客
                    tourist = true;
                    console.log('授权失败');
                    return;
                } else {
                    window.location.href = kf.getOriginUrl() + '/web/user/wechatAuth?id=' + kf.getSearchParam('id');
                    console.log('授权成功');
                }
            }

        } else {

            // 正常微信授权用户
            // 获取页面数据
            getLiveDetail(sessionId);
        }

    } else {
        // 浏览器游客
        tourist = true;
        // 获取页面数据
        getLiveDetail();
    }


    // 监听新消息提示
    $('#chatList')[0].addEventListener('scroll', function () {

        if($(this).scrollTop() + $(this)[0].offsetHeight == this.scrollHeight){
            $('#newMsg').hide();
        }
    });

    function pushHistory() {
        var state = {
            title: title,
            url: location.href
        };
        window.history.pushState(state, title, "");
    }

    // web浏览器监听关闭按钮
    window.addEventListener("beforeunload", function(event) {
        if(chatRoomId){
            quitChatRoom();
        }
    });

    pushHistory();
    // 监听浏览器的返回按钮
    window.addEventListener("popstate", function(e) {

        // alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能
        if(chatRoomId){
            quitChatRoom();
        }

        var ua = navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i)=="micromessenger") {
            WeixinJSBridge.call('closeWindow'); //微信
        } else if(ua.indexOf("alipay")!=-1){
            AlipayJSBridge.call('closeWebview'); //支付宝
        }else if(ua.indexOf("baidu")!=-1){
            BLightApp.closeWindow(); //百度
        }else{
            window.close(); //普通浏览器
        }

        pushHistory();

    }, false);

    $('#msgInput').on('focus', function () {
        $('#emoji').height('0');
        $('.send-box').css('bottom','0');
    })
    //
    // $('#msgInput').on('blur', function () {
    //     // $('#emoji').height('auto');
    //     // $('.send-box').css('bottom','4.18rem');
    // })


    $('.hint-info').on('animationend webkitAnimationEnd',function(e){
        $('.hint-info').hide();
    });

    var count = 0;
    $('.content').on('click', function(e){
        // console.log(e.target);
        var target = e.target;
        if(e.target.id == 'tab1' || $(e.target).parent('#tab1').length){

            $('#tab1').addClass("focus").siblings().removeClass("focus");
            if($('#tab1 img').attr('src') == "../../static/img/live/chat-room.png"){
                $('#tab1 img').attr('src', '../../static/img/live/chat-room-focus.png');
                $('#tab2 img').attr('src', '../../static/img/live/play.png');
                $('.chat-room').show();
                $('.introduce').hide();
            }
            if(!tourist){
                $('.footer-wraper').hide();
            }

        } else if(e.target.id == 'tab2' || $(e.target).parent('#tab2').length){

            $('#tab2').addClass("focus").siblings().removeClass("focus");
            if($('#tab2 img').attr('src') == "../../static/img/live/play.png"){
                $('#tab2 img').attr('src', '../../static/img/live/play-focus.png');
                $('#tab1 img').attr('src', '../../static/img/live/chat-room.png');
                $('.chat-room').hide();
                $('.introduce').show();
            }

            // 展开是否显示：有聊天室的场合
            count++;
            if(count == 1){
                showAll(true);
            }

            $('.footer-wraper').show();

        } else if(e.target.id == 'expression' || $(e.target).parent('#expression').length){
            // 用户
            $('#emoji').height('4.55rem');
            $('.send-box').css('bottom','4.55rem');

        } else if(e.target.id == 'send'){
            $('#msgInput').blur();
            if(!$('#msgInput').val()){
                console.log('请输入内容');
                $('.hint-info').text('发送内容不能为空').show();
            } else {
                // 发送内容
                sendMessage();
            }

            $('#emoji').height('0');
            $('.send-box').css('bottom','0');

        } else if(e.target.id == 'newMsg' || $(e.target).parent('#newMsg').length){

            $('#chatList').scrollTop($('#chatList')[0].scrollHeight);
            $('#newMsg').hide();

        } else if($(e.target).hasClass('rong-emoji-content')){

            $('#msgInput').val($('#msgInput').val() + $(e.target).attr('name'));

            $('#emoji').height('0');
            $('.send-box').css('bottom','0');

            //展开视频介绍
        } else if(e.target.id == 'show' || $(e.target).parent('#show').length) {

            if($('#show span').text() == '展开'){
                $('#show span').text('收起');
                $('#show img').attr('src', '../../static/img/live/hide.png');
                $('.intr-con').css({
                    'display': 'block',
                    'max-height': 'none'
                });
            } else {
                $('#show span').text('展开');
                $('#show img').attr('src', '../../static/img/live/show.png');
                $('.intr-con').css({
                    'display': '-webkit-box',
                    'max-height': '1.01333rem'
                });
            }

            // 微信提示分享遮罩
        } else if(e.target.id == 'sharingBox' || $(e.target).parents('#sharingBox').length){

            $('#sharingBox').hide();

        } else {
            // 设置
            $('#emoji').height('0');
            if(tourist){
                $('.footer-wraper').css('bottom','0');
            } else {
                $('.send-box').css('bottom','0');
            }
        }
    })

})