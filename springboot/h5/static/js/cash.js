$(function(){
    var type = getQueryString("type"); // 1 充值  2 提现
    $("#money").html(localStorage.getItem("yue"));
    if(type == '1'){
        $('title').html('充值');
    }
    if(type == '2'){
        $('title').html('提现');
    }
    // 数据初始化加载，首先展示充值明细
    // getCzList();
    getTxList();
    // 切换充值/提现明细
    $(".nav .item").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        let idx = $(this).index();
        if(idx == '0'){ // 充值记录
            // getCzList();
            getTxList();
        }
        if(idx == '1'){ // 提现记录
            // getTxList();
        }
    });
});

// 充值记录
function getCzList() {
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkChongzhiController/list/"+localStorage.getItem("openId"),
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data:{
            pageNumber:'1',
            pageSize:'100'
        },
        success: function(e) {
            if(e.ok){
                if(e.data.length){
                    let a = '';
                    $("#wrap").html(' ');
                    for(let i=0;i<e.data.length;i++){
                        a += '<div class="item">';
                        a += '<div class="l">';
                        // if(e.data[i].cz02){
                        //     a += '<p class="t">'+e.data[i].cz02+'(充值)</p>';
                        // }else{
                        //     a += '<p class="t">充值</p>';
                        // }
                        a += '<p class="t">微信充值</p>';
                        a += '<p class="b">';
                        // a += '<span class="status defaule" id="status">处理中</span>';
                        a += '<span class="time">手机号:'+e.data[i].cz03+'</span>';
                        a += '</p>';
                        a += '</div>';
                        a += '<div class="r">'+e.data[i].cz04+'元</div>';
                        a += '</div>';
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

// 提现记录
function getTxList() {
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkTixianController/list/"+localStorage.getItem("openId"),
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
                    for(let i=0;i<e.data.length;i++){
                        a += '<div class="item">';
                        a += '<div class="l">';
                        // if(e.data[i].tx02){
                        //     a += '<p class="t">'+e.data[i].tx02+'(提现)</p>';
                        // }else{
                        //     a += '<p class="t">提现</p>';
                        // }
                        a += '<p class="t">提现</p>';
                        a += '<p class="b">';
                        // a += '<span class="status defaule" id="status">处理中</span>';
                        a += '<span class="time">手机号:'+e.data[i].tx03+'</span>';
                        a += '</p>';
                        a += '</div>';
                        a += '<div class="r">'+e.data[i].tx04+'元</div>';
                        a += '</div>';
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
