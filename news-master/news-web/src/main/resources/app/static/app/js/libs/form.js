var pageArr = [];
var pageObj = {};
var getPageInfo = function () {
    var str;
    $('.page').each(function(i){
        str = $(this)[0].className.match(/p[0-9]*/);
        if (str.length >= 1) {
            pageArr.push(str[0]);
            pageObj[str] = i;
        }
    });
    console.log(pageArr)
}
window.onload = function () {
    // getPageInfo();
    checkWidth();
    // $('body>div').removeClass('none');
    // setTimeout(function () {
        // go('p0');
        // $('.up-box').css('display', 'block');
    // }, 1000);

    // $('.p2 .content').delegate('.l', 'click', function () {
    //     go($(this).attr('data-p'));
    // });

    // var doms = [];
    // // doms.push($('.home .btn')[0]);
    // doms.push($('.p0 .bg')[0]);
    // doms.push($('.p1 .bg')[0]);
    // doms.push($('.p2 .bg')[0]);
    // for (var i = 0, len = doms.length; i < len; i++) {
    //     doms[i].addEventListener('touchstart', globalTouchHandler, false);
    //     doms[i].addEventListener('touchmove', globalTouchHandler, false);
    //     doms[i].addEventListener('touchend', globalTouchHandler, false);
    // }

    // $('.page').live('touchstart', globalTouchHandler);
    // $('.page').live('touchmove', globalTouchHandler);
    // $('.page').live('touchend', globalTouchHandler);



   //  function shareWeibo(data) {
   //     window.location.href = "http://share.sina.cn/callback?"+
   //     "title=" + encodeURIComponent(data.title)+
   //     "&url=" + encodeURIComponent(data.url) +
   //     "&pic=" + encodeURIComponent(data.pic) +
   //     "&backurl=" + encodeURIComponent(data.url) +
   //     "&content=" + encodeURIComponent(data.content) +
   //     "&tp=" + encodeURIComponent(data.pic) +
   //     "&relateUid=";
   // }

   // function addCount(e){
   //     var href = $("." + $(e).attr("class") + "-tag").attr("href");
   //     $(".analyse_list iframe").attr("src", href);
   //     $(e).children().html(parseInt($(e).children().html())+1);
   // }

   //  $(".share_btn").bind('click',function(){
   //      // addCount($(this));
   //      var shareData = {};
   //      shareData.title = '';
   //      shareData.url = window.location.href;
   //      shareData.pic = $('#share-content').attr("share-pic");
   //      shareData.content = $('#share-content').val();
   //      setTimeout(shareWeibo(shareData), 1000);
   //  });

   //  $('.wx, .share').on('click', function () {
   //      if(!isWeiXin()){
   //          alert('请使用微信打开!');
   //          return;
   //      }
   //      $('.share').toggleClass('on');
   //  });

    

   //  $('.like_btn').on('click', function () {
   //      $(this).find('img').attr('src', 'http://n.sinaimg.cn/b1440713/20150403/icon_1.png');
   //      // var likenum =parseInt($('#like_num').html().substring(1))+1;
   //      // $('#like_num').html("("+likenum+")");
   //      // $(".analyse_list iframe").attr('src', $("[data-id='like-tag']").attr('href'));
   //  });
    

}
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}
// window.onresize = function () {
//     checkWidth();
// }
var checkWidth = function () {
    var w = $(window).width();
    var h = $(window).height();
    if (w/h>0.7) {
        $('.body-content').css('width', h*0.6837+'px');
    } else {
        $('.body-content').css('width', '100%');
    }
};
var his = ['loading'];
var his_curr = 'loading';
var go = function (str) {
    var h = his[his.length-1];
    $('.' + h).addClass('ms0').removeClass('on').removeClass('ms0');
    $('.' + str).addClass('on');
    his.push(str);
    his_curr = str;
    setTimeout(function () {
        // $('.' + h).removeClass('pre').removeClass('next');
        $('.body-content', $('.' + h)).css('-webkit-transform', 'translate3d(0,0,0)');
    }, 500);
};

// var initFormPage = function () {
//     fullSel(_p, $('.sel-prov'), '省份');
//     $('.sel-prov').on('change', fullCity);
//     $('.sel-city').on('change', fullCompany);
//     // $('.label').on('click', boxTap);
//     //$('.form .submit img').on('click', submitForm);
//     $('form .submit img')[0].onclick = submitForm;

//     var domsForm = [];
//     domsForm.push($('.gallery .imgs')[0]);
//     domsForm.push($('.gallery .imgs')[1]);
//     domsForm.push($('.block2 .car')[0]);
//     for (var i = 0, len = domsForm.length; i < len; i++) {
//         domsForm[i].addEventListener('touchstart', globalTouchHandler, false);
//         domsForm[i].addEventListener('touchmove', globalTouchHandler, false);
//         domsForm[i].addEventListener('touchend', globalTouchHandler, false);
//     }

//     $('.tvc .next').on('touchend', nextLi);
//     $('.tvc .pre').on('touchend', preLi);

//     $('.gallery .title .disbox').delegate('.b', 'click', function (e) {
        
//         var i = $(this).attr('data-i');
//         $('.gallery .img-list').addClass('none').eq(i).removeClass('none');
//         $('.gallery .title img').attr('src', 'img/title_'+i+'.png');
//     });
// };

var globalTouch = {};
function globalTouchHandler (e) {
    var e = e || window.event,
            el = $(this);
        e = e.originalEvent;
    // console.log(e.target.parentNode);
    if (e.touches.length <= 1) {
        switch(e.type){
            case 'touchstart':
            // if (/ B/.test(e.target.className)) {
            //     return;
            // }
            
            if(!(e.target instanceof Element)) el=el.parentNode;
            globalTouch.startDOM=el;
            globalTouch.x=e.touches[0].clientX;
            globalTouch.y=e.touches[0].clientY;
            globalTouch.dx=0;
            globalTouch.dy=0;
            globalTouch.touches=e.touches;
          

            if (/p/.test(his_curr)) {
                $('.' + his_curr + ' .body-content').addClass('ms0');
            }

            



            // addClass(el,'hover');
            break;

            case 'touchmove':
            e.stopPropagation();
            e.preventDefault();
            globalTouch.dx=e.touches[0].clientX-globalTouch.x;
            globalTouch.dy=e.touches[0].clientY-globalTouch.y;
            var touches=e.touches;
            globalTouch.touches=e.touches;

            if (/p[0-9]/.test(his_curr)) {
                $('.' + his_curr + ' .body-content').css('-webkit-transform', 'translate3d(0,' + globalTouch.dy + 'px,0)');
            }

            break;

            case 'touchend':

            if (/p[0-9]/.test(his_curr)) {
                $('.' + his_curr + ' .body-content').css('-webkit-transform', 'translate3d(0,0px,0)');
                $('.' + his_curr + ' .body-content').removeClass('ms0');
            }

            if (globalTouch.dy < -70 || globalTouch.dy > 70) {
                checkMoveY(globalTouch.dx, globalTouch.dy, el);
                return;
            }


            

            // if (globalTouch.dy < -20 || globalTouch.dy > 20) {
            //     checkMoveY(globalTouch.dx, globalTouch.dy, el);
            // }

            // if (globalTouch.dx > -20 || globalTouch.dx < 20) {
            //     var attr = el.parentNode.getAttribute('data-tap');
            //     if (attr) {
            //         childrenPage(attr);
            //     }
            // }


            globalTouch.dx = null;
            globalTouch.dy = null;
            // removeClass(el,'hover');
            break;
        }
    }
    if (e.touches.length > 1) {
        // window.top.location.reload();
    }
}
var checkMoveY = function (dx, dy, e) {
    var el = e;
    // console.log(el)
    //左滑
    if (dy < -20) {
        picChange('next', el);
    }

    //右滑
    if (dy > 20) {
        picChange('pre', el);
    }
    
};
// var changeImg = function (dx) {
//     changeImg.index = parseInt(dx/20);
//     if (!changeImg.curr) {
//         changeImg.curr = 1;
//     }
//     changeImg.index = (36*5+changeImg.curr+changeImg.index)%36;
//     // $('.car .bg.opa').attr('src', 'img/bg/image ' + changeImg.index + '.png');
//     $('.car .bg.c').attr('src', 'img/' + $('.color .ul').attr('data-i') + '/' + changeImg.index + '.png');
// };
// var tapColorLis = function () {
//     changeImg.curr = 1;
//     $('.color .ul').attr('data-i', $(this).attr('data-c'));
//     // $('.car .bg.opa').attr('src', 'img/bg/image 1.png');
//     $('.car .bg.c').attr('src', 'img/' + $(this).attr('data-c') + '/0.png');
// };
// var pageArr = [
//     'p0', 'p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'p7', 'p8', 'p9', 'p10'
// ];
// var pageObj = {
//     'p0': 0,
//     'p1': 1,
//     'p2': 2,
//     'p3': 3,
//     'p4': 4,
//     'p5': 5,
//     'p6': 6,
//     'p7': 7,
//     'p8': 8,
//     'p9': 9,
//     'p10': 10
// };
var picChange = function (str, el) {
console.log(el)

    var page  = el.attr('data-p')
    // console.log(str);
    var i = pageObj[page];
    
    // var lis = $('.gallery .i_1 .li'),
    //     ulParent = el.getAttribute('data-p'),
    //     ul = el,
    //     i = parseInt(ul.getAttribute('data-i'));

    if (str == 'pre') {
        if (i <= 0) {
            return;
            i = 11;
        }
        i -= 1;
        // $('.' + page).addClass(str);
        $('.' + page + ' .body-content').css('-webkit-transform', 'translate3d(0,35em,0)');
    }

    if (str == 'next') {
        if (i >= pageArr.length-1) {
            i = -1;
        }
        i += 1;
        // $('.' + page).addClass(str);
        $('.' + page + ' .body-content').css('-webkit-transform', 'translate3d(0,-35em,0)');
    }
    // $('.' + page).addClass(str);
    
    setTimeout(function () {
        go(pageArr[i]);
        // if (/step5|step19/.test(pageArr[i])) {
        //     $('body>div').removeClass('none');
        // }
    }, 150);
    
    // ul.setAttribute('data-i', i);
    // ul.style['-webkit-transform'] = 'translate3d(-'+100*i+'%, 0, 0)';
    // $('.' + ulParent + ' .slide-point span').removeClass('on').eq(i).addClass('on');
};
