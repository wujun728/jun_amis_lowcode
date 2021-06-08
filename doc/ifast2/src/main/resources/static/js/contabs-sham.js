let ConTabSham = (function($, window) {
	// debugger;
	// 计算元素集合的总宽度
	function calSumWidth(elements) {
		var width = 0;
		$(elements).each(function() {
			width += $(this).outerWidth(true);
		});
		return width;
	}
	// 滚动到指定选项卡
	function scrollToTab(element) {
		var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(element).nextAll());
		// 可视区域非tab宽度
		var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
		// 可视区域tab宽度
		var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
		// 实际滚动宽度
		var scrollVal = 0;
		if ($(".page-tabs-content").outerWidth() < visibleWidth) {
			scrollVal = 0;
		} else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
			if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
				scrollVal = marginLeftVal;
				var tabElement = element;
				while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
					scrollVal -= $(tabElement).prev().outerWidth();
					tabElement = $(tabElement).prev();
				}
			}
		} else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
			scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
		}
		$('.page-tabs-content').animate({
			marginLeft : 0 - scrollVal + 'px'
		}, "fast");
	}
	function menuItem(evt, that) {
		console.log('add menuItem..')

		let $this = $(that);
		// 获取标识数据

		var dataUrl = $this.attr('href') || $this.data('url'), dataIndex = $this.data('index'), menuName = $.trim($this
				.text()), flag = true;
		if (dataUrl == undefined || $.trim(dataUrl).length == 0)
			return false;

		console.log(dataUrl);
		// 选项卡菜单已存在
		debugger;
		$('.J_menuTab', parent.document).each(function() {
			if ($(this).data('id') == dataUrl) {
				if (!$(this).hasClass('active')) {
					$(this).addClass('active').siblings('.J_menuTab').removeClass('active');
					scrollToTab(this);
					// 显示tab对应的内容区
					$('.J_mainContent .J_iframe').each(function() {
						if ($(this).data('id') == dataUrl) {
							$(this).show().siblings('.J_iframe').hide();
							return false;
						}
					});
				}
				flag = false;
				return false;
			}
		});

		// 选项卡菜单不存在
		if (flag) {
			var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
			$('.J_menuTab', parent.document).removeClass('active');

			// 添加选项卡对应的iframe
			var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
			$('.J_mainContent', parent.document).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

			// 显示loading提示
			// var loading = layer.load();
			//
			// $('.J_mainContent iframe:visible').load(function () {
			// //iframe加载完成后隐藏loading提示
			// layer.close(loading);
			// });
			// 添加选项卡
			$('.J_menuTabs .page-tabs-content', parent.document).append(str);
			scrollToTab($('.J_menuTab.active', parent.document));
		}
		evt.preventDefault();
		return false;
	}

	return {
		add : menuItem
	};
})(jQuery, window);
