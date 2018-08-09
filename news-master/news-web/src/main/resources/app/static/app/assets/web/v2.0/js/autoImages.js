(function(w,d){
    var device = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase()));
    function WapImage(){
        this.options={
            dom: null,
            speed:100,
            isupdate:true,
            time:3000,
            leftOrright:'left',
            isfor:false,
            callBack:function(){}
        },
        this.eventName={
            touchstart:'touchstart',
            touchmove:'touchmove',
            touchend:'touchend',
        },
        this.point={
            x:5,
            y:5,
            pageX1:0,
            pageX2:0,
            pageY1:0,
            pageY2:0
        },
        this.page={
            bodyWidth:320,
            domUL:null,
            liList:null,
            index: 0,
            flag:false,
            sTime:0,
            eTime:0,
            isDown:false,
            mleft:0,
            back:30,
            moveId:[],
            nextId:null,
            prevId:null,
            isdom:false
        },
        this.Event={
            handleEvent: function(event,lib){
                event = event ? event : window.event;
                // console.log(event.type)
                switch(event.type){

                    case "touchstart":
                        var touch = event.touches[0];
                    case "mousedown":
                        if(lib.page.isDown) return;
                        lib.page.isDown=true;
                        lib.page.sTime=lib.page.eTime=new Date().getTime();
                        lib.Event.stop(lib,lib);
                        if(event.type=="mousedown"){
                            touch = event;
                            event.preventDefault();
                        }
                        lib.point.pageX1 = lib.point.pageX2 = touch.pageX;
                        lib.point.pageY1 = lib.point.pageY2 = touch.pageY;
                        lib.page.mleft = parseFloat(lib.page.domUL.style.marginLeft);
                        lib.page.mleft = lib.page.mleft ? lib.page.mleft : 0;
                    break;
                    case "touchmove":
                        var touch = event.touches[0];
                    case "mousemove":
                        if(!lib.page.isDown) return;

                        if(event.type=="mousemove"){
                            touch = event;
                        }
                        lib.point.pageX2 = touch.pageX;
                        lib.point.pageY2 = touch.pageY;

                        if(lib.point.pageX1==lib.point.pageX2){
                            event.preventDefault(); 
                            return false;
                        }
                        var changeX = lib.point.pageX1 - lib.point.pageX2;
                        var changeY = lib.point.pageY1 - lib.point.pageY2;
                        if(Math.abs(changeX)>Math.abs(changeY)) {//&#65533;&#65533;&#65533;&#65533;&#65533;&#188;&#65533;
                            event.preventDefault();    
                            lib.page.domUL.style.marginLeft=lib.page.mleft-changeX+'px';
                            if(parseFloat(lib.page.domUL.style.marginLeft)<= -(lib.page.liList.length-1)*lib.page.bodyWidth){
                                lib.page.domUL.style.marginLeft= -(lib.page.liList.length-1)*lib.page.bodyWidth+'px';
                                lib.page.mleft=-(lib.page.liList.length-1)*lib.page.bodyWidth;
                            }
                            if(parseFloat(lib.page.domUL.style.marginLeft)>0){
                                lib.page.domUL.style.marginLeft='0px';
                                lib.page.mleft=0;
                            }

                        }else if(Math.abs(changeY)>Math.abs(changeX)){//&#65533;&#65533;&#65533;&#65533;&#65533;&#188;&#65533;

                        }else{//&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;

                        }
                        break;
                    case "mouseup":
                    case "touchend":
                        if(!lib.page.isDown) return;
                        lib.page.eTime=new Date().getTime();
                        lib.page.mleft = parseFloat(lib.page.domUL.style.marginLeft);
                        lib.page.mleft = lib.page.mleft ? lib.page.mleft : 0;

                        var changeX = lib.point.pageX1 - lib.point.pageX2;
                        var changeY = lib.point.pageY1 - lib.point.pageY2;
                        if(Math.abs(changeX)>Math.abs(changeY)) {//&#65533;&#65533;&#65533;&#65533;&#65533;&#188;&#65533;

                            event.preventDefault();
                            lib.Event.move.call(this,lib);

                        }else if(Math.abs(changeY)>Math.abs(changeX)){//&#65533;&#65533;&#65533;&#65533;&#65533;&#188;&#65533;
                            lib.Event.move.call(this,lib);
                        }else{//&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;&#65533;
                            if((lib.page.eTime - lib.page.sTime) > 300) {//&#65533;&#65533;&#65533;&#65533;
                            }else{//&#65533;&#65533;&#65533;&#65533;
                                if(event.button==0 || event.type=='touchend'){
                                var a = lib.page.liList[lib.page.index].getElementsByTagName('a')[0];
                                if(typeof a.getAttribute('target')=='object'){
                                    w.location=a.getAttribute('hrefto')
                                }else{
                                    w.open(a.getAttribute('hrefto'));
                                }
                                }

                            }
                        }
                        lib.page.isDown=false;
                    break;
                    default:
                    break;
                }
            },
            position: function(lib,index){
                // if(index==undefined){
                //     lib.page.domUL.style.marginLeft= -(lib.page.index*lib.page.bodyWidth) +'px';
                // }else{
                //     lib.page.domUL.style.marginLeft= -(index*lib.page.bodyWidth) +'px';
                //     lib.page.index=index;
                // }
                if(!lib.options.isfor){
                    if(index==undefined){
                        lib.page.domUL.style.marginLeft= -(lib.page.index*lib.page.bodyWidth)  +'px';
                    }else{
                        lib.page.domUL.style.marginLeft= -((index-1)*lib.page.bodyWidth) +'px';
                        lib.page.index=index-1;
                    }
                    lib.options.callBack({"index":parseInt(lib.page.liList[lib.page.index].getAttribute('index'))+1, "total":lib.page.liList.length});
                }else{
                    if(index==undefined){
                        lib.page.domUL.style.marginLeft= -lib.page.bodyWidth +'px';
                    }else{
                        lib.page.domUL.style.marginLeft= -lib.page.bodyWidth +'px';
                        while(true){
                            if(parseInt(index)==parseInt(lib.page.liList[1].getAttribute('index'))+1){
                                break;
                            }
                            lib.page.domUL.insertBefore(lib.page.liList[lib.page.liList.length-1],lib.page.liList[0]);
                        }
                    }
                    lib.options.callBack({"index":parseInt(lib.page.liList[1].getAttribute('index'))+1, "total":lib.page.liList.length});
                }
            },
            stop:function(lib){
                for(var i =0;i<lib.page.moveId.length;i++){
                    clearInterval(lib.page.moveId[i]);
                }
                lib.page.moveId=[];
            },
            start:function(lib){
                if(lib.options.isupdate){
                    lib.page.moveId[lib.page.moveId.length] = setInterval(function(){
                        if(lib.options.leftOrright=='left'){
                            lib.Event.next(lib,lib);
                        }else{
                            lib.Event.prev(lib,lib);
                        }
                    },lib.options.time);
                }
            },
            next:function(lib){
                // console.log(lib.page.prevId.length+"nextId")
                // for (var n=0;n<lib.page.prevId.length;n++) {
                // //    clearInterval(lib.page.prevId[n]);
                // };
                // lib.page.prevId=[];
                clearInterval(lib.page.prevId);
                lib.page.prevId=null;
                // var left = (lib.page.bodyWidth-Math.abs(lib.point.pageX1-lib.point.pageX2))/lib.options.speed;
                var yu = Math.abs(parseInt(lib.page.domUL.style.marginLeft));
                while(true){
                    if(yu==0){
                        yu=lib.page.bodyWidth;
                        break;
                    }else if(yu<0){
                        yu= Math.abs(yu);
                        break;
                    }
                    yu=yu-lib.page.bodyWidth
                }
                // var left = (lib.page.bodyWidth-Math.abs(parseFloat(lib.page.domUL.style.marginLeft)%lib.page.bodyWidth))/lib.options.speed;
                var left = yu/lib.options.speed;
                var c = 0;
                if(lib.page.index==lib.page.liList.length-1){
                    lib.page.flag=false;
                    return;
                }
                clearInterval(lib.page.nextId);
                lib.page.nextId = window.setInterval(function(){
                    // lib.Event.stop(lib,lib);
                    // if(lib.page.moveId==null){
                    //     clearInterval(id);
                    // }
                    c=c+5;
                    lib.page.domUL.style.marginLeft= (parseFloat(lib.page.domUL.style.marginLeft)-left*5)+'px';
                    // console.log("next"+lib.page.domUL.style.marginLeft);
                    if(c>=lib.options.speed || parseFloat(lib.page.domUL.style.marginLeft)<= -(lib.page.liList.length-1)*lib.page.bodyWidth ){
                        if(parseFloat(lib.page.domUL.style.marginLeft)<= -(lib.page.liList.length-1)*lib.page.bodyWidth){
                            lib.page.domUL.style.marginLeft= -(lib.page.liList.length-1)*lib.page.bodyWidth+'px';
                        }
                        clearInterval(lib.page.nextId);
                        // for(var n=0;n=lib.page.nextId.length;n++){
                        //     clearInterval(lib.page.nextId[0]);
                        // }
                        // lib.page.nextId=[];
                        lib.page.index++;
                        lib.page.flag=false;
                        lib.Event.domUpdate.call(this,lib,'r');
                        if(lib.page.moveId.length==0){
                            lib.Event.start(lib,lib);
                        }

                    }
                },5);
            },
            prev:function(lib){
                // console.log(lib.page.nextId.length+"nextId")
                // for(var n=0;n=lib.page.nextId.length;n++){
                // //    clearInterval(lib.page.nextId[0]);
                // }
                clearInterval(lib.page.nextId);
                lib.page.nextId=null;
                // lib.page.nextId=[];
                // var left = (lib.page.bodyWidth-Math.abs(lib.point.pageX1-lib.point.pageX2))/lib.options.speed;
                // var left = (lib.page.bodyWidth-Math.abs(parseFloat(lib.page.domUL.style.marginLeft)%lib.page.bodyWidth))/lib.options.speed;
                var yu = Math.abs(parseInt(lib.page.domUL.style.marginLeft));
                // console.log(yu+"----"+lib.page.domUL.style.marginLeft)
                while(true){
                    if(yu==0){
                        yu=lib.page.bodyWidth;
                        break;
                    }else if(yu<0){
                        yu= lib.page.bodyWidth-Math.abs(yu);
                        break;
                    }
                    yu=yu-lib.page.bodyWidth
                }
                // var left = (lib.page.bodyWidth-yu)/lib.options.speed;
                var left = yu/lib.options.speed;
                var c = 0,id;
                if(lib.page.index==0){
                    lib.page.flag=false;
                    return;
                } 
                var ml = parseFloat(lib.page.domUL.style.marginLeft);
                clearInterval(lib.page.prevId);
                lib.page.prevId = window.setInterval(function(){
                    c=c+5;
                    lib.page.domUL.style.marginLeft= (parseFloat(lib.page.domUL.style.marginLeft)+left*5)+'px';
                    // console.log(lib.page.domUL.style.marginLeft);
                    if(c>=lib.options.speed || parseFloat(lib.page.domUL.style.marginLeft)>=0){
                        if(parseFloat(lib.page.domUL.style.marginLeft)>=0){
                            lib.page.domUL.style.marginLeft='0px';
                        }

                        clearInterval(lib.page.prevId);
                        // for (var n=0;n<lib.page.prevId.length;n++) {
                        //     clearInterval(lib.page.prevId[n]);
                        // };
                        // lib.page.prevId=[];

                        lib.page.index--;
                        lib.page.flag=false;
                        lib.Event.domUpdate.call(this,lib,'l');
                        if(lib.page.moveId.length==0){
                            lib.Event.start(lib,lib);
                        }
                    }
                },5);
            },
            move:function(lib){
                if(lib.page.flag) return;
                lib.page.flag=true;
                if(Math.abs(lib.point.pageX1-lib.point.pageX2)<lib.page.back){
                    var h = Math.abs(Math.abs(parseFloat(lib.page.domUL.style.marginLeft))-Math.abs(lib.page.bodyWidth*lib.page.index))
                    h = h/70;

                    var hi = 0;
                    var hid;
                    hid = window.setInterval(function(){

                        if(lib.point.pageX2>lib.point.pageX1){
                            lib.page.domUL.style.marginLeft = (parseFloat(lib.page.domUL.style.marginLeft) - h*5) +'px';
                        }else{

                            lib.page.domUL.style.marginLeft = (parseFloat(lib.page.domUL.style.marginLeft) + h*5) + 'px';
                        }
                        hi=hi+5;
                        if(hi>=70){
                            clearInterval(hid);
                            lib.page.domUL.style.marginLeft= -(lib.page.index*lib.page.bodyWidth) +'px';
                            lib.page.flag=false;
                        }
                    },5);
                    return;
                }

                if(lib.point.pageX1-lib.point.pageX2>0){
                    lib.Event.next.call(this,lib);
                }else if(lib.point.pageX2-lib.point.pageX1>0){
                    // console.log("===")
                    lib.Event.prev.call(this,lib);
                }
            },
            domUpdate: function(lib,type){
                if(lib.page.isdom) return;
                lib.page.isdom=true;

                if(!lib.options.isfor){
                    var index = lib.page.liList[lib.page.index].getAttribute('index');
                    lib.options.callBack({"index":parseInt(index)+1, "total":lib.page.liList.length});
                    lib.page.isdom=false;
                    return;
                }
                if(type=='l'){
                    lib.page.domUL.insertBefore(lib.page.liList[lib.page.liList.length-1],lib.page.liList[0]);
                    lib.page.domUL.style.marginLeft=-lib.page.bodyWidth+'px';//(parseFloat(lib.page.domUL.style.marginLeft)-lib.page.bodyWidth)+'px';
                    //lib.page.index++;
                }else if(type=='r'){
                    lib.page.domUL.appendChild(lib.page.liList[0]);
                    lib.page.domUL.style.marginLeft=-lib.page.bodyWidth+'px';//(parseFloat(lib.page.domUL.style.marginLeft)+lib.page.bodyWidth)+'px';
                    //lib.page.index--;
                }
                lib.page.index=1;
                // console.log(lib.page.index)
                var index = lib.page.liList[lib.page.index].getAttribute('index');
                lib.options.callBack({"index":parseInt(index)+1, "total":lib.page.liList.length});
                lib.page.isdom=false;
            }
        };
    };
    WapImage.prototype = {
        setoption: function(arg){
            for(var i in this.options){
                this.options[i]= arg[i] !== undefined ? arg[i] : this.options[i];
            }
            if(!device){
                this.eventName.touchstart='mousedown';
                this.eventName.touchmove='mousemove';
                this.eventName.touchend='mouseup';
            }
            //return temp;
        },
        bindEvent: function(){
            var lib = this;
            this.page.domUL.addEventListener(this.eventName.touchstart,function(event){lib.Event.handleEvent.call(lib,event,lib);},false);
            w.addEventListener(this.eventName.touchmove,function(event){lib.Event.handleEvent.call(lib,event,lib);},false);
            w.addEventListener(this.eventName.touchend,function(event){lib.Event.handleEvent.call(lib,event,lib);},false);
            top.addEventListener('resize',function(){lib.init();},false);
        },
        init:function(){
            // alert("===")
            this.page.bodyWidth=top.document.body.clientWidth;
            this.page.liList= this.options.dom.getElementsByTagName('li');
            this.page.domUL = this.options.dom.getElementsByTagName('ul')[0];
            this.options.dom.style.width=this.page.bodyWidth+'px';

            for(var i=0;i<this.page.liList.length;i++){
                var item = this.page.liList[i];
                var img = item.getElementsByTagName('img')[0];
                item.setAttribute('index',i);
                item.style.width=this.page.bodyWidth+'px';
                img.style.width = this.page.bodyWidth+'px';
            }
            if(this.page.liList.length<3){
                var length = this.page.liList.length;
                if(length==1){
                    this.page.domUL.appendChild(this.page.liList[0].cloneNode(true));
                    this.page.domUL.appendChild(this.page.liList[0].cloneNode(true));
                }else{
                    for(var i=0;i<length;i++){
                        this.page.domUL.appendChild(this.page.liList[i].cloneNode(true));
                    }
                }

                this.page.liList= this.options.dom.getElementsByTagName('li');    
            }
        },
        position:function(index){
            this.Event.position.call(this,this,index);
        },
        next:function(){
            this.Event.next.call(this,this);
        },
        prev:function(){
            this.Event.prev.call(this,this);
        },
        start: function(arg){
            this.setoption(arg);
            this.init();
            this.position();
            this.bindEvent();
            this.Event.domUpdate(this,'l');
            this.Event.start(this);

        }
    };
    var loaded=function(){
        w.WapImage=new WapImage();
        // w.WapImages=new WapImage();
    };
    (function(){
        if(d.body){
            loaded();
        }else{
            if(d.addEventListener){
                d.addEventListener( 'DOMContentLoaded', function(){
                    d.removeEventListener( 'DOMContentLoaded', arguments.callee, false );
                    loaded();
                }, false );
            }else if(d.attachEvent){
                d.attachEvent( 'onreadystatechange', function(){
                    if( d.readyState === 'complete' ){
                        d.detachEvent( 'onreadystatechange', arguments.callee );
                        loaded();
                    }
                });
            }
        }
    })();
})(window,document,undefined);

(function(w,d){
    var obj = {
        dom:document.getElementById('wapListImage'),
        isupdate:true,
        time:3000,
        isfor:true,
        leftOrright:'left',
        callBack:function(obj){
            var span = document.getElementById('wapListImage').getElementsByTagName('dl')[0].getElementsByTagName('span');
            for(var k = 0;k<span.length;k++){
                span[k].className='';
            }
            span[obj.index-1].className='selected';
            console.log(obj)
            $('#wapListImage .title .con').html($('#wapListImage ul img').eq(1).attr('alt'));
            $('#wapListImage .txt').html($('#wapListImage ul img').eq(1).attr('data-txt'));
            $('#wapListImage .tit_r').html(obj.index+'/'+obj.total);
// if($('#wapListImage ul img').eq(1).attr('alt') == "") {
//                     $('#wapListImage .title').hide();
//                 } else {
//                     $('#wapListImage .title').show();
//                 }
        }
    };
    WapImage.start(obj);
    WapImage.position(1)
// var img = new w.WapImage();
// img.start(obj);
})();