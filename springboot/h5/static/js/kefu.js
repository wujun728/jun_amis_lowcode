$(function(){
    var openId = localStorage.getItem("openId");
    console.log(openId);
    if(openId == null || openId == ''){
        messageBox("获取个人信息失败");
        setTimeout(function(){
            loginFail(); // 一键登录
        },200)
    }else{
        $.ajax({
            type: "GET",
            url: baseUrl + "/rest/lkCustomerServiceController/list/"+openId,
            contentType:"application/json;charset=UTF-8",
            dataType: "json",
            data:{
                pageNumber:'1',
                pageSize:'10',
            },
            success: function(e) {
                if(e.ok){
                    $("#tel").html(e.data[0].fxjOut1);
                    $("#weixin").html(e.data[0].fxjOut2);
                    $("#wangdian").html(e.data[0].fxjOut3);
                }else{
                    messageBox('获取数据失败，请稍后再试');
                }
            }
        });
    }
    
    
});