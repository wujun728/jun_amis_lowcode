var _w;
var _H;
var realWidth;
var realHeight; 

function refbackWihth(){
	_w = parseInt($(window).width()); //获取浏览器的宽度
	_H = parseInt($(window).height()); //获取浏览器的高度
	$(".background").css("width", _w).css("height", _H);
}

$(window).resize(function(){
	refbackWihth();
});

$(document).ready(function(){
	// 折叠面板功能
	var accordion = $("#stepForm").accordion();
	var current = 0;

	// 返回按钮不需要运行验证
	$("#registPage1 .prevbutton").click(function(){
		accordion.accordion("option", "active", 0);
		current = 0;
	});
	$("#registPage2  .prevbutton").click(function(){
		accordion.accordion("option", "active", 1);
		current = 1;
	});
	// 所有通过上面指定的目标重载的按钮都要运行验证
	$(".open2").click(function() {
	    accordion.accordion("option", "active", 2);
	    current = 2;
	});
	$("li .right").click(function() {
	    accordion.accordion("option", "active", 1);
	    current = 1;
	});
	$(".open0").click(function() {
	    accordion.accordion("option", "active", 0);
	    current = 0;
	});
	
	$(".background").css("background-image","url(../static/img/p2.jpg)");
	refbackWihth();
	
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	
	$('#stepForm').parent().addClass("fadeln");
    //1s后清除动画class，以便重复触发
    setTimeout(function(){
		$('#stepForm').parent().removeClass("fadeln");
	},1000);
	
});

