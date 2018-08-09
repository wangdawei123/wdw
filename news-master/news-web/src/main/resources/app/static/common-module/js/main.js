// 页面缩放
fnViewScale();
function fnViewScale(){
    var wrapper = $("#header-wrapper")[0];
    var sreenWidth = document.body.clientWidth;
    var iScale = sreenWidth/320;

    wrapper.style.transform = "scale("+iScale+")";
    wrapper.style.webkitTransform = "scale("+iScale+")";  
}
window.onresize = function(){
    fnViewScale();
}

