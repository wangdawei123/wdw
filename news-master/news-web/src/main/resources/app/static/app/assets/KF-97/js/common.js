/**
 * Created by hasee on 2017/6/1.
 * suyi
 * h5公共js
 */

$(function(){
    //点击立即下载
    var u = navigator.userAgent,
        d = document.querySelector(".download .box_btn");
    var comhref = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.fawan.news';
    if(d) {
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            d.href = comhref;

        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            d.href = comhref;
        }
    }
    //关闭下载框
    $('.download .close').on('click', function (evt) {
        evt.stopPropagation();
        $('.download').remove();
    });
});
