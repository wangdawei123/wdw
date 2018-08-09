

function navClick(target) {
    var id = $(target).data("to");
    $('html,body').animate({scrollTop:$('#'+id).offset().top}, 800);

}

$(function () {

    FastClick.attach(document.body);

    var cate = kf.getSearchParam('cate');
    var cid = kf.getSearchParam('cid');
    var share = kf.getSearchParam('share');
    var ispreview = kf.getSearchParam('ispreview');// 1 preview  0

    kf.post('/web/special/getNewSpecial', {'cate': cate, 'cid': cid, 'ispreview': ispreview}, null, function (res) {
        console.log(res);
        if(res.error_code){
            var error=template("notice_template",{error:res.error_msg});
            $("#wrapper").html(error);
            return;
        }
        document.title = res.data.title;

        var catalogArr = res.data.catalog;
        var specialObj = res.data.special_img;
        var shareObj = res.data.share_url;

        var bannerHtml = template("index_special_template_banner", {share: share, ispreview: ispreview, specialObj:specialObj, desc: res.data.desc, catalog: catalogArr});
        $('.banner').html(bannerHtml);

        var subdirectoryHtml = template("index_special_template_subdirectory", {share: share, ispreview: ispreview, specialObj:specialObj, desc: res.data.desc, catalog: catalogArr});
        $('.subdirectory').html(subdirectoryHtml);

        var leadHtml = template("index_special_template_lead", {share: share, ispreview: ispreview, specialObj:specialObj, desc: res.data.desc, catalog: catalogArr});
        $('.lead').html(leadHtml);

        var listHtml = template("index_special_template_list", {share: share, ispreview: ispreview, specialObj:specialObj, desc: res.data.desc, catalog: catalogArr});
        $(".special-con").html(listHtml);

        if(catalogArr.length > 8){
            $('<span id="allNav">更多...</span>').insertAfter('#wrapper .nav7');
        }

        $('#allNav').click(function () {
            $('#allNav').hide();
            $('.nav').show();
        })

        kf.appShare(0, shareObj.title, shareObj.desc, shareObj.icon, shareObj.url);
        kf.get("/web/weixin/sign",{url:document.location.href},null,function(package){
            var wxshare=new wechat(package.appId,package.timestamp,package.nonceStr,package.signature);
            wxshare.share(shareObj.title, shareObj.desc,shareObj.url,shareObj.icon);
        });

    })


});

