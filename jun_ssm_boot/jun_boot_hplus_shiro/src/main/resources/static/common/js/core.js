/**
 * 通用js方法封装处理
 */
var table = {
	config: {},
	options: {},
};
(function($) {
	$.extend({
		//表格处理
		table: {
			/*bootstrap-table表格*/
			initTable: function (options, success) {
				var defaults = {
					id: "table",
					url: "",
					columns: [],
					uniqueId: "id",//每一行的唯一标识，一般为主键列
					method: "post",//请求方式（*）
					undefinedText: "-", /*为undefiend时显示的字*/
					striped: false, //是否显示行间隔色
					queryParams: $.table.queryInitParams,
					responseHandler: $.table.responseHandler,
					toolbar: '',        //工具按钮用哪个容器
					loadingFontSize: 13,
					pageNumber: 1,
					pageSize: 10,
					pageList: [10, 20, 50, 999],
					contentType: "application/x-www-form-urlencoded",//用post请求，这个是必须条件，必须加上，get可以不用，亲测
					dataType: "json",
					cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination: true, //是否显示分页（*）
					sortable: false, //是否启用排序
					sortOrder: "asc", //排序方式
					sortName: "", //排序字段
					queryParamsType: "limit",
					sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
					showColumns: false, //是否显示所有的列
					showRefresh: false, //是否显示刷新按钮
					minimumCountColumns: 2, //最少允许的列数
					clickToSelect: true, //是否启用点击选中行
					strictSearch: true,
					showToggle: false, //是否显示详细视图和列表视图的切换按钮
					cardView: false, //是否显示详细视图
					detailView: false, //是否显示父子表
					showExport: false, //是否显示导出
					exportDataType: "basic", //basic', 'all', 'selected'.
					escape: true,//html转意
					onLoadSuccess: $.table.tableLoadSuccess,
				}
				var tableOptions = $.extend({}, defaults, options);
				table.options = tableOptions;
				$(tableOptions.id).bootstrapTable({
					url: tableOptions.url, //请求后台的URL（*）
					contentType: tableOptions.contentType, //用post请求，这个是必须条件，必须加上，get可以不用，亲测
					dataType: tableOptions.dataType,
					method: tableOptions.method, //请求方式（*）
					//toolbar: '#toolbar', //工具按钮用哪个容器
					undefinedText: tableOptions.undefinedText, /*为undefiend时显示的字*/
					striped: tableOptions.striped, //是否显示行间隔色
					cache: tableOptions.cache, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination: tableOptions.pagination, //是否显示分页（*）
					sortable: tableOptions.sortable, //是否启用排序
					sortOrder: tableOptions.sortOrder, //排序方式
					sortName: tableOptions.sortName, //排序方式
					toolbar: tableOptions.toolbar, // 指定工作栏
					loadingFontSize: tableOptions.loadingFontSize, // 自定义加载文本的字体大小
					//search: true, //是否使用客户端搜索
					queryParams: tableOptions.queryParams,//传递参数（*）
					responseHandler: tableOptions.responseHandler,
					queryParamsType: tableOptions.queryParamsType,
					sidePagination: tableOptions.sidePagination, //分页方式：client客户端分页，server服务端分页（*）
					pageNumber: tableOptions.pageNumber, //初始化加载第一页，默认第一页
					pageSize: tableOptions.pageSize, //每页的记录行数（*）
					pageList: tableOptions.pageList, //可供选择的每页的行数（*）
					showColumns: tableOptions.showColumns, //是否显示所有的列
					showRefresh: tableOptions.showRefresh, //是否显示刷新按钮
					minimumCountColumns: tableOptions.minimumCountColumns, //最少允许的列数
					clickToSelect: tableOptions.clickToSelect, //是否启用点击选中行
					strictSearch: tableOptions.strictSearch,
					//height: 460, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					showToggle: tableOptions.showToggle, //是否显示详细视图和列表视图的切换按钮
					uniqueId: tableOptions.uniqueId, //每一行的唯一标识，一般为主键列
					cardView: tableOptions.cardView, //是否显示详细视图
					detailView: tableOptions.detailView, //是否显示父子表
					showExport: tableOptions.showExport, //是否显示导出
					exportDataType: tableOptions.exportDataType, //basic', 'all', 'selected'.
					escape: tableOptions.escape,//html转意
					// align: "center",
					columns: tableOptions.columns,//表格列
					onLoadSuccess: tableOptions.onLoadSuccess,
				});
			},
			queryInitParams : function(params) {
				var temp = { //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
					limit: params.limit, //页面大小
					offset: params.offset //页码
				};
				return temp;
			},
			tableLoadSuccess : function(data) {
				$(".bs-checkbox").css('vertical-align','middle');
			},
			responseHandler : function(data) {
				return data;
			},
			// 刷新表格
			refreshTable: function(tableId) {
				var params = $.common.isEmpty(tableId) ? $(table.options.id).bootstrapTable('getOptions') : $(tableId).bootstrapTable('getOptions');
				if(!$.common.isEmpty(tableId)){
					$(tableId).bootstrapTable('refresh', params);
				} else{
					$(table.options.id).bootstrapTable('refresh', params);
				}
			},
			/*根据data选中数据*/
			checkTableBy : function (id,data) {
				$(id).bootstrapTable("checkBy", data)
			},
			/*根据uniqueId获取所选列*/
			getRowByUniqueId : function (id, val) {
				return $(id).bootstrapTable("getRowByUniqueId", val);
			},
			selectSingleData : function (id){
				var selectContent = $(id).bootstrapTable('getSelections');
				if(typeof(selectContent) == 'undefined' || selectContent == "") {
					toastr.error("请先选择一条数据!");
					return false;
				}else if(selectContent.length > 1){
					toastr.error("只能选择一条数据!");
					return false;
				}else{
					var selectData = selectContent[0];
					return selectData;
				}
			},
			selectMutiData : function (id){
				var checkedRows= $(id).bootstrapTable('getSelections');
				if(checkedRows.length==0){
					toastr.error("请先选择一条数据！");
					return false;
				}else{
					return checkedRows;
				}
			},
			/*更新某一列的值 index-行索引，field-字段名，value-值*/
			updateCell : function (id, index, field, value) {
				var updateCellOptions = {
					index: index,
					field: field,
					value: value
				}
				return $(id).bootstrapTable("updateCell", updateCellOptions);
			}
		},
		//公共处理
		common: {
			/*ajax请求*/
			postAjax : function (url, dataToPost, d, type, contentType, async) {
				$.ajax({
					url: url,
					cache: false,
					async: async == undefined ? true : async,
					data: dataToPost,
					type: type == undefined ? "POST" : type,
					contentType: contentType == undefined ? 'application/x-www-form-urlencoded; charset=UTF-8' : contentType,
					success: function (data) {
						if (typeof d == "function") {
							d(data);
						}
					},
					error:function (XMLHttpRequest, textStatus, errorThrown) {
						if(XMLHttpRequest.status==403){
							toastr.error("您没有权限访问，请联系管理员！")
						}else if(XMLHttpRequest.status==500){
							toastr.error("服务器内部错误！")
						}else{
							toastr.error("服务器未知错误！")
						}
					}
				});
			},
			/*load()*/
			load : function (id,url,d,t) {
				$(id).html("");
				$(id).load(url,function(response,status,XMLHttpRequest){
					if (typeof d == "function" && status=="success") {
						d();
					}
					if(status=="error"){
						if(t==undefined||t==1){
							$("#content").html(response);
						}else if(t=2){
							if(XMLHttpRequest.status==403){
								toastr.error("您没有权限访问！")
							}else if(XMLHttpRequest.status==500){
								toastr.error("服务器内部错误！")
							}else{
								toastr.error("服务器未知错误！")
							}
						}
					}
				})
			},
			//清除表单
			clearForm : function (id) {
				var objId = document.getElementById(id);
				if (objId == undefined) {
					return;
				}
				for (var i = 0; i < objId.elements.length; i++) {
					if (objId.elements[i].type == "text") {
						objId.elements[i].value = "";
					} else if (objId.elements[i].type == "password") {
						objId.elements[i].value = "";
					} else if (objId.elements[i].type == "radio") {
						objId.elements[i].checked = false;
					} else if (objId.elements[i].type == "checkbox") {
						objId.elements[i].checked = false;
					} else if (objId.elements[i].type == "select-one") {
						objId.elements[i].options[0].selected = true;
					} else if (objId.elements[i].type == "select-multiple") {
						for (var j = 0; j < objId.elements[i].options.length; j++) {
							objId.elements[i].options[j].selected = false;
						}
					} else if (objId.elements[i].type == "textarea") {
						objId.elements[i].value = "";
					}
				}
			},
			/*清除表单错误提示*/
			clearError : function (id) {
				$(id).find(".warning,.valid,.promimg").remove();
				$(id).find(".error").removeClass("error");
				$(id).find(".prombtn").removeClass("prombtn");
				$(id).find(".prominput").removeClass("prominput");
			},
			/*保留两位小数*/
			numberTwo : function (num) {
				if (isNaN(num) || num == "") {
					return num;
				} else {
					if (isNaN(parseFloat(num).toFixed(2))) {
						return num;
					} else {
						return parseFloat(num).toFixed(2);
					}
				}
			},
			/*数字千分话并保留两位小数*/
			numToTwo : function (num) {
				try {
					num = $.common.numberTwo(num).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
				} finally {
					return num;
				}
			},
			// 判断是否为json对象
			isJsonObject : function (obj) {
				var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
				return isjson;
			},
			// 图片预览
			imageView : function (value, height, width, target) {
				if (width == null || $.trim(width) == "") {
					width = 'auto';
				}
				if (height == null || $.trim(height) == "") {
					height = 'auto';
				}
				// blank or self
				var _target = (target == null || $.trim(target) == "") ? 'self' : target;
				if (value == null || $.trim(value) == "") {
					return "-";
				} else {
					var strhtml  = '<figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">'
						strhtml += '	<a href="%s" itemprop="contentUrl">'
						strhtml += '		<img src="%s" class="img-circle img-xs" itemprop="thumbnail"/>'
						strhtml += '	</a>'
						strhtml += '</figure>'
					return $.common.sprintf(strhtml, value, value);
				}
			},
			// 图片预览
			imageSquareView : function (value, height, width, target) {
				if (width == null || $.trim(width) == "") {
		        	width = 'auto';
		        }
		        if (height == null || $.trim(height) == "") {
		        	height = 'auto';
		        }
				// blank or self
				var _target = (target == null || $.trim(target) == "") ? 'self' : target;
				if (value == null || $.trim(value) == "") {
					return "-";
				} else {
					var strhtml  = '<figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">'
						strhtml += '	<a href="%s" itemprop="contentUrl">'
						strhtml += '		<img src="%s" class="img-square img-md" itemprop="thumbnail"/>'
						strhtml += '	</a>'
						strhtml += '</figure>'
					return $.common.sprintf(strhtml, value, value);
				}
			},
			//参数拼接
			sprintf : function (str) {
				var args = arguments, flag = true, i = 1;
				str = str.replace(/%s/g, function () {
					var arg = args[i++];
					if (typeof arg === 'undefined') {
						flag = false;
						return '';
					}
					return arg;
				});
				return flag ? str : '';
			},
			// 判断字符串是否为空
			isEmpty : function (value) {
				if (value == null || $.trim(value) == "") {
					return true;
				}
				return false;
			},
			startWith: function(value, start) {
                var reg = new RegExp("^" + start);
                return reg.test(value)
            },
            endWith: function(value, end) {
                var reg = new RegExp(end + "$");
                return reg.test(value)
            },
			// 空格截取
			trim: function (value) {
				if (value == null) {
					return "";
				}
				return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
			},
			/*禁用button*/
			mask : function (e) {
				var i = "<i class='icon icon-spin icon-spinner-indicator'></i>"
				$(e).append(i);
				$(e).attr('disabled', "true");//添加disabled属性
			},
			/*启用button*/
			unmask : function (e) {
				$(e).children('i').remove();
				$(e).removeAttr('disabled');//添加disabled属性
			}
		},
		//弹窗处理
		modal: {
			// 打开遮罩层
			loading : function (message) {
				$.blockUI({ message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>' });
			},
			// 关闭遮罩层
			closeLoading : function () {
				setTimeout(function(){
					$.unblockUI();
				}, 50);
			},
			/*询问框*/
			confirm : function(content,d){
				layer.confirm(content, {
					icon: 3,
					title: "系统提示",
					btn: ['确认', '取消'],
					btnclass: ['btn btn-primary', 'btn btn-danger'],
				}, function (index) {
					layer.close(index);
					d(true);
				});
			},
			// 弹窗详情
			dialog_detail : function(title, url, w, h) {
				if ($.common.isEmpty(title)) {
					title = false;
				}
				// 如果是移动端，就使用自适应大小弹窗
				var isFullOpen = false;
				var maxmin = true;
				if (navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i) && $.common.isEmpty(w)) {
					w = '100%';
					h = '100%';
					maxmin = false;
					isFullOpen = true;
				}
				if ($.common.isEmpty(url)) {
					url = "404.html";
				}
				if ($.common.isEmpty(w)) {
					w = '800';
				}
				if ($.common.isEmpty(h)) {
					h = ($(window).height() - 20);
				}
				if(w != '100%'){
					w = w + 'px';
				}
				if(h != '100%'){
					h = h + 'px';
				}
				var index = layer.open({
					id : 'layerForm',
					type : 2,
					area : [w, h],
					title : title,
					content : url,
					fix : false, // 不固定
					maxmin : maxmin,
					shadeClose : false,
					scrollbar: false, //屏蔽屏幕滚动条
					shade : 0.3,
					success : function(layero, index) {
					}
				});
				if (isFullOpen) {
					layer.full(index);
				}
			},
			dialog_open : function(title, url, w, h, callback) {
				if ($.common.isEmpty(title)) {
					title = false;
				}
				// 如果是移动端，就使用自适应大小弹窗
				var isFullOpen = false;
				var maxmin = true;
				if (navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i) && $.common.isEmpty(w)) {
					w = '100%';
					h = '100%';
					maxmin = false;
					isFullOpen = true;
				}
				if ($.common.isEmpty(url)) {
					url = "404.html";
				}
				if ($.common.isEmpty(w)) {
					w = '800';
				}
				if ($.common.isEmpty(h)) {
					h = ($(window).height() - 20);
				}
				if(w != '100%'){
					w = w + 'px';
				}
				if(h != '100%'){
					h = h + 'px';
				}
				if ($.common.isEmpty(callback)) {
					callback = function(index, layero) {
						var iframeWin = layero.find('iframe')[0];
						iframeWin.contentWindow.submitHandler(index,layero);
					}
				}
				var index = layer.open({
					id : 'layerForm',
					type : 2,
					area : [w, h],
					title : title,
					content : url,
					fix : false, // 不固定
					maxmin : maxmin,
					shadeClose : false,
					scrollbar: false, //屏蔽屏幕滚动条
					shade : 0.3,
					btn : [ '保存', '取消' ],
					yes : callback,
					cancel : function(index) {
						return true;
					}
				});
				if (isFullOpen) {
					layer.full(index);
				}
			},
			dialog_openFull : function(title, url, callback) {
				if ($.common.isEmpty(title)) {
					title = false;
				}
				if ($.common.isEmpty(url)) {
					url = "404.html";
				}
				if ($.common.isEmpty(callback)) {
					callback = function(index, layero) {
						var iframeWin = layero.find('iframe')[0];
						iframeWin.contentWindow.submitHandler(index, layero);
					}
				}
				var index = layer.open({
					id : 'layerForm',
					type : 2,
					area : [ '100%', '100%' ],
					title : title,
					content : url,
					fix : false, // 不固定
					maxmin : false,
					shadeClose : false,
					scrollbar: false, //屏蔽屏幕滚动条
					shade : 0.3,
					btn : [ '保存', '取消' ],
					yes : callback,
					cancel : function(index) {
						return true;
					}
				});
				layer.full(index);
			}
		},
		//日期处理
		datetimeset: {
			//date类型到字符串
			formatterDateTime : function (date) {
				var datetime = date.getFullYear()
					+ "-"// "年"
					+ ((date.getMonth() + 1) >= 10 ? (date.getMonth() + 1)
						: "0" + (date.getMonth() + 1))
					+ "-"// "月"
					+ (date.getDate() < 10 ? "0" + date.getDate() : date
						.getDate())
					+ " "
					+ (date.getHours() < 10 ? "0" + date.getHours() : date
						.getHours())
					+ ":"
					+ (date.getMinutes() < 10 ? "0" + date.getMinutes()
						: date.getMinutes())
					+ ":"
					+ (date.getSeconds() < 10 ? "0" + date.getSeconds()
						: date.getSeconds());
				return datetime;
			},
			//long类型转时间字符串
			longMsTimeConvertToDateTime : function (time) {
				var myDate = new Date(time);
				return $.datetimeset.formatterDateTime(myDate);
			},
			/*日期+*/
			addDate : function (date, days) {
				if (days == undefined || days == '') {
					days = 1;
				}
				var date = new Date(date);
				date.setDate(date.getDate() + days);
				var month = date.getMonth() + 1;
				var day = date.getDate();
				return date.getFullYear() + '-' + $.datetimeset.getFormatDate(month) + '-' + $.datetimeset.getFormatDate(day);
			},
			getFormatDate : function (arg) {
				if (arg == undefined || arg == '') {
					return '';
				}
				var re = arg + '';
				if (re.length < 2) {
					re = '0' + re;
				}
				return re;
			}
		}
	});
})(jQuery);