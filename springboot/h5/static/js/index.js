$(function(){

  localStorage.setItem("userInfo",'');
    var openId = localStorage.getItem("openId");
    if(openId == null || openId == ''){
      messageBox("获取个人信息失败");
      setTimeout(function(){
          loginFail('0'); // 一键登录
      },200);
      // return false;
    };

    $.ajax({
      type: "GET",
        url: baseUrl + "/rest/lkMyController/"+localStorage.getItem("openId"),
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        success: function(data){
          console.log(data);
          if(data.ok){
            if(data.data){
              let openId = localStorage.getItem("openId");
              localStorage.setItem("userInfo",JSON.stringify(data.data));
              // sessionStorage.setItem("userInfo",JSON.stringify(data.data));
              
            }
            // else{
            //   window.location="view/login.html";//跳转到绑定页面
            // }
          }else{
            $(".hb").css("display","block");
          }
          // else{
          //   window.location="view/login.html";//跳转到绑定页面
          //   // messageBox('数据加载失败1，请稍后再试')
          // }
        }
    });
    // 判断是否是新人
    // if(!localStorage.getItem("userInfo")){
      // let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      // if(!userInfo.tel){
        // $(".hb").css("display","block");
      // }
    // }
    // 立即领取新人红包
    $("#bhBtn").click(function(){
      // let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      messageBox('请先完成新人认证！');
      setTimeout(function(){
        window.location.href = 'view/xrrz.html?type=0';
      },1000);

      

    });
    driverApply(); //加入龙锟司机
    // 关闭新人红包
    $(".hb-gb").click(function(){
      $(".hb").css("display","none");
    }) 
    
    getBanner(openId); // 获取banner
    getCarListA(openId,"1"); //获取购车信息
    getCarListA(openId,"0");
    share(openId);
});

// 获取banner
function getBanner(openId) {
  $.ajax({  
    url: baseUrl + "/rest/lkBannerController/list/"+openId+"?pageNumber=1&pageSize=4",
    contentType:"application/json;charset=UTF-8",
    dataType: "json",
    success: function(data){
      console.log(data)
      if(data.ok){
        // $("#banner").attr("src",baseUrl+"/img/server/" + data.data[0].bnPic);
        if(data.data.length){
          let a = '';
          $("#banner").html(" ");
          for(let i=0;i<data.data.length;i++){
            a += '<div class="swiper-slide"><a href="javascript:void(0);">';
            a += '<img src="'+baseUrl+"/img/server/" + data.data[i].bnPic+'" /></a></div>';
          }
          $("#banner").append(a);
          var swiper = new Swiper('.swiper-container', {
            pagination: {
              el: '.swiper-pagination',
            },
            autoplay: {
              delay: 3000,
              stopOnLastSlide: false,
              disableOnInteraction: true,
            },
          });
        }
      }else{
        messageBox(data.message);
      }
    }
  });
}
//获取租车信息
function getCarListA(openId,type) {
  console.log("b")
  if(type == '0'){
    var saleType = "购车";
  }
  if(type == '1'){
    var saleType = "租车";
  }
  $.ajax({
    type: "GET",
    url: baseUrl + "/rest/lkCarMainlistController/list/"+openId+"?pageNumber=1&pageSize=4",
    contentType:"application/json;charset=UTF-8",
    data: {
      saleType: saleType,
    },
    dataType: "json",
    success: function(data){
        if(data.ok){
          console.log(data);
          var a ='';
          for(var i = 0;i<data.data.length;i++){
            a += '<div class="carDetail">';
            a += '<a href="view/carDetail.html?id='+data.data[i].id+'&type='+type+'">';
            a += '<div class="car-pic">';
            a += '<img src="'+baseUrl+'/img/server/'+data.data[i].carCover+'"  alt="汽车图片">';
            a += '</div>';
            a += '<div class="carName" >'+data.data[i].carName;
            if(type == '1'){
              a += '<span></span></div>';
              a += '<div class="carPrice"><text>出租:</text>'+data.data[i].rentalPrice+'</div>';
            }
            if(type == '0'){
              a += '</div>';
              a += '<div class="carPrice"><text>指导价:</text>'+data.data[i].rentalPrice+'</div>';
            }
            a += '</a>';
            a += '</div>';
          }
          if(type == '1'){
            $("#paixu").append(a);
          }
          if(type == '0'){
            $("#paixu-1").append(a);
          }
        }else{
          messageBox(data.message);
        }
    }
  });
}

// 加入龙锟司机
function driverApply(){
  console.log('c')
  $("#driverApply").click(function(){
    console.log('a')
    let openId = localStorage.getItem("openId");
    setTimeout(function(){
      window.location.href = 'view/driverApply.html?openId='+openId;
    },300);
  });
}

function share(openId){
  //定义分享字段内容
  var shareTitle='龙锟汽车';
  var shareDesc='【龙锟汽车】以“用户为中心”的汽车生活共享平台，为用户提供汽车租售、金融贷款、车后服务等生态链多元化服务。';
  var shareImgUrl='http://www.zhaodui.com.cn/lkqc/code/h5/static/images/1.jpg';
  // alert(url);
  // alert(encodeURIComponent(url));
  // var shareData = {
  //     contoUrl:encodeURIComponent(url)
  // }
  var shareData = {
      contoUrl:window.location.href
  }
  // console.log(shareData);
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