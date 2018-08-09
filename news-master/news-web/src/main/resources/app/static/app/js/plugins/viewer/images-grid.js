
(function($) {

    $.fn.imagesGrid = function(options) {

        var args = arguments;

        return this.each(function() {

            if ($.isPlainObject(options)) {
                var cfg = $.extend({}, $.fn.imagesGrid.defaults, options);
                cfg.element = $(this);
                this._imgsGridInst = new ImagesGrid(cfg);
                this._imgsGridInst.render();
                return;
            }

            if (this._imgsGridInst) {
                switch (options) {
                    case 'modal.open':
                        this._imgsGridInst.modal.open(args[1]);
                        break;
                    case 'modal.close':
                        this._imgsGridInst.modal.close();
                        break;
                }
            }

        });

    };

    $.fn.imagesGrid.defaults = {
        images: [],
        cells: 5,
        align: false,
        getViewAllText: function(imagesCount) {
            return 'View all ' + imagesCount + ' images';
        }
    };

    function ImagesGrid(cfg) {

        cfg = cfg || {};

        this.images = cfg.images;
        this.isAlign = cfg.align;
        this.maxGridCells = (cfg.cells < 1)? 1: (cfg.cells > 6)? 6: cfg.cells;
        this.imageLoadCount = 0;
        this.modal = null;

        this.$window = $(window);
        this.$el = cfg.element;
        this.$gridItems = [];

        this.render = function() {

            this.setGridClass();
            this.renderGridImages();

            this.modal = new ImagesGridModal({ images: this.images });

            //this.$window.on('resize', this.resize.bind(this));

        };

        this.setGridClass = function() {

            this.$el.removeClass(function(index, classNames) {
                if (/(imgs-grid-\d)/.test(classNames)) {
                    return RegExp.$1;
                }
            });

            var cellsCount = (this.images.length > this.maxGridCells)?
                this.maxGridCells: this.images.length;

            this.$el.addClass('imgs-grid imgs-grid-' + cellsCount);

        };

        this.renderGridImages = function() {

            if (!this.images) {
                return;
            }

            this.$el.empty();
            this.$gridItems = [];

            var i, item;
            for (i = 0; i < this.images.length; ++i) {

                if (i == this.maxGridCells) {
                    break;
                }

                item = $('<div>', {
                    class: 'imgs-grid-image',
                    click: this.imageClick.bind(this),
                    data: { index: i }
                });

                item.append(
                    $('<div>', {
                        class: 'image-wrap'
                    }).append(
                        $('<img>', {
                            src: this.images[i],
                            load: this.imageLoaded.bind(this)
                        })
                    )
                );

                this.$gridItems.push(item);

            }

            this.$el.append(this.$gridItems);

            if (this.images.length > this.maxGridCells) {
                this.renderViewAll();
            }

        };

        this.renderViewAll = function() {

            this.$el.find('.imgs-grid-image:last .image-wrap').append(
                $('<div>', {
                    class: 'view-all'
                }).append(
                    $('<span>', {
                        class: 'view-all-cover',
                    }),
                    $('<span>', {
                        class: 'view-all-text',
                        text: cfg.getViewAllText(this.images.length)
                    })
                )
            );

        };

        this.resize = function(event) {
            if (this.isAlign) {
                this.align();
            }
        };

        this.imageClick = function(event) {
            var imageIndex = $(event.currentTarget).data('index');
            this.modal.open(imageIndex);
        };

        this.imageLoaded = function() {
            ++this.imageLoadCount;
            if (this.imageLoadCount == this.$gridItems.length) {
                this.imageLoadCount = 0;
                this.allImagesLoaded()
            }
        };

        this.allImagesLoaded = function() {
            if (this.isAlign) {
                this.align();
            }
        };

        this.align = function() {

            var len = this.$gridItems.length;

            switch (len) {
                case 2:
                case 3:
                    this.alignItems(this.$gridItems);
                    break;
                case 4:
                    this.alignItems(this.$gridItems.slice(0, 2));
                    this.alignItems(this.$gridItems.slice(2));
                    break;
                case 5:
                case 6:
                    this.alignItems(this.$gridItems.slice(0, 3));
                    this.alignItems(this.$gridItems.slice(3));
                    break;
            }

        };

        this.alignItems = function(items) {

            var height = items.map(function(item) {
                return item.find('img').height();
            });

            var itemHeight = Math.min.apply(null, height);

            $(items).each(function() {

                var item = $(this),
                    imgWrap = item.find('.image-wrap'),
                    img = item.find('img'),
                    imgHeight = img.height();

                imgWrap.height(itemHeight);

                if (imgHeight > itemHeight) {
                    var top = Math.floor((imgHeight - itemHeight) / 2);
                    img.css({ top: -top });
                }

            });

        };

    }

    function ImagesGridModal(cfg) {

        this.images = cfg.images;
        this.imageIndex = null;

        this.$modal = null;
        this.$indicator = null;
        this.$document = $(document);

        this.open = function(imageIndex) {
            if (this.$modal && this.$modal.is(':visible')) {
                return;
            }
            this.imageIndex = parseInt(imageIndex) || 0;
            this.render();
        };

        this.close = function() {

            if (!this.$modal) {
                return;
            }

            this.$modal.animate({
                opacity: 0
            }, {
                duration: 100,
                complete: function() {
                    this.$modal.remove();
                    this.$modal = null;
                    this.$indicator = null;
                    this.imageIndex = null;
                }.bind(this)
            });

            //this.$document.off('keyup', this.keyUp);

        };

        this.render = function() {

            this.renderModal();
            this.renderCloseButton();
            this.renderInnerContainer();
            this.renderIndicatorContainer();

            //this.keyUp = this.keyUp.bind(this);
            //this.$document.on('keyup', this.keyUp);

            this.$modal.animate({ opacity: 1 }, { duration: 100 });

        };

        this.renderModal = function() {
            this.$modal = $('<div>', {
                class: 'imgs-grid-modal'
            }).appendTo('body');

            var distX,
                startX,
                self = this,
                allowedTime = 300,
                elapsedTime,
                startTime;

            this.$modal.on('touchstart', function (event) {
                event.stopPropagation();
                event.preventDefault();

                var obj   = event.changedTouches || event.originalEvent.changedTouches,
                    touch = obj[0];

                distX = 0;
                startX = touch.pageX;
                startTime = new Date().getTime();
            });

            this.$modal.on('touchmove', function (event) {
                event.stopPropagation();
                event.preventDefault();
            });

            this.$modal.on('touchend', function (event) {
                event.stopPropagation();
                event.preventDefault();

                var obj   = event.changedTouches || event.originalEvent.changedTouches,
                    touch = obj[0];

                distX = touch.pageX - startX;
                elapsedTime = new Date().getTime() - startTime;
                if(elapsedTime <= allowedTime){
                    self.close();
                }
            });
        };

        this.renderCloseButton = function() {
            this.$modal.append($('<div>', {
                class: 'modal-close',
                click: this.close.bind(this)
            }));
        };

        this.renderInnerContainer = function() {

            this.$modal.append(
                $('<div>', {
                    class: 'modal-inner'
                }).append(
                    $('<div>', {
                        class: 'modal-image'
                    }).append(
                        $('<img>', {
                            src: this.images[this.imageIndex]
                        })
                    )
                )
            );

            var box    = this.$modal.find('.modal-inner'),
                img    = box.find('img'),
                modalH = this.$modal.height(),
                imgH   = img.height(),
                margin;
            if(modalH > imgH){
                margin = Math.floor((modalH - imgH)/2);
                box.css('top', margin);
            }else {
                box.css('top', 0);
            }

            var distX,
                startX,
                detectX,
                detectY,
                cachedX,
                cachedY,
                touchStarted = false,
                self = this,
                allowedTime = 45,
                elapsedTime,
                minDist = 45,
                startTime;

            this.$modal.find('img').on('touchstart', function (evt) {
                evt.preventDefault();
                evt.stopPropagation();
                var touch = evt.changedTouches || evt.originalEvent.changedTouches,
                    obj   = touch[0];

                touchStarted = true;
                distX = 0;
                cachedX = startX = detectX = obj.pageX;
                cachedY = detectY = obj.pageY;
                startTime = new Date().getTime();

                setTimeout(function () {
                    if(cachedX === detectX && cachedY === detectY && !touchStarted){
                        self.close();
                    }
                }, 200)
            });

            this.$modal.find('img').on('touchmove', function (evt) {
                evt.stopPropagation();
                evt.preventDefault();
                var touch = evt.changedTouches || evt.originalEvent.changedTouches,
                    obj   = touch[0];

                detectX = obj.pageX;
                detectY = obj.pageY;
                distX = obj.pageX - startX;
                $(this).css({position: 'relative', left: distX});
            });

            this.$modal.find('img').on('touchend', function (evt) {
                evt.stopPropagation();
                evt.preventDefault();
                touchStarted = false;
                elapsedTime = new Date().getTime() - startTime;
                if(elapsedTime >= allowedTime){
                    if(Math.abs(distX) >= minDist){
                        distX > 0 ? self.prev() : self.next();
                        this.style.left = '';
                    }else {
                        this.style.left = '';
                    }
                }else {
                    this.style.left = '';
                }
            });
        };

        this.renderIndicatorContainer = function() {


            this.$indicator = $('<div>', {
                class: 'modal-indicator'
            });


            var list;
            if (this.images.length == 1) {
                list = $('<div>1/1</div>');
            }

            if(this.images.length > 1) {
                // for (var i = 0; i < this.images.length; ++i) {
                //     list.append($('<li>', {
                //         class: this.imageIndex == i ? 'selected' : '',
                //         click: this.indicatorClick.bind(this),
                //         data: {index: i}
                //     }));
                // }

                var ix = this.imageIndex;
                ix++;
                list = $('<div>'+ ix +'/'+ this.images.length + '</div>');
            }

            this.$indicator.append(list);

            this.$modal.append(this.$indicator);

        };

        this.prev = function() {
            if (this.imageIndex > 0) {
                --this.imageIndex;
            } else {
                this.imageIndex = this.images.length - 1;
            }
            this.updateImage();
        };

        this.next = function() {
            if (this.imageIndex < this.images.length - 1) {
                ++this.imageIndex;
            } else {
                this.imageIndex = 0;
            }
            this.updateImage();
        };

        this.updateImage = function() {

            var index  = this.imageIndex,
                img    = this.$modal.find('.modal-image img'),
                modalH = this.$modal.height(),
                box    = this.$modal.find('.modal-inner'),
                imgH,
                margin;

            img.attr('src', this.images[index]);
            var nimg  = this.$modal.find('.modal-image img');
            imgH = nimg.height();
            if(modalH > imgH){
                margin = Math.floor((modalH - imgH)/2);
                box.css('top', margin);
            }else {
                box.css('top', 0);
            }

            if (this.$indicator) {
                var div = this.$indicator.find('div'),
                    ix  = this.imageIndex;
                ix++;
                div.text(ix + '/' + this.images.length);
            }

        };

        this.imageClick = function(event) {
            this.next();
        };

        this.indicatorClick = function(event) {
            var index = $(event.target).data('index');
            this.imageIndex = index;
            this.updateImage();
        };

        this.keyUp = function(event) {
            if (this.$modal) {
                switch (event.keyCode) {
                    case 27: // Esc
                        this.close();
                        break;
                    case 37: // Left arrow
                        this.prev();
                        break;
                    case 39: // Right arrow
                        this.next();
                        break;
                }
            }
        };

    }

})(jQuery);
