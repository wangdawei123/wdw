;(function($,kf){

    function Law(page,option){
        var defaults={
            ruleContainer:".cd-popup",
            ruleTrigger:".cd-popup-trigger",
            ruleClose:".cd-popup-close",
            tab:[],
            activity_id:0,
            vote_people:0,
            vote_btn:".v-btn",
            is_weixin:0
        };
        this.options=$.extend({},defaults,option);
        this.loading=false;
        this.init(page);
        if(page=='index'){
            this.register_index();
        }else if(page=='detail'){
            this.register_detail();
        }
    }

    Law.prototype.init = function(page) {
        var self=this;
        // if(kf.App){
        //     kf.getSessionId();
        // }else if(kf.inApp()){
        //     document.cookie="PHPSESSID="+kf.getSessionId()+";path=/";
        // }

        $(document).on('click',self.options.vote_btn,function(){
            self.vote(page,this,$(this).data("target"));
        })

        $('.hint-info').on('animationend webkitAnimationEnd',function(e){
            $('.hint-info').hide();
        });
    };

    Law.prototype.index = function(render) {
        var self=this;
        var ua = window.navigator.userAgent.toLowerCase();
        if(ua.indexOf('micromessenger') > -1){
            self.is_weixin = 1;
        }
        kf.post("/web/Activitymicrovideo/microvideoIndex",{activity_id:self.options.activity_id,is_weixin:self.is_weixin},function(){
            // kf.loading(1);
        },function(data){
            // kf.loading(0);
            render(data);
        })
    };

    Law.prototype.register_index = function() {
        var self=this;
        // self.slide();
        self.rule();
        self.tab();
        $(document).on('click',self.options.ruleContainer, function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
                event.preventDefault();
                $('.cd-popup-container').animate({bottom:'-'+1300/75+'rem'});
                setTimeout(function () {
                    $(self.options.ruleContainer).removeClass('is-visible');
                    $('html,body').removeClass('ovfHiden');
                }, 300)
            }
        });
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup-container').animate({bottom:'-'+1300/75+'rem'});
                setTimeout(function () {
                    $(self.options.ruleContainer).removeClass('is-visible');
                    $('html,body').removeClass('ovfHiden');
                }, 300)
            }
        });
    };

    Law.prototype.register_detail = function() {
        var self=this;
        $(document).on('click',self.options.message_btn,function(){
            var message=$("#message textarea").val();
            if(message && message.trim().length && self.options.vote_people){
                self.addComment(self.options.vote_people,message);
            }else{
                $('.hint-info').text('内容不能为空').show();
            }
        });
    };

    Law.prototype.vote = function(page,that,vote_people) {
        var self=this;
        if(kf.isInApp() || kf.App){
            kf.post('/web/Activitymicrovideo/microvideoVote',{activity_id:self.options.activity_id,vote_people:vote_people},function(){
                if(self.loading) return false;
                if($(that).hasClass("bg-gray")) return false;
                self.loading=true;
            },function(data){
                self.loading=false;
                // alert(data.error_code)
                if(data.error_code==-2){
                    $('.hint-info').text(data.error_msg).show();
                }else if(data.error_code==-1){
                    kf.login(location.href);
                }else if(data.error_code==0){

                    $(that).addClass("bg-gray").text('已完成');
                    if(page == 'index'){

                        var $targetLi = $(that).parent("li");
                        var id = $targetLi.attr('data-id');
                        var $moveTarget = $targetLi;

                        var current = '',
                            other = '';
                        if($(that).parents(".ranking-list").hasClass('day')){
                            current = '.ranking-list.day li';
                            other = '.ranking-list.all li';
                        } else if($(that).parents(".ranking-list").hasClass('all')){
                            current = '.ranking-list.all li';
                            other = '.ranking-list.day li';
                        }

                        // 设置current票数及data-votenum
                        var $voteNum=$targetLi.find(".voteNum em");
                        $voteNum.html($voteNum.html()?parseInt($voteNum.html())+1:1);
                        $targetLi.attr('data-votenum', $voteNum.html());

                        // 设置other票数及data-votenum
                        $(other).each(function (t,i) {
                            if($(this).attr('data-id') == id){
                                $(this).find('.v-btn ').addClass("bg-gray").text('已完成');
                                $(this).attr('data-votenum', parseInt($(this).attr('data-votenum'))+1);
                                $(this).find('.voteNum em').html($(this).attr('data-votenum'));
                                $moveTarget = $(this);
                            }
                        })
                        // 设置data-index
                        var data = {};
                        if($(that).parents(".ranking-list").hasClass('day')){
                            data = self.voteCommon($targetLi, current);
                        }else{
                            $(other).each(function (t,i) {
                                if($(this).attr('data-id') == id){
                                    data = self.voteCommon($(this), other);
                                }
                            })
                        }

                        $('.hint-info').text("投票成功").show();
                        // var timmer = setTimeout(function () {
                        if($targetLi.find(".num")){
                            $targetLi.find(".num").remove();
                        }
                        $targetLi.append("<span class='num'>" + "+1" + "</span>");
                        $targetLi.find(".num").animate({top:"0",left:"5.5rem",opacity:"0"},800).fadeOut();

                        // 移动标签
                        if($(that).parents(".ranking-list").hasClass('day')){

                            if(data.numArr.length-data.count == 0){
                                $targetLi.append('<div class="gift"><img src="/static/img/law/gift-1.png"></div>');
                                $targetLi.find('.img-border').addClass('head-focus');
                                $(current).eq(data.numArr.length-data.count).find('.gift').remove();
                                $(current).eq(data.numArr.length-data.count).find('.img-border').removeClass('head-focus');
                                $(current).eq(data.numArr.length-data.count).find('.rank-num').removeClass('ranking-index').addClass('c-gray');
                            }
                            setTimeout(function(){
                                $(current).eq(data.numArr.length-data.count).before($targetLi.clone());
                                $targetLi.remove();
                            }, 1000)
                        }else{

                            if(data.numArr.length-data.count == 0){
                                $moveTarget.append('<div class="gift"><img src="/static/img/law/gift-1.png"></div>');
                                $moveTarget.find('.img-border').addClass('head-focus');
                                $(other).eq(data.numArr.length-data.count).find('.gift').remove();
                                $(other).eq(data.numArr.length-data.count).find('.img-border').removeClass('head-focus');
                                $(other).eq(data.numArr.length-data.count).find('.rank-num').removeClass('ranking-index').addClass('c-gray');
                            }
                            $(other).eq(data.numArr.length-data.count).before($moveTarget.clone());
                            $moveTarget.remove();
                        }

                        // }, 1200)
                    } else if(page == 'detail'){
                        var $voteTotal = $(that).parents("#info").find('#voteTotal');
                        var voteNum = parseInt($voteTotal.text());
                        $voteTotal.text(voteNum+1);
                    }

                }
            },function(){
                self.loading=false;
            },function(){
                self.loading=false;
            })
        }else{
            // alert('mlink')
            // 跳转到app
            var link='https://aih3gj.mlinks.cc/A06D?activity?url=:url';//短链地址

            var options =
                {
                    mlink: "A06D",
                    button: document.querySelector('a#btnOpenApp'),
                    autoLaunchApp : false,
                    autoRedirectToDownloadUrl: true,
                    downloadWhenUniversalLinkFailed: false,
                    inapp : false,
                    params: {
                        url:location.href
                    },
                }
            new Mlink(options);
            $("#btnOpenApp").trigger("click");
        }

    };


    Law.prototype.voteCommon = function ($targetLi, items) {
        var indexTmp = $targetLi.attr('data-index');
        var numArr = [];// 前面的所有标签
        $(items).each(function (t,i) {
            if(t<indexTmp){
                numArr.push($(i).attr('data-votenum'));
            }
        })

        var numTmp = $targetLi.attr('data-votenum');
        var count = 0// 从前面所有的标签中查找比当前票数大的标签个数
        numArr.forEach(function (t,i) {
            if(numTmp > parseInt(t)){
                count++;
            }
        })

        //设置标签的index及显示的排名顺序
        $targetLi.attr('data-index', numArr.length-count);
        $targetLi.find('.ranking-index').text(parseInt($targetLi.attr('data-index'))+1);
        $(items).each(function (t,i) {
            if(t<numArr.length && t>numArr.length-count-1){
                $(this).attr('data-index', parseInt($(this).attr('data-index'))+1);
                $(this).find('.ranking-index').text(parseInt($(this).attr('data-index'))+1);
            }
        })
        return {"numArr":numArr,"count":count};
    }

    Law.prototype.detail = function(vote_people,render) {
        var self=this;
        var ua = window.navigator.userAgent.toLowerCase();
        if(ua.indexOf('micromessenger') > -1){
            self.is_weixin = 1;
        }
        kf.post("/web/Activitymicrovideo/getMicroVideoDetail",{activity_id:self.options.activity_id,people_id:vote_people,is_weixin:self.is_weixin},function(){
            // kf.loading(1);
        },function(data){
            // kf.loading(0);
            render(data);
        });
    };


    Law.prototype.commentlist = function(vote_people,page,render) {
        var self=this;
        kf.post("/web/Activitymicrovideo/getCommentList",{people_id:vote_people, page: page},function(){
            // kf.loading(1);
        },function(data){
            // kf.loading(0);
            render(data);
        });
    };

    Law.prototype.addComment = function(vote_people,message) {
        kf.post("/web/Activitybluesky/addComment",{people_id:vote_people,comment:message},function(){

        },function(data){
            if(data.error_code==-1){
                kf.login(location.href);
            }else if(data.error_code==-2){
                $('.hint-info').text(data.error_msg).show();
            }else if(data.error_code==0){
                $('.hint-info').text("评论成功,等审核通过后显示出来").show();
                $("#message textarea").val("");
            }

        })
    };

    // Law.prototype.slide = function() {
    //     $(document).on('click',".hide-show",function(){
    //         var con=$(this).attr('src');
    //         var resume=$(this).parent().parent().siblings(".resume");
    //         if(con=='/static/img/bluesky/hide.png'){
    //             $(this).attr('src','/static/img/bluesky/show.png');
    //             resume.slideUp(300);
    //             // resume.siblings().slideUp();
    //         }else{
    //             $(this).attr('src','/static/img/bluesky/hide.png');
    //             resume.slideDown(300);
    //             $(this).parents(".ranking-list").find(".resume").not(resume).slideUp(300);
    //             $(this).parents(".ranking-list").find(".hide-show").not(this).attr("src","/static/img/bluesky/show.png");
    //             // resume.next('div.resume').slideUp(300).parent().siblings().find('div.resume').slideDown(300);
    //         }
    //     })
    //
    // };

    Law.prototype.rule = function() {
        var self=this;
        $(document).on('click',self.options.ruleClose,function(){
            $('.cd-popup-container').animate({bottom:'-'+1300/75+'rem'});
            setTimeout(function () {
                $(self.options.ruleContainer).removeClass('is-visible');
                $('html,body').removeClass('ovfHiden');
            }, 300)
        }).on('click',self.options.ruleTrigger,function(){
            $(self.options.ruleContainer).addClass('is-visible');
            $('.cd-popup-container').animate({bottom:'-40px'});
            $('html,body').addClass('ovfHiden');
        })
    };

    Law.prototype.tab = function() {
        this.options.tab.forEach(function(item){
            $(document).on('click',item,function(){

                var to="."+$(item).data("to");
                $("span[class*=tab]").removeClass("show");
                $(this).addClass("show");
                $(".ranking-list,.date-rule").not(to).addClass("hidden");
                $(".ranking-list,.date-rule").filter(to).removeClass("hidden");
                console.log($(item).data("to"))

                if($(item).data("to") == 'all'){
                    $('.rule-con.day').hide();
                    $('.rule-con.all').show();
                }else if($(item).data("to") == 'day'){
                    $('.rule-con.all').hide();
                    $('.rule-con.day').show();
                }
            });
        })
    };



    Law.prototype.timeFormat = function(time) {
        var timeObj = new Date(time.replace(/\-/g, "/"));
        var year = timeObj.getFullYear();
        var month = timeObj.getMonth()+1;
        var date = timeObj.getDate();
        month = month<10?'0'+month:month;
        date = date<10?'0'+date:date;
        return year+'年'+month+'月'+date+'日';
    }

    window.Law=Law;

})(jQuery,kf);