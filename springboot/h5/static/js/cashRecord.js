$(function(){
    var openId = localStorage.getItem("openId")
    getTxList();
    $(".nav .item").click(function(){
        var idx = $(this).index();
        $(this).addClass('active').siblings().removeClass('active');
        if(idx == '0'){ // 0 充值提现  1 奖励提现  3  红包领取
            // getTxList('充值');
            getTxList('奖励');
        }
        if(idx == '1'){
            // getTxList('奖励');
            getHbList(openId);
        }
        // if(idx == '2'){
        //     getHbList(openId)
        // }
    });
    
});
// 提现记录
function getTxList(type) {
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkTixianController/list/"+localStorage.getItem("openId"),
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data:{
            pageNumber:'1',
            pageSize:'100',
            leixing:type
        },
        success: function(e){ 
            if(e.ok){
                console.log(e);
                if(e.data.length){
                    let a = '';
                    $("#wrap").html(' ');
                    for(let i=0;i<e.data.length;i++){
                        a += '<div class="item">';
                        a += '<div class="l">';
                        if(e.data[i].tx07 == '充值'){
                            a += '<p class="t">充值升级会员</p>';
                        }else{
                            a += '<p class="t">'+e.data[i].tx07+'提现</p>';
                        }
                        a += '<p class="b">';
                        a += '<span class="time">手机号:'+e.data[i].tx03+'</span>';
                        a += '</p>';
                        a += '</div>';
                        a += '<div class="r">'+e.data[i].tx04+'元</div>';
                        a += '</div>';
                        // if(type){
                        //     if(type == '充值'){
                        //         if(e.data[i].tx07 == '充值'){
                        //             a += '<div class="item">';
                        //             a += '<div class="l">';
                        //             a += '<p class="t">充值提现</p>';
                        //             a += '<p class="b">';
                        //             // a += '<span class="status defaule" id="status">处理中</span>';
                        //             a += '<span class="time">手机号:'+e.data[i].tx03+'</span>';
                        //             a += '</p>';
                        //             a += '</div>';
                        //             a += '<div class="r">'+e.data[i].tx04+'元</div>';
                        //             a += '</div>';
                        //         }
                        //     }
                        //     if(type == '奖励'){
                        //         console.log('2')
                        //         if(e.data[i].tx07 == '提现'){
                        //             console.log('3')
                        //             a += '<div class="item">';
                        //             a += '<div class="l">';
                        //             a += '<p class="t">'+e.data[i].tx07+'</p>';
                        //             a += '<p class="b">';
                        //             a += '<span class="time">手机号:'+e.data[i].tx03+'</span>';
                        //             a += '</p>';
                        //             a += '</div>';
                        //             a += '<div class="r">'+e.data[i].tx04+'元</div>';
                        //             a += '</div>';
                        //         }
                        //     }
                        // }else{
                        //     a += '<div class="item">';
                        //     a += '<div class="l">';
                        //     a += '<p class="t">'+e.data[i].tx07+'</p>';
                        //     a += '<p class="b">';
                        //     a += '<span class="time">手机号:'+e.data[i].tx03+'</span>';
                        //     a += '</p>';
                        //     a += '</div>';
                        //     a += '<div class="r">'+e.data[i].tx04+'元</div>';
                        //     a += '</div>';
                        // }
                    }
                    $("#wrap").append(a);
                }else{
                    $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
                }
            }else{
                messageBox('获取数据失败，请稍后再试')
            }

        }
    });
}
function getHbList(openId){
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
                if(e.data.length){
                    let a = '';
                    $("#wrap").html(' ');
                    // idx  0 未使用  1 已使用
                    for(let i=0;i<e.data.length;i++){
                        if(e.data[i].hb05 == '已发放'){
                            a += '<div class="itema">';
                            a += '<div class="topa">'+e.data[i].hb06+'</div>';
                            a += '<div class="mid">';
                            a += '<div class="la"><div class="price">￥'+e.data[i].hb04+'</div></div>';
                            a += '<div class="ra">';
                            a += '<p class="infoa">'+e.data[i].hb09+'</p>';
                            a += '<p class="infoa">'+e.data[i].hb10+'</p>';
                            a += '</div>';
                            a += '</div>';
                            // a += '<div class="bt">';
                            // a += '<div class="btn" data-id="">立即领取</div>';
                            // a += '</div>';
                            a += '</div>';
                        }
                    }
                    if(a){
                        $("#wrap").append(a);
                    }else{
                        $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
                    }
                    
                    // 立即使用 出现二维码
                    $(".btn").click(function(){
                        var id = $(this).attr('data-id');
                        $(".qrcode-box").css('display','block');
                        var _w = $("#qrCode").width();
                        jQuery('#qrCode').qrcode({
                            render: "canvas",
                            width: _w,
                            height: _w,
                            text: id
                        });
                    });
                    // 关闭二维码
                    $(".close").click(function(){
                        $(".qrcode-box").css('display','none');
                        $("#qrCode").html('');
                    });
                }else{
                    $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
                }
            }else{
                messageBox('获取数据失败，请稍后再试')
            }

        }
    });
}