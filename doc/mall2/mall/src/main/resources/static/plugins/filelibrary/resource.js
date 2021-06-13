/**
 * jquery全局函数封装
 */
(function ($) {
    /**
     * Jquery类方法
     */
    $.fn.extend({

    	/**
         * 删除元素
         */
        delete: function (index, url) {
            $(this).click(function () {
                var param = {};
                param[index] = $(this).attr('data-id');
                layer.confirm('确定要删除吗？', function (index) {
                    $.post(url, param, function (result) {
                        result.code === 1 ? $.show_success(result.msg, result.url)
                            : $.show_error(result.msg);
                    });
                    layer.close(index);
                });
            });
        },

        /**
         * 选择图片文件
         * @param option
         */
        selectImages: function (option) {
            var $this = this
                // 配置项
                , defaults = {
                    name: ''            // input name
                    , imagesList: '.uploader-list'    // 图片列表容器
                    , imagesItem: '.file-item' // 图片元素的容器
                    , imageDelete: '.file-item-delete'   // 删除按钮元素
                    , multiple: false    // 是否多选
                    , limitNum: null // 默认为-1，即不限制数量
                    , done: null  // 选择完成后的回调函数
                }
                , options = $.extend({}, defaults, option);
            // 显示文件库 选择文件
            $this.fileLibrary({
                type: 'image'
                , done: function (data, $touch) {
                    var list = options.multiple ? data : [data[0]];
                    // 判断回调参数是否存在, 否则执行默认
                    if (typeof options.done === 'function') {
                        return options.done(data, $touch);
                    }
                	// 新增图片列表
                    var $html = $(template('tpl-file-item', {list: list, name: options.name}))
                        , $imagesList = $this.next(options.imagesList);
                    if (options.limitNum > 0
                    		&& $imagesList.find(options.imagesItem).length + list.length > options.limitNum) {
                        layer.msg('图片数量不能大于' + options.limitNum + '张', {anim: 6});
                        return false;
                    }
                    // 注册删除事件
                    $html.find(options.imageDelete).click(function () {
                        $(this).parent().remove();
                    });
                    // 渲染html（多图拼接，单图替换）
                    options.multiple ? $imagesList.append($html) : $imagesList.html($html);
                }
            });
        }

    });

    /**
     * Jquery全局函数
     */
    $.extend({
        /**
         * 操作成功弹框提示
         * @param msg
         * @param url
         */
        show_success: function (msg, url) {
            layer.msg(msg, {
                icon: 1
                , time: 1800
                // , anim: 1
                , shade: 0.5
                , end: function () {
                    url === undefined ? window.location.reload() : window.location = url;
                }
            });
        },

        /**
         * 操作失败弹框提示
         * @param msg
         * @param reload
         */
        show_error: function (msg, reload) {
            var time = reload ? 1800 : 0;
            layer.alert(msg, {
                title: '提示'
                , icon: 2
                , time: time
                , anim: 6
                , end: function () {
                    reload && window.location.reload();
                }
            });
        },

        /**
         * 文件上传 (单文件)
         * 支持同一页面多个上传元素
         *  $.uploadImage({
         *   pick: '.upload-file',  // 上传按钮
         *   list: '.uploader-list' // 缩略图容器
         * });
         */
        uploadImage: function (option) {
            // 文件大小
            var maxSize = option.maxSize !== undefined ? option.maxSize : 2
                // 初始化Web Uploader
                , uploader = WebUploader.create({
                    // 选完文件后，是否自动上传。
                    auto: true,
                    // 允许重复上传
                    duplicate: true,
                    // 文件接收服务端。
                    server: STORE_URL + '/upload/image',
                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: {
                        id: option.pick,
                        multiple: false
                    },
                    // 文件上传域的name
                    fileVal: 'iFile',
                    // 图片上传前不进行压缩
                    compress: false,
                    // 文件总数量
                    // fileNumLimit: 1,
                    // 文件大小2m => 2097152
                    fileSingleSizeLimit: maxSize * 1024 * 1024,
                    // 只允许选择图片文件。
                    accept: {
                        title: 'Images',
                        extensions: 'gif,jpg,jpeg,bmp,png',
                        mimeTypes: 'image/*'
                    },
                    // 缩略图配置
                    thumb: {
                        quality: 100,
                        crop: false,
                        allowMagnify: false
                    },
                    // 文件上传header扩展
                    headers: {
                        'Accept': 'application/json, text/javascript, */*; q=0.01',
                        'X-Requested-With': 'XMLHttpRequest'
                    }
                });
            //  验证大小
            uploader.on('error', function (type) {
                // console.log(type);
                if (type === "F_DUPLICATE") {
                    // console.log("请不要重复选择文件！");
                } else if (type === "F_EXCEED_SIZE") {
                    alert("文件大小不可超过" + maxSize + "m 哦！换个小点的文件吧！");
                }
            });

            // 当有文件添加进来的时候
            uploader.on('fileQueued', function (file) {
                var $uploadFile = $('#rt_' + file.source.ruid).parent()
                    , $list = $uploadFile.next(option.list)
                    , $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<input type="hidden" name="' + $uploadFile.data('name') + '" value="">' +
                    '<i class="fa fa-remove file-item-delete"></i>' +
                    '</div>'
                    ),
                    $img = $li.find('img'),
                    $delete = $li.find('.file-item-delete');
                // 删除文件
                $delete.on('click', function () {
                    uploader.removeFile(file);
                    $delete.parent().remove();
                });
                // $list为容器jQuery实例
                $list.empty().append($li);
                // 创建缩略图
                // 如果为非图片文件，可以不用调用此方法。
                // thumbnailWidth x thumbnailHeight 为 100 x 100
                uploader.makeThumb(file, function (error, src) {
                    if (error) {
                        $img.replaceWith('<span>不能预览</span>');
                        return;
                    }
                    $img.attr('src', src);
                }, 1, 1);
            });
            // 文件上传成功，给item添加成功class, 用样式标记上传成功。
            uploader.on('uploadSuccess', function (file, response) {
                if (response.code === 1) {
                    var $item = $('#' + file.id);
                    $item.addClass('upload-state-done')
                        .children('input[type=hidden]').val(response.data.path);
                } else
                    uploader.uploadError(file);
            });
            // 文件上传失败
            uploader.on('uploadError', function (file) {
                uploader.uploadError(file);
            });
            // 显示上传出错信息
            uploader.uploadError = function (file) {
                var $li = $('#' + file.id),
                    $error = $li.find('div.error');
                // 避免重复创建
                if (!$error.length) {
                    $error = $('<div class="error"></div>').appendTo($li);
                }
                $error.text('上传失败');
            };
        }

    });

})(jQuery);

/**
 * app.js
 */
$(function () {

    /**
     * 点击侧边开关 (一级)
     */
    $('.switch-button').on('click', function () {
        var header = $('.tpl-header'), wrapper = $('.tpl-content-wrapper'), leftSidebar = $('.left-sidebar');
        if (leftSidebar.css('left') !== "0px") {
            header.removeClass('active') && wrapper.removeClass('active') && leftSidebar.css('left', 0);
        } else {
            header.addClass('active') && wrapper.addClass('active') && leftSidebar.css('left', -280);
        }
    });

    /**
     * 侧边栏开关 (二级)
     */
    $('.sidebar-nav-sub-title').click(function () {
        $(this).toggleClass('active');
    });

    // 刷新按钮
    $('.refresh-button').click(function () {
        window.location.reload();
    });

    // 删除图片 (数据库已有的)
    $('.file-item-delete').click(function () {
        var _this = this;
        layer.confirm('您确定要删除该图片吗？', {
            title: '友情提示'
        }, function (index) {
            $(_this).parent().remove();
            layer.close(index);
        });
    });

});