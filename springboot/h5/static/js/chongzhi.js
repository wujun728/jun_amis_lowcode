$(function(){
    var type = getQueryString('type'); // 1 充值  2 提现
    var wxInfo = JSON.parse(localStorage.getItem("wxInfo"));
    var userInfo = JSON.parse(localStorage.getItem("userInfo"));
    var grade = getQueryString('a'); // 默认1为充值升级会员等级
    var award = getQueryString('b'); // 奖励金余额
    $("#userName").val(wxInfo.nickname); // 昵称
    // console.log("type="+type+'=a='+grade+'=b='+award)
    // 判断是否完成认证
    if(userInfo == '' || userInfo == null){
        $(".tips").css("display","block");
    }else{
        $("#tel").val(userInfo.tel);
    }
    // 判断是充值还是提现
    if(type == '1'){ // type  1 充值  2 提现
        $("#chongzhi").css('display','block');
        $("#cash").css('display','none');
        console.log('充值');
        if(grade){ // 升级会员等级
            console.log('升级');
            $('title').html("立即升级");
            $("#czBtn").html("立即升级")
            $("#price").css("display","block");
            $("#czJeBox").css("display","none");
            tabGrade(grade); // 选择等级升级档次(初级升高级，高级升VIP，初级升VIP)
            recharge();
        }else{ // 一般充值
            console.log('一般充值')
            $("#czTit").css("display","block");
            recharge();
        }
    }
    if(type == '2'){  // 提现
        console.log('提现')
        $("#chongzhi").css('display','none');
        $("#cash").css('display','block');
        $('title').html("提现");
        cash();
    }
});
// 选择等级升级档次(初级升高级，高级升VIP，初级升VIP)
function tabGrade(grade){
    $("#price .item").click(function(){
        let idx = $(this).attr("data-idx");
        let xh = $(this).attr("data-xh");
        let gradeName = $(this).attr("data-gradename");
        let price = $(this).attr("data-price");
        console.log("grade=="+grade+"==idx=="+idx+"==gradeName=="+gradeName);
        if(xh == grade){
            $(this).addClass("active").siblings().removeClass("active");
            $("#czJe").val(price);
            // $("#czJe").val('0.01');
        }else{
            console.log('aa');
        }
        // if(idx == grade){
        //     messageBox('您已经是'+gradeName);
        // }else{
            // $(this).addClass("active").siblings().removeClass("active");
            // $("#czJe").val(price);
        // }
        // console.log( $("#czJe").val());
    });
}
// 充值
function recharge(){
    $("#czBtn").click(function(){
        var userInfo = JSON.parse(localStorage.getItem("userInfo"));
        let czJe = $("#czJe").val();
        let openid = localStorage.getItem("openId");
        let userName = $("#userName").val();
        if(userInfo == '' || userInfo == null){
            messageBox('请先完成认证');
            return false;
        }
        if(czJe == '' || czJe == null){
            messageBox('请输入金额');
            return false;
        }
        $.ajax({
            url: baseUrl+"/rest/tenPayController/app/tenpay/prepay?openid="+openid+"&total_fees="+czJe+"&body="+userName,
            type: 'GET',
            dataType:'json',
            success:function(e) {
                console.log(JSON.stringify(e));
                var data = e;
                var appId = data.appid;
                var timeStamp = data.timestamp;
                var nonceStr = data.noncestr;
                var packages = data.prepayid;
                var paySign = data.sign;
                pay(appId,timeStamp,nonceStr,packages,paySign);
            }		 
        });
        // postCz();
    });
}

// 红包充值
function hbCz(openid,userName,tel,czJe){
    var czData = {
        cz01: openid,
        cz02: userName, // 姓名
        cz03: tel, // 手机
        cz04: czJe, // 金额
        cz05: "", // 时间
        cz06: "",
        cz07: "奖励",
        cz08: "",
        cz09: "",
    }
    console.log(czData);
    // window.location.href = 'my.html'
    $.ajax({
        type: "POST",
        url: baseUrl + "/rest/lkChongzhiController",
        contentType:"application/json;charset=UTF-8",
        data: JSON.stringify(czData),
        dataType: "json",
        success: function(e){
            if(e.ok){
                messageBox('红包提现到账户余额成功！');
                setTimeout(function(){
                    window.location.href = 'my.html'
                },1000);
            }else{
                messageBox('红包提现到账户余额失败！');
            }
        }
    });
}
// 立即充值
function postCz(){ // 0 初级会员升级高级会员 1 高级会员升级VIP会员 2初级会员升级VIP会员
    let czJe = $("#czJe").val();
    let openid = localStorage.getItem("openId");
    let userName = $("#userName").val();
    let tel = $("#tel").val();
    let idx = $(".price .item.active").attr("data-no");
    let grade = getQueryString('a');
    var czData = {
        cz01: openid,
        cz02: userName, // 姓名
        cz03: tel, // 手机
        cz04: czJe, // 金额
        cz05: "", // 时间
        cz06: "",
        cz07: "充值",
        cz08: "",
        cz09: "会员升级",
    }
    console.log('idx='+idx);
    console.log('czData=='+JSON.stringify(czData));
    console.log('grade='+grade);

    if(grade){ // 升级等级
        console.log('a');
        // abb(idx,czJe);
        $.ajax({
            type: "POST",
            url: baseUrl + "/rest/lkChongzhiController",
            contentType:"application/json;charset=UTF-8",
            data: JSON.stringify(czData),
            dataType: "json",
            success: function(e){
                if(e.ok){
                    abb(idx,czJe);
                }else{
                    messageBox('充值失败，请稍后再试')
                }
            }
        });
    }else{ // 一般充值
        console.log('b');
        $.ajax({
            type: "POST",
            url: baseUrl + "/rest/lkChongzhiController",
            contentType:"application/json;charset=UTF-8",
            data: JSON.stringify(czData),
            dataType: "json",
            success: function(e){
                if(e.ok){
                    messageBox('充值成功！')
                    setTimeout(function(){
                        window.history.go(-1);
                    },1000)
                }else{
                    messageBox('充值失败，请稍后再试')
                }
    
            }
        });
    }
    // 充值
}


function abb(idx,price){
    console.log('会员等级=='+idx);
    console.log('金额='+price)
    // return false;
    // 充值升会员等级
    if(idx == '0'){ // 初级会员升高级会员
        var userInfo = JSON.parse(localStorage.getItem("userInfo"));
        userInfo.type = '高级会员';
        userInfo.createDate = '';
        userInfo.overDate = '';
        userInfo.startDate = '';
        userInfo.updateDate = '';
        var userInfoA = userInfo;
        cdd(userInfoA,price);
    }
    if(idx == '1'){ // 高级会员升VIP会员
        var userInfo = JSON.parse(localStorage.getItem("userInfo"));
        userInfo.type = 'VIP会员';
        userInfo.createDate = '';
        userInfo.overDate = '';
        userInfo.startDate = '';
        userInfo.updateDate = '';
        var userInfoA = userInfo;
        cdd(userInfoA,price);
    }
    if(idx == '2'){ // 初级会员升VIP会员
        var userInfo = JSON.parse(localStorage.getItem("userInfo"));
        userInfo.type = 'VIP会员';
        userInfo.createDate = '';
        userInfo.overDate = '';
        userInfo.startDate = '';
        userInfo.updateDate = '';
        var userInfoA = userInfo;
        cdd(userInfoA,price);
    }
}

function bcc(price){
    console.log('价格(资产)='+price);
    // casha(price);
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkMyController/"+localStorage.getItem("openId"),
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        success: function(data){
            if(data.ok){
                if(data.data){
                    localStorage.setItem("userInfo",JSON.stringify(data.data));
                    sessionStorage.setItem("userInfo",JSON.stringify(data.data));
                    setTimeout(function(){
                        casha(price);
                    },500);
                }
            }
        }
    });
}
function cdd(userInfoA,price){
    console.log('用户信息='+JSON.stringify(userInfoA));
    console.log("价格="+price);
    // bcc(price);
    $.ajax({
        type: "POST",
        // url: baseUrl + "/rest/lkMyController/"+userInfoA.id,
        url: baseUrl + "/rest/lkMyController",
        contentType:"application/json;charset=UTF-8",
        data: JSON.stringify(userInfoA),
        dataType: "json",
        success: function(e){

            if(e.ok){
                // messageBox('升级成功！');
                bcc(price);
            }else{
                messageBox('升级失败，请稍后再试')
            }

        }
    });
}
// 会员升级先充值再提现
function casha(price){
    console.log('升级充钱在提现'+price);
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    let openId = localStorage.getItem("openId");
    let userName = userInfo.name;
    let txJe = price;
    let txMobile = userInfo.tel;
    let mark = '充值';
    let hb = '';
    let tips = '会员等级升级成功';
    let tip = '会员升级';
    postTx(openId,userName,txMobile,txJe,mark,hb,tips,tip);
}

function cash(){
    $("#txBtn").click(function(){
        console.log('提现');
        let openId = localStorage.getItem("openId");
        let userName = $("#userName").val();
        let txJe = $("#txJe").val();
        let txMobile = $("#txMobile").val();
        let mark = '提现';
        let hb = '';
        let tips = '提现成功';
        let tip = '';
        postTx(openId,userName,txMobile,txJe,mark,hb,tips,tip);
    });
}

// 提现
function postTx(openId,userName,txMobile,txJe,mark,hb,tips,tip){
    var txData = {
        tx01: openId,
        tx02: userName, // 姓名
        tx03: txMobile, // 手机
        tx04: txJe, // 金额
        tx05: "", // 时间
        tx06: "", 
        tx07: mark,
        tx08: hb, // 名称
        tx09: tip,
    }
    console.log("提现数据="+JSON.stringify(txData));
    $.ajax({
        type: "POST",
        url: baseUrl + "/rest/lkTixianController",
        contentType:"application/json;charset=UTF-8",
        data: JSON.stringify(txData),
        dataType: "json",
        success: function(e){
            if(e.ok){
                messageBox(tips);
                setTimeout(function(){
                    window.history.go(-1);
                },1000)
            }else{
                messageBox(e.message)
            }

        }
    });
}
// 红包提现
function putHb(openId,userName,mobile,hbJe,hbId,hbName){
    let hbData = {
        hb01: openId, // openId
        hb02: userName, // 姓名
        hb03: mobile, // 手机
        hb04: hbJe, // 金额
        hb05: '是', // 是否消费
        hb06: hbName,
        hb07: '',
        hb08: '',
        id:hbId
    }
    console.log(hbData);
    $.ajax({
        type: "PUT",
        url: baseUrl + "/rest/lkHongbaoController/"+hbId,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data: JSON.stringify(hbData),
        success: function(e){
            if(e.ok){
                // messageBox('新人红包提现成功！');
                hbCz(openId,userName,mobile,hbJe); //红包金额转化为账户余额
            }else{
                // messageBox('领取失败，请稍后再试！');
            }
        }
    });
}

//h5唤起微信支付
function onBridgeReady(appId,timeStamp,nonceStr,packages,paySign){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appId,  //公众号名称，由商户传入     
            "timeStamp":timeStamp,  //时间戳，自1970年以来的秒数     
            "nonceStr":nonceStr,    //随机串     
            "package":"prepay_id="+packages, 
            "signType":"MD5",   //微信签名方式： 
            "paySign":paySign   //微信签名 
        },
        function(res){
            console.log(res.err_code + res.err_desc);
            console.log(res.err_msg);
            // console.log(res.err_code + res.err_desc);
            // console.log(res.err_msg);
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                // console.log("支持成功-谢谢你对永安基金会的支持，备注：测试系统");// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。//
                // window.location.href = "${ctxPath}/jump/toOrderCommCai";
                postCz(); //
            }else if (res.err_msg === 'get_brand_wcpay_request:cancel') {
                console.log("取消支付");
            }
        }
    );  
};
function pay(appId,timeStamp,nonceStr,packages,paySign){  

    if (typeof WeixinJSBridge == "undefined"){  
        if( document.addEventListener ){  
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);  
        }else if (document.attachEvent){  
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);   
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);  
        }  
    }else{  
        onBridgeReady(appId,timeStamp,nonceStr,packages,paySign);  
    }   

};