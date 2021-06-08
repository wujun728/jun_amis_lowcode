//全局配置
$.ajaxSetup({
	cache: false
});

//layer皮肤设置
layer.config({
	extend: 'moon/style.css',
	skin: 'layer-ext-moon'
});

//提示信息设置
toastr.options.timeOut = 1000;

/** 刷新选项卡 */
var refreshItem = function(){
	var topWindow = $(window.parent.document);
	var currentId = $('.page-tabs-content', topWindow).find('.active').attr('data-id');
	var target = $('.J_iframe[data-id="' + currentId + '"]', topWindow);
	var url = target.attr('src');
	target.attr('src', url).ready();
}

/** 关闭选项卡 */
var closeItem = function(dataId){
	var topWindow = $(window.parent.document);
	if(!$.common.isEmpty(dataId)){
		window.parent.$.modal.closeLoading();
		// 根据dataId关闭指定选项卡
		$('.J_menuTab[data-id="' + dataId + '"]', topWindow).remove();
		// 移除相应tab对应的内容区
		$('.J_mainContent .J_iframe[data-id="' + dataId + '"]', topWindow).remove();
		return;
	}
	var panelUrl = window.frameElement.getAttribute('data-panel');
	$('.page-tabs-content .active i', topWindow).click();
	if(!$.common.isEmpty(panelUrl)){
		$('.J_menuTab[data-id="' + panelUrl + '"]', topWindow).addClass('active').siblings('.J_menuTab').removeClass('active');
		$('.J_mainContent .J_iframe', topWindow).each(function() {
			if ($(this).data('id') == panelUrl) {
				$(this).show().siblings('.J_iframe').hide();
				return false;
			}
		});
	}
}

/** 创建选项卡 */
function createMenuItem(dataUrl, menuName) {
	var panelUrl = window.frameElement.getAttribute('data-id');
	dataIndex = Math.floor((Math.random() * 100) + 1);
	flag = true;
	if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
	var topWindow = $(window.parent.document);
	// 选项卡菜单已存在
	$('.J_menuTab', topWindow).each(function() {
		if ($(this).data('id') == dataUrl) {
			if (!$(this).hasClass('active')) {
				$(this).addClass('active').siblings('.J_menuTab').removeClass('active');
				$('.page-tabs-content').animate({ marginLeft: ""}, "fast");
				scrollToTab(this);
				// 显示tab对应的内容区
				$('.J_mainContent .J_iframe', topWindow).each(function() {
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
		var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '" data-panel="' + panelUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
		$('.J_menuTab', topWindow).removeClass('active');
		// 添加选项卡对应的iframe
		var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" data-panel="' + panelUrl + '" seamless></iframe>';
		$('.J_mainContent', topWindow).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);
		window.parent.$.modal.loading("数据加载中，请稍后...");
		$('.J_mainContent iframe:visible', topWindow).load(function () {
			window.parent.$.modal.closeLoading();
		});
		// 添加选项卡
		$('.J_menuTabs .page-tabs-content', topWindow).append(str);
		scrollToTab($('.J_menuTab.active', topWindow));
	}
	return false;
}

//滚动到指定选项卡
function scrollToTab(element) {
	var topWindow = $(window.parent.document);
	var marginLeftVal = calSumWidth($(element).prevAll()),
	marginRightVal = calSumWidth($(element).nextAll());
	// 可视区域非tab宽度
	var tabOuterWidth = calSumWidth($(".content-tabs", topWindow).children().not(".J_menuTabs"));
	//可视区域tab宽度
	var visibleWidth = $(".content-tabs", topWindow).outerWidth(true) - tabOuterWidth;
	//实际滚动宽度
	var scrollVal = 0;
	if ($(".page-tabs-content", topWindow).outerWidth() < visibleWidth) {
		scrollVal = 0;
	} else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
		if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
			scrollVal = marginLeftVal;
			var tabElement = element;
			while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content", topWindow).outerWidth() - visibleWidth)) {
				scrollVal -= $(tabElement).prev().outerWidth();
				tabElement = $(tabElement).prev();
			}
		}
	} else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
		scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
	}
	$('.page-tabs-content', topWindow).animate({ marginLeft: 0 - scrollVal + 'px' }, "fast");
}
//计算元素集合的总宽度
function calSumWidth(elements) {
	var width = 0;
	$(elements).each(function() {
		width += $(this).outerWidth(true);
	});
	return width;
}
//图片查看器
var initPhotoSwipeFromDOM = function(gallerySelector) {
	var parseThumbnailElements = function(el) {
		var thumbElements = el.childNodes,
			numNodes = thumbElements.length,
			items = [],
			figureEl,
			linkEl,
			size,
			item;
		for(var i = 0; i < numNodes; i++) {
			figureEl = thumbElements[i]; // <figure> element
			// include only element nodes 
			if(figureEl.nodeType !== 1) {
				continue;
			}
			linkEl = figureEl.children[0]; // <a> element
			var img = new Image();
			img.src = linkEl.getAttribute('href');
			linkEl.setAttribute('data-size', img.naturalWidth + 'x' + img.naturalHeight);
			size = linkEl.getAttribute('data-size').split('x');
			// create slide object
			item = {
				src: linkEl.getAttribute('href'),
				w: parseInt(size[0], 10),
				h: parseInt(size[1], 10)
			};
			if(figureEl.children.length > 1) {
				// <figcaption> content
				item.title = figureEl.children[1].innerHTML; 
			}
			if(linkEl.children.length > 0) {
				// <img> thumbnail element, retrieving thumbnail url
				item.msrc = linkEl.children[0].getAttribute('src');
			} 
			item.el = figureEl; // save link to element for getThumbBoundsFn
			items.push(item);
		}
		return items;
	};

	// find nearest parent element
	var closest = function closest(el, fn) {
		return el && ( fn(el) ? el : closest(el.parentNode, fn) );
	};

	// triggers when user clicks on thumbnail
	var onThumbnailsClick = function(e) {
		e = e || window.event;
		e.preventDefault ? e.preventDefault() : e.returnValue = false;
		var eTarget = e.target || e.srcElement;
		// find root element of slide
		var clickedListItem = closest(eTarget, function(el) {
			return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
		});
		if(!clickedListItem) {
			return;
		}
		// find index of clicked item by looping through all child nodes
		// alternatively, you may define index via data- attribute
		var clickedGallery = clickedListItem.parentNode,
			childNodes = clickedListItem.parentNode.childNodes,
			numChildNodes = childNodes.length,
			nodeIndex = 0,
			index;
		for (var i = 0; i < numChildNodes; i++) {
			if(childNodes[i].nodeType !== 1) { 
				continue; 
			}
			if(childNodes[i] === clickedListItem) {
				index = nodeIndex;
				break;
			}
			nodeIndex++;
		}
		if(index >= 0) {
			// open PhotoSwipe if valid index found
			openPhotoSwipe( index, clickedGallery );
		}
		return false;
	};

	// parse picture index and gallery index from URL (#&pid=1&gid=2)
	var photoswipeParseHash = function() {
		var hash = window.location.hash.substring(1),
		params = {};
		if(hash.length < 5) {
			return params;
		}
		var vars = hash.split('&');
		for (var i = 0; i < vars.length; i++) {
			if(!vars[i]) {
				continue;
			}
			var pair = vars[i].split('=');  
			if(pair.length < 2) {
				continue;
			}
			params[pair[0]] = pair[1];
		}
		if(params.gid) {
			params.gid = parseInt(params.gid, 10);
		}
		return params;
	};

	var openPhotoSwipe = function(index, galleryElement, disableAnimation, fromURL) {
		var pswpElement = document.querySelectorAll('.pswp')[0],
			gallery,
			options,
			items;
		items = parseThumbnailElements(galleryElement);
		// define options (if needed)
		options = {
			// define gallery index (for URL)
			galleryUID: galleryElement.getAttribute('data-pswp-uid'),
			getThumbBoundsFn: function(index) {
				// See Options -> getThumbBoundsFn section of documentation for more info
				var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
					pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
					rect = thumbnail.getBoundingClientRect(); 
				return {x:rect.left, y:rect.top + pageYScroll, w:rect.width};
			}
		};
		// PhotoSwipe opened from URL
		if(fromURL) {
			if(options.galleryPIDs) {
				// parse real index when custom PIDs are used 
				// http://photoswipe.com/documentation/faq.html#custom-pid-in-url
				for(var j = 0; j < items.length; j++) {
					if(items[j].pid == index) {
						options.index = j;
						break;
					}
				}
			} else {
				// in URL indexes start from 1
				options.index = parseInt(index, 10) - 1;
			}
		} else {
			options.index = parseInt(index, 10);
		}
		// exit if index not found
		if( isNaN(options.index) ) {
			return;
		}
		if(disableAnimation) {
			options.showAnimationDuration = 0;
		}
		// Pass data to PhotoSwipe and initialize it
		gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);
		gallery.init();
	};

	// loop through all gallery elements and bind events
	var galleryElements = document.querySelectorAll( gallerySelector );

	for(var i = 0, l = galleryElements.length; i < l; i++) {
		galleryElements[i].setAttribute('data-pswp-uid', i+1);
		galleryElements[i].onclick = onThumbnailsClick;
	}

	// Parse URL and open gallery if it contains #&pid=3&gid=1
	var hashData = photoswipeParseHash();
	if(hashData.pid && hashData.gid) {
		openPhotoSwipe( hashData.pid ,  galleryElements[ hashData.gid - 1 ], true, true );
	}
};

initPhotoSwipeFromDOM('.my-gallery');

//回到顶部绑定
$(function() {
	if ($.fn.toTop !== undefined) {
		$('#scroll-up').toTop();
	}
});

(function($) {
	'use strict';
	$.fn.toTop = function(opt) {
		var elem = this;
		var win = $(window);
		var doc = $('html, body');
		var options = $.extend({
			autohide : true,
			offset : 50,
			speed : 500,
			position : true,
			right : 15,
			bottom : 5
		}, opt);
		elem.css({
			'cursor' : 'pointer'
		});
		if (options.autohide) {
			elem.css('display', 'none');
		}
		if (options.position) {
			elem.css({
				'position' : 'fixed',
				'right' : options.right,
				'bottom' : options.bottom,
			});
		}
		elem.click(function() {
			doc.animate({
				scrollTop : 0
			}, options.speed);
		});
		win.scroll(function() {
			var scrolling = win.scrollTop();
			if (options.autohide) {
				if (scrolling > options.offset) {
					elem.fadeIn(options.speed);
				} else
					elem.fadeOut(options.speed);
			}
		});
	};
})(jQuery);