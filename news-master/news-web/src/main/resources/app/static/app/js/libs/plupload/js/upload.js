var OssUpload = {
    oss_host: '',
    oss_policyBase64: '',
    oss_accessid: '',
    oss_signature: '',
    oss_expire: '',
    oss_callbackbody: '',
    oss_key: '',
    oss_upfilename: '',
    button_id: '', // 选择图片按钮id
    prefix: '',
    upObj: null,
    filesUrl:[],
    // 获得签名
    get_signature: function() {
        __this = this;
        $.ajax({
            url: "/admin/common/ajaxOssUploadSign",
            type: "post",
            dataType: "json",
            async: false,
            success: function(ret) {
                if(ret.error_code == 0) {
                    var obj = eval ("(" + ret.sign + ")");
                    __this.oss_host = obj['host'];
                    __this.oss_policyBase64 = obj['policy'];
                    __this.oss_accessid = obj['accessid'];
                    __this.oss_signature = obj['signature'];
                    __this.oss_expire = parseInt(obj['expire']);
                    __this.oss_callbackbody = obj['callback'];
                    __this.oss_key = obj['dir'];
                }
            }
        });
    },
    // 生成随机字符串
    random_string: function (len) {
        len = len || 32;
        var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
        var maxPos = chars.length;
        var pwd = '';
        for (i = 0; i < len; i++) {
            pwd += chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    },
    // 获得文件后缀名
    get_suffix: function (filename) {
        var pos = filename.lastIndexOf('.');
        var suffix = '';
        if (pos != -1) {
            suffix = filename.substring(pos)
        }
        return suffix;
    },
    // 生成上传文件名
    calculate_object_name: function (filename) {
        var suffix = this.get_suffix(filename);
        return this.oss_key + this.prefix + '_' + (new Date().getTime()) + this.random_string(15) + suffix;
    },
    // 设置上传参数
    set_upload_param: function (up, filename) {
        this.get_signature();
        this.oss_upfilename = this.calculate_object_name(filename);
        this.filesUrl[filename] = this.oss_host+'/'+this.oss_upfilename;
        var new_multipart_params = {
            'key' : this.oss_upfilename,
            'policy': this.oss_policyBase64,
            'OSSAccessKeyId': this.oss_accessid,
            'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
            'callback' : this.oss_callbackbody,
            'signature': this.oss_signature,
        };

        up.setOption({
            'url': this.oss_host,
            'multipart_params': new_multipart_params
        });
    },
    // 上传类
    uploader: function () {
        __this = this;
        this.upObj = new plupload.Uploader({
            runtimes : 'html5,flash,silverlight,html4',
            browse_button : __this.button_id,
            //multi_selection: false,
            container: document.getElementById('oss-container'),
            flash_swf_url : '/js/libs/plupload/js/Moxie.swf',
            silverlight_xap_url : '/js/libs/plupload/js/Moxie.xap',
            url : 'http://oss.aliyuncs.com',
            // 图片压缩
            resize: {
                quality: 70,
            },

            filters: {
                mime_types : [ //只允许上传图片
                    { title : "Image files", extensions : "jpg,gif,png,bmp" },
                ],
                max_file_size : '2mb', //最大只能上传2mb的文件
                prevent_duplicates : true //不允许选取重复文件
            },

            init: {
                FilesAdded: __this.files_added,
                UploadProgress: __this.upload_progress,
                FileUploaded: __this.finished,
                Error: __this.error,
                BeforeUpload: __this.before_upload,
            }
        });
        return this.upObj;
    },
    // 文件选择成功
    files_added: function (up, files) {
        up.start();
    },
    before_upload: function(up, file) {
        __this.set_upload_param(up, file.name);
    },
    // 上传进度回调
    upload_progress: function(up, file) {
        $('#selectimages').parents('.input-group:first').find(":text").val("上传中……");
    },
    // 上传完成
    finished: function (up, file, info) {
        $.noty.closeAll();
        if (info.status == 200) // 上传成功
        {
            $('#selectimages').parents('.input-group:first').find(":text").val(__this.filesUrl[file.name]);
            $('#image_preview').show();
            $('#image_preview').attr('src',__this.filesUrl[file.name]);
        }
        else // 上传失败
        {
            noty({
                text: info.response,
                type: 'error'
            });
        }
    },
    error: function (up, err) {
        if (err.code == -600) {
            noty({
                text: "选择的文件太大了",
                type: 'error'
            });
        }
        else if (err.code == -601) {
            noty({
                text: "选择的文件后缀不对",
                type: 'error'
            });
        }
        else if (err.code == -602) {
            noty({
                text: "这个文件已经上传过一遍了",
                type: 'error'
            });
        }
        else
        {
            noty({
                text: err,
                type: 'error'
            });
        }
    }
};