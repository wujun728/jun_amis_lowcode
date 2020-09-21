$(function(){
	var openId = localStorage.getItem("openId");
	var sjOpenId = getQueryString('openId');
	if(sjOpenId == '' || sjOpenId == 'null' || sjOpenId == null){
        console.log(sjOpenId);
    }else{
		if(sjOpenId != openId){
			localStorage.setItem("sjOpenId",sjOpenId);
			shareWrap();
		}
		
    }
	$.ajax({
		type: "GET",
		url: baseUrl + "/rest/lkDriverRecruitmentController/list/"+openId+"?pageNumber=1&pageSize=100",
		contentType:"application/json;charset=UTF-8",
		success: function(data){
			let e = JSON.parse(data);
			if(e.ok){ 
				if(e.data.length){ // 已申请
					$("#btnTwo").css("display","flex");
				}else{ // 未申请
					$("#btnOne").css("display","flex");
					creatDriver(); // 司机加入申请
				}
			}else{
				console.log('数据加载失败...')
			}
		}
	});
	share(openId); // 龙昆司机分享
	$("#btnThr").click(function(){
		$(".pop").css("display",'block');
	});
	$(".pop").click(function(){
		$(".pop").css("display",'none');
	});
	$("#btnThred").click(function(){
		$(".pop").css("display",'block');
	});
	$(".pop").click(function(){
		$(".pop").css("display",'none');
	});
});

function creatDriver(openId){
	// 同意协议
	$(".agree").click(function(){
		let flag = $(this).hasClass("active");
		if(flag){
			$(this).removeClass("active");
		}else{
			$(this).addClass("active");
		}
	});
	// 提交申请
	$("#btnOne .btn").click(function(){
		var openId = localStorage.getItem("openId");
		let inputName = $("#inputName").val();
		let inputTel = $("#inputTel").val();
		let inputCity = $("#inputCity").val();
		let type = $('input:radio[name="carType"]:checked').val();
		let flag = $(".agree").hasClass("active");
		var sjOpenId = getQueryString('openId');
		if(inputName == '' || inputName == null){
			messageBox("请输入您的名字");
			return false;
		}
		if(inputTel == '' || inputTel == null){
			messageBox("请填写您的手机号码");
			return false;
		}
		if(inputCity == '' || inputCity == null){
			messageBox("请填写城市");
			return false;
		}
		if(type == '' || type == null){
			messageBox("请选择您的加盟方式");
			return false;
		}
		if(!flag){
			messageBox("请同意加入");
			return false;
		}
		let driverData = {
			applicationCity: inputCity,
			applicationName: inputName,
			applicationOpenid: openId,
			applicationTel: inputTel,
			lkSpare1: type,
			wechatNo: openId
		}
		console.log(driverData);
		$.ajax({
			type: "POST",
			url: baseUrl + "/rest/lkDriverRecruitmentController",
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify(driverData),
			success: function(data){
				let e = JSON.parse(data);
				if(e.ok){
					
					if(sjOpenId == '' || sjOpenId == 'null' || sjOpenId == null){
						messageBox("提交成功！");
						setTimeout(function(){
							window.location.href = 'shenhe.html';
						},1000);
					}else{
						localStorage.getItem("sjOpenId",sjOpenId);
						creatFxhy(openId,inputName,inputTel,sjOpenId)
					}
					
				}else{
					messageBox("申请失败，请稍后再试！");
				}
			}
		});
	});	
}
// 关注公众弹窗
function shareWrap(){
    $(".bg, .sharea").css("display","block");
    $(".closea").click(function(){
        $(".bg, .sharea").css("display","none");
    });
}
function creatFxhy(openId,name,tel,sjOpenId){
    let fxhyDate = {
        fxhy01: openId,
        fxhy02: name,
        fxhy03: tel,
        fxhy04: '龙锟司机',
        fxhy05: sjOpenId,
        fxhy06: '',
        fxhy07: '',
    }
    $.ajax({
        type: "POST",
        url: baseUrl + "/rest/lkFxhyController",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(fxhyDate),
        success: function(data){
            console.log(data);
            let e = JSON.parse(data);
            if(e.ok){
                setTimeout(function(){
                    window.location.href = 'shenhe.html';
                },1000);
            }else{
                messageBox(e.message);
            }
            
        }
    });
}
function share(openId){
	//定义分享字段内容
	var shareTitle='【龙锟汽车】您的好友推荐您加入龙锟司机';
	var shareDesc='有车，有货，有钱赚，一起拿福利！以“用户为中心”的汽车生活共享平台，为用户提供汽车租售、金融贷款、车后服务等多元化服务。';
	var shareImgUrl='http://www.zhaodui.com.cn/lkqc/code/h5/static/images/1.jpg';
	var url = shareUrl1+'?openId='+openId;
	// alert(url);
	// alert(encodeURIComponent(url));
	// var shareData = {
	//     contoUrl:encodeURIComponent(url)
	// }
	var shareData = {
		contoUrl:window.location.href
	}
	console.log(shareData);
	console.log(url);
	$.ajax({
		type: "POST",
		url: baseUrl+"/rest/tenPayController/getSignature/lkqc",
		contentType: "application/json",
		data: JSON.stringify(shareData),
		dataType: "json",
		success: function (data) {
			var appId = 'wxb7f916883185fcbe';
			var timestamp = data.timestamp;
			var nonceStr = data.nonceStr;
			var signature = data.signature; 
			var jsApiList =  ["updateAppMessageShareData", 'updateTimelineShareData', 'onMenuShareTimeline', 'onMenuShareAppMessage'];
			// var jsApiList = ['onMenuShareAppMessage','onMenuShareTimeline','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'];
			var testUrl = data.url;
			
			wx.config({
				debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId: 'wxb7f916883185fcbe', 
				timestamp:timestamp, // 必填，生成签名的时间戳
				nonceStr: nonceStr, // 必填，生成签名的随机串
				signature: signature,// 必填，签名，见附录1
				jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
	
			wx.ready(function(){	
				wx.checkJsApi({
					jsApiList :  ["updateAppMessageShareData", 'updateTimelineShareData', 'onMenuShareTimeline', 'onMenuShareAppMessage'],
					success : function(res) {
						// 以键值对的形式返回，可用的api值true，不可用为false
						// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
						// alert(res.checkResult);
						// alert(res.errMsg);
					}
				});
				// 分享朋友圈
				wx.onMenuShareTimeline({ 
					title: shareTitle, 
					desc: shareDesc,
					link: testUrl,
					imgUrl: shareImgUrl, 
					success: function () {
						console.log("设置回调成功1")
					}
				});
				// 分享朋友
				wx.onMenuShareAppMessage({ 
					title: shareTitle, 
					desc: shareDesc,
					link: testUrl,
					imgUrl: shareImgUrl, 
					success: function () {
						console.log("设置回调成功2")
					}
				});
				/*
				wx.updateAppMessageShareData({ 
					title: shareTitle, // 分享标题
					desc: shareDesc, // 分享描述
					link: testUrl,
					imgUrl: shareImgUrl, // 分享图标
					success: function () {
						//设置成功
						console.log("设置分享给朋友回调成功")
					},
					fail:function(res){
						console.log("设置分享给朋友回调失败，接口调用次数："+idNumber+",失败原因："+JSON.stringify(res))
					}
				});
				
				wx.updateTimelineShareData({ 
					title: shareTitle, // 分享标题
					desc: shareDesc, // 分享描述
					link: testUrl,
					imgUrl: shareImgUrl, // 分享图标
					success: function () {
						//设置成功
						console.log("设置分享到朋友圈回调成功,")
					},
					fail:function(res){
							
					}
				});
  
				*/
				
			});
			wx.error(function(data){
				var resStr = "签名信息验证失败:"+JSON.stringify(data);		
				console.log(resStr)			  
			});	
			
		}
	});
	
}