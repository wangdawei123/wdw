/**
 * Pull down/up to refresh
 *
 * @author Zeno Li
 * @date   2016-08-01
 */
;(function ($,win,doc) {
    var p  = 2,
        empty = false,
        loading = false;

    /**
     * @param {object}     options
     *        {string}     url       ajax request url
     *        {object}     params    other params except page
     *        {function}   getHtml   get the html string
     *        {function}   bindEvent bind event on these new DOM
     */
    function _pagination(options) {
        var defaults = {
            url: null,
            reqType: 'POST',
            class:null,
            params: {
                p: p
            },
            delay: 1000,
            getHtml:function () {

            },
            bindEvent: function () {

            }
        },
        defaults = $.extend(true, defaults, options);
        if(!defaults.url.length){
            throw new Error('url is empty');
        }

        $(win).on('scroll', debounce(function () {
            var clientHeight = win.screen.availHeight || win.innerHeight,
                scrollHeight = doc.documentElement.scrollHeight || doc.body.scrollHeight,
                scrolltop = doc.body.scrollTop;

            if(scrolltop + clientHeight >= scrollHeight - 50 && !empty && !loading){
                loading = true;
                showTips(!empty,defaults.class);
                $.ajax({
                    url: defaults.url,
                    type: defaults.reqType,
                    data: defaults.params,
                    success: function (response) {
                        if (response.error_code === 0) {
                            if (!response.data.length) {
                                $(win).off('scroll');
                                empty = true;
                                showTips(!empty,defaults.class);
                                return false;
                            }

                            var html = defaults.getHtml(response.data);
                            $('.content-module').append(html);
                            ++defaults.params.p;
                            defaults.bindEvent();
                            loading = false;
                        }
                    }
                });
            }
        }, 100));
    }

    function debounce(func, wait, immediate) {
        var timeout;
        return function() {
            var context = this, args = arguments;
            var later = function() {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };
            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    }

    function showTips(more,type) {
        if(type!=1){
        if(document.querySelector('#load_more_data')){
            document.body.removeChild(document.querySelector('#load_more_data'));
        }
        var frg = document.createDocumentFragment(),
            div = document.createElement('div');
        div.id = 'load_more_data';
        div.style.width = '100%';
        var innerDiv = document.createElement('div');
        innerDiv.style.margin = '10px auto';
        innerDiv.style.width = '200px';
//        innerDiv.style.textAlign = 'center';
        innerDiv.style.color = '#999999';
        innerDiv.innerHTML = more ? '<p class="wait"><i class="icon"></i>正在为您加载更多</p>' :'<p class="wait"><i class="icon"></i>已经到底部了</p>';
        div.appendChild(innerDiv);
        frg.appendChild(div);
        document.querySelector('body').appendChild(frg);
       }else{
         if(document.querySelector('#load_more_data')){
             var b=document.querySelector('#index');
            b.removeChild(document.querySelector('#load_more_data'));
        }
        var frg = document.createDocumentFragment(),
            div = document.createElement('div');
        div.id = 'load_more_data';
        div.style.width = '100%';
        var innerDiv = document.createElement('div');
        innerDiv.style.margin = '10px auto';
        innerDiv.style.width = '200px';
//        innerDiv.style.textAlign = 'center';
        innerDiv.style.color = '#999999';
        innerDiv.innerHTML = more ? '<p class="wait"><i class="icon"></i>正在为您加载更多</p>' :'<p class="wait"><i class="icon"></i>已经到底部了</p>';
        div.appendChild(innerDiv);
        frg.appendChild(div);
        document.querySelector('#index').appendChild(frg);
      }
    }

    window.Pagination = _pagination;
})(jQuery,window,document);