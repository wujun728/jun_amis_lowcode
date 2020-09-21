$(function(){
    // var userInfo = JSON.parse(localStorage.getItem("userInfo"));
    var openId = localStorage.getItem("openId");
    var name = unescape(getQueryString("name"));
    var sjOpenId = getQueryString("openId");
    localStorage.setItem("sjOpenId",sjOpenId);
    var type = getQueryString("type");
    // if(userInfo.name == '' || userInfo.name ==null){
    //     $(".no").text(' ');
    // }else{
    //     $(".no").text('您已领取了该红包，不能再领取了！');
    // }
    // console.log(name)
    // if(name == null || name == '' || name == 'null'){
    //     console.log('a')
    // }else{
    //     console.log('b')
    //     $("#tjr").html(name);
    //     $(".bg, .share").css("display","block");
    //     $(".gb").click(function(){
    //         $("#tjr").html(' ');
    //         $(".bg, .share").css("display","none");
    //     });
    // }

    if(openId){
        getNewhb(openId,type);
    }
    // getNewhb(openId);
});
function getNewhb(openId,type){
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
                    for(let i=0;i<e.data.length;i++){
                        if(e.data[i].hb06 == '新人红包'){
                            a += '<div class="item">';
                            a += '<div class="top">'+e.data[i].hb06+'</div>';
                            a += '<div class="mid">';
                            a += '<div class="l"><div class="price">￥'+e.data[i].hb04+'</div></div>';
                            a += '<div class="r">';
                            a += '<p class="info">'+e.data[i].hb09+'</p>';
                            a += '<p class="info">'+e.data[i].hb10+'</p>';
                            a += '</div>';
                            a += '</div>';
                            a += '<div class="bt">';
                            a += '<div class="btn" data-id="'+e.data[i].id+'">立即领取</div>';
                            a += '</div></div>';
                        }
                    }
                    $(".wrap").html(' ').append(a);
                    // 立即领取
                    $(".btn").click(function(){
                        messageBox('领取成功！');
                        $(this).text('已经领取');
                        setTimeout(function(){
                            if(type=='0'){
                                window.location.href = '../index.html';
                            }
                            if(type=='1'){
                                window.location.href = 'myTeam.html';
                            }
                        },1000);
                    });
                }
            }
        }
    });
}