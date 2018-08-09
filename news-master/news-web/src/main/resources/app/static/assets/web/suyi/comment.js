/**
 * Created by suyi on 2017/5/9.
 * h5评论
 */
'use strict';

$(function(){
    pinglun_m2(1,1);
    if(_CONST_.maxnum > 3){
        $('.more1').show();
        $('.more2').hide();
        _CONST_.commentsMore = true;
    }else{
        $('.more1').hide();
        $('.more2').show();
        _CONST_.commentsMore = false;
    }
});


window.page_m2 = 1;
//定时刷新
if(_CONST_.refresh){
    setInterval(function () {
        window.flag_m2 = false;
        pinglun_m2(window.page_m2,1);
    }, _CONST_.refresh * 1000);
}

//刷新聊天互动（评论）
var pinglun_m2 = function (page,type) {
    if(window.flag_m2) return;
    console.debug('page_m2:'+window.page_m2);
    console.debug(_CONST_.cid);
    console.debug(getPrefixUrl() + 'comment/ajaxGetList');
    $.ajax({
        url: getPrefixUrl() + 'comment/ajaxGetList',
        dataType:"json",
        type: 'POST',
        data: {
            cid: _CONST_.cid,
            uid: _CONST_.uid,
            page: page || 1
        },
        success:function(response){
            var data = response.data,
                html = '';
//                    if(!data.length){
//                        _CONST_.commentsMore = false;
//                        setTimeout(function () {
//                            $('.no-comment').show();
//                        }, 1200);
//                        return false;
//                    }
            html += getCommentHtml(data,response.allcomment);
            $('.module_5ul').html(html);
            console.debug(response.allcomment);
            if(type == 2  &&  response.allnum == 1){
                window.page_m2 = parseInt(window.page_m2)+1;
            }
            //评论底部
            if(response.allnum == 1){
                $('.more1').show();
                $('.more2').hide();
                _CONST_.commentsMore = true;
            }else{
                $('.more1').hide();
                $('.more2').show();
                _CONST_.commentsMore = false;
            }
        },
        error: function (data) {
            console.log(data)
        },
        complete:function(){
            window.flag_m2=false;
//                    setTimeout(function () {
//                        $('.more').hide();
//                    }, 1000);
        }
    });
};

//获取聊天互动（评论）列表html
function getCommentHtml(data,allcomment) {
    var html = '';
    for (var i = 0, len = data.length, item; i < len; i++){
        item = data[i];
        // html += '<a href="fawan://detail/news/comment/' + item.id + '?username='+ item.user.name +'">';
        html += '<a href="javascript:void(0);" onclick="kf.addToAppComment('+item.id+', \''+item.user.name+'\')" style="display: block">';
        html += '<div class="li">';
        html += '<div class="head">';
        html += '<div class="img" style="border-radius: 50%;;height: 30px">';
        html += '<img style="border-radius: 50%" src="'+ item.user.image +'" alt="">';
        html += '</div>';
        html += '<div class="name" style="width: 100px;">'+ item.user.name ;
        html += '<div class="time">'+ item.create_time +'</div>';
        html += '</div>';
        html += '<div class="rl">'+ (allcomment - i) +'楼</div>';
        html += '</div>';
        if(item.comment.length){
            if(item.comment[0].user){
                html += '<div class="reply">';
                html += '<a href="javascript:void(0)" class="other">@'+ item.comment[0].user.name +'</a>'+ '： '+ item.comment[0].content;
                html += '</div>';
            }else{
                html += '<div class="reply">';
                html +=  item.comment[0].content;
                html += '</div>';
            }
        }
        html += '<div class="con" style="color:black;">';
        html += item.content;
        html += '</div>';

        html += '</div></a>';
    }
    return html;
}


window.flag_m2 = false;
//滚动页面加载更多
window.addEventListener('scroll',function(e){
    if (window.flag_m2 != 'true' &&  _CONST_.commentsMore) {
        setTimeout(function(){
            if($(window).scrollTop() + $(window).height() > $(document).height() - 100){
                pinglun_m2(page_m2,2);
            }
        }, 10);
    }
}, false);
//点击加载更多
$('.more1').on('click',function(){
    if (window.flag_m2 != 'true' &&  _CONST_.commentsMore) {
        setTimeout(function(){
            if($(window).scrollTop() + $(window).height() > $(document).height() - 100){
                pinglun_m2(page_m2,2);
            }
        }, 10);
    }
});

//获取前缀url
function getPrefixUrl() {
    var location = window.location,
        origin   = location.origin,
        path     = location.pathname,
        pathArr  = path.split('/'),
        newPath  = '';
    //pathArr = pathArr.slice(1,2);//本地
    pathArr = pathArr.slice(1,-4);
    newPath += origin + '/' + pathArr.join('/') + '/';
    return newPath;
}