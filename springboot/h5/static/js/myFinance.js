$(function(){
    var openId = localStorage.getItem("openId");
    $("#money").html(localStorage.getItem("yue"));
    // $("#jiangli").html(localStorage.getItem("yue"));
    // 数据初始化
    getMyZichan(openId);
    getHb(openId);
    // getJiangli(openId);
    // getCzList(openId);
    getJiangli(openId);
    // 选项卡切换
    $(".nav .item").click(function(){
        var idx = $(this).index();
        $(this).addClass("active").siblings().removeClass('active');
        if(idx == '0'){
            // getMyZichan(openId);
            // getCzList(openId);
            getJiangli(openId);
        }
        if(idx == '1'){
            getHbList(openId);
            // getJiangli(openId);
            // $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
        }
        // if(idx == '2'){
        //     getHbList(openId);
        //     // $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
        // }
    });
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
                    $("#chongzhi").html(data.data[0].zc04);
                    $("#jiangli").html(data.data[0].zc04);
                }else{
                    $("#chongzhi").html('0.00');
                    $("#jiangli").html('0.00');
                }
            }else{
                messageBox('数据加载失败，请稍后再试')
            }
        }
    });
}
// 充值记录
function getCzList(openId){
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkChongzhiController/list/"+openId,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data:{
            pageNumber:'1',
            pageSize:'100',
            leixing:'充值'
        },
        success: function(e) {
            if(e.ok){
                if(e.data.length){
                    let a = '';
                    $("#wrap").html(' ');
                    for(let i=0;i<e.data.length;i++){
                        a += '<div class="item">';
                        a += '<div class="l">';
                            a += '<p class="t">'+e.data[i].cz07+'</p>';
                        a += '<p class="b">';
                        // a += '<span class="status defaule" id="status">处理中</span>';
                        a += '<span class="time">'+e.data[i].cz03+'</span>';
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
                messageBox('获取数据失败，请稍后再试');
            }
        }
    });
}
// 奖励余额
function getJiangli(openId){
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkChongzhiController/list/"+openId,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data:{
            pageNumber:'1',
            pageSize:'100',
            leixing:'奖励'
        },
        success: function(e) {
            if(e.ok){
                if(e.data.length){
                    
                    $("#wrap").html(' ');
                    var arr = [];
                    for(let i=0;i<e.data.length;i++){
                        if(e.data[i].cz05 != 'string'){
                            arr.push(e.data[i])
                        }
                    }
                    console.log(arr);
                    if(arr.length){
                        let a = '';
                        for(let i=0;i<arr.length;i++){
                            a += '<div class="item">';
                            a += '<div class="l">';
                            a += '<p class="t">'+arr[i].cz07+'</p>';
                            a += '<p class="b">';
                            // a += '<span class="status defaule" id="status">处理中</span>';
                            a += '<span class="time">'+arr[i].cz03+'</span>';
                            a += '</p>';
                            a += '</div>';
                            a += '<div class="r">'+arr[i].cz04+'元</div>';
                            a += '</div>';
                        }
                        $("#wrap").append(a);
                    }else{
                        $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
                    }
                    // $("#jiangli").html(sum);
                }else{
                    $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
                }
            }else{
                messageBox('获取数据失败，请稍后再试');
            }
        }
    });
}

// 红包
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
                    for(let i=0;i<e.data.length;i++){
                        // if(e.data[i].hb05 == '否'){
                            a += '<div class="item">';
                        // }
                        // if(e.data[i].hb05 == '是'){
                        //     a += '<div class="item a">';
                        // }
                        a += '<div class="l">';
                        a += '<p class="t">'+e.data[i].hb06+'</p>';
                        a += '<p class="b">'+e.data[i].hb09+'</p>';
                        a += '<p class="b">'+e.data[i].hb10+'</p>';
                        a += '</div>';
                        a += '<div class="r">￥'+e.data[i].hb04+'</div>';
                        a += '</div>';
                    }
                    $("#wrap").append(a);
                }
            }else{
                messageBox('获取数据失败，请稍后再试');
            }
        } 
    })
}
// 红包个数
function getHb(openId){
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
                    $("#hb").html(e.data.length);
                }
            }else{
                messageBox('获取数据失败，请稍后再试');
            }
        } 
    })
}