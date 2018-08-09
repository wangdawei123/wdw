/**
 * Created by suixiangjun on 2018/2/9.
 */
$(function () {
    //下拉加载
    $(window).scroll(function(){
        //到底部加载更多
        if($(document).scrollTop()>=$(document).height()-$(window).height()){
            var div1tem = $('body').height();
            var str =''
            //==========================模拟数据=================================================================================
            //图文形式
            var imgtextHtml = ''
                +'<li class="new-line big-imagetext-news">'
                +'<a href="" class="image-box">'
                + '<img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告">'
                +'</a>'
                +'<div class="new-line-info">'
                +'<h3><a href="#">《杜拉拉升职记》演员照片被医院擅自用作妇科广告</a></h3>'
                + '<p>刚刚</p>'
                +'</div>'
                +'</li>';
            //视频形式
            var videoHtml = ''
                +'<li class="new-line big-video-news">'
                +'<a href="" class="image-box">'
                +'<img src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告">'
                +'<p class="video-time"><i>01:25</i></p>'
                +'</a>'
                +'<div class="new-line-info">'
                +'<h3> <a href="#">《杜拉拉升职记》演员照片被医院擅自用作妇科广告</a></h3>'
                +'<p>刚刚</p>'
                +'</div>'
                +'</li>'
            //文章形式
            var textHtml =""
                +'<li class="new-line big-text-news">'
                +'<div class="new-line-info">'
                +'<h3> <a href="#">《杜拉拉升职记》演员照片被医院擅自用作妇科广告</a></h3>'
                +'<p>刚刚</p>'
                +'</div>'
                +'</li>';
            //图集形式
            var imgHtml =""
                +'<li class="new-line big-img-news">'
                +'<a href="" class="image-box">'
                +'<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'
                +'<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'
                +'<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'
                +'<div><img  src="https://kanfaimage.oss-cn-beijing.aliyuncs.com/20180207/news_151800040971138.jpg?x-oss-process=image/resize,m_fill,w_228,h_170" alt="《杜拉拉升职记》演员照片被医院擅自用作妇科广告"></div>'
                +'<p class="img-count"><i>14图</i></p>'
                +'</a>'
                +'<div class="new-line-info">'
                +'<i>图集</i>'
                +'<p>15小时前</p>'
                +'</div>'
                +'</li>';
            //==========================模拟数据结束=================================================================================
            setTimeout(function () {
                $(".newlist").append(imgtextHtml+videoHtml+textHtml+imgHtml);
            },2000)
        }
    })

})
