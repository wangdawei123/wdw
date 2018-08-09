/**
 * Created by Leslie on 2016/4/28.
 */

function pagination(options) {
    if(typeof options !== 'object'){
        throw 'options must be an object';
    }
    this.page = 'page' in options ? options.page : 1;
    this.size = 'size' in options ? options.size : 10;
    this.url  = 'url'  in options ? options.url  : (function(){throw 'url is required'})();
    this.more = false;
    this.type = 'type' in options ? options.type : (function(){throw 'type is required'})();
}

pagination.prototype.getList = function () {
    var self = this;
    $.post(
        self.url,
        {page: self.page, pcount: self.size},
        function (response) {
            if(response.error_code){
                throw response.error_msg;
            }

            self.more = response.more;
            $('.item-list').append(self.getListHtml(response.data));
            if(self.more){
                $('.more').show();
                self.show();
            }else {
                $('.more').hide();
            }
            self.getContent();
        }
    );
}

pagination.prototype.getListHtml = function (data) {
    var html = '';
    $.each(data, function (index,elem) {
        html += '<li class="item-content" data-id="' + elem.contentid + '">';
        html += '<a href="javascript:void(0);">' + elem.title + '</a>';
        html += '</li>';
    });

    return html;
}

pagination.prototype.getContent = function () {
    var self = this;
    $('.item-content').each(function () {
        $(this).on('click', function () {
            window.location.href = 'http://m.dz169.net/'+ self.type +'/' + this.dataset['id'];
        })
    });
}

pagination.prototype.show = function () {
    var self = this;
    $('.more').on('click', function () {
        if(self.more){
            self.page++;
            self.getList(self.url);
        }
    });
}