$(function(){
    var openId = localStorage.getItem("openId");
    getHbList(openId,'0');
    $(".nav .item").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        var idx = $(this).index();
        getHbList(openId,idx); // idx  0 未使用  1 已使用
    });
});
function getHbList(openId,idx){
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkHongbaoController/list/"+openId,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data:{
            pageNumber:'1',
            pageSize:'100'
        },
        success: function(e){ 
            if(e.ok){
                console.log(e);
                if(e.data.length){
                    let a = '';
                    $("#wrap").html(' ');
                    // idx  0 未使用  1 已使用
                    // 未使用  待审批  已发放
                    for(let i=0;i<e.data.length;i++){
                        if(idx == '0'){
                            // if(e.data[i].hb05 == '否'){
                            if(e.data[i].hb05 == '未使用'){
                                a += '<div class="item">';
                                a += '<div class="top">'+e.data[i].hb06+'</div>';
                                a += '<div class="mid">';
                                a += '<div class="l"><div class="price">￥'+e.data[i].hb04+'</div></div>';
                                a += '<div class="r">';
                                a += '<p class="info">'+e.data[i].hb09+'</p>';
                                a += '<p class="info">'+e.data[i].hb10+'</p>';;
                                a += '</div>';
                                a += '</div>';
                                a += '<div class="bt">';
                                a += "<div class='btn' data-da='"+JSON.stringify(e.data[i])+"' data-id='"+e.data[i].id+"' data-price='"+e.data[i].hb04+"' data-tit='"+e.data[i].hb06+"'>申请提现</div>";
                                a += '</div></div>';
                            }
                        }
                        if(idx == '1'){
                            if(e.data[i].hb05 == '待审批'){
                                a += '<div class="item">';
                                a += '<div class="top">'+e.data[i].hb06+'</div>';
                                a += '<div class="mid">';
                                a += '<div class="l"><div class="price">￥'+e.data[i].hb04+'</div></div>';
                                a += '<div class="r">';
                                a += '<p class="info">'+e.data[i].hb09+'</p>';
                                a += '<p class="info">'+e.data[i].hb10+'</p>';;
                                a += '</div>';
                                a += '</div>';
                                // a += '<div class="bt">';
                                // a += "<div class='btn' data-da='"+JSON.stringify(e.data[i])+"' data-id='"+e.data[i].id+"' data-price='"+e.data[i].hb04+"' data-tit='"+e.data[i].hb06+"'>申请提现</div>";
                                // a += '</div>';
                                a += '</div>';
                            }
                        }
                        if(idx == '2'){
                            if(e.data[i].hb05 == '已发放'){
                                a += '<div class="item">';
                                a += '<div class="top">'+e.data[i].hb06+'</div>';
                                a += '<div class="mid">';
                                a += '<div class="l"><div class="price">￥'+e.data[i].hb04+'</div></div>';
                                a += '<div class="r">';
                                a += '<p class="info">'+e.data[i].hb09+'</p>';
                                a += '<p class="info">'+e.data[i].hb10+'</p>';;
                                a += '</div>';
                                a += '</div>';
                                // a += '<div class="bt">';
                                // a += "<div class='btn' data-da='"+JSON.stringify(e.data[i])+"' data-id='"+e.data[i].id+"' data-price='"+e.data[i].hb04+"' data-tit='"+e.data[i].hb06+"'>申请提现</div>";
                                // a += '</div>';
                                a += '</div>';
                            }
                        }
                    }
                    if(a){
                        $("#wrap").append(a);
                    }else{
                        $("#wrap").html(' ').append('<div class="no" style="text-align: center;">目前暂无数据</div>');
                    }
                    
                    // 立即使用 出现二维码
                    $(".btn").click(function(){
                        let putHbData = $(this).attr('data-da');
                        putHbInfo(putHbData);
                        // let hbId = $(this).attr('data-id');
                        // let hbPrice = $(this).attr('data-price');
                        // let hbName = $(this).attr('data-tit');
                        // let hbData = {
                        //     hbId:hbId,
                        //     hbPrice:hbPrice,
                        //     hbName:hbName
                        // }
                        // window.location.href = 'chongzhi.html?type=2&b='+hbPrice+'&c='+JSON.stringify(hbData);
                        // window.location.href = 'chongzhi.html?type=2&b='+hbPrice+'&c=xrhb&hbid='+hbId+'&hbprice='+hbPrice+'&hbname='+hbName;
                        // console.log(id);
                        // $(".qrcode-box").css('display','block');
                        // var _w = $("#qrCode").width();
                        // jQuery('#qrCode').qrcode({
                        //     render: "canvas",
                        //     width: _w,
                        //     height: _w,
                        //     text: id
                        // });
                    });
                    // 关闭二维码
                    // $(".close").click(function(){
                    //     $(".qrcode-box").css('display','none');
                    //     $("#qrCode").html('');
                    // });
                }else{
                    $("#wrap").html(' ').append('<div class="no" style="text-align: center;">目前暂无数据</div>');
                }
            }else{
                messageBox('获取数据失败，请稍后再试')
            }

        }
    });
}


// 申请提现
function putHbInfo(b){
    console.log(b);
    console.log(JSON.parse(b));
    // bpmStatus: null
    // createBy: null
    // createDate: null
    // createName: null

    // hb01: "owbQq1O2R1KudXcM7tJC7J-EVzYk"
    // hb02: ""
    // hb03: "15160409976"
    // hb04: "100"
    // hb05: "否"
    // hb06: "新人红包"
    // hb07: ""
    // hb08: ""
    // hb09: null
    // id: "2c922c966cefd404016cf0fa150a0033"
    // console.log(a.hb01);
    let a = JSON.parse(b);
    let hbData = {
        hb01: a.hb01, // 姓名
        hb02: a.hb02, // 姓名
        hb03: a.hb03, // 手机
        hb04: a.hb04, // 金额
        hb05: '待审批', // 是否消费
        hb06: a.hb06,
        hb07: a.hb07,// 消费时间
        hb08: a.hb08, // 获取时间
        hb09: a.hb09, // 备注
        hb10: a.hb10,
        id: a.id
    }
    console.log(hbData);
    $.ajax({
        type: "PUT",
        url: baseUrl + "/rest/lkHongbaoController/"+a.id,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data: JSON.stringify(hbData),
        success: function(e){
        if(e.ok){
            messageBox('申请成功，请联系客服！');
            setTimeout(function(){
                window.location.href = 'hb.html';
            },1000);
        }else{
            messageBox('领取失败，请稍后再试！');
        }
        }
    });
}