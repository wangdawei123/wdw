/**
 * Created by suixiangjun on 2018/2/7.
 */
$(function () {
    var page = 2;//请求第几页
    var limit = 8;
    var searchKey = $("#searchKey").val();
    //下拉加载
    $(window).scroll(function(){
        //到底部加载更多
        if($(document).scrollTop()>=$(document).height()-$(window).height()){
            //==========================模拟数据=================================================================================

            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: '/searchLoad',
                data: {page:page,limit:limit,searchKey:encodeURI(searchKey)},
                success: function(result){
                    var resultHtml = "";
                    var data =  result;
                    if(data.length){
                        //拼接字符串
                        for(var i = 0; i < data.length ; i ++){
                            switch ( data[i].contentType){
                                case 1:;
                                case 2:if($.trim(data[i].image)){
                                    //不等于空
                                    resultHtml += '<li class="new-line big-imagetext-news">'+
                                        '<a href="/detail/'+data[i].id+'/'+data[i].contentType+'" class="image-box">'+
                                        '<img src="'+data[i].image+'">'+
                                        '</a> '+
                                        '<div class="new-line-info">' +
                                        '<h3>' +
                                        '<a href="/detail/'+data[i].id+'/'+data[i].contentType+'">"'+data[i].title+'"</a>' +
                                        '</h3>'+
                                        '<p>'+data[i].createtime+'</p>'+
                                        '</div>' +
                                        '</li>'
                                }else{
                                    resultHtml += '<li class="new-line big-text-news">'+
                                        '<div class="new-line-info">'+
                                        '<h3><a href="/detail/'+data[i].id+'/'+data[i].contentType+'">"'+data[i].title+'"</a></h3>'+
                                        '<p>' + data[i].createtime +
                                        '</p>'+
                                        '</div></li>';
                                }
                                    break;
                                case 3:
                                   resultHtml += '<li class="new-line big-img-news">'+
                                    ' <a href="/detail/'+data[i].id+'/'+data[i].contentType+'" class="image-box">'+
                                    '<div><img src="'+data[i].imageList[0]+'"></div>'+
                                    '<div><img  src="'+data[i].imageList[1]+'"></div>'+
                                    '<div><img  src="'+data[i].imageList[2]+'"></div>'+
                                    '<div><img  src="'+data[i].imageList[3]+'"></div>'+
                                     '<p class="img-count"><i>'+data[i].num + '图</i></p>'+
                                    '</a>'+
                                    '<div class="new-line-info">'  +
                                    '<i>图集</i>'+
                                    '<p>'+data[i].createtime+'</p>'+
                                    '</div>'+
                                    '</li>';
                                    break;
                                case 4:resultHtml += '<li class="new-line big-video-news">'+
                                    '<a href="/detail/'+data[i].id+'/'+data[i].contentType+'" class="image-box">'+
                                    '<img src="'+data[i].image+'" >'+
                                    '<p class="video-time"><i>'+data[i].duration+'</i></p>'+
                                    ' </a><div class="new-line-info">'+
                                    ' <h3> <a href="/detail/'+data[i].id+'/'+data[i].contentType+'">"'+data[i].title+'"</a></h3>'+
                                    ' <p>' +data[i].createtime
                                    '</p>'+
                                    '</div></li>';
                                    break;
                            }
                        }
                        $(".newlist").append(resultHtml);
                        page++;
                    }else{
                        $(".loading").css("display","none");
                    }

                }
            });

        }
    })

    //把时间毫秒值转换为字符输出
    function timeago(dateTimeStamp){
        // dateTimeStamp是一个时间毫秒，注意时间戳是秒的形式，在这个毫秒的基础上除以1000，就是十位数的时间戳。13位数的都是时间毫秒。
        var minute=1000*60;      //把分，时，天，周，半个月，一个月用毫秒表示
        var  hour=minute*60;
        var day=hour*24;
        var week=day*7;
        var halfamonth=day*15;
        var month=day*30;

        var  now=new Date().getTime();   //获取当前时间毫秒
        var diffValue=now - dateTimeStamp;//时间差

        if(diffValue<0){return;}

        var  minC=diffValue / minute;  //计算时间差的分，时，天，周，月
        var  hourC=diffValue / hour;
        var  dayC=diffValue / day;
        var  weekC=diffValue / week;
        var  monthC=diffValue / month;

        if(monthC>=1){
            var result="" + parseInt(monthC) + "月前";
        }
        else if(weekC>=1){
            result="" + parseInt(weekC) + "周前";
        }
        else if(dayC>=1){
            result=""+ parseInt(dayC) +"天前";
        }
        else if(hourC>=1){
            result=""+ parseInt(hourC) +"小时前";
        }
        else if(minC>=1){
            result=""+ parseInt(minC) +"分钟前";
        }else
            result="刚刚";
        return result;
    }


    //把字符串型的时间转换为时间毫秒值
    // function toString(stringTime){
    //     var time1 = new Date(Date.parse(stringTime.replace(/-/g, "/")));
    //     var date = time1.getTime();
    //     var aa = timeago(date);
    //     return aa;
    // }
    //
    // //遍历p标签，转换后再给它赋值
    // for(var i=0;i<$(".classs").length;i++){
    //     $(".classs").eq(i).text(toString($(".classs").eq(i).text()));
    // }



});