$(function(){
    function init(openId){
        $.ajax({
            type: "GET",
            url: baseUrl + "/rest/lkCarApplyController/list/"+openId+"?pageNumber=1&pageSize=100",
            contentType:"application/json;charset=UTF-8",
            dataType: "json",
            success: function(data){
                if(data.ok){
                    if(data.data.length){
                        let a = '';
                        $("#carDetail").html(' ');
                        for(let i=0;i<data.data.length;i++){
                            a += '<div class="item">';
                            a += '<div class="item-a">';
                            if(data.data[i].name){
                                a += '<div class="l">'+data.data[i].name+'</div>';
                            }else{
                                a += '<div class="l"></div>';
                            }
                            a += '<div class="r">'+data.data[i].orderData+'</div>';
                            a += '</div>';
                            a += '<div class="item-b">';
                            a += '<div class="l">';
                            a += '<img src="'+baseUrl+"/img/server/" + data.data[i].remake1+'" alt="">';
                            a += '</div>';
                            a += '<div class="r">';
                            a += '<p class="a">'+data.data[i].carName+'</p>';
                            a += '<p class="b">申请服务:'+data.data[i].carType+'</p>';
                            a += '<p class="c">首租金额:'+data.data[i].carMoney+'元</p>';
                            a += '<p class="d">期数:'+data.data[i].rentDate+'期</p>';
                            a += '<p class="e">月供:'+data.data[i].rentMoney+'元</p>';
                            a += '</div>';
                            a += '</div>';
                            a += '</div>';
                        }
                        $("#order").html(a);
                    }else{
                        $("#order").html('<div style="height: 3rem;line-height: 3rem;text-align: center;color: #FF7E00;">暂无数据</div>');
                    }
                }else{
                    alert(data.message);
                }
            }
        });
    }
    function tab(dataType,openId){
        $.ajax({
            type: "GET",
            url: baseUrl + "/rest/lkCarApplyController/list/"+openId+"?pageNumber=1&pageSize=100",
            contentType:"application/json;charset=UTF-8",
            data: {
                orderData: dataType,
            },
            dataType: "json",
            success: function(data){
                console.log(data)
                if(data.ok){
                    if(data.data.length){
                        let a = '';
                        $("#carDetail").html(' ');
                        for(let i=0;i<data.data.length;i++){
                            a += '<div class="item">';
                            a += '<div class="item-a">';
                            if(data.data[i].name){
                                a += '<div class="l">'+data.data[i].name+'</div>';
                            }else{
                                a += '<div class="l"></div>';
                            }
                            // a += '<div class="l">'+data.data[i].name+'</div>';
                            a += '<div class="r">'+data.data[i].orderData+'</div>';
                            a += '</div>';
                            a += '<div class="item-b">';
                            a += '<div class="l">';
                            a += '<img src="'+baseUrl+"/img/server/" + data.data[i].remake1+'" alt="">';
                            a += '</div>';
                            a += '<div class="r">';
                            a += '<p class="a">'+data.data[i].carName+'</p>';
                            a += '<p class="b">申请服务:'+data.data[i].carType+'</p>';
                            a += '<p class="c">首租金额:'+data.data[i].carMoney+'元</p>';
                            a += '<p class="d">期数:'+data.data[i].rentDate+'期</p>';
                            a += '<p class="e">月供:'+data.data[i].rentMoney+'元</p>';
                            a += '</div>';
                            a += '</div>';
                            a += '</div>';
                        }
                        $("#order").html(a);
                    }else{
                        $("#order").html('<div style="height: 3rem;line-height: 3rem;text-align: center;color: #FF7E00;">暂无数据</div>');
                    }
                }else{
                    alert(data.message);
                }
            }
        });
    }
    var openId = localStorage.getItem("openId");
    if(openId == null || openId == ''){
        messageBox("获取个人信息失败");
        setTimeout(function(){
            loginFail('1'); // 一键登录
        },200);
        return false;
    }
    init(openId);// 初始化加载数据
    // 切换加载数据
    $(".header-item").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        var dataType = $(this).attr("data-type");
        if(dataType == '0'){
            tab("已预约",openId);
        }
        if(dataType == '1'){
            tab("办理中",openId);
        }
        if(dataType == '2'){
            // init(openId);
            tab("已完结",openId);
        }
    });
});