/**
 * Created by suyi on 2017/5/9.
 * 文章点赞
 */
'use strict';

$('.circle').click(function () {
    addLike();
});
var likeed = false;
setLikeStatus();
//添加点赞
function addLike() {
    var type = 'add';
    if (!likeed) {
        //var uuid = getCookie('PHPSESSID');
        var uuid = _CONST_.uuid;
        $.ajax({
            url: _CONST_.likeUrl,
            type: 'POST',
            data: {
                cid: _CONST_.cid,
                uuid: uuid,
                devID: _CONST_.devid
            },
            success: function (response) {
                likeed = true;
                if (response.status === 200) {
                    updateLikeNum(type);
                } else if (response.status == 506) {
                    layer.msg(response.message);
                    $('.circle')[0].style.backgroundColor = '#CCCCCC';
                } else {
                    layer.msg(response.message);
                }
            }
        });
    } else {
        layer.msg('您已经点过赞了~');
    }
}

function setLikeStatus() {
    var is_love = _CONST_.is_love;
    if (is_love == 1) {
        //$('.circle').addClass('on');
        //$('.circle')[0].style.backgroundColor = '#CCCCCC';
        //likeed = true;

        $('.circle').addClass('on');
        $(".circle").css("background","#CCCCCC");
        $(".box").addClass("boxShadow_style");
        likeed = true;
    }
}

//更新点赞数
function updateLikeNum(type) {
    var numObj = $('#circle_num');
    var num = numObj.html();
    if (type === 'add') {
        ++num;
    } else if (type === 'min') {
        --num;
        if (num < 0) {
            num = 0;
        }
    }
    $('.circle')[0].style.backgroundColor = '#CCCCCC';
    numObj.html(num);
}









//function setCookie(name, value) {
//    var Days = 30;
//    var exp = new Date();
//    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
//    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
//}
//
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
}
//
//function delCookie(name) {
//    var exp = new Date();
//    exp.setTime(exp.getTime() - 1);
//    var cval = getCookie(name);
//    if (cval != null) {
//        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
//    }
//}