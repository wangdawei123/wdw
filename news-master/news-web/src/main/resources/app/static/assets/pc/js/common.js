//焦点图切换效果 
/*
imgsCon  //运动元素的id或class
iconsCon  //包含按钮的元素的id或class
prevIcon  //向前按钮的元素的id或class
nextIcon   //向后按钮的元素的id或class
chooseIcon   //按钮被选择时增加或删除的class  默认是on     
focusNum  //包含显示当前第几页的元素的id或class
moveTime  //动画运动时间  默认800毫秒
delayTime  //动画延迟时间  默认5000毫秒
isAuto  //是否自动轮换  默认不自动
isCycle  //是否运动到头继续循环  默认不循环
isSingleImg // 是单图刷的轮换还是多图刷刷刷的轮换 默认是刷刷刷的轮换
direction //运动方向 默认是水平运动
step //每次轮的步数 默认是1
cannotClickClass //左右不能点击时加的class 默认加end
beginPos //适用于起始位置不是left 0 的运动 默认是0
callback //回调函数 function (index) {} 其中对外有当前动画的index
*/
function autoMove(options) {
    var $imgs_c = $(options.imgsCon),
        $icons_c = $(options.imgsCon).parent().parent().find(options.iconsCon),
        $imgs = $imgs_c.children(),
        $icons = $icons_c.children(),
        iconsTagName = $icons.eq(0).get(0) ? $icons.eq(0).get(0).tagName.toLowerCase() : 'li',
        $prevIcon = $(options.imgsCon).parent().parent().find(options.prevIcon),
        $nextIcon = $(options.imgsCon).parent().parent().find(options.nextIcon),
        chooseIcon = options.chooseIcon || 'on',
        step = options.step || 1,
        cannotClickClass = options.cannotClickClass || 'end',
        moveTime = options.moveTime || 800,
        delayTime = options.delayTime || 5000,
        isSingleImg = options.isSingleImg || false,//是否是单图刷的轮换，默认是刷刷刷的轮换
        isAuto = options.isAuto || false,
        isCycle = options.isCycle || false,
        directionData = {},
        direction = options.direction || 'left',
        moveDistance = direction === 'left' ? $imgs.eq(0).outerWidth(true) : $imgs.eq(0).outerHeight(true),
        beginPos = options.beginPos || 0,
        page = 0,
        moveTimer = null,
        showImgLen = $imgs.length,
        move;

    if (showImgLen <= step) {
        $prevIcon.addClass(cannotClickClass);
        $nextIcon.addClass(cannotClickClass);
        return;
    } else if (step == 1 && isCycle && !isSingleImg) {
        $imgs_c.append($imgs.eq(0).clone(true));
        if (direction === 'left') {
            $imgs_c.width(moveDistance * (showImgLen + 1));
        }      
    } else if (!isSingleImg) {
        if (direction === 'left') {
            $imgs_c.width(moveDistance * showImgLen);
        }
    } else if (isSingleImg) {
        $imgs.eq(0).css(direction, '0px');
    }

    if (options.iconsCon && $icons_c.children().get(0) && $imgs_c.children().length != 1) {
        var iconclone = $icons_c.children().eq(0),
            iconclass = iconclone.attr('class'),
            tag = iconclone.get(0).tagName.toLowerCase(),
            shtml = '';
        for (var i = 0; i < showImgLen; i++) {
            (i == 0) ? shtml += '<' + tag + ' class="' + chooseIcon + '"' + '></' + tag + '>' : shtml += '<' + tag + '></' + tag + '>';
        }
        $icons_c.html(shtml);
    }

    move = function(){
        window.clearTimeout(moveTimer);
        if (!$imgs_c.is(':animated') && !isSingleImg) {
            page++;         
            if (page === Math.ceil(showImgLen / step) && isCycle) {
                $icons_c.children().eq(0).addClass(chooseIcon).siblings().removeClass(chooseIcon);
                directionData[direction] = beginPos - moveDistance * step * page;
                $imgs_c.stop().animate(directionData, moveTime, function () {
                    $imgs_c.css(direction, beginPos + 'px');
                    page = 0;
                    if (options.callback) {
                        options.callback(page);
                    }
                    if (isAuto) {
                        moveTimer = window.setTimeout(move, delayTime);
                    }
                });
            } else if (page === Math.ceil(showImgLen / step) && !isCycle) {
                page--;
                return;
            } else {
                directionData[direction] = beginPos - moveDistance * step * page;
                $imgs_c.stop().animate(directionData, moveTime, function() {
                    if (options.callback) {
                        options.callback(page);
                    }
                    if (page + 1 === Math.ceil(showImgLen / step) && !isCycle) {
                        $nextIcon.addClass(cannotClickClass);
                    }
                });
                $icons_c.children().eq(page).addClass(chooseIcon).siblings().removeClass(chooseIcon);
                if (isAuto) {
                    moveTimer = window.setTimeout(move, delayTime);
                }
            }
        } else if (!$imgs.eq(page).is(':animated') && isSingleImg) {
            page++; 
            if (page === showImgLen) {
                page = 0;
                directionData[direction] = beginPos - moveDistance;
                $imgs.eq(showImgLen - 1).stop().animate(directionData, moveTime);
            } else {
                directionData[direction] = beginPos - moveDistance;
                $imgs.eq(page - 1).stop().animate(directionData, moveTime);
            }
            directionData[direction] = beginPos;
            $imgs.eq(page).css(direction, moveDistance).stop().animate(directionData, moveTime, function() {
                if (options.callback) {
                    options.callback(page);
                }
            });
            $icons_c.children().eq(page).addClass(chooseIcon).siblings().removeClass(chooseIcon);
            if (isAuto) {
                moveTimer = window.setTimeout(move, delayTime);
            }
        }
    };

    if (isAuto) {
        moveTimer = window.setTimeout(move, delayTime);
    }

    $imgs_c.delegate($(this).children(), 'mouseover', function (e) {
            window.clearTimeout(moveTimer);
    });

    $imgs_c.delegate($(this).children(), 'mouseout', function (e) {
        if (isAuto) {
            moveTimer = window.setTimeout(move, delayTime);
        }
    });

    $icons_c.delegate(iconsTagName, 'mouseover', function (e) {
        window.clearTimeout(moveTimer);
        if (!isSingleImg) {            
            page = $(e.target).index();
            directionData[direction] = beginPos - moveDistance * step * page;
            $imgs_c.stop().animate(directionData, moveTime, function() {
                if (options.callback) {
                    options.callback(page);
                }
            });
            $(e.target).addClass(chooseIcon).siblings().removeClass(chooseIcon);
        } else if (isSingleImg && $(e.target).index() !== page) {
            if (page > $(e.target).index()) {//点顺序小于当前的按钮
                $imgs.eq($(e.target).index()).css(direction, beginPos - moveDistance);
                directionData[direction] = moveDistance - beginPos;
                $imgs.eq(page).stop(false, true).animate(directionData, moveTime);
                page = $(e.target).index();
                directionData[direction] = beginPos;
                $imgs.eq(page).stop().animate(directionData, moveTime, function() {
                    if (options.callback) {
                        options.callback(page);
                    }
                });
                $(e.target).addClass(chooseIcon).siblings().removeClass(chooseIcon);
            } else {
                directionData[direction] = beginPos - moveDistance;
                $imgs.eq(page).stop(false, true).animate(directionData, moveTime);
                page = $(e.target).index();
                directionData[direction] = beginPos;
                $imgs.eq(page).css(direction, moveDistance).stop().animate(directionData, moveTime, function() {
                    if (options.callback) {
                        options.callback(page);
                    }
                });
                $(e.target).addClass(chooseIcon).siblings().removeClass(chooseIcon);
            }
        }
    });

    $icons_c.delegate(iconsTagName, 'mouseout', function (e) {
        if (isAuto) {
            moveTimer = window.setTimeout(move, delayTime);
        }
    });

    $prevIcon.click(function() {
        $(this).next().removeClass(cannotClickClass);//$nextIcon
        if (page === 0 && !isCycle && !isSingleImg) {
            return;
        } else {
            window.clearTimeout(moveTimer);
            if (!$imgs_c.is(':animated') && !isSingleImg) {
                page--;
                if (page === -1) {
                    $imgs_c.css(direction, beginPos - moveDistance * step * showImgLen + 'px');
                    page = showImgLen - 1;
                }
                directionData[direction] = beginPos - moveDistance * step * page;
                $imgs_c.stop().animate(directionData, moveTime, function() {
                    if (options.callback) {
                        options.callback(page);
                    }
                    if (page === 0 && !isCycle) {
                        $prevIcon.addClass(cannotClickClass);
                    }
                });
                $imgs_c.next().children().eq(page).addClass(chooseIcon).siblings().removeClass(chooseIcon);
                if (isAuto) {
                    moveTimer = window.setTimeout(move, delayTime);
                }
            } else if (!$imgs.eq(page).is(':animated') && isSingleImg) {
                page--;
                if (page === -1) {
                    page = showImgLen - 1;
                    $imgs.eq(page).css(direction, beginPos - moveDistance);
                    directionData[direction] = moveDistance - beginPos;
                    $imgs.eq(0).stop(false, true).animate(directionData, moveTime);
                } else {
                    $imgs.eq(page).css(direction, beginPos - moveDistance);
                    directionData[direction] = moveDistance - beginPos;
                    $imgs.eq(page + 1).stop(false, true).animate(directionData, moveTime);
                }
                directionData[direction] = beginPos;
                $imgs.eq(page).stop().animate(directionData, moveTime, function() {
                    if (options.callback) {
                        options.callback(page);
                    }
                });
                $imgs_c.next().children().eq(page).addClass(chooseIcon).siblings().removeClass(chooseIcon);
                if (isAuto) {
                    moveTimer = window.setTimeout(move, delayTime);
                }
            }
        }
    });

    $nextIcon.click(function() {    
        $(this).prev().removeClass(cannotClickClass);//$prevIcon
        if (page === showImgLen - 1 && !isCycle && !isSingleImg) {
            return;
        } else {
            move();
        }
    });
}

autoMove({
    'imgsCon': '#imgs_cont1',
    'iconsCon': '#icons_cont1',
    'prevIcon': '#prevIcon1',
    'nextIcon': '#nextIcon1',
    'chouse_icon': 'on',
    'isAuto': true,
    'isCycle': true
});
//广告轮播
$('.J_commSlide').each(function(){
    var that = $(this),
        slidebox = that.find('.fslide'),
        pointbox = that.find('.fpoint');
    autoMove({
        'imgsCon': slidebox,
        'iconsCon': pointbox,
        'chooseIcon': 'on',
        'isAuto': true,
        'isCycle': true
    });
});

/**
 * 关闭互层
 */
function showPopup(){
    $('.comm-popup').on('click','.close',function(){
        $('.comm-popup').hide()
        $('.mask').hide()
        // ICP证申请
        if(location.href.indexOf('/index/index/137') > -1 || location.href.indexOf('/index/index/164') > -1)[
            location.href = '/pc/index/index'
        ]
    })
    $('.J-header-btn').on('click','.i9',function(e){
        e.preventDefault();
        var dataType = $(this).attr('data-type');
        if(dataType != 'more'){
            $('.mask').show()
            $('.comm-popup[data-type='+dataType+']').show()
        }
    })
    $('.J-header-btn').on('click','.tologina',function(e){
        e.preventDefault();
        var dataType = $(this).attr('data-type');
        if(dataType != 'more'){
            $('.mask').show()
            $('.comm-popup[data-type='+dataType+']').show()
        }
    })
      $('.J-header-btn a').on('mouseenter',function(){
        var dataType = $(this).attr('data-type')
        var left = $(this).offset().left
        $('.nav-hover-popup[data-type='+dataType+']').css({'left':left,'right':'auto'}).show()
    })
    $('.J-header-btn a').on('mouseleave',function(){
        $('.nav-hover-popup').hide()
    })
    $('.nav-hover-popup').hover(function(){
        var dataType = $(this).attr('data-type')
        $('.J-header-btn a[data-type='+dataType+']').addClass('on')
        $(this).show()
    },function(){
         var dataType = $(this).attr('data-type')
        $('.J-header-btn a[data-type='+dataType+']').removeClass('on')
        $(this).hide()
    })
}
showPopup()
/**
 * [isEmptyObj check empty obj]
 * @return {Boolean}
 */
function isEmptyObj(obj){
    var flag = true
    for(var i in obj){
        if(obj[i] && obj[i] != ''){
            flag = false
        }
    }
    return flag
}
/**
 * [formEvent ]
 * @param  {[type]} items [validate list]
 * @param  {[type]} btn   [form btn]
 */
function formEvent(items,btn){
    var $btn = $(btn);
    var obj = {};
    for(var i = 0 ; i < items.length ; i++){
        (function(i){
            if(items[i].type == 'input'){
                // console.log($('#'+items[i].id))
                $('#'+items[i].id).on('input',function(){
                    var val = $(this).val();
                    obj[i] = val ;
                    if(val){
                        $btn.removeClass('disable')
                    }
                    if(isEmptyObj(obj)){
                        $btn.addClass('disable')
                    }
                   
                })
            }
        })(i)

    }

}


/**
 * [validate description]
 * @return {[type]} [description]
 */
function validate(items){
    var right = 0;
    var arr = [];
    for(var i = 0 ; i < items.length ; i++){
        if(items[i].type == 'input'){
            // console.log($('#'+items[i].id))
            var val = $('#'+items[i].id).val()
            if(!val){
                layer.msg(items[i].wran);
                return false
            }
            else if(items[i].rules!='' && !items[i].rules.test(val)){
                layer.msg(items[i].error);
                return false
            }
            else{
                right++
            }
        }
    }
    if(right == items.length){
        return true
    }
}
/**
 * [submitForm description]
 * @param  {[id]} layer [description]
 * @param  {[array]} items [description]
 * @param  {[id]} btn   [description]
 */
function submitForm(layer,items,btn){
    var $btn = $(btn);
    formEvent(items,btn)
    $btn.on('click',function(){
        if($(this).hasClass('disable')){
            return
        }
        var re = validate(items);
        // ICP证申请
        if(!$('#agreement').hasClass('a-focus')){
            window.layer.msg('请勾选我已阅读并同意');
            return;
        }
        if(!$('#password1').val() || !$('#password2').val()){
            window.layer.msg('密码不能为空');
            return;
        }
        if($('#password1').val() != $('#password2').val()){
            window.layer.msg('确认密码输入有误');
            return;
        }
        if(re && btn == '#registerBtn'){
//            $(layer).find('form').submit();

            if(!$('.mask')){
                $('.mask').show();
            }
            $('.comm-popup[data-type="toregister"]').hide();
            $('.comm-popup[data-type="toregisterLose"]').show();

        }
    })
    
}
submitForm('#loginLayer',[{
        id:'loginTel',
        type:'input',
        wran:'手机号不能为空',
        error:'手机号输入有误',
        rules:/^1[34578]\d{9}$/
    },
    {
        id:'loginCode',
        type:'input',
        wran:'验证码不能为空',
        error:'验证码输入有误',
        rules:''
    },
  ],'#loginBtn')

// ICP证申请
submitForm('#registerLayer',[{
    id:'email',
    type:'input',
    wran:'邮箱不能为空',
    error:'邮箱输入有误',
    rules:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
},{
    id:'userName',
    type:'input',
    wran:'用户名不能为空',
    error:'用户名输入有误',
    rules:''
},{
    id:'verificationCode',
    type:'input',
    wran:'验证码不能为空',
    error:'验证码输入有误',
    rules:''
}
],'#registerBtn')

submitForm('#feedBack',[{
        id:'feedText',
        type:'input',
        wran:'反馈意见不能为空',
        error:'反馈意见输入有误',
        rules:''
    },
    {
        id:'feedTel',
        type:'input',
        wran:'手机号不能为空',
        error:'手机号输入有误',
        rules:/^1[34578]\d{9}$/
    },
  ],'#feedBackBtn')
/**
 * [initSearch header search function]
 */
function initSearch(){
    var wrap = $('.J-search');
    var left = wrap.offset().left
    var top = wrap.offset().top+wrap.height()+4
    var width = wrap.outerWidth();
    var type = wrap.attr('data-type')
    wrap.on('blur','input ',function(){
        wrap.removeClass('search-on')
    })
    wrap.on('keyup','input',function(){
        if($(this).val()){
            wrap.addClass('search-on');
            $('.J-search-layer').css({'left':left,'top':top,'width':width})
            if(type == 'page'){
                $('.J-search-layer').addClass('search-fixed')
            }
            $('.J-search-layer').show()
        }
        else{
            wrap.removeClass('search-on')
        }
    })
    wrap.on('focus','input',function(){
        wrap.addClass('search-on')
//        $(this).val('')
    })
//    wrap.on('click','.btn',function(){
//       var val = $(wrap).find('input').val('')
//       if(!val){
//            return
//       }
//       window.open('url'+val)
//    })
}
//ICP证申请
if(window.location.href.indexOf('/index/162') == -1){
    initSearch();
}

/**
 * [initGlobalFixed description]
 */
function initGlobalFixed(){

    var sideBar = $('.J-side-bar');
    var shareBar = $('.J-share-bar');
    var gbNav = $('.global-header');
    var subNav = $('.global-sub-nav');
    var backBar = $('.back-top');
    var reload = $('.reload')
    var navHeight = gbNav.height();
    var navTop = gbNav.scrollTop();
    var top = $('#J_offsetFlag').offset().top;
    var winWidth = $(window).width();
    var mWidth = 1200;
    var left = (winWidth > mWidth) && ((winWidth-mWidth )/2 - 120) > 0 ? Math.abs((winWidth-mWidth )/2 - 120): 0;
    var right = winWidth > mWidth ? (winWidth-mWidth )/2 - 70 : 0;
    var navLeft = 0;

    function initTop(bar,fixClass){
        var scrollTop = $(window).scrollTop();
        var fixedTop = fixClass == 'nav-fixed' ? 0 : navHeight + 10;
        var initTop = top;
        if(fixClass == 'nav-fixed'){
            var zkNavLayer = $('.global-pop');
            if(scrollTop > navTop){
                zkNavLayer.addClass(fixClass).css('top',60);
            }
            else{
                zkNavLayer.removeClass(fixClass).css('top',192);
            }
        }
        if(scrollTop > navTop){
            bar.css('top',fixedTop).addClass(fixClass);
        }
        else {
            bar.css('top',50).removeClass(fixClass);
        }

    }
    function backTop(bar){
        var scrollTop = $(window).scrollTop();
        if(scrollTop > 0 ){
            bar.css('display','block');
        }
        else{
            bar.hide();
        }
    }

     // 侧边栏
//    initTop(sideBar,'side-bar-fixed');
    // 分享栏
    initTop(shareBar,'share-bar-fixed');
    // 导航栏
//    initTop(gbNav,'nav-fixed');
    
    gbNav.css({'left':navLeft}).show();
    backBar.css({'right':right})
    backBar.on('click',function(){
        $("body,html").animate({"scrollTop":0},800);
    })
    reload.on('click',function(){
        window.location.reload();
    })

    $(window).scroll(function(){
        //回到顶部
        backTop(backBar);
//        initTop(sideBar,'side-bar-fixed');
        initTop(shareBar,'share-bar-fixed');
//        initTop(gbNav,'nav-fixed');
    })
}
initGlobalFixed();
// 导航功能
function initNav(){
    var navWrap = $('.global-header'),
        zkBtn = $('.J-zk'),
        gbLayer = $('.global-pop');

    zkBtn.hover(function(){
        gbLayer.show();
    },function(){
        gbLayer.hide();
    })
    gbLayer.hover(function(){
        $(this).show();
    },function(){
        $(this).hide();
    })
    if($('.global-sub-nav').length){
        var subNav = $('.global-sub-nav');
        navWrap.find('li').hover(function(e){
            var node = $(this);
            var idx = node.index();
            $('.global-sub-nav').find('li').eq(idx).show().siblings().hide();
        })
    }
}
initNav();
//about share begin
function shareFuc(node,classname,position){
    var type = $(node).attr('data-name'),
        infoNode =$(node).parents(classname),
        bshare_url = infoNode.attr("data-url") || window.location.href,
        bshare_title = infoNode.attr("data-title") || "",
        bshare_summary = infoNode.attr("data-summary") || "",
        bshare_pic = infoNode.attr('data-pic') || "";

    var share_url='http://api.bshare.cn/share/'+type+'?url=' + bshare_url + '&title=' + encodeURIComponent(bshare_title) + '&summary=' + encodeURIComponent(bshare_summary) +'&pic=' + bshare_pic;
    if(!type){
        return;
    }
    if(type=='weixin'){
        var weixin=$(node).find('.wechat-qrcode');
        weixin.find('img').attr({'src':share_url});
        if(position){
            weixin.css(position);
            weixin.css({'display':'block'});
        }
        else{
            var left = 90;
            var top = -22;
            weixin.css({'left':left,'top':top,'display':'block'});
        }
    }
    else{
        window.open(share_url,'_blank');
    }
};

function shareEvent(wrap,tag,position){
    wrap = $(wrap)
    wrap.find(tag).each(function(idx,node){
        var type = $(node).attr('data-name')
        if(type == 'weixin'){
            $(node).hover(function(){
                shareFuc(node,wrap,position);
            },function(){
                $(node).find('.wechat-qrcode').hide();
            })
        }
        else{
            $(node).on('click',function(){
                shareFuc(node,wrap);
            })
        }
    })
}
shareEvent('.J-share-bar ul','li')
shareEvent('.J-share-list ul','li')

//effect
$('.J-side-more-btn').hover(function(){
    $('.J-side-more').show()
    },
function(){
    $('.J-side-more').hide() 
})
$('.J-side-more').hover(function(){
    $('.J-side-more').show()
},function(){
    $('.J-side-more').hide()
})

