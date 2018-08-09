/**
 * Created by suyi on 2017/5/9.
 * 文章点赞
 */
'use strict';

addLike();
setLikeStatus();
//添加点赞
function addLike() {
    $('.module_10 .rx_btn').off('click').on('click', function () {
        var id = $('.module_10 .rx_btn .con').data('id'),
            $this = $('.module_10 .rx_btn .con'),
            type;
        if(!_CONST_.uid){
            //layer.msg('请登录');
            window.location.href = "fawan://get/user";
        }else{
            if(!$this.hasClass('on')) {
                type = 'add';
                $.ajax({
                    url: _CONST_.likeUrl,
                    type: 'POST',
                    data: {
                        id: id,
                        type: type,
                        uid:_CONST_.uid
                    },
                    success: function (response) {
                        if(response.error_code === 0){
                            updateLikeNum($this, type);
                        }else {
                            layer.msg(response.error_msg);
                        }
                    }
                });
            }else {
                layer.msg('您已经点过赞了~');
            }
        }
    });
}

//设置是否点赞
//function setLikeStatus() {
//    var like = window.localStorage.getItem('host_like');
//    if(like){
//        like = JSON.parse(like);
//        var cid = like[_CONST_.cid];
//        if(cid){
//            var id = $('.module_10 .rx_btn .con').data('id');
//            if(cid.indexOf(id) > -1){
//                $('.module_10 .rx_btn .con').addClass('on');
//            }
//        }
//    }
//}
function setLikeStatus() {
    var is_love = _CONST_.is_love;
    if(is_love == 1){
        $('.module_10 .rx_btn .con').addClass('on');
        $('.module_10 .rx_btn .con')[0].style.backgroundColor = 'grey';
    }
}

//更新点赞数
function updateLikeNum(obj, type) {
    var num = ~~obj.html(),
        id  = obj.data('id');
    if(type === 'add'){
        ++num;
    }else if(type === 'min'){
        --num;
        if(num < 0){
            num = 0;
        }
    }
    obj.addClass('on');
    $('.module_10 .rx_btn .con')[0].style.backgroundColor = 'grey';
    //obj.addClass('on').html(num);
    //cacheLikeStatus(id);
}

//缓存点赞
function cacheLikeStatus(id) {
    var like = window.localStorage.getItem('host_like');
    if(like){
        like = JSON.parse(like);
        if(like.hasOwnProperty(_CONST_.cid)){
            like[_CONST_.cid].push(id);
        }else {
            like[_CONST_.cid] = [];
            like[_CONST_.cid].push(id);
        }
    }else {
        like = {};
        like[_CONST_.cid] = [];
        like[_CONST_.cid].push(id);
    }
    window.localStorage.setItem('host_like', JSON.stringify(like));
}