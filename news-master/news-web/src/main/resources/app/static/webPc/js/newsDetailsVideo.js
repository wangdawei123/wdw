/**
 * Created by kb on 2018/2/9.
 */
$(function () {
    // 分享
    $('#share-1').share({sites: ['qzone', 'weibo']});
    $('.sbanc').text('');
    $('.sbanc').html('<i class="icon fl"></i>');

    // 点击视频
    var flant = true;
    $('.video').click(function () {
        if (flant) {
            $('#myvideo')[0].pause();
            flant = false;
        } else {
            $('#myvideo')[0].play();
            flant = true;
        }
    });

    // 视频描述
    var text = $('.intro').data('text');
    var em = $('.intro').find('em');
    if (em.html()&&(em.html().length < 50)) {
        $('.slide-show-btn').hide()
    }
    $('.intro').on('click', '.slide-show-btn', function () {
        if (em.html().length < text.length) {
            em.html(text)
            $(this).html('收起').removeClass('show')
        } else {
            em.html(text.slice(0, 50))
            $(this).html('展开').addClass('show')
        }
    })

    // 小窗视频
    var videoBoxTop = $('.video-box').offset().top + $('.video-box').height()
    $(window).on('scroll', function () {
        var top = $(this).scrollTop();
        if (top > videoBoxTop) {
            $('.video-fixed').show()
        } else {
            $('.video-fixed').hide()
        }
    })
    $('.video-fixed .close').on('click', function () {
        $('.video-fixed').hide()
    })
})