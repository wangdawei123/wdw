/**
 * [showImg play img]
 * @param  {[number]} index [curindex]
 */

//function preloadImg(url) {
//    var img = new Image();
//    img.src = url;
//    if(img.complete) {
//        return;
//    }
//    else {
//        img.onload = function() {
//        }
//    }
//}
//preloadImg(initData[index-1].img);
            function showImg(index){
                nindex = index

                $('.bigshowimg .bigimg img').css('opacity',0).attr('src',"").attr('src', initData[index-1].img);
                $('.summary .title').html(initData[index-1].title);
                $('.summary .content').html(initData[index-1].detail);
                var curIndex = nindex < 10 ? '0'+ (parseInt(nindex)) : nindex
                $('.summary_c .nownum').html(curIndex);
                window.location.hash = 'p=' + index;
 }
imgOnlod($('.bigshowimg .bigimg img')[0],function(){
    $('.bigshowimg .bigimg img').animate({'opacity':1},200);
})
function imgOnlod(img,callback){
    if(!img){
        return;
    }else{
        img.onload=callback;
    }
}

/**
 * [getQuery get hash]
 * @return {[hash]} 
 */
function getQuery(){
    var hash = window.location.hash.split('#')[1];
    if(hash && hash.length > 1){
        return hash.split('=')[1]
    }
}
/**
 * [init page]
 */
function init(){
    if(getQuery()){
        nindex = getQuery()
    }
    else{
        nindex = 1
    }
    showImg(nindex)// 大图展示初始化
    //set num
    $('.totalnum').html(domlen);
    //haddle class
    $('.silde_left').addClass('end');
    $('.silde_right').removeClass('end');

}
//初始化图集
init()
/**
 * [reload description]
 * @return {[type]} [description]
 */
function reload(){
    showEndList(false)
    showImg(1);


}
$('.J-replay').click(function(e){
    e.preventDefault();
    reload()
})
/**
 * [showEndList ]
 * @return {[type]} [description]
 */
function showEndList(flag){
    var wrap = $('.J-altas-wrap');
    var endLayer = $('.J-photo-end');
    if(flag){
        wrap.hide();
        endLayer.show()
    }
    else{
        wrap.show();
        endLayer.hide()
    }
}
//大图左右两侧切换
$('.silde_left').click(function() {
    console.log(nindex)
    if (nindex == 1) {
        return;
    }
    nindex--;
    $('.silde_right').removeClass('end');
    //设置图片
    showImg(nindex)
    if (nindex == 0) {
        $('.silde_left').addClass('end');
    }
    
});
$('.silde_right').click(function() {
    if (nindex >= domlen) {
        $('.silde_right').addClass('end');
        showEndList(true)
        return;
    }
    console.log(nindex)
    nindex++;
    //设置图片
    showImg(nindex)
    $('.silde_left').removeClass('end');

});

$('.mousehover_l').click(function() {
    $('.silde_left').trigger('click');
});
$('.mousehover_r').click(function() {
    $('.silde_right').trigger('click');
});

