/**
 * Pull down/up to refresh
 *
 * @author Zeno Li
 * @date   2016-08-01
 */
;(function ($,win,doc) {
    var page  = 2,
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
            reqType: 'GET',
            params: {
                page: page
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

            if(scrolltop + clientHeight >= scrollHeight - 100 && !empty && !loading){
                loading = true;
                showTips(!empty);
                $.ajax({
                    url: defaults.url,
                    type: defaults.reqType,
                    data: defaults.params,
                    success: function (response) {
                        if (response.error_code === 0) {
                            if (!response.data.length) {
                                $(win).off('scroll');
                                empty = true;
                                showTips(!empty);
                                return false;
                            }

                            var html = defaults.getHtml(response.data);
                            $('.content-module').append(html);
                            ++defaults.params.page;
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

    function showTips(more) {
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
        innerDiv.style.textAlign = 'center';
        innerDiv.innerHTML = more ? '加载中...' :'没有更多数据';
        div.appendChild(innerDiv);
        frg.appendChild(div);
        document.querySelector('body').appendChild(frg);
    }

    window.Pagination = _pagination;
})(jQuery,window,document);