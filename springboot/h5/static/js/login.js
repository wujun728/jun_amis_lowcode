$(function() {
    // 注册登陆
    $(".submit").click(function(){ 
        var openId = localStorage.getItem("openId");
        var sjOpenId = getQueryString("openId");
        // var firstname = $('#firstname').val();
        // var tel = $('#tel').val();
        // var password = $('#password').val();
        // var password1 = $('#password1').val();
        var sms = $("#code1").val();
        // if(firstname == '' || firstname==null) {
        //     messageBox("请输入您的姓名");
        //     return false;
        // }
        if(tel == '' || tel==null) {
            messageBox("请输入您的电话");
            return false;
        }
        // if(password == '' || password==null) {
        //     alert("请输入您的密码");
        //     return false;
        // }
        // if(password1 == '' || password1==null) {
        //     messageBox("请再次输入您的密码");
        //     return false;
        // }
        // if(password != password1){
        //     messageBox('您输入的俩次密码不一致');
        //     return false;
        // }
        if(sms == '' || sms==null) {
            messageBox("请输入短信验证码");
            return false;
        }
        let data = {
            lkSpare1: '', //备用1
            lkSpare2: '', //备用2
            name: '', //姓名
            openid: openId,
            tel: tel,  //电话
            type: "初级会员", //类型
          }
        $.ajax({
            type: "POST",
            // /rest/lkMyController
            url: baseUrl + "/rest/lkMyController",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(data),
            success: function(data){
                console.log(data);
                let e = JSON.parse(data);
                if(e.ok){
                    if(sjOpenId == null || sjOpenId == '' || sjOpenId == 'null'){
                        messageBox("注册成功！");
                        setTimeout(function(){
                            window.location="../index.html";//跳转到首页
                        },1000);
                    }else{
                        creatFxhy(openId,firstname,tel,sjOpenId);
                    }
                    
                }else{
                    messageBox(e.message);
                }
                
            }
        });
        
    });

});

function creatFxhy(openId,name,tel,sjOpenId){
    let fxhyDate = {
        fxhy01: openId,
        fxhy02: '',
        fxhy03: tel,
        fxhy04: '初级会员',
        fxhy05: sjOpenId,
        fxhy06: '',
        fxhy07: '',
    }
    $.ajax({
        type: "POST",
        // /rest/lkMyController
        url: baseUrl + "/rest/lkFxhyController",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(fxhyDate),
        success: function(data){
            console.log(data);
            let e = JSON.parse(data);
            if(e.ok){
                // messageBox("注册成功！");
                setTimeout(function(){
                    window.location="../index.html";//跳转到首页
                },1000);
            }else{
                messageBox(e.message);
            }
            
        }
    });
}