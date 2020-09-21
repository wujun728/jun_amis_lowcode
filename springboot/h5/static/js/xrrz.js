$(function(){
    let wxInfo = JSON.parse(localStorage.getItem("wxInfo"));
    $("#rm").text(wxInfo.nickname)
    // 同意协议
	$(".agree").click(function(){
		let flag = $(this).hasClass("active");
		if(flag){
			$(this).removeClass("active");
		}else{
			$(this).addClass("active");
		}
	});
    $("#btn").click(function(){
        let openId = localStorage.getItem("openId");
        var sjOpenId = localStorage.getItem("sjOpenId");
        let wxInfo = JSON.parse(localStorage.getItem("wxInfo"));
        let name = wxInfo.nickname;
        let mobile = $("#mobile").val();
        let smsCode = $("#code1").val(); // 短信验证码
        let templateparam = $("#templateparam").val(); //四位数随机码
		let flag = $(".agree").hasClass("active");
        if(mobile == '' || mobile == 'null'){
            messageBox('请输入手机号');
            return false;
        }
        if(smsCode == '' || smsCode == 'null'){
            messageBox('请输入短信验证码');
            return false;
        }
        if(smsCode != templateparam){
            messageBox('短信验证码不正确');
            return false;
        }
        if(!flag){
			messageBox("请同意加入");
			return false;
		}
        let myData = {
            lkSpare1: '', //备用1
            lkSpare2: '', //备用2
            name: name, //姓名
            openid: openId,
            tel: mobile,  //电话
            type: "初级会员", //类型
        }
        console.log(myData);
        $.ajax({
            type: "POST",
            url: baseUrl + "/rest/lkMyController",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(myData),
            success: function(data){
                console.log(data);
                let e = JSON.parse(data);
                if(e.ok){
                    if(sjOpenId == null || sjOpenId == '' || sjOpenId == 'null' || sjOpenId == openId){
                        messageBox("认证成功！");
                        setTimeout(function(){
                            getList(mobile,openId,name,getQueryString('type'));
                        },1000);
                    }else{
                        creatFxhy(openId,name,mobile,sjOpenId,getQueryString('type'));
                    }
                }
            }
        });
    });
});

// 立即领取新人
function getList(mobile,openId,name,type){
    let hbData = {
        hb01: localStorage.getItem("openId"), // 接受手机号
        hb02: name, // 姓名
        hb03: mobile, // 接收人
        hb04: '100', // 金额
        hb05: '未使用', // 是否消费
        hb06: '新人红包',
        hb07: '',// 消费时间
        hb08: '', // 获取时间
        hb09: '在完成新人认证之后，红包将自动发放至您的账户。', // 备注
        hb10: '本红包只限本人用于租车购车消费，不能叠加使用',
        hb11: '',
        hb12: '',
        hb13: '',
        hb14: '',
        hb15: '',
    }
    console.log(hbData);
    $.ajax({
        type: "POST",
        url: baseUrl + "/rest/lkHongbaoController",
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data: JSON.stringify(hbData),
        success: function(e){
        if(e.ok){
            messageBox('新人红包领取成功！');
            setTimeout(function(){
                window.location.href = 'xrhb.html?type='+type;
            },1000);
        }else{
            messageBox('领取失败，请稍后再试！');
        }
        }
    });
}

function creatFxhy(openId,name,tel,sjOpenId,type){
    let fxhyDate = {
        fxhy01: openId,
        fxhy02: name,
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
                // messageBox("认证成功！");
                // setTimeout(function(){
                    // window.location="../index.html";//跳转到首页
                    // window.history.back(-1);
                    getList(tel,openId,name,type)
                // },1000);
            }else{
                messageBox(e.message);
            }
            
        }
    });
}