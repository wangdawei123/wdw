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

// 展开与收起
$(function(){
$(".case-btn").click(function(){
	if($(this).prev('span').hasClass('case-con')){
		$(this).prev('span').removeClass("case-con");
		$(this).addClass("case-btnc");
		$(this).text("收起");
	}else{
		$(this).prev('span').addClass("case-con");
		$(this).removeClass("case-btnc");
		$(this).text("展开");
		}
	})
})


