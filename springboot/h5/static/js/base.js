var baseUrl = "https://www.zhaodui.com.cn/fxjldt";
var shareUrl = "http://www.zhaodui.com.cn/lkqc/code/h5/view/share.html";
var shareUrl1 = "http://www.zhaodui.com.cn/lkqc/code/h5/view/driverApply.html";
var smsUrl = "http://www.zhaodui.com.cn/fxj380";

// 获取url链接的参数
function getQueryString(name){  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
    var r = window.location.search.substr(1).match(reg);  
    if (r != null) return unescape(r[2]); return null;              
};

$(function(){
    var _h = $(window).height();
    $(".main").css('height',(_h-50))
    $(".mainb").css('height',_h)
})


// var appId = 'wxf81777ac930f7ac3';
// var appId = 'wx57cd8295fa059c32'; //利达痛
var appId = 'wxb7f916883185fcbe';
var appsecret = '7e178020d083853a56eed6a349602012';

// var appId = 'wx0e8c1bda556e308e';

//var redirectUri = 'http://www.zhaodui.com.cn/lkqc/code/h5/index.html';
var redirectUri = 'http%3a%2f%2fwww.zhaodui.com.cn%2flkqc%2fcode%2fh5%2findex2.html'
// var redirectUri = 'http://gk2018.sinogk.com/zytb/testcs/index1.html';

// 消息提示框
function messageBox(message){
    $(".message-box").html(message).css("display",'block');
    setTimeout(function(){
        $(".message-box").css("display",'none').html(" ");
    },1000);
}

// 一键登录
function loginFail(type){
    let login = '';
    login += '<div class="login">';
    login += '<div class="login-wrap">';
    login += '<p>获取个人信息失败，请重新登陆</p>';
    login += '<div class="login-btn" id="loginBtn">一键登陆</div>';
    login += '</div></div>';
    $("body").append(login);
    $("#loginBtn").click(function(){
        if(type == '0'){
            window.location.href = './index1.html'; 
        }
        if(type == '1'){
            window.location.href = '../index1.html';
        }
        
    });
}

$(".message").click(function(){
    messageBox('敬请期待...')
});
$(".qrcode").click(function(){
    $(".qrcode-box").css("display",'block');
});
$(".close").click(function(){
    $(".qrcode-box").css("display",'none');
});

$("#share").click(function(){
    let wxInfo = JSON.parse(localStorage.getItem("wxInfo"));
    console.log(wxInfo);
    let name = wxInfo.nickname;
    console.log(name)
    let openId = localStorage.getItem("openId");
    setTimeout(function(){
      window.location.href = 'share.html?openId='+openId+'&name='+name;
    },300);
});

// owbQq1O2R1KudXcM7tJC7J-EVzYk

function picView(picUrl){
    console.log(picUrl);
    let b = '';
    b += '<div class="pic-bg"></div>';
    b += '<div class="pic-wrap">';
    b += '<div class="pic-box">';
    b += '<img src="'+picUrl+'" alt="">';
    b += '</div>';
    b += '</div>';
    $("body").append(b);
    $('.pic-bg').click(function(){
        console.log('aa')
        $(".pic-bg").css("display","none")
        $(".pic-wrap").css("display","none")
    });
}