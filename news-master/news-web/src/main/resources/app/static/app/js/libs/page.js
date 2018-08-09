/*
 page.js
 分页
 */
$('#example2_previous').click(function () {
    var previous = $(this).val();
    if(previous == 0){
        page =  previous+1;
    }else{
        if(page>1){
            page = page-1;
        }else if(page==1){
            page = 1;
        }
    }
    $(this).val(page);
    newsSearch(page);
});

$('#example2_next').click(function () {

    var next = $(this).val();
    if(next==0){
        page = next+2;
    }else{
        page = next+1;
    }
    $(this).val(page);
    newsSearch(page);

});
//搜索绑定
function newsSearch(page) {
    var id = $("#search-bind").data('id'),
        keyword = $('#keyword').val();
    keyword = keyword.trim();
    var bindtype = $("#bindtype").val();
    var bindurl = $("#bindurl").val();
    if (!keyword.length) {
        noty({
            type: 'error',
            text: '搜索关键字为空',
            timeout: 1000
        });
        $.noty.closeAll();
        return false;
    }

    $.ajax({
        url: bindurl,
        type: 'post',
        data: {
            keyword: keyword,
            type: bindtype,
            filter : 'video',
            filter_id : id,
            page : page
        },
        success: function (response) {
            $('#bind-modal .modal-body').empty();//
            if (response.error_code === 0) {
                var data = response.data;
                if (data.length > 0) {
                    var html = getBindTableHtml(data);
                    $('#bind-modal .modal-body').html('').append(html);
                    $('.bind-btn').on('click', function () {
                        var bid = $(this).data('id');
                        addBind(id, bid);
                    });
                    $('#bind-modal').modal('show');
                } else {
                    noty({
                        type: 'success',
                        text: '没有找到任何内容,试试其他关键字吧',
                        timeout: 1000
                    });

                    $.noty.closeAll();
                }
            } else {
                noty({
                    type: 'error',
                    text: response.error_msg,
                    timeout: 1000
                });

                $.noty.closeAll();
            }
        }
    });
}
