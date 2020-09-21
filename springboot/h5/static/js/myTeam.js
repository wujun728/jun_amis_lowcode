$(function(){
  var openId = localStorage.getItem("openId");
  var userInfoA = localStorage.getItem("userInfo")
  if(!userInfoA){ // 未完成新人认证
    $(".tips").css("display",'block');
    $("#grade").click(function(){
      messageBox('请先完成新人认证！');
      setTimeout(function(){
        window.location.href = 'xrrz.html?type=1';
      },1000);
    });
  }else{ //完成新人认证
    var userInfo = JSON.parse(userInfoA);
    $("#dengji").text(userInfo.type);
    if(userInfo.type == '初级会员'){
      $("#grade").attr('data-grade','1');
    }
    if(userInfo.type == '高级会员'){
      $("#grade").attr('data-grade','2');
    }
    if(userInfo.type == 'VIP会员'){
      // $("#grade").attr('data-grade','3');
      $("#grade").css("display","none");
    }
    // 提现
    $("#cashBtn").click(function(){
      var b = $(this).attr("data-p");
      if(b>0){
        window.location.href = 'chongzhi.html?type=2&b='+b;
      }else{
        messageBox('可提现金额不足');
      }
    });
    // 晋升
    $("#grade").click(function(){
      var a = $(this).attr('data-grade');
      window.location.href = 'chongzhi.html?type=1&a='+a;  // a 1为初级  2为高级 3为VIP
    });

    getMyZichan(openId);
    // 我的团队
    getFxhyList(openId);
    // 我的团队切换
    $(".wrap .item").click(function(){
      let flag = $(this).attr("data-flag");
      let idx = $(this).index();
      if(flag != 'true'){
        $(this).find('.box').css("display",'none');
        $(this).attr("data-flag","true");
      }else{
        $(this).find('.box').css("display",'block');
        $(this).attr("data-flag","false");
      }
      getFxhy(idx);
    });
    driverApply(); // 邀请好友成为司机
    fxdd(openId); // 分销订单
    $("#fxdda").click(function(){
      let flag = $(this).attr("data-flag");
      console.log(flag)
      if(flag != 'true'){
        $("#fxdd").css("display","none");
        $(this).attr("data-flag","true");
      }else{
        $("#fxdd").css("display","block");
        $(this).attr("data-flag","false");
      }
    });
  }
});


// 充值余额
function getMyZichan(openId){
  $.ajax({
      type: "GET",
      url: baseUrl + "/rest/lkWodezichanController/list/"+openId,
      contentType:"application/json;charset=UTF-8",
      dataType: "json",
      data: {
          pageNumber:'1',
          pageSize:'100'
      },
      success: function(data){
          if(data.ok){
              console.log(data.data)
              if(data.data.length){
                  $("#price").html(data.data[0].zc05);
                  $("#cashBtn").attr('data-p',data.data[0].zc05);
                  $("#A").html('¥'+data.data[0].zc05);
                  $("#B").html('¥'+data.data[0].zc05);
              }else{
                  $("#price").html('0.00');
                  $("#cashBtn").attr('data-p','0');
                  $("#A").html('¥0.00');
                  $("#B").html('¥0.00');
              }
          }else{
              messageBox('数据加载失败，请稍后再试')
          }
      }
  });
}
function getFxhyList(openId){
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
        if(data.data.length){
          $("#a").html('('+data.data.length+'人)');
          var a = '';
          var arr = [];
          var brr = [];
          var crr = [];
          for(let i=0;i<data.data.length;i++){
              a += '<div class="list">';
              a += '<div class="l">'+data.data[i].fxhy02+'</div>';
              a += '<div class="r">'+data.data[i].fxhy04+'</div>';
              a += '</div>';
              if(data.data[i].fxhy04 == '初级会员'){
                arr.push(data.data[i]);
              }
              if(data.data[i].fxhy04 == '高级会员'){
                brr.push(data.data[i]);
              }
              if(data.data[i].fxhy04 == '龙锟司机'){
                crr.push(data.data[i]);
              }
          }
          $("#team").html(' ').append(a);
          $("#chuji").html("初级会员 ("+arr.length+"人)");
          $("#gaoji").html("高级会员 ("+brr.length+"人)");
          $("#siji").html("龙锟司机 ("+brr.length+"人)");
          $("#arr").val(JSON.stringify(arr));
          $("#brr").val(JSON.stringify(brr));
          $("#crr").val(JSON.stringify(crr));
        }
      }else{
        messageBox(data.message);
      }
    }
  });
}

function getFxhy(idx){
  // idx 0 初级会员  1 高级会员
  
  // 
  // 
  if(idx == '0'){
    if($("#arr").val()){
      var arr = JSON.parse($("#arr").val());
      if(arr.length){
        console.log(arr)
        var a = '';
        for(let i=0;i<arr.length;i++){
          a += '<div class="list">';
          a += '<div class="l">'+arr[i].fxhy02+'</div>';
          a += '<div class="r">'+arr[i].fxhy04+'</div>';
          a += '</div>';
        }
        $("#chujiList").html(' ').append(a)
      }
    }else{
      $("#chujiList").html('<div class="no">暂无数据</div>');
    }
    
  }
  if(idx == '1'){
    if($("#brr").val()){
      var brr = JSON.parse($("#brr").val());
      if(brr.length){
        var a = '';
        for(let i=0;i<brr.length;i++){
          a += '<div class="list">';
          a += '<div class="l">'+brr[i].fxhy02+'</div>';
          a += '<div class="r">'+brr[i].fxhy04+'</div>';
          a += '</div>';
        }
        $("#gaojiList").html(' ').append(a)
      }
    }else{
      $("#gaojiList").html('<div class="no">暂无数据</div>');
    }
    
  }
  if(idx == '2'){
    if($("#crr").val()){
      var crr = JSON.parse($("#crr").val());
      if(crr.length){
        var a = '';
        for(let i=0;i<crr.length;i++){
          a += '<div class="list">';
          a += '<div class="l">'+crr[i].fxhy02+'</div>';
          a += '<div class="r">'+crr[i].fxhy04+'</div>';
          a += '</div>';
        }
        $("#sijiList").html(' ').append(a)
      }
    }else{
      $("#sijiList").html('<div class="no">暂无数据</div>');
    }
  }
}

// 加入龙锟司机
function driverApply(){
  console.log('c')
  $("#driverApply").click(function(){
    console.log('a')
    let openId = localStorage.getItem("openId");
    setTimeout(function(){
      window.location.href = 'driverApply.html?openId='+openId;
    },300);
  });
}
// 分销订单POST /rest/lkFxddController
function fxdd(openId){
  $.ajax({
    type: "GET",
    url: baseUrl + "/rest/lkFxddController/list/"+openId,
    contentType:"application/json;charset=UTF-8",
    dataType: "json",
    data: {
        pageNumber:'1',
        pageSize:'100'
    },
    success: function(data){
        if(data.ok){
            console.log(data.data)
            if(data.data.length){
              let a = ''
              for(let i=0;i<data.data.length;i++){
                a += '<div class="itema">';
                a += '<div class="l">'+data.data[i].fxdd02+'</div>';
                a += '<div class="r">'+data.data[i].fxdd02+'</div>';
                a += '</div>';

              }
              $("#fxdd").html('').append(a);
            }else{
                $("#fxdd").html('').append('<div class="no">暂无数据</div>');
            }
        }else{
            messageBox('数据加载失败，请稍后再试')
        }
    }
});
}