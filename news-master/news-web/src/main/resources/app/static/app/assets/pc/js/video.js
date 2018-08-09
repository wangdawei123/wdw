var text = $('.J-intro').attr('data-text');
var em = $('.J-intro').find('em');
if(em.html().length < 50 ) {
    $('.J-show-btn').hide()
}
$('.J-intro').on('click','.J-show-btn',function () {
   
    if(em.html().length < text.length){
        em.html(text)
        $(this).html('收起').removeClass('show')
    }
    else{
        em.html(text.slice(0,50))
        $(this).html('展开').addClass('show')
    }
})
var videoBox = $('.J-video');
var videoFixed = $('.J-video-fixed');
var videoFixedClose = $('.J-video-fixed .close');
var videoBoxTop = videoBox.offset().top + videoBox.height()
$(window).on('scroll',function(){
    var top = $(this).scrollTop();
    if(top > videoBoxTop){
        videoFixed.show()
    }
    else{
        videoFixed.hide()
    }
})
videoFixedClose.on('click',function(){
    videoFixed.hide()
})