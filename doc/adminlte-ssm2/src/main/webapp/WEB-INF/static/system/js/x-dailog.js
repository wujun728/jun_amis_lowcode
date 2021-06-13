//空格截取
function trim(value) {
	if (value == null) {
		return "";
	}
	return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
}

//判断字符串是否为空
function isEmpty(value) {
	if (value == null || trim(value) == "") {
		return true;
	}
	return false;
}

function dialog_detail(title, url, w, h) {
	if (isEmpty(title)) {
		title = false;
	}
	if (isEmpty(url)) {
		url = "404.html";
	}
	if (isEmpty(w)) {
		w = '800';
	}
	if (isEmpty(h)) {
		h = ($(window).height() - 20);
	}
	// 如果是移动端，就使用自适应大小弹窗
	if (navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i)) {
		w = '90%';
		h = '80%';
	} else {
		w = w + 'px';
		h = h + 'px';
	}
	layer.open({
		id : 'layerForm',
		type : 2,
		area : [w, h],
		title : title,
		content : url + "?r=" + new Date().getTime(),
		fix : false, // 不固定
		maxmin : true,
		shadeClose : false,
		scrollbar: false, //屏蔽屏幕滚动条
		shade : 0.3,
		success : function(layero, index) {
		}
	});
}

function dialog_open(title, url, w, h, callback) {
	if (isEmpty(title)) {
		title = false;
	}
	if (isEmpty(url)) {
		url = "404.html";
	}
	if (isEmpty(w)) {
		w = '800';
	}
	if (isEmpty(h)) {
		h = ($(window).height() - 20);
	}
	// 如果是移动端，就使用自适应大小弹窗
	if (navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i)) {
		w = '90%';
		h = '80%';
	} else {
		w = w + 'px';
		h = h + 'px';
	}
	if (isEmpty(callback)) {
		callback = function(index, layero) {
			var iframeWin = layero.find('iframe')[0];
			iframeWin.contentWindow.submitHandler(index,layero);
		}
	}
	layer.open({
		id : 'layerForm',
		type : 2,
		area : [w, h],
		title : title,
		content : url + "?r=" + new Date().getTime(),
		fix : false, // 不固定
		maxmin : true,
		shadeClose : false,
		scrollbar: false, //屏蔽屏幕滚动条
		shade : 0.3,
		btn : [ '保存', '取消' ],
		yes : callback,
		cancel : function(index) {
			return true;
		}
	});
}

function dialog_openFull(title, url, callback) {
	if (isEmpty(title)) {
		title = false;
	}
	if (isEmpty(url)) {
		url = "404.html";
	}
	if (isEmpty(callback)) {
		callback = function(index, layero) {
			var iframeWin = layero.find('iframe')[0];
			iframeWin.contentWindow.submitHandler(index, layero);
		}
	}
	layer.open({
		id : 'layerForm',
		type : 2,
		area : [ '100%', '100%' ],
		title : title,
		content : url + "?r=" + new Date().getTime(),
		fix : false, // 不固定
		maxmin : true,
		shadeClose : false,
		scrollbar: false, //屏蔽屏幕滚动条
		shade : 0.3,
		btn : [ '保存', '取消' ],
		yes : callback,
		cancel : function(index) {
			return true;
		}
	});
}

//带有保存取消按钮
$("body").delegate(".dialog", "click", function() {
	var me = this;
	var url = $(this).attr('data-url');
	var width = $(me).attr('data-width');
	var height = $(me).attr('data-height');
	var title = $(me).attr('data-title');
	dialog_open(title, url, width, height);
});

//带有保存取消按钮 （全屏)
$("body").delegate(".dialogFull", "click", function() {
	var me = this;
	var url = $(this).attr('data-url');
	var width = $(me).attr('data-width');
	var height = $(me).attr('data-height');
	var title = $(me).attr('data-title');
	dialog_openFull(title, url, width, height);
});

//自定义保存取消
$("body").delegate(".dialog_detail", "click", function() {
	var me = this;
	var url = $(this).attr('data-url');
	var width = $(me).attr('data-width');
	var height = $(me).attr('data-height');
	var title = $(me).attr('data-title');
	dialog_detail(title, url, width, height, false);
});

/*关闭弹出框口*/
function x_admin_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}  

