$(function(){
    var openId = localStorage.getItem('openId');
    var type = getQueryString("type"); // 0 购车  1 租车
    var saleType = ''
    if(type == '0'){
        saleType = '购车';
    }
    if(type == '1'){
        saleType = '租车';
    }
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkCarMainlistController/list/"+openId,
        contentType:"application/json;charset=UTF-8",
        data: {
            lkSpare1:'',
            saleType:saleType,
            pageNumber:'1',
            pageSize:'100'
        },
        dataType: "json",
        success: function(data){
            if(data.ok){
                console.log(data);
            var a ='';
            for(var i = 0;i<data.data.length;i++){
                a +='<div class="warp" >';
                a +='<a href="carDetail.html?id='+data.data[i].id+'&type='+type+'">';
                a +='<div class="carName">'+data.data[i].carName+'</div>';
                if(type == '0'){
                    a +='<div class="type">多种可选购车方案</div>';
                }
                if(type == '1'){
                    a +='<div class="type">多种可选租赁方案</div>';
                }
                a +='<div class="carPicture">';
                a +='<img src="'+baseUrl+'/img/server/'+data.data[i].carCover+'" alt="图片" >';         
                a +='</div>';
                a +='<div class="carPrice">';
                if(type == '0'){
                    a +='<text >指导价</text>';
                    a +='<div class="carPrice-1">￥'+data.data[i].guidancePrice+'</div>';
                }
                if(type == '1'){
                    a +='<text >月租</text>';
                    a +='<div class="carPrice-1">￥'+data.data[i].rentalPrice+'</div>';
                }
                
                a +='</div><hr/>';
                // a +='<div class="carBattery">';
                // a +='<div class="icon"></div>';
                // a +='<text >官方续航：</text>';
                // a +='<div class="carBattery-1" >'+data.data[i].carEndurance+'</div>';
                // a +='</div>';
                // a +='<div class="carTime" style="margin-top: .2rem;">   ';
                // a +='<div class="icon"></div>   ';          
                // a +='<text>时速：</text>';
                // a +='<div class="carTime-1"></div>';
                // a +='</div>';
                // a +='<div class="carCharge">';
                // a +='<div class="icon"></div>';              
                // a +='<text>充电时间：</text>';
                // a +='<div class="carCharge-1"></div>';
                // a +='</div>';
                a +='</a></div>';
            }
            $(".carDetail").append(a);
            }else{
                alert(data.message);
            }
        }
    });
    if(type == '0'){ // 0 购车  1 租车
        $('title').html('购车');
        var a = '';
        a += '<a href="javascript:void(0);" data-lkspare="">全部</a>';
        a += '<a href="javascript:void(0);" data-lkspare="商用车">商用车</a>';
        a += '<a href="javascript:void(0);" data-lkspare="乘用车">乘用车</a>';
        $("#carType").html(" ").append(a);
        aa(type);
    }
    if(type == '1'){ // 0 购车  1 租车
        $('title').html('租车');
        var a = '';
        a += '<a href="javascript:void(0);" data-lkspare="">全部</a>';
        a += '<a href="javascript:void(0);" data-lkspare="商用车">商用车</a>';
        a += '<a href="javascript:void(0);" data-lkspare="乘用车">乘用车</a>';
        $("#carType").html(" ").append(a);
        aa(type);
    }
});

function aa(type){ // type 0 购车  1 租车
    $("#carType a").click(function(){
        var openId = localStorage.getItem('openId');
        let lkspare = $(this).attr("data-lkspare");
        $(this).addClass("active").siblings().removeClass("active");
        var saleType = ''
        if(type == '0'){
            saleType = '购车';
        }
        if(type == '1'){
            saleType = '租车';
        }
        $.ajax({
            type: "GET",
            url: baseUrl + "/rest/lkCarMainlistController/list/"+openId,
            contentType:"application/json;charset=UTF-8",
            data: {
                lkSpare1:lkspare,
                saleType:saleType,
                pageNumber:'1',
                pageSize:'100'
            },
            dataType: "json",
            success: function(data){
                console.log(data)
                if(data.ok){
                    if(data.data.length){
                        let b = '';
                        $("#carDetail").html(' ');
                        for(let i=0;i<data.data.length;i++){
                            b +='<div class="warp" >';
                            b +='<a href="carDetail.html?id='+data.data[i].id+'&type='+type+'">';
                            b +='<div class="carName">'+data.data[i].carName+'</div>';
                            if(type == '0'){
                                b +='<div class="type">多种可选购车方案</div>';
                            }
                            if(type == '1'){
                                b +='<div class="type">多种可选租赁方案</div>';
                            }
                            
                            b +='<div class="carPicture">';
                            b +='<img src="'+baseUrl+'/img/server/'+data.data[i].carCover+'" alt="图片" >';         
                            b +='</div>';
                            b +='<div class="carPrice">';
                            if(type == '0'){
                                b +='<text >指导价</text>';
                                b +='<div class="carPrice-1">￥'+data.data[i].guidancePrice+'</div>';
                            }
                            if(type == '1'){
                                b +='<text >月租</text>';
                                b +='<div class="carPrice-1">￥'+data.data[i].rentalPrice+'</div>';
                            }
                            // b +='<text >指导价</text>';
                            // b +='<div class="carPrice-1">￥'+data.data[i].guidancePrice+'</div>';
                            b +='</div><hr/>';
                            // b +='<div class="carBattery">';
                            // b +='<div class="icon"></div>';
                            // b +='<text >官方续航：</text>';
                            // b +='<div class="carBattery-1" >'+data.data[i].carEndurance+'</div>';
                            // b +='</div>';
                            // b +='<div class="carTime">   ';
                            // b +='<div class="icon"></div>   ';          
                            // b +='<text>时速：</text>';
                            // b +='<div class="carTime-1"></div>';
                            // b +='</div>';
                            // b +='<div class="carCharge">';
                            // b +='<div class="icon"></div>';              
                            // b +='<text>充电时间：</text>';
                            // b +='<div class="carCharge-1"></div>';
                            // b +='</div>';
                            b +='</a></div>';
                        }
                        $("#carDetail").html(b);
                    }else{
                        $("#carDetail").html('暂无数据');
                    }
                }else{
                    alert(data.message);
                }
            }
        });
    });
}