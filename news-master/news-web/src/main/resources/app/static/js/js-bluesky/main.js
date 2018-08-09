// 页面缩放
fnViewScale();
function fnViewScale(){
    var wrapper = $("#wrapper")[0];
    var sreenWidth = document.body.clientWidth;
    var iScale = sreenWidth/320;

    wrapper.style.transform = "scale("+iScale+")";
    wrapper.style.webkitTransform = "scale("+iScale+")";  
}
window.onresize = function(){
    fnViewScale();
}

// 榜单规则

jQuery(document).ready(function($){
	//open popup
	$('.cd-popup-trigger').on('click', function(event){
		event.preventDefault();
		$('.cd-popup').addClass('is-visible');
	});
	
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$('.cd-popup').removeClass('is-visible');
	    }
    });
});

//航空梦列表切换
$('.tap-title span').first().addClass('show').siblings().removeClass('show');
$('.ranking-list').first().show().find('li div.resume').first().show();
$('.tap-title span').click(function(){
	var index=$('.tap-title span').index(this);
	$(this).addClass('show').siblings().removeClass('show');
	$('.ranking-list').eq(index).show().siblings('.ranking-list').hide();
	$('.resume').hide();
	$('.ranking-list').eq(index).find('li div.resume').first().show()
	})
	
$('.vote-btn').click(function(){
	$(this).addClass('sub-btn');	
	$(this).text('已投票');
	})

$('.hide-show').click(function(){
	var con=$(this).attr('src');
	if(con=='../../../../public/static/img/bluesky/hide.png'){
		$(this).attr('src','../../../../public/static/img/bluesky/show.png');
		$(this).parent().parent().next('div.resume').slideUp(300);
		}else{
	$(this).attr('src','../../../../public/static/img/bluesky/hide.png');
	$(this).parent().parent().next('div.resume').slideDown(300).parent().siblings().find('div.resume').slideUp(300);
	$(this).parents().siblings().find('.hide-show').attr('src','../../../../public/static/img/bluesky/show.png');
	}
})

