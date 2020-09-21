$(function(){
    var id = getQueryString("id");
    var type = getQueryString("type");
    var userName = localStorage.getItem("openId");
    let carCover = getQueryString("carCover");
    if(type == '0'){
      $("#boxA").css("display","block");
      $("#boxB").css("display","none");
      chooseCase(type,userName);
      $("#btnA").click(function(){
        $(".tel").css("display","block");
      });
      suc('0',id,carCover,userName);
    }
    if(type == '1'){
      $("#boxA").css("display","none");
      $("#boxB").css("display","block");
      chooseCase(type,userName);
      $("#btnB").click(function(){
        $(".tel").css("display","block");
      });
      suc('1',id,carCover,userName);
    }
   
});

function chooseCase(type,uesername) {
  let carNo = getQueryString("carNo");
  let carCover = getQueryString("carCover");
  let planGroup = "";
  if(type == '0'){
    planGroup = "租车服务";
  }
  if(type == '1'){
    planGroup = "购车服务";
  }
  $.ajax({
    type: "GET",
    url: baseUrl + "/rest/lkFinancialPlanController/list/"+uesername,
    contentType:"application/json;charset=UTF-8",
    data: {
      planGroup: planGroup,
      pageNumber: "1",
      pageSize: "10",
      carNo:carNo
    },
    dataType: "json",
    success: function(data){
      if(data.ok){
        if(data.data.length){
          console.log(data);
          $("#caseA").html(" ");
          $("#caseB").html(" ");
          $("#carName").val(data.data[0].carName);
          let a = '';
          for(let i=0;i<data.data.length;i++){
            if(i == '0'){
              a += '<div class="item active" data-a="'+data.data[i].firstRent+'" data-b="'+data.data[i].monthlyRent+'" data-c="'+data.data[i].rentData+'">';
            }else{
              a += '<div class="item" data-a="'+data.data[i].firstRent+'" data-b="'+data.data[i].monthlyRent+'" data-c="'+data.data[i].rentData+'">';
            }
            if(type == '1'){
              // a += '<div class="a">';
              // a += '<div class="txt-a">首付</div>';
              // a += '<div class="txt-b">'+data.data[i].rentData+'期</div>';
              // a += '</div>';
              // a += '<div class="a">';
              // a += '<div class="txt-a">月供</div>';
              // a += '<div class="txt-b">'+data.data[i].monthlyRent+'元</div>';
              // a += '</div>';
              a += '<div class="a">';
              a += '<div class="txt-a">首付</div>';
              a += '<div class="txt-b">'+data.data[i].firstRent+'元</div>';
              a += '</div>';
              a += '<div class="a">';
              a += '<div class="txt-a">月供</div>';
              a += '<div class="txt-b">'+data.data[i].monthlyRent+'元</div>';
              a += '</div>';
              a += '<div class="a">';
              a += '<div class="txt-a">期数</div>';
              a += '<div class="txt-b">'+data.data[i].rentData+'期</div>';
              a += '</div>';
            }
            if(type == '0'){
              a += '<div class="a">';
              a += '<div class="txt-a">租期</div>';
              a += '<div class="txt-b">'+data.data[i].rentData+'期</div>';
              a += '</div>';
              a += '<div class="a">';
              a += '<div class="txt-a">月租</div>';
              a += '<div class="txt-b">'+data.data[i].monthlyRent+'元</div>';
              a += '</div>';
              a += '<div class="a">';
              a += '<div class="txt-a">押金</div>';
              a += '<div class="txt-b">'+data.data[i].firstRent+'元</div>';
              a += '</div>';
            }
            // a += '<div class="a">';
            // a += '<div class="txt-a">租期</div>';
            // a += '<div class="txt-b">'+data.data[i].rentData+'期</div>';
            // a += '</div>';
            // a += '<div class="a">';
            // a += '<div class="txt-a">月租</div>';
            // a += '<div class="txt-b">'+data.data[i].monthlyRent+'元</div>';
            // a += '</div>';
            // a += '<div class="a">';
            // if(type == '0'){
            //   a += '<div class="txt-a">押金</div>';
            // }
            // if(type == '1'){
            //   a += '<div class="txt-a">首租</div>';
            // }
            // a += '<div class="txt-b">'+data.data[i].firstRent+'元</div>';
            // a += '</div>';
            
            
            a += '<div class="icon"></div>';
            a += '</div>';
          }
          if(type == '0'){
            $("#caseA").append(a);
          }
          if(type == '1'){
            $("#caseB").append(a);
          }
          $(".fangan>.item").click(function(){
            $(this).addClass('active').siblings().removeClass('active');
          });
        }else{
          console.log("暂无相关信息...");
        }
      }else{
        console.log("数据加载失败...");
      }
    }
  });
}
function suc(type,id,carCover,openId){
  $(".success").click(function(){
    if(type == '0'){
      var data = '';
      var name = $("#name").val();
      var sex = $(':radio[name="sex"]:checked').val();
      var sty = $(':radio[name="yuyue-sty"]:checked').val();
      var tel = $("#tel").val();
      var smsCode = $("#code1").val();
      if(name == '' || name == null){
        messageBox('请输入姓名');
        return false;
      }
      if(sex == '' || sex == null){
        messageBox('请选择性别');
        return false;
      }
      if(sty == '' || sty == null){
        messageBox('请选择自用或营运');
        return false;
      }
      if(tel == '' || tel == null){
        messageBox('请输入手机号');
        return false;
      }
      if(smsCode == '' || smsCode == null){
        messageBox('请输入短信验证码');
        return false;
      }
      $("#caseA .item").each(function(){
        if($(this).hasClass('active')){
          let firstrent = $(this).attr("data-a");
          let monthlyrent = $(this).attr("data-b");
          let rentdata = $(this).attr("data-c");
          let carName = $("#carName").val();
          data = {
            openid: openId,
            id: id,
            name: name,
            tel: tel,
            carMoney: firstrent,
            rentMoney: monthlyrent,
            rentDate: rentdata,
            carName:carName,
            carType: '租车服务',
            orderData: '已预约',
            remake1: carCover,
            remake2: sex,
            remake3: sty
          }
        }
      });
      if(data){
        // console.log(data);
        tijiao(data,id);
      }else{
        messageBox("请选择方案？");
      }
    }
    if(type == '1'){
      var data = '';
      var name = $("#name").val();
      var sex = $(':radio[name="sex"]:checked').val();
      var sty = $(':radio[name="yuyue-sty"]:checked').val();
      var tel = $("#tel").val();
      var smsCode = $("#code1").val();
      if(name == '' || name == null){
        messageBox('请输入姓名');
        return false;
      }
      if(sex == '' || sex == null){
        messageBox('请选择性别');
        return false;
      }
      if(tel == '' || tel == null){
        messageBox('请输入手机号');
        return false;
      }
      if(smsCode == '' || smsCode == null){
        messageBox('请输入短信验证码');
        return false;
      }
      $("#caseB .item").each(function(){
        if($(this).hasClass('active')){
          let firstrent = $(this).attr("data-a");
          let monthlyrent = $(this).attr("data-b");
          let rentdata = $(this).attr("data-c");
          let carName = $("#carName").val();
          data = {
            openid: openId,
            id: id,
            name: name,
            tel: tel,
            carMoney: firstrent,
            rentMoney: monthlyrent,
            rentDate: rentdata,
            carName: carName,
            carType: '购车服务',
            orderData: '已预约',
            remake1: carCover,
            remake2: sex,
            remake3: sty,
          }
        }
      });
      if(data){
        tijiao(data,id);
      }else{
        messageBox("请选择方案？");
      }
    }
  });
  $(".cancel").click(function(){
    $(".tel").css("display","none");
  });
}
// 提交
function tijiao(data,id){
  $.ajax({
    type: "POST",
    url: baseUrl + "/rest/lkCarApplyController",
    contentType:"application/json;charset=UTF-8",
    data:JSON.stringify(data),
    success: function(data){
      console.log(JSON.parse(data));
      let e = JSON.parse(data);
      if(e.ok){
        messageBox("提交成功！")
        setTimeout(function(){
          window.location = "../index.html";
        },1000);
      }else{
        console.log('加载失败...')
      }
    }
  });
}