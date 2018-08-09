;(function($,kf){

    function Bluesky(page,option){
        var defaults={
            ruleContainer:".cd-popup",
            ruleTrigger:".cd-popup-trigger",
            ruleClose:".cd-popup-close",
            tab:[],
            activity_id:0,
            vote_people:0,
            vote_btn:".vote_btn"
        };
        this.options=$.extend({},defaults,option);
        this.loading=false;
        this.init();
        if(page=='index'){
            this.register_index();
        }else if(page=='detail'){
            this.register_detail();
        }
    }

    Bluesky.prototype.init = function() {
        var self=this;
        self.scaleView();
        $(document).on('click',self.options.vote_btn,function(){
            kf.log($(this).data("target"));
            self.vote(this,$(this).data("target"));
        })
    };

    Bluesky.prototype.index = function(render) {
        var self=this;
        kf.post("/web/Activitybluesky/getBlueSkyList",{activity_id:self.options.activity_id},function(){
            kf.loading(1);
        },function(data){
            console.log(data);
            kf.loading(0);
            render(data);
        })
    };

    Bluesky.prototype.register_index = function() {
        var self=this;
        self.slide();
        self.rule();
        self.tab();
        $(document).on('click',self.options.ruleContainer, function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
                event.preventDefault();
                $(self.options.ruleContainer).removeClass('is-visible');
            }
        });
        $(document).keyup(function(event){
            if(event.which=='27'){
                $(self.options.ruleContainer).removeClass('is-visible');
            }
        });
    };

    Bluesky.prototype.register_detail = function() {
        var self=this;
        kf.log(self.options.message_btn);
        $(document).on('click',self.options.message_btn,function(){
            var message=$("textarea#msg").val();
            if(message && message.trim().length && self.options.vote_people){
                self.comment(self.options.vote_people,message);
            }else{
                kf.notice("内容不能为空");
            }
        });
    };

    Bluesky.prototype.vote = function(that,vote_people) {
        var self=this;
        kf.post('/web/Activitybluesky/Vote',{activity_id:self.options.activity_id,vote_people:vote_people},function(){
            if(self.loading) return false;
            if($(that).hasClass("sub-btn")) return false;
            self.loading=true;
        },function(data){
            self.loading=false;
            if(data.error_code==-2){
                kf.notice(data.error_msg);
            }else if(data.error_code==-1){
                kf.login();
            }else if(data.error_code==0){
                $(that).addClass("sub-btn");
                $(that).text('已投票');
                var voteNum=$(that).parents("li").find(".voteNum");
                voteNum.html(parseInt(voteNum.html())+1+"票");
                kf.notice("投票成功");
            }
        },function(){
            self.loading=false;
        },function(){
            self.loading=false;
        })
    };

    Bluesky.prototype.detail = function(vote_people,render) {
        var self=this;
        kf.post("/web/Activitybluesky/getBlueSkyDetail",{activity_id:self.options.activity_id,people_id:vote_people},function(){
            kf.loading(1);
        },function(data){
            kf.loading(0);
            render(data);
        });
    };

    Bluesky.prototype.comment = function(vote_people,message) {
        kf.post("/web/Activitybluesky/addComment",{people_id:vote_people,comment:message},function(){

        },function(data){
            if(data.error_code==-1){
                kf.login();
            }else if(data.error_code==-2){
                kf.notice(data.error_msg);
            }else if(data.error_code==0){
                kf.notice("留言成功,等审核通过后显示出来");
                $("#msg").val("");
            }

            kf.log(data);
        })
    };

    Bluesky.prototype.slide = function() {
        $(document).on('click',".hide-show",function(){
            var con=$(this).attr('src');
            var resume=$(this).parent().parent().siblings(".resume");
            if(con=='/static/img/bluesky/hide.png'){
                $(this).attr('src','/static/img/bluesky/show.png');
                resume.slideUp(300);
                // resume.siblings().slideUp();
            }else{
                $(this).attr('src','/static/img/bluesky/hide.png');
                resume.slideDown(300);
                $(this).parents(".ranking-list").find(".resume").not(resume).slideUp(300);
                $(this).parents(".ranking-list").find(".hide-show").not(this).attr("src","/static/img/bluesky/show.png");
                // resume.next('div.resume').slideUp(300).parent().siblings().find('div.resume').slideDown(300);
            }
        })
        
    };

    Bluesky.prototype.rule = function() {
        var self=this;
        $(document).on('click',self.options.ruleClose,function(){
            $(self.options.ruleContainer).removeClass('is-visible');
        }).on('click',self.options.ruleTrigger,function(){
            $(self.options.ruleContainer).addClass('is-visible');
        })
    };

    Bluesky.prototype.tab = function() {
        this.options.tab.forEach(function(item){
            $(document).on('click',item,function(){
                
                var to="."+$(item).data("to");
                $("span[class*=tab]").removeClass("show");
                $(this).addClass("show");
                $(".ranking-list,.date-rule").not(to).addClass("hidden");
                $(".ranking-list,.date-rule").filter(to).removeClass("hidden");
            });
        })
    };

    Bluesky.prototype.scaleView = function() {
        var wrapper = $("#wrapper")[0];
        var sreenWidth = document.body.clientWidth;
        var iScale = sreenWidth/320;

        wrapper.style.transform = "scale("+iScale+")";
        wrapper.style.webkitTransform = "scale("+iScale+")"; 
    };

    window.Bluesky=Bluesky;

})(jQuery,kf);