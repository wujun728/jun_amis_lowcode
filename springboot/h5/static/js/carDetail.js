$(function(){
  var id = getQueryString("id");
  var typea = getQueryString("type");
  var userName = localStorage.getItem("openId");
  if(typea == '0'){
    $(".carRent").css('display','none');
  }
  if(typea == '1'){
    $(".carFinance").css('display','none');
  }
  // 车型详情/租赁/金融 切换
  $("#wrap>div").click(function(){
    $(this).addClass("active").siblings().removeClass("active");
    let index = $(this).attr("data-idx");
    if(index == '0'){
      $(".box-a").css("display","block");
      $(".box-b").css("display","none");
      $(".box-c").css("display","none");
    }
    if(index == '1'){
      $(".box-a").css("display","none");
      $(".box-b").css("display","block");
      $(".box-c").css("display","none");
      var carNo =$("#carNo").val();
      var carCover =$("#carCover").val();
      chooseCase("0",userName,carNo,carCover); // 1为租赁服务 2为金融方案
    }
    if(index == '2'){
      $(".box-a").css("display","none");
      $(".box-b").css("display","none");
      $(".box-c").css("display","block");
      var carNo =$("#carNo").val();
      var carCover =$("#carCover").val();
      chooseCase("1",userName,carNo,carCover); // 1为租赁服务 2为金融方案
    }
  });
  // 立即预约
  $("#yuyue").click(function(){
    if(typea == '0'){ // 购车
      // $(".box-a").css("display","none");
      // $(".box-b").css("display","none");
      // $(".box-c").css("display","block");
      // $(".carFinance").addClass("active").siblings().removeClass("active");
      var carNo =$("#carNo").val();
      var carCover =$("#carCover").val();
      chooseCase("1",userName,carNo,carCover); // 1为租赁服务 2为金融方案
      // var ur = 'rentCar.html?carNo='+carNo+'&type='+type+'&id='+id+'&carCover='+carCover;
      window.location.href = 'rentCar.html?carNo='+carNo+'&type=1&id='+id+'&carCover='+carCover;
    }
    if(typea == '1'){ // 租车
      // $(".box-a").css("display","none");
      // $(".box-b").css("display","block");
      // $(".box-c").css("display","none");
      // $(".carRent").addClass("active").siblings().removeClass("active");
      var carNo =$("#carNo").val();
      var carCover =$("#carCover").val();
      chooseCase("0",userName,carNo,carCover); // 1为租赁服务 2为金融方案
      window.location.href = 'rentCar.html?carNo='+carNo+'&type=0&id='+id+'&carCover='+carCover;
    }


    
    
  });
  // 详细配置/车辆实拍 切换
  $(".tab>div").click(function(){
    $(this).addClass("active").siblings().removeClass("active");
    let index = $(this).index();
    if(index == '0'){
      $(".base-info").css("display","block");
    }
    if(index == '1'){
      $(".base-info").css("display","none");
    }
  });
  // 外观/内饰 切换
  $(".tab-box>div").click(function(){
    $(this).addClass("active").siblings().removeClass("active");
    let index = $(this).index();
    let carNo = $(this).attr("data-no");
    if(index == '0'){
      carInfo(carNo,"外观",userName);
    }
    if(index == '1'){
      carInfo(carNo,"内饰",userName);
    }
  });

  // 选中方案
  $(".check-box>.a").click(function(){
    let flag = $(this).hasClass('active');
    if(!flag){
      $(this).addClass("active");
    }else{
      $(this).removeClass("active");
    }
  });
  

  // 加载产品数据
  $("#wrap>div").attr("data-id",id);
  $.ajax({
    type: "GET",
    url: baseUrl + "/rest/lkCarMainlistController/list/"+userName,
    contentType:"application/json;charset=UTF-8",
    data: {
      id: id,
      pageNumber:'1',
      pageSize:'10'
    },
    dataType: "json",
    success: function(data){
      if(data.ok){
        // console.log(data);
        if(data.data.length){
          $("#banner").attr("src",baseUrl+"/img/server/" + data.data[0].carCover); // banner
          $(".compangName").text(data.data[0].carCompany); // 车名
          $(".carName").text(data.data[0].carName); // 车名
          $(".carPrice-1").text(data.data[0].guidancePrice); // 厂家指导价：
          $(".carBattery-1").text(data.data[0].carEndurance); // 续航
          $(".lowprice").text(data.data[0].lowestPrice); // 现在价格
          $(".chazhi").text(data.data[0].marketPrice); // 降价
          $(".tab-box>div").attr("data-no",data.data[0].carNo); // 
          // 获取车辆详细信息
          $.ajax({
            type: "GET",
            url: baseUrl + "/rest/lkCarParameterController/list/"+userName,
            contentType:"application/json;charset=UTF-8",
            data: {
                carNo:data.data[0].carNo,
                pageNumber:'1',
                pageSize:'10'
            },
            dataType: "json",
            success: function(data) {
              if(data.ok){
                // console.log(data);
                if(data.data.length){
                  $("#baseInfo").html(" ");
                  let a = "";
                  for(let i=0;i<data.data.length;i++){
                    a += '<div class="item" >';
                    a += '<div style="text-align: right">'+data.data[i].lkSpare4+'</div>';
                    a += '<div style="text-align: left;">'+data.data[i].lkSpare5+'</div>';
                    a += '</div>';
                  }
                  $("#baseInfo").append(a);
                }else{
                  console.log("暂无相关信息...")
                }
              }else{
                console.log("信息加载失败...")
              }
            }
          });
          carInfo(data.data[0].carNo,"外观","admin");
          $("#carNo").val(data.data[0].carNo);
          $("#carCover").val(data.data[0].carCover);

          // 
        }else{
          console.log("暂无相关信息...")
        }
      }else{
        console.log("数据加载失败...");
      }
    }


    

  });

  function carInfo(id,name,uesername){
    $.ajax({
      type: "GET",
      url: baseUrl + "/rest/lkCarPicController/list/"+uesername,
      contentType:"application/json;charset=UTF-8",
      data: {
          carNo: id,
          picName: name,
          pageNumber:'1',
          pageSize:'10',
      },
      dataType: "json",
      success: function(data){
        if(data.ok){
          if(data.data.length){
            $(".top").html(" ");
            $(".bottom").html(" ");
            var str =data.data[0].littlePicAddress;
            var arr = str.split(",");
            var a ="";   
            var str1 =data.data[0].picAddress;
            var arr1 = str1.split(",");
            var b ="";
            for(var i =0;i<arr.length;i++){
              a +='<div class="pic view-img" data-src="'+baseUrl+'/img/server/'+arr[i]+'"><img src="'+baseUrl+'/img/server/'+arr[i]+'"  alt=""></div>';
            }
            for(var i =0;i<arr1.length;i++){
              b +='<div class="pic view-img" data-src="'+baseUrl+'/img/server/'+arr1[i]+'"><img src="'+baseUrl+'/img/server/'+arr1[i]+'"  alt=""></div>';
            }
            $("#carPicA").append(a);
            $("#carPicB").append(b);
            viewPic(); // 查看大图
          }else{
            console.log("暂无相关信息...");
          }
        }else{
          console.log("数据加载失败...");
        }
      }
    });
  }

  function chooseCase(type,uesername,carNo,carCover){
    let planGroup = "";
    if(type == '0'){
      planGroup = "租车服务";
      // type = '1';
    }
    if(type == '1'){
      planGroup = "购车服务";
      // type = '0'
    }
    var ur = 'rentCar.html?carNo='+carNo+'&type='+type+'&id='+id+'&carCover='+carCover;
    $.ajax({
      type: "GET",
      url: baseUrl + "/rest/lkFinancialPlanController/list/"+uesername,
      contentType:"application/json;charset=UTF-8",
      data: {
        carNo:carNo,
        planGroup: planGroup,
        pageNumber: "1",
        pageSize: "10"
      },
      dataType: "json",
      success: function(data){
        if(data.ok){
          if(data.data.length){
            console.log(data);
            $("#caseA").html(" ");
            $("#caseB").html(" ");
            let a = '';
            for(let i=0;i<1;i++){
              a += '<div class="item">';

              if(type == '1'){
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
              // if(type == '1'){
              //   a += '<div class="txt-a">押金</div>';
              // }
              // if(type == '0'){
              //   a += '<div class="txt-a">首租</div>';
              // }
              // a += '<div class="txt-b">'+data.data[i].firstRent+'元</div>';
              // a += '</div>';
             
              
              a += '<div class="icon"></div>';
              a += '</div>';
            }
            if(type == '0'){
              $("#caseA").append(a);
              $("#viewA").attr("href",ur);
              // $("#yuyue").attr("href",ur);
              
            }
            if(type == '1'){
              $("#caseB").append(a);
              $("#viewB").attr("href",ur);
              $("#yuyue").attr("href",ur);
            }
            $(".fangan>.item").click(function(){
              $(this).addClass('active').siblings().removeClass('active');
            });
          }else{
            console.log("暂无相关信息...");
            if(type == '0'){
              $("#caseA").html(' ').append('<div style="height:2rem;line-height:2rem;text-align: center;">暂无租车服务</div>');
              $("#caseA1").css("display",'none');
            }
            if(type == '1'){
              $("#caseB").html(' ').append('<div style="height:2rem;line-height:2rem;text-align: center;">暂无金融方案</div>');
              $("#caseB1, #caseB2").css("display",'none');
            }
          }
        }else{
          console.log("数据加载失败...");
        }
      }
    });
  }

  function viewPic(){
    $(".view-img").click(function(){
      let picUrl = $(this).attr("data-src");
      console.log(picUrl);
      picView(picUrl);
    });
  }

});