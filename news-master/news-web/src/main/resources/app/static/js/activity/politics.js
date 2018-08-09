;(function($,kf){

    function Politics(option){
        var defaults={
            page: 0,
            pcount: 10,
            sessionid: ''
        };
        this.options=$.extend({},defaults,option);
        this.init();
    }

    Politics.prototype.init = function() {
        var self=this;
    };

    Politics.prototype.index = function(render) {
        var self=this;
        kf.post("/web/activity/getLawList",{page: self.options.page, pcount: self.options.pcount, sessionid: self.options.sessionId}, null, function(res){
            // console.log(res);
            render(res);
        })
    };

    Politics.prototype.detail = function(iconid, render) {
        var self=this;
        // console.log(iconid);
        kf.post("/web/activity/getLawDetail",{iconid: iconid, page: self.options.page, pcount: self.options.pcount, sessionid: self.options.sessionId}, null, function(res){
            // console.log(res);
            render(res);
        });
    };

    window.Politics=Politics;

})(jQuery,kf);