$(function(){
    $.ajax({
        type: "GET",
      url: baseUrl + "/rest/lkChargingPileController/list/admin?pageNumber=1&pageSize=10",
      contentType:"application/json;charset=UTF-8",
      dataType: "json",
      success: function(data){
          if(data.ok){
            console.log(data);
            // $("#banner").attr("src","https://www.zhaodui.com.cn/fxj380/img/server/" + data.data[0].carCover);
            // $(".carName").text(data.data[0].carName);
            // $(".carPrice-1").text(data.data[0].guidancePrice);
            // $(".carBattery-1").text(data.data[0].carEndurance);
            // let carName =data.data[0].carName;
            // console.log(carName)
          var arr =[];
          for(var i = 0;i<data.data.length;i++){
              var title = data.data[i].lkSpare2;
              var point = data.data[i].longitude + data.data[i].latitude;
              var address = data.data[i].address;
              var tel = data.data[i].lkSpare1;
              var arrb = {
                title:title,
                point:point,
                address:address,
                tel:tel
              }
              arr.push(arrb);
          }
          console.log(arr);
          $("#markerArr").append(arr);
          console.log(typeof(arr));
          var a ='';
          for(var i = 0; i<data.data.length;i++){ 
          a +='<div class="search">';
          a +='<div class="left">';
          a +='<div class="top">'+data.data[i].lkSpare2+'</div>';
          a +='<div class="mid">';
          a +='<div class="detail-address">'+data.data[i].address+'</div>';
          a +='</div>';
          a +='<div class="bottom">';
          a +='<div style="color: #009688;">便民网点</div>';
          a +=' <div class="pile-number">'+data.data[i].allParkingLot+'个桩位</div>';
          a +=' </div>';
          a +=' </div>';
          a +='<div class="right">';
          a +='<div class="pic-2">';
          a +=' <img src="../static/images/gothere.png" alt="">';
          a +='</div>';
          a +='<div style="font-size: .2rem;">到这里</div>';
          a +='</div>';          
          a +='</div>';
         }
         $(".noway").append(a);
          }else{
            alert(data.message);
          }
      }
  });
});