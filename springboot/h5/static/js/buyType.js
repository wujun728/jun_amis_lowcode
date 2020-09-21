$(function(){
  var id = getQueryString("id");
  var openId = localStorage.getItem("openId");
  $.ajax({
      type: "GET",
      url: baseUrl + "/rest/lkCarMainlistController/list/"+openId,
      contentType:"application/json;charset=UTF-8",
      data: {
          id: id,
          pageNumber:'1',
          pageSize:'100'
      },
      dataType: "json",
      success: function(data){
          if(data.ok){
            console.log(data);
            $("#banner").attr("src",baseUrl+"/img/server/" + data.data[0].carCover);
            $(".carName").text(data.data[0].carName);
            $(".carPrice-1").text(data.data[0].guidancePrice);
            $(".carBattery-1").text(data.data[0].carEndurance);
            let carName =data.data[0].carName;
            console.log(carName)
            var a ='';
            for(var i = 0;i<data.data.length;i++){
              a += '<a href="carDetail.html?id='+data.data[i].id+'" data-carName='+data.data[0].carName+' >';
              a +='<div class="carType" >车型介绍</div>'; 
              a += '</a>';
              a += '<a href="rentCar.html?id='+data.data[i].id+'">';
              a +='<div class="carRent"> 租赁服务</div>';
              a += '</a>';
              a += '<a href="buyType.html?id='+data.data[i].id+'">';
              a +='<div class="carFinance"> 金融方案</div>';
              a += '</a>';
            }
            $("#wrap").append(a);
          }else{
            alert(data.message);
          }
      }
  });
  $.ajax({
    type: "GET",
    url: baseUrl + "/rest/lkFinancialPlanController/list/"+openId,
    contentType:"application/json;charset=UTF-8",
    data: {
      planGroup:"购车服务",
      pageNumber:'1',
      pageSize:'100'
    },
    dataType: "json",
    success: function(data){
        if(data.ok){
          console.log(data);
        var a ='';
        for(var i = 0;i<data.data.length;i++){
          a +='<input id="'+data.data[i].planNo+'" type="radio"';
          a += 'name="PurchaseIdentity" data-rentData="'+data.data[i].rentData+'" ';
          a += 'data-monthlyRent="'+data.data[i].monthlyRent+'" data-planNo="'+data.data[i].planNo+'"';
          a += ' data-firstRent="'+data.data[i].firstRent+'" value="'+data.data[i].planNo+'">';
          a +='<label for="'+data.data[i].planNo+'">';
          a +='<div class="kuang">';
          a +='<div class="money">';
          a +='<div class="top">押金</div>';
          a +='<div class="bottom">'+data.data[i].firstRent+'</div>';
          a +='</div>';
          a +='<div class="month">';
          a +='<div class="top">月租</div>';
          a +='<div class="bottom">'+data.data[i].monthlyRent+'</div>';
          a +='</div>';
          a +='<div class="rent">';
          a +='<div class="top">租期</div>';
          a +='<div class="bottom">'+data.data[i].rentData+'</div>';
          a +='</div>';
          a +='</div>';
          a +='</label>';
        }
        $("#check").append(a);
       
        }else{
          alert(data.message);
        }
    }
  });
  $(".submit").click(function(){
    // let check = $("input[type='radio']:checked").val();
    // let check = $("input[type='radio']:checked").attr("val");
    let rentdata = $("input[type='radio']:checked").attr("data-rentdata");
    let monthlyrent = $("input[type='radio']:checked").attr("data-monthlyrent");
    let firstrent = $("input[type='radio']:checked").attr("data-firstrent");
    let planno = $("input[type='radio']:checked").attr("data-planno");
    var carName =$(".carName").text();
    console.log(carName)
    let data = {
      carMoney:firstrent,
      rentMoney:monthlyrent,
      rentDate:rentdata,
      carName:carName,
      carType:'购车服务',
      orderData:'未完成'
  }
  if(firstrent == null || data == ''){
    alert('请选择您的方案');
    return false;
  }
    $.ajax({
      type: "POST",
      url: baseUrl + "/rest/lkCarApplyController",
      contentType:"application/json;charset=UTF-8",
      data:JSON.stringify(data),
      // data:{
      //   carMoney:firstrent,
      //   rentMoney:monthlyrent,
      //   rentDate:rentdata,
      //     // applicationName:JSON.stringify(inputName),
      //     // applicationTel:inputNumber,
      //     // applicationCity:JSON.stringify(inputCity)
      // },
      success: function(data){
          console.log(data)
          if(data.ok){
              
          }else{
              alert();
          }
      }
  });
  })
});