/**
 * Created by kb on 2018/2/9.
 */
$(function () {

    var getIndex = 0;
    $('.carousel').carousel({interval: false, wrap: false});

    $('#myCarousel').on('slide.bs.carousel', function (event) {
        var $hoder = $('#myCarousel').find('.item'),
            $items = $(event.relatedTarget);
        //getIndex就
        getIndex = $hoder.index($items);//是轮播到当前位置的索引
        console.log(getIndex);
        var currentPage = 0;
        if (getIndex + 1 <= 9) {
            currentPage = '0' + (getIndex + 1);
        }
        $('.desc-page .now-num').text(currentPage);
        $('.desc-info .content').text($hoder.eq(getIndex).children('img').attr('alt'));
    })
    $('.glyphicon-chevron-right').on('click', function () {
        var $hoder = $('#myCarousel').find('.item');
        // 显示列表  3表示轮播图的长度
        if (getIndex + 1 == $hoder.length) {
            $('.image-wrap').hide();
            $('.photo-end-layer').show();
        }
    })

    // 重新浏览
    $('.J-replay').on('click', function (e) {
        e.preventDefault();
        location.reload()
    })
    $('#share-1, #share-2').share({sites: ['qzone', 'weibo']});
    $(' .sbanc').text('');
    $(' .sbanc').html('<i class="icon fl"></i>');
//        $('#share-1').share({sites: ['qzone', 'weibo']});
//        $('#share-1 .sbanc').text('');
//        $('#share-1 .sbanc').html('<i class="icon fL"></i>');
})