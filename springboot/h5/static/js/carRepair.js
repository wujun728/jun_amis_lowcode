$(function(){
    $("#location").click(function(){
		console.log('a')
        var shareData = {
            contoUrl:window.location.href
        }
        $.ajax({
            type: "POST",
            url: baseUrl+"/rest/tenPayController/getSignature/lkqc",
            contentType: "application/json",
            data: JSON.stringify(shareData),
            dataType: "json",
            success: function(data){
                var appId = 'wxb7f916883185fcbe';
                var timestamp = data.timestamp;
                var nonceStr = data.nonceStr;
                var signature = data.signature; 
                //var jsApiList =  ["updateAppMessageShareData", 'updateTimelineShareData', 'onMenuShareTimeline', 'onMenuShareAppMessage','getLocation','openLocation'];
                var jsApiList =  ['getLocation','openLocation'];
                // var jsApiList = ['openLocation','onMenuShareAppMessage','onMenuShareTimeline','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'];
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
                    wx.getLocation({
                        type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                        success : function(res) {
							console.log(res);
                            //使用微信内置地图查看位置接口
                            wx.openLocation({
                                latitude : 24.515080, // 纬度，浮点数，范围为90 ~ -90
                                longitude : 118.133310, // 经度，浮点数，范围为180 ~ -180。
                                name : '厦门昕华泰汽车修理厂', // 位置名
                                address : '厦门市湖里区枋湖工业小区73号', // 地址详情说明
                                scale : 28, // 地图缩放级别,整形值,范围从1~28。默认为最大
                                infoUrl : '' // 在查看位置界面底部显示的超链接,可点击跳转（测试好像不可用）
                            });
                        },
                        cancel : function(res) {}
                    });
                });
            }
        });
        
    });
});