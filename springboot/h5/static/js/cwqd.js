$(function(){
    var openId = localStorage.getItem("openId");
	$.ajax({
        type: "GET",
		url: baseUrl + "/rest/lkCwqdController/list/"+openId+"?pageNumber=1&pageSize=100",
		contentType:"application/json;charset=UTF-8",
		success: function(data){
			let e = JSON.parse(data);
			if(e.ok){ 
                if(e.data.length){ // 已申请
                    console.log('1')
					$(".box").css("display","none");
					$(".box1").css("display","block");
				}else{ // 未申请
					$(".box").css("display","block");
					$(".box1").css("display","none");
					// creatDriver(); // 司机加入申请
				}
			}else{
				console.log('数据加载失败...')
			}
		}
	});
    // 立即申请 成为渠道
    $("#btn").click(function(){
        let openId = localStorage.getItem("openId");
        let company = $("#company").val();
        let name = $("#name").val();
        let mobile = $("#mobile").val();
        // let idNum = $("#idNum").val();
        let addr = $("#addr").val();
        let smsCode = $("#code1").val();
        if(company == '' || company == null){
            messageBox("请输入公司名称");
            return false;
        }
        if(name == '' || name == null){
            messageBox("请输入姓名");
            return false;
        }
        if(mobile == '' || mobile == null){
            messageBox("请输入手机号码");
            return false;
        }
        if(addr == '' || addr == null){
            messageBox("请输入门店地址");
            return false;
        }
        if(smsCode == '' || smsCode == null){
            messageBox("请输入短信验证码");
            return false;
        }
        let cwqdData = {
            qd01: openId,
            qd02: name,
            qd03: mobile,
            qd05: addr,
            qd09: company
        }
        $.ajax({
            type: "POST",
            url: baseUrl + "/rest/lkCwqdController",
            contentType:"application/json;charset=UTF-8",
            data: JSON.stringify(cwqdData),
            dataType: "json",
            success: function(e){
                if(e.ok){
                    messageBox('申请成功');
                    setTimeout(function(){
                        window.location.href = '../index.html';
                    },1000);
                }else{
                    messageBox('提现失败，请稍后再试')
                }
            }
        });
    });
});