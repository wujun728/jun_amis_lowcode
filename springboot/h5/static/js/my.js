$(function(){
    var openId = localStorage.getItem("openId");
    if(openId == null || openId == ''){
        messageBox("获取个人信息失败");
        setTimeout(function(){
            loginFail('1'); // 一键登录
        },200);
        // return false;
    }
    let wxInfo = JSON.parse(localStorage.getItem("wxInfo"));
    console.log(wxInfo)
    let headimgurl = "url('"+wxInfo.headimgurl+"')"
    $(".head").css('background-image',headimgurl);
    $("#userName").text(wxInfo.nickname);

    if(localStorage.getItem("userInfo")){
        var userInfo = JSON.parse(localStorage.getItem("userInfo"));
        // if(userInfo.name){
        //     $("#userName").text(userInfo.name);
        // }else{
        //     $("#userName").text(userInfo.tel);
        // }
        $(".renzheng").css("display","none");
        $("#grade").html("当前等级："+userInfo.type);
        if(userInfo.type == '初级会员'){
            $("#grade").attr('data-grade','1');
        }
        if(userInfo.type == '高级会员'){
            $("#grade").attr('data-grade','2');
        }
        if(userInfo.type == 'VIP会员'){
            $("#grade").attr('data-grade','3');
            // $("#grade").css("display","none");
        }
        // 晋升
        $("#grade").click(function(){
            var a = $(this).attr('data-grade');
            if(a != '3'){
                window.location.href = 'chongzhi.html?type=1&a='+a;  // a 1为初级  2为高级 3为VIP
            }else{
                messageBox('您已经是VIP会员！');
            }
            
        });
    }else{
        $("#userName").css("display","none");
        $("#grade").click(function(){
            var a = $(this).attr('data-grade');
            if(!a){
                messageBox('请先完成新人认证'); 
                setTimeout(function(){
                    setTimeout(function(){
                        window.location.href = 'xrrz.html?type=0';
                      },1000);
                },1000);
            }
        });
    }

    


	getMyZichan(openId);
    getTxList(openId);
    isDriver(openId);
});

// 账户余额
function a(){
    if(Number($("#czTotal").val())>0){
        let num = (Number($("#czTotal").val()) - Number($("#txTotal").val())).toFixed(2);
        $("#myAccount").html('账户余额：'+num+' 元');
        localStorage.setItem("yue",num);
    }else{
        localStorage.setItem("yue",'0.00');
    }
	
}
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
                // console.log(data.data)
                if(data.data.length){
                    console.log('1')
                    $("#czTotal").val(data.data[0].zc04);
                    $("#myAccount").html('账户余额：'+data.data[0].zc10+' 元');
                    //a();
                }else{
                    console.log('2')
					localStorage.setItem("yue",'0.00');
				}
            }else{
                messageBox('数据加载失败，请稍后再试')
            }
        }
    });
}
// 提现记录
function getTxList(openId){
    $.ajax({
        type: "GET",
        url: baseUrl + "/rest/lkTixianController/list/"+openId,
        contentType:"application/json;charset=UTF-8",
        dataType: "json",
        data:{
            pageNumber:'1',
            pageSize:'100'
        },
        success: function(e){ 
            if(e.ok){
                if(e.data.length){
					let sum = 0;
                    for(let i=0;i<e.data.length;i++){
						sum = sum + Number(e.data[i].tx04); 
                    }
					$("#txTotal").val(sum);
					a();
                }else{
                    $("#wrap").html(' ').append('<div class="no">目前暂无数据</div>');
                }
            }else{
                messageBox('获取数据失败，请稍后再试')
            }

        }
    });
}
// 邀请好友
function share(openId){
	$("#share").click(function(){
		let content = shareUrl+'?openId'+openId;
		let _w = $("#qrCode").width();
		jQuery('#qrCode').qrcode({
			render: "canvas",
			width: _w,
			height: _w,
			text: content
		});
		$(".share").css('display','block');
	});
	$(".share-close").click(function(){
		$(".share").css('display','none');
	});
}
// 是否成为龙锟司机
function isDriver(openId){
    $.ajax({
		type: "GET",
		url: baseUrl + "/rest/lkDriverRecruitmentController/list/"+openId+"?pageNumber=1&pageSize=100",
		contentType:"application/json;charset=UTF-8",
		success: function(data){
			let e = JSON.parse(data);
			if(e.ok){ 
				if(e.data.length){ // 已申请
					if(e.data[0].lkRemark){
                        $("#sjrz").css("display","block");
                    }
				}
			}else{
				console.log('数据加载失败...')
			}
		}
	});
}

$("#sharea").click(function(){
    let wxInfo = JSON.parse(localStorage.getItem("wxInfo"));
    console.log(wxInfo);
    let name = wxInfo.nickname;
    console.log(name)
    let openId = localStorage.getItem("openId");
    setTimeout(function(){
      window.location.href = 'share.html?openId='+openId+'&name='+name;
    // window.location.href = 'share.html?openId='+openId;
    },300);
});