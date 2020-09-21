$(function(){
    var openId = localStorage.getItem("openId");
    var sjOpenId = getQueryString('openId'); // 上级的openid
    if(openId == null || openId == ''){
        messageBox("获取个人信息失败");
        setTimeout(function(){
            loginFail('1'); // 一键登录
        },200);
    }
    var userInfoStr = localStorage.getItem("userInfo");
    // var shareU = '';
    if(userInfoStr){
        let userInfo = JSON.parse(localStorage.getItem("userInfo"));
        let name = userInfo.name;
        let shareU = shareUrl+'?openId='+openId;
        console.log(sjOpenId);
        let searchUrl =window.location.href;
        let searchData =searchUrl.split("=");
        let sjName =decodeURI(searchData[2]);
        if(sjOpenId == '' || sjOpenId == 'null' || sjOpenId == null){
            console.log(sjOpenId);
        }else{
            if(sjName != name){
                $(".box").css("display","none");
                $(".boxA").css("border-radius",".1rem");
                localStorage.setItem("sjOpenId",sjOpenId);
                $("#shangji").html(sjName);
                // $(".bg, .sharea").css("display","block");
                // $(".closea").click(function(){
                //     $(".bg, .sharea").css("display","none");
                // });
                shareWrap(); // 关注公众号弹窗
            }else{
                console.log('2')
            }
        }
        // console.log(wi);
        getQrCode(shareU,name)
    }else{
        var sjOpenId = getQueryString('openId'); // 上级的openid
        let searchUrl =window.location.href;
        let searchData =searchUrl.split("=");
        let sjName =decodeURI(searchData[2]);
        let shareU = shareUrl+'?openId='+sjOpenId;
        getQrCode(shareU,sjName);
        $(".box").css("display","none");
        $(".boxA").css("border-radius",".1rem");
        localStorage.setItem("sjOpenId",sjOpenId);
        $("#shangji").html(sjName);
        $(".bg, .sharea").css("display","block");
        $(".closea").click(function(){
            $(".bg, .sharea").css("display","none");
        });
    }

    // 扫码邀请
    $("#smShare, #codeBoxA").click(function(){
        shareWrap(); // 关注公众号弹窗
        // $(".bg, .share").css("display","block");
        // let content = shareUrl+'?openId='+openId;
        // console.log(content)
		// let _w = $("#qrCode").width();
		// jQuery('#qrCode').qrcode({
		// 	render: "canvas",
		// 	width: _w,
		// 	height: _w,
		// 	text: content
		// });
    });
    // 关闭
    $(".gb").click(function(){
        $(".bg, .share").css("display","none");
    });
    // 微信邀请
    $("#wxShare").click(function(){
        $(".share-bg").css("display","block");
    });
    $(".share-bg").click(function(){
        $(".share-bg").css("display","none");
    })
    // 邀请记录
    getTeamList(openId);
    share(openId,escape(name));
});
function getQrCode(shareU,name){
    jQuery('#codeBoxA').qrcode({
        render: "canvas",
        width: '50',
        height: '50',
        text: shareU
    });
    $("#tjr").html(name);
}
// 关注公众弹窗
function shareWrap(){
    $(".bg, .sharea").css("display","block");
    $(".closea").click(function(){
        $(".bg, .sharea").css("display","none");
    });
}
function share(openId,name){
    //定义分享字段内容
    var shareTitle= '【龙锟汽车】您的好友邀请您拿红包';
    var shareDesc= '有车，有货，有钱赚，一起拿福利！以“用户为中心”的汽车生活共享平台，为用户提供汽车租售、金融贷款、车后服务等多元化服务。';
    var shareImgUrl='http://www.zhaodui.com.cn/lkqc/code/h5/static/images/1.jpg';
    var url = shareUrl+'?openId='+openId+"&name="+name;
    // alert(url);
    // alert(encodeURIComponent(url));
    // var shareData = {
    //     contoUrl:encodeURIComponent(url)
    // }
    var shareData = {
        contoUrl:window.location.href
        // contoUrl:window.location.href+'?openId='+openId+"&name="+name,
        // contoUrl:'http://www.zhaodui.com.cn/lkqc/code/h5/view/xrhb.html?openId='+openId+"&name="+name,
    }
    console.log(shareData);
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
// 邀请记录
function getTeamList(openId){
    $.ajax({ 
        url: baseUrl + "/rest/lkFxhyController/list/"+openId,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data: {
            pageNumber:'1',
            pageSize:'100'
        },
        success: function(data){
            console.log(data)
            if(data.ok){
                // $("#banner").attr("src",baseUrl+"/img/server/" + data.data[0].bnPic);
                if(data.data.length){
                //   $("#a").html('('+data.data.length+'人)');
                var a = '';
                for(let i=0;i<data.data.length;i++){
                    a += '<div class="item">';
                    a += '<div class="l">'+data.data[i].fxhy02+'</div>';
                    a += '<div class="r">'+data.data[i].fxhy04+'</div>';
                    a += '</div>';
                }
                $("#shareBox").html(' ').append(a)
                }else{
                    $("#shareBox").html(' ').append('<div class="no"><div class="icona"></div><p>暂无邀请记录</p></div>')
                }
            }else{
                messageBox(data.message);
            }
        }
    });
}
  