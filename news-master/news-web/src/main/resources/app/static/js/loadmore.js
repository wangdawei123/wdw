/*
 * loadmore.js
 * 加载更多
 *
 * @time 2016-4-18 17:40:25
 * @author 飞鸿影~
 * @email jiancaigege@163.com
 * 可以传的参数默认有：size,scroll 可以自定义
 * */
/**
 * Created by kanfa on 2018/3/13.
 */
;(function(w,$){
    var loadmore = {
        /*单页加载更多 通用方法
         *
         * @param callback 回调方法
         * @param config 自定义参数
         * */
        get : function(callback, config){
            var config = config ? config : {}; /*防止未传参数报错*/

            var counter = 0; /*计数器*/
            var pageStart = 0;
            var pageSize = config.size ? config.size : 10;

            /*默认通过点击加载更多*/
            $(document).on('click', '.js-load-more', function(){
                counter ++;
                pageStart = counter * pageSize;

                callback && callback(config, pageStart, pageSize);
            });

            /*通过自动监听滚动事件加载更多,可选支持*/
            config.isEnd = false; /*结束标志*/
            config.isAjax = false; /*防止滚动过快，服务端没来得及响应造成多次请求*/
            $(window).scroll(function(){

                /*是否开启滚动加载*/
                if(!config.scroll){
                    return;
                }

                /*滚动加载时如果已经没有更多的数据了、正在发生请求时，不能继续进行*/
                if(config.isEnd == true || config.isAjax == true){
                    return;
                }

                /*当滚动到最底部以上100像素时， 加载新内容*/
                if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
                    counter ++;
                    pageStart = counter * pageSize;

                    callback && callback(config, pageStart, pageSize);
                }
            });

            /*第一次自动加载*/
            callback && callback(config, pageStart, pageSize);
        },

    }

    $.loadmore = loadmore;
})(window, window.jQuery || window.Zepto);

$.loadmore.get(getData, {
    scroll: true, //默认是false,是否支持滚动加载
    size:7, //默认是10
    flag: 1, //自定义参数，可选，示例里没有用到
});

function getData(config, offset,size){

    config.isAjax = true;

    $.ajax({
        type: 'GET',
        url: '/contentLoad',
        dataType: 'json',
        success: function(reponse){

            config.isAjax = false;

            var data = reponse.data.rows;
            var sum = reponse.data.rows.length;

            var result = '';

            /************业务逻辑块：实现拼接html内容并append到页面*****************/

            console.log(data );
            console.log(offset , size, sum);

            /*如果剩下的记录数不够分页，就让分页数取剩下的记录数
             * 例如分页数是5，只剩2条，则只取2条
             *
             * 实际MySQL查询时不写这个
             */
            if(sum - offset < size ){
                size = sum - offset;
            }


            /*使用for循环模拟SQL里的limit(offset,size)*/
            for(var i=offset; i< (offset+size); i++){
                result +='<if data[i].contentType == 2  &&  data[i].image !="">'+
                    '<li class="new-line big-imagetext-news">'+
                    ' <a href="" class="image-box">'+
                    '<img src="'+data[i].image+'" alt="'+data[i].title+'">'+
                    '</a> '+
                    '<div class="new-line-info"><h3>'+
                    '<a href="#">"'+data[i].title+'"</a></h3>'+
                    '<p>刚刚-图文</p>'+
                    '</div></li>'+
                    '</if>'+
                    '<if  data[i].contentType == 2  && data[i].image =="">'+
                    '<li class="new-line big-text-news">'+
                    '<div class="new-line-info">'+
                    '<h3><a href="#">"'+data[i].title+'"</a></h3>'+
                    '<p>刚刚-文章</p>'+
                    '</div></li>'+
                    '</if>'+
                    '<if  data[i].contentType == 4 >'+
                    '<li class="new-line big-video-news">'+
                    '<a href="" class="image-box">'+
                    '<img src="'+data[i].image+'" alt="'+data[i].title+'">'+
                    '<p class="video-time"><i>01:25</i></p>'+
                    ' </a><div class="new-line-info">'+
                    ' <h3> <a href="#">"'+data[i].title+'"</a></h3>'+
                    ' <p>刚刚-视频</p>'+
                    '</div></li>'+
                    '</if>'+
                    '<if  data[i].contentType == 3 >'+
                    '<li class="new-line big-img-news">'+
                    ' <a href="" class="image-box">'+
                    '<div><img src="'+data[i].image+'" alt="'+data[i].title+'"></div>'+
                    '<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'+
                    '<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'+
                    '<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'+
                    '<p class="img-count"><i>14图</i></p>'+
                    '</a><div class="new-line-info">'+
                    '<i>图集</i>'+
                    '<p>15小时前</p>'+
                    '</div></li>'+
                    '</if>';
            }

            $('.newlist').append(result);

            /*******************************************/

            /*隐藏more*/
            if ( (offset + size) >= sum){
                $(".js-load-more").hide();
                config.isEnd = true; /*停止滚动加载请求*/
                //提示没有了
            }else{
                $(".js-load-more").show();
            }
        },
        error: function(xhr, type){
            alert('Ajax error!');
        }
    });
}



