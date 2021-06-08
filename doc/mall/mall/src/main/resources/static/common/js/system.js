/**
 * 通用js方法封装处理
 */
(function($) {
	$.extend({
		// 弹出层封装处理
		modal : {
			dialog_detail : function(title, url, w, h) {
				if ($.common.isEmpty(title)) {
					title = false;
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
				// 如果是移动端，就使用自适应大小弹窗
				if (navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i)) {
					w = '100%';
					h = '100%';
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
			},
			dialog_open : function(title, url, w, h, callback) {
				if ($.common.isEmpty(title)) {
					title = false;
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
				// 如果是移动端，就使用自适应大小弹窗
				if (navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i)) {
					w = '100%';
					h = '100%';
				} else {
					w = w + 'px';
					h = h + 'px';
				}
				if ($.common.isEmpty(callback)) {
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
			},
			// 显示图标
			icon : function(type) {
				var icon = "";
				if (type == "warning") {
					icon = 0;
				} else if (type == "success") {
					icon = 1;
				} else if (type == "error") {
					icon = 2;
				} else {
					icon = 3;
				}
				return icon;
			},
			// 消息提示
			msg : function(content, type) {
				if (type != undefined) {
					layer.msg(content, {
						icon : $.modal.icon(type),
						time : 1000,
						shift : 5
					});
				} else {
					layer.msg(content);
				}
			},
			// 错误消息
			msgError : function(content) {
				$.modal.msg(content, "error");
			},
			// 成功消息
			msgSuccess : function(content) {
				$.modal.msg(content, "success");
			},
			// 警告消息
			msgWarning : function(content) {
				$.modal.msg(content, "warning");
			},
			// 弹出提示
			alert : function(content, type) {
				layer.alert(content, {
					icon : $.modal.icon(type),
					title : "系统提示",
					btn : [ '确认' ],
					btnclass : [ 'btn btn-primary' ],
				});
			},
			// 消息提示并刷新父窗体
			msgReload : function(msg, type) {
				layer.msg(msg, {
					icon : $.modal.icon(type),
					time : 500,
					shade : [ 0.1, '#8F8F8F' ]
				}, function() {
					$.modal.reload();
				});
			},
			// 错误提示
			alertError : function(content) {
				$.modal.alert(content, "error");
			},
			// 成功提示
			alertSuccess : function(content) {
				$.modal.alert(content, "success");
			},
			// 警告提示
			alertWarning : function(content) {
				$.modal.alert(content, "warning");
			},
			// 关闭窗体
			close : function() {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			// 关闭全部窗体
			closeAll : function() {
				layer.closeAll();
			},
			// 确认窗体
			confirm : function(content, callBack) {
				layer.confirm(content, {
					icon : 3,
					title : "系统提示",
					btn : [ '确认', '取消' ]
				}, function(index) {
					layer.close(index);
					callBack(true);
				});
			},
			// 禁用按钮
			disable: function() {
				var doc = window.top == window.parent ? window.document : window.parent.document;
				$("a[class*=layui-layer-btn]", doc).addClass("layer-disabled");
			},
			// 启用按钮
			enable: function() {
				var doc = window.top == window.parent ? window.document : window.parent.document;
				$("a[class*=layui-layer-btn]", doc).removeClass("layer-disabled");
			},
			// 打开遮罩层
			loading : function(message) {
				$.blockUI({
					message : '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>'
				});
			},
			// 关闭遮罩层
			closeLoading : function() {
				setTimeout(function() {
					$.unblockUI();
				}, 100);
			},
			// 重新加载
			reload : function() {
				parent.location.reload();
			}
		},
		// 校验封装处理
		validate : {
			// 判断返回标识是否唯一 false 不存在 true 存在
			unique : function(value) {
				if (value == "0") {
					return true;
				}
				return false;
			}
		},
		// 树插件封装处理
		tree : {
			_option : {},
			_lastValue : {},
			// 初始化树结构
			init : function(options) {
				var defaults = {
					id : "tree", // 属性ID
					expandLevel : 0, // 展开等级节点
					view : {
						selectedMulti : false, // 设置是否允许同时选中多个节点
						nameIsHTML : true // 设置 name 属性是否支持 HTML 脚本
					},
					check : {
						enable : false, // 置 zTree 的节点上是否显示 checkbox
						nocheckInherit : true, // 设置子节点是否自动继承
					},
					data : {
						key : {
							title : "title" // 节点数据保存节点提示信息的属性名称
						},
						simpleData : {
							enable : true // true / false 分别表示 使用 / 不使用 简单数据模式
						}
					},
				};
				var options = $.extend(defaults, options);
				$.tree._option = options;
				// 树结构初始化加载
				var setting = {
					callback : {
						onClick : options.onClick, // 用于捕获节点被点击的事件回调函数
						onCheck : options.onCheck, // 用于捕获 checkbox /
						onDblClick : options.onDblClick // 用于捕获鼠标双击之后的事件回调函数
					},
					check : options.check,
					view : options.view,
					data : options.data
				};
				$.get(options.url,
					function(data) {
						var treeId = $("#treeId").val();
						tree = $.fn.zTree.init($("#" + options.id), setting, data);
						$._tree = tree;
						var nodes = tree.getNodesByParam("level", options.expandLevel - 1);
						for (var i = 0; i < nodes.length; i++) {
							tree.expandNode(nodes[i], true, false, false);
						}
						var node = tree.getNodesByParam("id", treeId, null)[0];
						$.tree.selectByIdName(treeId, node);
					}
				);
			},
			// 搜索节点
			searchNode : function() {
				// 取得输入的关键字的值
				var value = $.common.trim($("#keyword").val());
				if ($.tree._lastValue == value) {
					return;
				}
				// 保存最后一次搜索名称
				$.tree._lastValue = value;
				var nodes = $._tree.getNodes();
				// 如果要查空字串，就退出不查了。
				if (value == "") {
					$.tree.showAllNode(nodes);
					return;
				}
				$.tree.hideAllNode(nodes);
				// 根据搜索值模糊匹配
				$.tree.updateNodes($._tree.getNodesByParamFuzzy("name", value));
			},
			// 根据Id和Name选中指定节点
			selectByIdName : function(treeId, node) {
				if (!$.common.isEmpty(treeId) && treeId == node.id) {
					$._tree.selectNode(node, true);
				}
			},
			// 显示所有节点
			showAllNode : function(nodes) {
				nodes = $._tree.transformToArray(nodes);
				for (var i = nodes.length - 1; i >= 0; i--) {
					if (nodes[i].getParentNode() != null) {
						$._tree.expandNode(nodes[i], true, false, false, false);
					} else {
						$._tree.expandNode(nodes[i], true, true, false, false);
					}
					$._tree.showNode(nodes[i]);
					$.tree.showAllNode(nodes[i].children);
				}
			},
			// 隐藏所有节点
			hideAllNode : function(nodes) {
				var tree = $.fn.zTree.getZTreeObj("tree");
				var nodes = $._tree.transformToArray(nodes);
				for (var i = nodes.length - 1; i >= 0; i--) {
					$._tree.hideNode(nodes[i]);
				}
			},
			// 显示所有父节点
			showParent : function(treeNode) {
				var parentNode;
				while ((parentNode = treeNode.getParentNode()) != null) {
					$._tree.showNode(parentNode);
					$._tree.expandNode(parentNode, true, false, false);
					treeNode = parentNode;
				}
			},
			// 显示所有孩子节点
			showChildren : function(treeNode) {
				if (treeNode.isParent) {
					for ( var idx in treeNode.children) {
						var node = treeNode.children[idx];
						$._tree.showNode(node);
						$.tree.showChildren(node);
					}
				}
			},
			// 更新节点状态
			updateNodes : function(nodeList) {
				$._tree.showNodes(nodeList);
				for (var i = 0, l = nodeList.length; i < l; i++) {
					var treeNode = nodeList[i];
					$.tree.showChildren(treeNode);
					$.tree.showParent(treeNode)
				}
			},
			// 获取当前被勾选集合
			getCheckedNodes : function(column) {
				var _column = $.common.isEmpty(column) ? "id" : column;
				var nodes = $._tree.getCheckedNodes(true);
				return $.map(nodes, function(row) {
					return row[_column];
				}).join();
			},
			// 不允许根父节点选择
			notAllowParents : function(_tree) {
				var nodes = _tree.getSelectedNodes();
				for (var i = 0; i < nodes.length; i++) {
					if (nodes[i].level == 0) {
						$.modal.msgError("不能选择根节点（" + nodes[i].name + "）");
						return false;
					}
					if (nodes[i].isParent) {
						$.modal.msgError("不能选择父节点（" + nodes[i].name + "）");
						return false;
					}
				}
				return true;
			},
			// 不允许最后层级节点选择
			notAllowLastLevel : function(_tree) {
				var nodes = _tree.getSelectedNodes();
				for (var i = 0; i < nodes.length; i++) {
					if (!nodes[i].isParent) {
						$.modal.msgError("不能选择最后层级节点（" + nodes[i].name + "）");
						return false;
					}
				}
				return true;
			},
			// 隐藏/显示搜索栏
			toggleSearch : function() {
				$('#search').slideToggle(200);
				$('#btnShow').toggle();
				$('#btnHide').toggle();
				$('#keyword').focus();
			},
			// 折叠
			collapse : function() {
				$._tree.expandAll(false);
			},
			// 展开
			expand : function() {
				$._tree.expandAll(true);
			}
		},
		// 校验封装处理
		table : {
			// datatable初始化封装
			init : function(options) {
				var defaults = {
					language : {
						emptyTable : "没有查询到数据",
						zeroRecords : "没有查询到数据",
						lengthMenu : "每页 _MENU_ 个",
						infoEmpty : '显示第 0 至 0 项记录，共 0 项',
						infoFiltered : '',
						info : '',
						loadingRecords : "正在努力加载数据中，请稍后......",
						paginate : {
							first : "首页",
							previous : "<",
							next : ">",
							last : "末页"
						},
					},
					searching : false,
					processing : true,
					serverSide : true,
					responsive : true,
					bAutoWidth : false, // 是否非自动宽度
					bSort : false,
					lengthMenu : [ 10, 30, 50, 100 ],
					order : [],
					pageLength : 10,
					displayStart : 0,
					dom : "<'row'<'col-sm-12'tr>>\t\t\t<'row'<'col-sm-6 'l><'col-sm-6 dataTables_pager 'p>>",
					headerCallback : function(e, a, t, n, s) {
						$(e.getElementsByTagName("th")).parent().css("background-color", "#f7f7fa");
					},
					drawCallback : function() {
						// datatable 渲染复选框
						$('input[type=checkbox]').iCheck({
							checkboxClass : 'icheckbox_minimal-blue',
						});
						// datatable 渲染图片查看
					    $('.magnific-image-single').magnificPopup({
							type:'image',
						});
					}
				};
				// 合并表格设置参数
				var options = $.extend(defaults, options);
				var table = $('#' + options.id);
				// 渲染表格
				data_oTable = table.on('xhr.dt', function(e, settings, json) {
				}).DataTable({
					language : options.language,
					searching : options.searching,
					serverSide : options.serverSide,
					ajax : options.ajax,
					responsive : options.responsive,
					bAutoWidth : options.bAutoWidth, // 是否非自动宽度
					bSort : options.bSort,
					lengthMenu : options.lengthMenu,
					order : options.order,
					pageLength : options.pageLength,
					displayStart : options.displayStart,
					dom : options.dom,
					headerCallback : options.headerCallback,
					columnDefs : options.columnDefs,
					drawCallback : options.drawCallback,
				});
				table.on("ifChecked ifUnchecked", ".m-group-checkable", function() { // 选择框监听
					var e = $(this).closest("table").find("td:first-child .m-checkable");
					var a = $(this).is(":checked");
					$(e).each(function() {
						a ? $(this).iCheck("check") : $(this).iCheck("uncheck")
					});
				});
				table.on("ifChecked ifUnchecked", ".m-checkable", function() { // 选择框监听
					var checkAll = $('input.m-group-checkable'); // 全选的input
					var checkboxs = $('input.m-checkable'); // 所有单选的input
					if (checkboxs.filter(':checked').length == checkboxs.length) {
						checkAll.prop('checked', true);
					} else {
						checkAll.prop('checked', false);
					}
					checkAll.iCheck('update');
				});
				table.on("click", "*[delete-url]", function() {
					var deleteUrl = $(this).attr('delete-url');
					var id = $(this).attr('data-id');
					$.modal.confirm('您确定要删除该条记录吗?', function(index) {
						$.post(deleteUrl, {
							"id" : id
						}, function(json) {
							if (json.code == 200) {
								data_oTable.draw(false);
								$.modal.msgSuccess("删除成功");
							} else {
								$.modal.msgWarning(json.msg);
							}
						});
					});
				});
				return data_oTable;
			},
			removeAll : function(dataUrl, callback) {
				$.modal.confirm('您确定要清空记录吗？', function(index) {
					$.post(dataUrl, {}, function(json) {
						if (json.code == 200) {
							callback(true);
						} else {
							$.modal.msgWarning(json.msg);
						}
					});
				});
			},
			// 图片预览
			imageView : function(value, height, width, target) {
				if ($.common.isEmpty(width)) {
					width = 'auto';
				}
				if ($.common.isEmpty(height)) {
					height = 'auto';
				}
				// blank or self
				var _target = $.common.isEmpty(target) ? 'self' : target;
				if (!$.common.isEmpty(value)) {
					var	strhtml =  '<a class="magnific-image-single" href="%s">'
						strhtml += '	<img src="%s" class="img-circle img-xs"/>'
						strhtml += '</a>'
					return $.common.sprintf(strhtml, value, value);
				} else {
					return $.common.nullToStr(value);
				}
			},
			// 图片预览
			imageSquareView : function(value, height, width, target) {
				if ($.common.isEmpty(width)) {
					width = 'auto';
				}
				if ($.common.isEmpty(height)) {
					height = 'auto';
				}
				// blank or self
				var _target = $.common.isEmpty(target) ? 'self' : target;
				if (!$.common.isEmpty(value)) {
					var strhtml  = '<a class="magnific-image-single" href="%s">'
						strhtml += '	<img src="%s" class="img-square img-md"/>'
						strhtml += '</a>'
					return $.common.sprintf(strhtml, value, value);
				} else {
					return $.common.nullToStr(value);
				}
			},
		},
		// 通用方法封装处理
		common : {
			// 判断字符串是否为空
			isEmpty : function(value) {
				if (value == null || this.trim(value) == "") {
					return true;
				}
				return false;
			},
			// 空对象转字符串
			nullToStr : function(value) {
				if ($.common.isEmpty(value)) {
					return "-";
				}
				return value;
			},
			// 是否显示数据 为空默认为显示
			visible : function(value) {
				if ($.common.isEmpty(value) || value == true) {
					return true;
				}
				return false;
			},
			// 空格截取
			trim : function(value) {
				if (value == null) {
					return "";
				}
				return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
			},
			// 比较两个字符串（大小写敏感）
			equals : function(str, that) {
				return str == that;
			},
			// 比较两个字符串（大小写不敏感）
			equalsIgnoreCase : function(str, that) {
				return String(str).toUpperCase() === String(that)
						.toUpperCase();
			},
			// 将字符串按指定字符分割
			split : function(str, sep, maxLen) {
				if ($.common.isEmpty(str)) {
					return null;
				}
				var value = String(str).split(sep);
				return maxLen ? value.slice(0, maxLen - 1) : value;
			},
			// 字符串格式化(%s )
			sprintf : function(str) {
				var args = arguments, flag = true, i = 1;
				str = str.replace(/%s/g, function() {
					var arg = args[i++];
					if (typeof arg === 'undefined') {
						flag = false;
						return '';
					}
					return arg;
				});
				return flag ? str : '';
			},
			// 指定随机数返回
			random : function(min, max) {
				return Math.floor((Math.random() * max) + min);
			},
			// 判断字符串是否是以start开头
			startWith : function(value, start) {
				var reg = new RegExp("^" + start);
				return reg.test(value)
			},
			// 判断字符串是否是以end结尾
			endWith : function(value, end) {
				var reg = new RegExp(end + "$");
				return reg.test(value)
			},
			// 数组去重
			uniqueFn : function(array) {
				var result = [];
				var hashObj = {};
				for (var i = 0; i < array.length; i++) {
					if (!hashObj[array[i]]) {
						hashObj[array[i]] = true;
						result.push(array[i]);
					}
				}
				return result;
			},
			// 数组中的所有元素放入一个字符串
			join : function(array, separator) {
				if ($.common.isEmpty(array)) {
					return null;
				}
				return array.join(separator);
			},
			// 获取form下所有的字段并转换为json对象
			formToJSON : function(formId) {
				var json = {};
				$.each($("#" + formId).serializeArray(), function(i,
						field) {
					json[field.name] = field.value;
				});
				return json;
			}
		}
	});
})(jQuery);